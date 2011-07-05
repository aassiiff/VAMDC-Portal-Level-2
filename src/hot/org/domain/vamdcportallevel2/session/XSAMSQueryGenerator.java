package org.domain.vamdcportallevel2.session;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.log.Log;

@Name("xsamsQuery")
public class XSAMSQueryGenerator {

	@Logger
	private Log log;

	@In
	private RegistryBrowser registryBrowser;

	private QueryString queryString = null; // Query String encoded

	private String xsamsURL = null; // Populated by xsamInterface.page.xml which receives xsam url
	private String queryResultFormat = "XSAMS";

	// Atom Name
	private String atomSymbol = null;
	
	// Atomic Mass
	private String atomMassNumber = null;
	
	// Atomic Charge
	private String atomIonCharge = null;
	
	private String moleculeChemicalName = null;	
	private String moleculeInchiKey = null;
	private String moleculeInchi = null;
	private String moleculeOrdinaryStructuralFormula = null;
	
	private String molecularSpeciesInChIKeyValue = null;
	
	private String molecularSpeciesStoichiometrcFormulaValue = null;
	
	private String atomNuclearChargeFrom = null;
	private String atomNuclearChargeTo = null;

	private String atomIonChargeFrom = null;
	private String atomIonChargeTo = null;

	private String atomStateEnergyFrom = null;
	private String atomStateEnergyTo = null;

	private String radTransWavelengthExperimentalValueFrom = null;
	private String radTransWavelengthExperimentalValueTo = null;
	
	private String molecularStateEnergyValueFrom = null;
	private String molecularStateEnergyValueTo = null;
	
	private String dummyFrom = null;
	private String dummyTo = null;

	private String[] selectedURLFromCheckBox;
	private boolean disableCheckBox = true;
	
	private String submitedQueryStatus = "";

	public String getXsamsURL() {
		return xsamsURL;
	}

	// The key part ... if URL is passed then single query will be submitted
	public void setXsamsURL(String xsamsURL) {
		disableCheckBox = false;
		this.xsamsURL = xsamsURL;
	}

	public String getAtomSymbol() {
		return atomSymbol;
	}

	public void setAtomSymbol(String atomSymbol) {
		this.atomSymbol = atomSymbol;
	}

	public String getAtomNuclearChargeFrom() {
		return atomNuclearChargeFrom;
	}

	public void setAtomNuclearChargeFrom(String atomNuclearChargeFrom) {
		this.atomNuclearChargeFrom = atomNuclearChargeFrom;
	}

	public String getAtomNuclearChargeTo() {
		return atomNuclearChargeTo;
	}

	public void setAtomNuclearChargeTo(String atomNuclearChargeTo) {
		this.atomNuclearChargeTo = atomNuclearChargeTo;
	}

	public String getAtomIonChargeFrom() {
		return atomIonChargeFrom;
	}

	public void setAtomIonChargeFrom(String atomIonChargeFrom) {
		this.atomIonChargeFrom = atomIonChargeFrom;
	}

	public String getAtomIonChargeTo() {
		return atomIonChargeTo;
	}

	public void setAtomIonChargeTo(String atomIonChargeTo) {
		this.atomIonChargeTo = atomIonChargeTo;
	}

	public String getAtomStateEnergyFrom() {
		return atomStateEnergyFrom;
	}

	public void setAtomStateEnergyFrom(String atomStateEnergyFrom) {
		this.atomStateEnergyFrom = atomStateEnergyFrom;
	}

	public String getAtomStateEnergyTo() {
		return atomStateEnergyTo;
	}

	public void setAtomStateEnergyTo(String atomStateEnergyTo) {
		this.atomStateEnergyTo = atomStateEnergyTo;
	}

	public String getRadTransWavelengthExperimentalValueFrom() {
		return radTransWavelengthExperimentalValueFrom;
	}

	public void setRadTransWavelengthExperimentalValueFrom(
			String radTransWavelengthExperimentalValueFrom) {
		this.radTransWavelengthExperimentalValueFrom = radTransWavelengthExperimentalValueFrom;
	}

	public String getRadTransWavelengthExperimentalValueTo() {
		return radTransWavelengthExperimentalValueTo;
	}

	public void setRadTransWavelengthExperimentalValueTo(
			String radTransWavelengthExperimentalValueTo) {
		this.radTransWavelengthExperimentalValueTo = radTransWavelengthExperimentalValueTo;
	}
	
	public String getMolecularStateEnergyValueFrom() {
		return molecularStateEnergyValueFrom;
	}

	public void setMolecularStateEnergyValueFrom(
			String molecularStateEnergyValueFrom) {
		this.molecularStateEnergyValueFrom = molecularStateEnergyValueFrom;
	}

	public String getMolecularStateEnergyValueTo() {
		return molecularStateEnergyValueTo;
	}

	public void setMolecularStateEnergyValueTo(String molecularStateEnergyValueTo) {
		this.molecularStateEnergyValueTo = molecularStateEnergyValueTo;
	}

	public String getMolecularSpeciesInChIKeyValue() {
		return molecularSpeciesInChIKeyValue;
	}

	public void setMolecularSpeciesInChIKeyValue(
			String molecularSpeciesInChIKeyValue) {
		this.molecularSpeciesInChIKeyValue = molecularSpeciesInChIKeyValue;
	}

	public String getMolecularSpeciesStoichiometrcFormulaValue() {
		return molecularSpeciesStoichiometrcFormulaValue;
	}

	public void setMolecularSpeciesStoichiometrcFormulaValue(
			String molecularSpeciesStoichiometrcFormulaValue) {
		this.molecularSpeciesStoichiometrcFormulaValue = molecularSpeciesStoichiometrcFormulaValue;
	}

	public String getDummyFrom() {
		return dummyFrom;
	}

	public void setDummyFrom(String dummyFrom) {
		this.dummyFrom = dummyFrom;
	}

	public String getDummyTo() {
		return dummyTo;
	}

	public void setDummyTo(String dummyTo) {
		this.dummyTo = dummyTo;
	}

	public String getQueryResultFormat() {
		return queryResultFormat;
	}

	public void setQueryResultFormat(String queryResultFormat) {
		this.queryResultFormat = queryResultFormat;
	}

	public String[] getSelectedURLFromCheckBox() {
		return selectedURLFromCheckBox;
	}

	public void setSelectedURLFromCheckBox(String[] selectedURLFromCheckBox) {
		this.selectedURLFromCheckBox = selectedURLFromCheckBox;
	}

	public boolean isDisableCheckBox() {
		return disableCheckBox;
	}

	public void setDisableCheckBox(boolean disableCheckBox) {
		this.disableCheckBox = disableCheckBox;
	}

	public String getSubmitedQueryStatus() {
		return submitedQueryStatus;
	}

	public void setSubmitedQueryStatus(String submitedQueryStatus) {
		this.submitedQueryStatus = submitedQueryStatus;
	}

	public void executeQuery() {
		log.info("XSAMSQuery.executeQuery() action called");
		submitedQueryStatus = "";
		// log.info("XSAMSQuery selectedURLFromCheckBox length: " +
		// selectedURLFromCheckBox.length);

		log.info("XSAMSQuery.xsamsURL: " + xsamsURL);
		log.info("XSAMSQuery.atomSymbol: " + atomSymbol);
		log.info("XSAMSQuery.queryResultFormat: " + queryResultFormat);

		log.info("XSAMSQuery.atomNuclearChargeFrom: " + atomNuclearChargeFrom);
		log.info("XSAMSQuery.atomNuclearChargeTo: " + atomNuclearChargeTo);

		log.info("XSAMSQuery.atomIonChargeFrom: " + atomIonChargeFrom);
		log.info("XSAMSQuery.atomIonChargeTo: " + atomIonChargeTo);

		log.info("XSAMSQuery.atomStateEnergyFrom: " + atomStateEnergyFrom);
		log.info("XSAMSQuery.atomStateEnergyTo: " + atomStateEnergyTo);

		log.info("XSAMSQuery.radTransWavelengthExperimentalValueFrom: "
				+ radTransWavelengthExperimentalValueFrom);
		log.info("XSAMSQuery.radTransWavelengthExperimentalValueTo: "
				+ radTransWavelengthExperimentalValueTo);

		String xsamsQuery = "SELECT ALL WHERE "; // AtomSymbal = '" + atomSymbol +
												// "' ";
		boolean firstEntry = true;
		
		if (atomSymbol != null && atomSymbol.trim().length() > 0) {
			xsamsQuery = xsamsQuery + " AtomSymbol = '" + atomSymbol + "'";
			firstEntry = false;
		}

		if (atomNuclearChargeFrom != null
				&& atomNuclearChargeFrom.trim().length() > 0) {
			if(firstEntry != true){
				xsamsQuery = xsamsQuery + " AND ";
			} else {
				firstEntry = false;
			}
			xsamsQuery = xsamsQuery + " AtomNuclearCharge >= "
					+ atomNuclearChargeFrom;
		}
		if (atomNuclearChargeTo != null
				&& atomNuclearChargeTo.trim().length() > 0) {
			if(firstEntry != true){
				xsamsQuery = xsamsQuery + " AND ";
			} else {
				firstEntry = false;
			}
			xsamsQuery = xsamsQuery + " AtomNuclearCharge <= "
					+ atomNuclearChargeTo;
		}
		if (atomIonChargeFrom != null && atomIonChargeFrom.trim().length() > 0) {
			if(firstEntry != true){
				xsamsQuery = xsamsQuery + " AND ";
			} else {
				firstEntry = false;
			}
			xsamsQuery = xsamsQuery + " AtomIonCharge >= "
					+ atomIonChargeFrom ;
		}
		if (atomIonChargeTo != null && atomIonChargeTo.trim().length() > 0) {
			if(firstEntry != true){
				xsamsQuery = xsamsQuery + " AND ";
			} else {
				firstEntry = false;
			}
			xsamsQuery = xsamsQuery + " AtomIonCharge <= "
					+ atomIonChargeTo;
		}
		if (atomStateEnergyFrom != null
				&& atomStateEnergyFrom.trim().length() > 0) {
			if(firstEntry != true){
				xsamsQuery = xsamsQuery + " AND ";
			} else {
				firstEntry = false;
			}
			xsamsQuery = xsamsQuery + " AtomStateEnergy >= "
					+ atomStateEnergyFrom;
		}
		if (atomStateEnergyTo != null && atomStateEnergyTo.trim().length() > 0) {
			if(firstEntry != true){
				xsamsQuery = xsamsQuery + " AND ";
			} else {
				firstEntry = false;
			}
			xsamsQuery = xsamsQuery + " AtomStateEnergy <= "
					+ atomStateEnergyTo ;
		}
		if (radTransWavelengthExperimentalValueFrom != null
				&& radTransWavelengthExperimentalValueFrom.trim().length() > 0) {
			if(firstEntry != true){
				xsamsQuery = xsamsQuery + " AND ";
			} else {
				firstEntry = false;
			}
			//RadTransWavelengthExperimentalValue ==> RadTransWavelength
			xsamsQuery = xsamsQuery
					+ " RadTransWavelength >= "
					+ radTransWavelengthExperimentalValueFrom ;
		}
		if (radTransWavelengthExperimentalValueTo != null
				&& radTransWavelengthExperimentalValueTo.trim().length() > 0) {
			if(firstEntry != true){
				xsamsQuery = xsamsQuery + " AND ";
			} else {
				firstEntry = false;
			}
			//RadTransWavelengthExperimentalValue ==> RadTransWavelength
			xsamsQuery = xsamsQuery
					+ " RadTransWavelength <= "
					+ radTransWavelengthExperimentalValueTo;
		}
		
		if (molecularStateEnergyValueFrom != null
				&& molecularStateEnergyValueFrom.trim().length() > 0) {
			if(firstEntry != true){
				xsamsQuery = xsamsQuery + " AND ";
			} else {
				firstEntry = false;
			}
			// MolecularStateEnergyValue ==> MoleculeStateEnergy
			xsamsQuery = xsamsQuery + " MoleculeStateEnergy >= "
					+ molecularStateEnergyValueFrom;
		}
		if (molecularStateEnergyValueTo != null
				&& molecularStateEnergyValueTo.trim().length() > 0) {
			if(firstEntry != true){
				xsamsQuery = xsamsQuery + " AND ";
			} else {
				firstEntry = false;
			}
			xsamsQuery = xsamsQuery + " MoleculeStateEnergy <= "
					+ molecularStateEnergyValueTo;
		}
		if (molecularSpeciesInChIKeyValue != null
				&& molecularSpeciesInChIKeyValue.trim().length() > 0) {
			if(firstEntry != true){
				xsamsQuery = xsamsQuery + " AND ";
			} else {
				firstEntry = false;
			}
			xsamsQuery = xsamsQuery + " MolecularSpeciesInChIKey = '"
					+ molecularSpeciesInChIKeyValue + "'";
		}
		// MolecularSpeciesStoichiometrcFormula ==> MoleculeStoichiometricFormula
		if (molecularSpeciesStoichiometrcFormulaValue != null
				&& molecularSpeciesStoichiometrcFormulaValue.trim().length() > 0) {
			if(firstEntry != true){
				xsamsQuery = xsamsQuery + " AND ";
			} else {
				firstEntry = false;
			}
			xsamsQuery = xsamsQuery + " MoleculeStoichiometricFormula = '"
			+ molecularSpeciesStoichiometrcFormulaValue + "'";
		}

		this.queryString = new QueryString("REQUEST", "doQuery");
		this.queryString.add("LANG", "VSS1");
		this.queryString.add("FORMAT", queryResultFormat);
		this.queryString.add("QUERY", xsamsQuery);

		log.info("XSAMSQuery: " + queryString.toString());

		// IndexInList is index of submitted queries
		int indexInList = -99;
		XSAMSQueryAdaptor xsamsAdaptorTemp = null;
		Thread tempXSAMSThread = null;
		
		// xsamsURL is not null then it is single query
		// else parse selectedURLFromCheckBox 
		// which is populated based on the clicked checkboxes
		
		// SubmittedTAPQueryList is list of both XSAMs and TAP queries
		// name is misleading
		if (xsamsURL != null) {
			indexInList = registryBrowser.getSubmittedTAPQueryList().size();

			// xsamsQuery is passed for display purposes
			xsamsAdaptorTemp = new XSAMSQueryAdaptor(indexInList, xsamsURL
					+ "sync/?", queryString, xsamsQuery);

			xsamsAdaptorTemp.setQueryType(queryResultFormat);
			registryBrowser.getSubmittedTAPQueryList().add(0,xsamsAdaptorTemp);
			//registryBrowser.getSubmittedTAPQueryList().
			tempXSAMSThread = new Thread(xsamsAdaptorTemp);
			tempXSAMSThread.start();
		} else if (selectedURLFromCheckBox != null) {
			if (selectedURLFromCheckBox.length > 0) {
				for (int i = 0; i < selectedURLFromCheckBox.length; i++) {

					log.info("selectedURLFromCheckBox[i]: "
							+ selectedURLFromCheckBox[i]);
					indexInList = registryBrowser.getSubmittedTAPQueryList()
							.size();
					xsamsAdaptorTemp = new XSAMSQueryAdaptor(indexInList,
							selectedURLFromCheckBox[i] + "sync/?", queryString,
							xsamsQuery);
					xsamsAdaptorTemp.setQueryType(queryResultFormat);
					registryBrowser.getSubmittedTAPQueryList().add(0,
							xsamsAdaptorTemp);

					tempXSAMSThread = new Thread(xsamsAdaptorTemp);
					tempXSAMSThread.start();

				}
			}
		}

		submitedQueryStatus = "Query: " + xsamsQuery + " submitted. \n";
		submitedQueryStatus = submitedQueryStatus + "Please check Query Log.";
		
		setDefaultValuesForForm();
		//disableCheckBox = false;
	}
	
	private void setDefaultValuesForForm(){
		atomSymbol = null;

		atomNuclearChargeFrom = null;
		atomNuclearChargeTo = null;

		atomIonChargeFrom = null;
		atomIonChargeTo = null;

		atomStateEnergyFrom = null;
		atomStateEnergyTo = null;

		radTransWavelengthExperimentalValueFrom = null;
		radTransWavelengthExperimentalValueTo = null;

		dummyFrom = null;
		dummyTo = null;
	}
}
