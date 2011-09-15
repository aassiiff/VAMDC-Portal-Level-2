package org.vamdc.portal.session.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.domain.vamdcportallevel2.entity.ExtendedRegistry;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.log.Log;
import org.vamdc.portal.session.controller.ProcessCodeManager;
import org.vamdc.portal.session.controller.ProcessCodeManagerThread;
import org.vamdc.portal.session.controller.QueryLog;
import org.vamdc.portal.session.controller.RegistryBrowser;
import org.vamdc.portal.session.controller.RegistryBrowserQueryThread;
import org.vamdc.portal.session.controller.SelectedRestrictables;
import org.vamdc.portal.session.controller.WavelengthWaveNumber;
import org.vamdc.portal.session.controller.WavelengthWavelength;
import org.vamdc.portal.session.forms.Collisions;
import org.vamdc.portal.session.forms.FreeForm;
import org.vamdc.portal.session.forms.SpeciesAtoms;
import org.vamdc.portal.session.forms.SpeciesMolecules;
import org.vamdc.portal.session.forms.Transitions;

@Scope(ScopeType.SESSION)
@Name("xsamsQueryNew")
public class XSAMSQueryGeneratorNew {

	@Logger
	private Log log;

	@In(create = true)
	private RegistryBrowser registryBrowser;
	
	@In(create = true)
	private ProcessCodeManager processCodeManager;
	
	@In(create = true)
	private QueryLog queryLog;

	private ArrayList<ExtendedRegistry> extendedRegistryList = null;
	private List<XSAMSQueryHeadResponse> headResponseList = null;
	private RegistryBrowserQueryThread registryBrowserQueryThread;
	private int threadsFinished = 0;

	private boolean pollEnabled = true;
	private Date startTime = null;

	private List<Future<XSAMSQueryHeadResponse>> futures;

	private ArrayList<AbstractQuery> submittedXSAMSQueryListTemp;

	@In(create = true)
	SpeciesAtoms speciesAtoms;

	@In(create = true)
	SpeciesMolecules speciesMolecules;

	@In(create = true)
	Transitions transitions;

	@In(create = true)
	Collisions collisions;

	@In(create = true)
	WavelengthWavelength wavelengthWavelength;

	@In(create = true)
	WavelengthWaveNumber wavelengthWaveNumber;

	@In(create = true)
	FreeForm freeForm;
	
	@In(create = true)
	SelectedRestrictables selectedRestrictables;

	private QueryString query = null; // Query String encoded
	private String queryString;

	//private String xsamsURL = null; // Populated by xsamInterface.page.xml which
									// receives xsams url
	private String queryResultFormat = "XSAMS";

	private List<String> selectedURLFromCheckBox;// = new ArrayList<String>();
	private List<String> selectedURLFromCheckBoxStage2;// = new
														// ArrayList<String>();
	// Default Value
	//private String speciesForm = "---";
	//private String wavelenthForm = "---";
	
	private boolean atomsForm = false;
	private boolean moleculesForm = false;
	private boolean transitionsForm = false;
	private boolean collisionsForm = false;

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}
	
	public boolean isAtomsForm() {
		return atomsForm;
	}

	public void setAtomsForm(boolean atomForm) {
		this.atomsForm = atomForm;
		transitions.setAtomForm(atomForm);
	}

	public boolean isMoleculesForm() {
		return moleculesForm;
	}

	public void setMoleculesForm(boolean moleculesForm) {
		this.moleculesForm = moleculesForm;
		transitions.setMoleculeForm(moleculesForm);
	}

	public boolean isTransitionsForm() {
		return transitionsForm;
	}

	public void setTransitionsForm(boolean transtionsForm) {
		this.transitionsForm = transtionsForm;
	}

	public boolean isCollisionsForm() {
		return collisionsForm;
	}

	public void setCollisionsForm(boolean collisionsForm) {
		this.collisionsForm = collisionsForm;
	}

	public boolean isPollEnabled() {
		return pollEnabled;
	}

	public void setPollEnabled(boolean pollEnabled) {
		this.pollEnabled = pollEnabled;
	}

	public List<String> getSelectedURLFromCheckBox() {
		return selectedURLFromCheckBox;
	}

	public ArrayList<AbstractQuery> getSubmittedXSAMSQueryListTemp() {
		return submittedXSAMSQueryListTemp;
	}

	public void setSubmittedXSAMSQueryListTemp(
			ArrayList<AbstractQuery> submittedXSAMSQueryListTemp) {
		this.submittedXSAMSQueryListTemp = submittedXSAMSQueryListTemp;
	}

	public void setSelectedURLFromCheckBox(
			List<String> selectedURLFromCheckBoxValue) {
		for (int i = 0; i < selectedURLFromCheckBoxValue.size(); i++) {
			System.out.println("setSelectedURLFromCheckBox: "
					+ selectedURLFromCheckBoxValue.get(i) + ": "
					+ selectedURLFromCheckBoxValue.size());
			// Due to table each selected check box is sent separately
			// The last check box overwrite previous values
			// Created another check box to overcome this issue.
			this.selectedURLFromCheckBoxStage2.add(selectedURLFromCheckBoxValue
					.get(i));
		}
		this.selectedURLFromCheckBox = selectedURLFromCheckBoxValue;
	}

	private boolean registryBrowserQuery = true;
	private boolean processCodeManagerQuery = true;

	public void populateExtendedRegistryList() {

		if (registryBrowserQuery == true) {
			registryBrowserQueryThread = new RegistryBrowserQueryThread(
					registryBrowser);

			Thread thread = new Thread(registryBrowserQueryThread);
			thread.start();
			registryBrowserQuery = false;
		}
		if(processCodeManagerQuery == true){
			processCodeManager.setProcessSpreadSheet(registryBrowser.getProcessSpreadSheet());
			ProcessCodeManagerThread processCodeManagerThread= new ProcessCodeManagerThread(processCodeManager);
			
			Thread thread = new Thread(processCodeManagerThread);
			thread.start();
			processCodeManagerQuery = false;
		}
	}

	public void executeQueryStage2() {
		// IndexInList is index of submitted queries
		int indexInList = -99;
		XSAMSQueryAdaptor xsamsAdaptorTemp = null;
		Thread tempXSAMSThread = null;
		ExtendedRegistry tempExtendedRegistry = null;
		submittedXSAMSQueryListTemp = new ArrayList<AbstractQuery>();
		
		SubmittedQuery submittedQuery = new SubmittedQuery();
		submittedQuery.setQueryString(queryString);
		for (int i = 0; i < this.selectedURLFromCheckBoxStage2.size(); i++) {
			System.out.println("executeQueryStage2: "
					+ this.selectedURLFromCheckBoxStage2.get(i) + "  : "
					+ selectedURLFromCheckBoxStage2.size());

			tempExtendedRegistry = registryBrowser
					.getExtendedResource(selectedURLFromCheckBoxStage2.get(i));

			xsamsAdaptorTemp = new XSAMSQueryAdaptor(indexInList,
					selectedURLFromCheckBoxStage2.get(i) + "sync?", query,
					queryString);

			xsamsAdaptorTemp.setExtendedRegistry(tempExtendedRegistry);

			xsamsAdaptorTemp.setQueryType(queryResultFormat);
			
			submittedQuery.getSubmittedQueryList().add(0, xsamsAdaptorTemp);
			submittedXSAMSQueryListTemp.add(0, xsamsAdaptorTemp);

			tempXSAMSThread = new Thread(xsamsAdaptorTemp);
			tempXSAMSThread.start();

		}
		queryLog.getSubmittedQueryList().add(0,submittedQuery);
		
		selectedURLFromCheckBox = selectedURLFromCheckBoxStage2;
		clearForm();
	}

	public boolean executeQueryStage1() {
		log.info("XSAMSQueryNew.executeQuery() action called:  "
				+ this.atomsForm + " " + this.moleculesForm + " " + this.transitionsForm + " " + this.collisionsForm);

		queryString = "SELECT ALL WHERE ";
		if (atomsForm == true) {
			queryString = queryString + speciesAtoms.getQueryString() + " ";
		} 
		
		if (moleculesForm == true) {
			if (atomsForm == true && (speciesAtoms.getQueryString().trim().length() > 0)) {
				queryString = queryString + " OR ";
			}
			queryString = queryString + speciesMolecules.getQueryString();
		}

		if (transitionsForm == true) {
			if (atomsForm == true && (speciesAtoms.getQueryString().trim().length() > 0)
					|| moleculesForm == true && (speciesMolecules.getQueryString().trim().length() > 0)){
				queryString = queryString + " AND ";
			}
			queryString = queryString + transitions.getQueryString();
		}

		if (collisionsForm == true) {
			if (atomsForm == true && (speciesAtoms.getQueryString().trim().length() > 0)
					|| moleculesForm == true && (speciesMolecules.getQueryString().trim().length() > 0)
					|| transitionsForm == true && (transitions.getQueryString().trim().length() > 0)) {
				queryString = queryString + " AND ";
			}
			queryString = queryString + collisions.getQueryString();
		}
		
		//System.out.println("Query String :" + queryString + " " + queryString.trim().contains("==") + " " + queryString.trim().contains(">"));
		if(queryString.trim().contains("=") || queryString.trim().contains(">=") || 
				queryString.trim().contains("<=") || queryString.trim().contains("IN")){
			submitHeadRequest();
			return true;
		} else {
			return false;
		}
	}

	public void executeQueryStage1FreeForm() {
		queryString = "";
		queryString = freeForm.getQueryString();
		submitHeadRequest();
	}

	private boolean submitHeadRequest() {

		this.query = new QueryString("REQUEST", "doQuery");
		this.query.add("LANG", "VSS1");
		this.query.add("FORMAT", queryResultFormat);
		this.query.add("QUERY", queryString);

		log.info("XSAMSQuery: " + query.toString());

		System.out.println(queryString);

		if (extendedRegistryList == null) {
			extendedRegistryList = registryBrowserQueryThread
					.getExtendedRegistryList();
		}

		// Don't use the size to initialize the Array List
		// extendedRegistryList.size()
		futures = new ArrayList<Future<XSAMSQueryHeadResponse>>();

		for (int i = 0; i < extendedRegistryList.size(); i++) {
			System.out.println(extendedRegistryList.get(i).getResource()
					.getTitle()
					+ "  " + extendedRegistryList.get(i).getXsamURL());

			final ExecutorService service = Executors.newFixedThreadPool(15);
			/*
			 * if (!extendedRegistryList.get(i).getResource().getTitle()
			 * .contains("BASECOL")) {
			 *  }
			 */
			System.out.println(extendedRegistryList.get(i).getResource()
					.getTitle()
					+ "  " + extendedRegistryList.get(i).getXsamURL());
			
			if(selectedRestrictables.containsWholeRestrictableList(extendedRegistryList.get(i).getRestrictableList())){
				futures.add(service.submit(new XSAMSQueryHeadRequestThread(
						extendedRegistryList.get(i).getResource().getTitle(),
						extendedRegistryList.get(i).getXsamURL(), query.toString())));
				startTime = new Date();
			} 
			/*
			if (futures.size() == 0){
				return false;
			}*/
			
		}

		pollEnabled = true;
		headResponseList = new ArrayList<XSAMSQueryHeadResponse>(
				extendedRegistryList.size());
		selectedURLFromCheckBox = new ArrayList<String>();
		selectedURLFromCheckBoxStage2 = new ArrayList<String>();

		System.out.println("headResponseList.isEmpty(): "
				+ headResponseList.isEmpty());
		// Thread.sleep(1000)
		new Thread() {
			public void run() {
				if (futures != null) {
					int futuresLength = futures.size();
					int counter = 1;
					threadsFinished = 0;
					for (Future<XSAMSQueryHeadResponse> future : futures) {
						System.out.println(future.isDone() + " "
								+ (new Date().getTime() - startTime.getTime()));
						if (!future.isDone()
								&& (new Date().getTime() - startTime.getTime()) > 20000) {
							System.out.println("in If condition " + future.isDone() + " "
									+ (new Date().getTime() - startTime.getTime()));
							counter++;
							future.cancel(true);
						} else {
							System.out.println("Else Before sleep: " + future.isDone() + " "
									+ (new Date().getTime() - startTime.getTime()));
							if (counter++ == futuresLength) {
								new Thread(new HeadResponseParserThread(future,
										true)).start();
							} else {
								new Thread(new HeadResponseParserThread(future,
										false)).start();
							}
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							System.out.println("Else After sleep: " + future.isDone() + " "
									+ (new Date().getTime() - startTime.getTime()));
						}
					}
					// try { Thread.sleep(1000); } catch (Exception e){}
					// pollEnabled = false;
					/*
					System.out.println("headResponseList.isEmpty(): "
							+ headResponseList.isEmpty());*/
				}
			}
		}.start();
		return true;
	}

	public List<XSAMSQueryHeadResponse> getHeadResponseList() {
		// System.out.println("Query Polling");
		return headResponseList;
	}

	/* */
	class HeadResponseParserThread implements Runnable {
		Future<XSAMSQueryHeadResponse> future;
		boolean lastThread = false;

		HeadResponseParserThread(Future<XSAMSQueryHeadResponse> futureValue,
				boolean lastThreadValue) {
			this.future = futureValue;
			this.lastThread = lastThreadValue;
		}

		public void run() {
			int timeElapsed = (int)(new Date().getTime() - startTime.getTime());
			System.out.println("Time Elapsed Before: " + (new Date().getTime() - startTime.getTime()));
			int waitingTime;
			if(timeElapsed > 20000){
				waitingTime = 1000;
			} else {
				waitingTime = (20000 - timeElapsed) + 5000;
			}
			try {
				if (future != null) {
				}
				XSAMSQueryHeadResponse tempXSAMSQueryHeadResponse = future.get(waitingTime, TimeUnit.MILLISECONDS);
				//future.get();
				
				synchronized (this) {
					headResponseList.add(tempXSAMSQueryHeadResponse);
				}
				// try { Thread.sleep(500); } catch (Exception e){}
				System.out.println("Respnse Received for: " + queryString);
				if (tempXSAMSQueryHeadResponse.getStatusCode().equals("200")) {
					selectedURLFromCheckBox.add(tempXSAMSQueryHeadResponse
							.getURL());
				}
				threadsFinished = threadsFinished + 1;
				if (threadsFinished == futures.size()) {
					pollEnabled = false;
				}
				System.out.println("Time Elapsed After: " + (new Date().getTime() - startTime.getTime()));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (Exception e) {
				System.out.println("Time Elapsed in Catch: " + (new Date().getTime() - startTime.getTime()));
				threadsFinished = threadsFinished + 1;
				if (threadsFinished == futures.size()) {
					pollEnabled = false;
				}
			}
		}
	}

	public void clearForm() {
		speciesAtoms.clearFields();
		speciesMolecules.clearFields();
		transitions.clearFields();
		collisions.clearFields();
		//wavelengthWavelength.clearFields();
		//wavelengthWaveNumber.clearFields();
		freeForm.setQueryString("SELECT ALL WHERE ");
	}
	
	public void toggleEditable(){
		speciesAtoms.toggleEditable();
		speciesMolecules.toggleEditable();
		
		/*
		 * It is Hack due to check box in the Data Table
		 */
		speciesMolecules.emptySelectedIsotopesFromCheckBox2();
		transitions.toggleEditable();
		collisions.toggleEditable();
	}
	
	public void enableEditable(){
		speciesAtoms.setEditable(true);
		speciesMolecules.setEditable(true);
		transitions.setEditable(true);
		collisions.setEditable(true);
	}
	
	public void disableEditable(){
		speciesAtoms.setEditable(false);
		speciesMolecules.setEditable(false);
		transitions.setEditable(false);
		collisions.setEditable(false);
	}

}
