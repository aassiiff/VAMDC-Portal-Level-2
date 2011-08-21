package org.domain.vamdcportallevel2.session;

import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.log.Log;

@Name("navigator")
@Scope(ScopeType.SESSION)
public class Navigator {

	@Logger
	private Log log;
	
	@In (create=true)
	private XSAMSQueryGeneratorNew xsamsQueryNew;
	
	@In(create = true)
	SelectedRestrictables selectedRestrictables;
	
	private String mainOptionPanel = "/xsamsForm/XSAMSMainOption.xhtml";

	private String subOptionPanel = "/xsamsForm/empty.xhtml";
	
	// Number of Form Panels are equal to Number of Main Options
	private String formPanel1 = "/xsamsForm/emptyWithWidth.xhtml";
	private String formPanel1A = "/xsamsForm/emptyWithWidth.xhtml";
	private String formPanel2 = "/xsamsForm/emptyWithWidth.xhtml";
	private String formPanel3 = "/xsamsForm/emptyWithWidth.xhtml";
	private String formPanel4 = "/xsamsForm/emptyWithWidth.xhtml";
	private String rightPanel = "/xsamsForm/restrictableTable.xhtml";
	private String formButtonsPanel = "/xsamsForm/empty.xhtml";
	
	private boolean atomsForm = false;
	private boolean moleculesForm = false;
	private boolean transtionsForm = false;
	private boolean collisionsForm = false;
	
	private boolean stage1Display = false;
	private boolean stage2Display = false;
	
	private List<String> selectedNodes;

	public void mainOptionFirst() {
		log.info("mainOptionFirst()");
		subOptionPanel = "/xsamsForm/mainOptionFirst.xhtml";
	}

	public void mainOptionSecond() {
		log.info("mainOptionSecond()");
		subOptionPanel = "/xsamsForm/mainOptionSecond.xhtml";
	}

	public void mainOptionThird() {
		log.info("mainOptionThird()");
		subOptionPanel = "/xsamsForm/mainOptionThird.xhtml";
	}
	
	public void subOptionSpeciesAtoms(){
		log.info("subOptionSpeciesAtoms()");
		xsamsQueryNew.setAtomsForm(true);
		//subOption13Disabled = false;
		//subOption32Disabled = true;
		//log.info("subOption21() " + subOption32Disabled + "  "  + subOption13Disabled);
		formPanel1 = "/xsamsForm/speciesAtoms.xhtml";
		formPanel4 = "/xsamsForm/emptyWithWidth.xhtml";
		formButtonsPanel = "/xsamsForm/xsamsFormButtons.xhtml";
		this.atomsForm = true;
	}
	
	public void subOptionSpeciesMolecules(){
		log.info("subOptionSpeciesMolecules()");
		xsamsQueryNew.setMoleculesForm(true);
		formPanel1A = "/xsamsForm/speciesMolecules.xhtml";
		formPanel4 = "/xsamsForm/emptyWithWidth.xhtml";
		formButtonsPanel = "/xsamsForm/xsamsFormButtons.xhtml";
		this.moleculesForm = true;
	}
	
	public void subOptionTransitions(){
		log.info("subOptionTransitions");		
		xsamsQueryNew.setTransitionsForm(true);
		//formPanel1 = "/xsamsForm/empty.xhtml";
		formPanel2 = "/xsamsForm/transitions.xhtml";	
		formPanel4 = "/xsamsForm/emptyWithWidth.xhtml";	
		formButtonsPanel = "/xsamsForm/xsamsFormButtons.xhtml";
		this.transtionsForm = true;
	}
	
	public void subOptionCollisions(){
		log.info("subOptionCollisions");		
		xsamsQueryNew.setCollisionsForm(true);
		//formPanel1 = "/xsamsForm/empty.xhtml";
		formPanel4 = "/xsamsForm/emptyWithWidth.xhtml";	
		formPanel3 = "/xsamsForm/collisions.xhtml";	
		formButtonsPanel = "/xsamsForm/xsamsFormButtons.xhtml";
		this.collisionsForm = true;
	}
	
	public void subOptionWavelengthWavelength(){
		log.info("subOptionWavelengthWavelength()");
		//xsamsQueryNew.setWavelenthForm("WAVELENGTH");
		formPanel2 = "/xsamsForm/wavelengthWavelength.xhtml";
		//formPanel3 = "/xsamsForm/empty.xhtml";
		formButtonsPanel = "/xsamsForm/xsamsFormButtons.xhtml";
	}
	
	public void subOptionWavelengthFrequency(){
		log.info("subOptionWavelengthFrequncy()");
		//xsamsQueryNew.setWavelenthForm("FREQUENCY");
		formPanel2 = "/xsamsForm/wavelengthFrequency.xhtml";
		formPanel3 = "/xsamsForm/empty.xhtml";
		formButtonsPanel = "/xsamsForm/xsamsFormButtons.xhtml";
	}
	
	public void subOptionWavelengthEnergy(){
		log.info("subOptionWavelengthEnergy()");
		//xsamsQueryNew.setWavelenthForm("ENERGY");
		formPanel2 = "/xsamsForm/wavelengthEnergy.xhtml";
		formPanel3 = "/xsamsForm/empty.xhtml";
		formButtonsPanel = "/xsamsForm/xsamsFormButtons.xhtml";
	}
	
	public void subOptionWavelengthWavenumber(){
		log.info("subOptionWavenumber()");
		//xsamsQueryNew.setWavelenthForm("WAVENUMBER");
		formPanel2 = "/xsamsForm/wavelengthWavenumber.xhtml";
		formPanel3 = "/xsamsForm/empty.xhtml";
		formButtonsPanel = "/xsamsForm/xsamsFormButtons.xhtml";
	}
	
	public void subOptionFreeForm(){
		log.info("Free Form");	
		
		xsamsQueryNew.setAtomsForm(false);
		xsamsQueryNew.setMoleculesForm(false);
		xsamsQueryNew.setTransitionsForm(false);
		xsamsQueryNew.setCollisionsForm(false);
		
		formPanel1 = "/xsamsForm/empty.xhtml";
		formPanel1A = "/xsamsForm/empty.xhtml";
		formPanel2 = "/xsamsForm/empty.xhtml";
		formPanel3 = "/xsamsForm/empty.xhtml";
		formPanel4 = "/xsamsForm/freeForm.xhtml";	
		formButtonsPanel = "/xsamsForm/empty.xhtml";
		this.clearForm();
	}
	
	public void nodeChanged(){
		log.info("nodeChanged");
		//subOption32Disabled = true;
		//subOption11Disabled = true;
		//subOption23Disabled = true;
	}
	public String getMainOptionPanel() {
		return mainOptionPanel;
	}

	public void setMainOptionPanel(String mainOptionPanel) {
		this.mainOptionPanel = mainOptionPanel;
	}

	public String getSubOptionPanel() {
		return subOptionPanel;
	}

	public void setSubOptionPanel(String subOptionPanel) {
		this.subOptionPanel = subOptionPanel;
	}

	public String getFormPanel1() {
		return formPanel1;
	}

	public void setFormPanel1(String formPanel) {
		this.formPanel1 = formPanel;
	}
	
	public String getFormPanel1A() {
		return formPanel1A;
	}

	public void setFormPanel1A(String formPanel1A) {
		this.formPanel1A = formPanel1A;
	}

	public String getFormPanel2() {
		return formPanel2;
	}

	public void setFormPanel2(String formPanel2) {
		this.formPanel2 = formPanel2;
	}

	public String getFormPanel3() {
		return formPanel3;
	}

	public void setFormPanel3(String formPanel3) {
		this.formPanel3 = formPanel3;
	}
	
	public String getFormPanel4() {
		return formPanel4;
	}
	
	public String getRightPanel() {
		//log.info("Right Panel Called");
		return rightPanel;
	}

	public void setRightPanel(String rightPanel) {
		this.rightPanel = rightPanel;
	}

	public void setFormPanel4(String formPanel4) {
		this.formPanel4 = formPanel4;
	}

	public String getFormButtonsPanel() {
		return formButtonsPanel;
	}

	public void setFormButtonsPanel(String formButtonsPanel) {
		this.formButtonsPanel = formButtonsPanel;
	}

	public List<String> getSelectedNodes() {
		return selectedNodes;
	}

	public void setSelectedNodes(List<String> selectedNodes) {
		this.selectedNodes = selectedNodes;
	}
	
	public boolean isStage1Display() {
		return stage1Display;
	}

	public void setStage1Display(boolean stage1Display) {
		this.stage1Display = stage1Display;
	}

	public boolean isStage2Display() {
		return stage2Display;
	}

	public void setStage2Display(boolean stage2Display) {
		this.stage2Display = stage2Display;
	}

	public void executeQueryStage1(){
		xsamsQueryNew.executeQueryStage1();
		xsamsQueryNew.toggleEditable();
		this.stage1Display = true;
		mainOptionPanel = "/xsamsForm/XSAMSMainOption.xhtml";
		//formPanel1 = "/xsamsForm/xsamsQueryResponseStage1.xhtml";
		//formPanel1A = "/xsamsForm/empty.xhtml";
		//formPanel2 = "/xsamsForm/empty.xhtml";
		//formPanel3 = "/xsamsForm/empty.xhtml";
		formButtonsPanel = "/xsamsForm/refineButton.xhtml";	
		rightPanel = "/xsamsForm/xsamsQueryResponseStage1.xhtml";
	}
	
	public void executeQueryStage1FreeForm(){
		this.stage1Display = true;
		mainOptionPanel = "/xsamsForm/XSAMSMainOption.xhtml";
		//mainOptionPanel = "/xsamsForm/empty.xhtml";
		//formPanel1 = "/xsamsForm/xsamsQueryResponseStage1.xhtml";
		//formPanel1A = "/xsamsForm/empty.xhtml";
		//formPanel2 = "/xsamsForm/empty.xhtml";
		//formPanel3 = "/xsamsForm/empty.xhtml";
		formButtonsPanel = "/xsamsForm/emptyWithWidth.xhtml";
		rightPanel = "/xsamsForm/xsamsQueryResponseStage1.xhtml";
	}
	
	public void executeQueryStage2(){
		xsamsQueryNew.executeQueryStage2();
		mainOptionPanel = "/xsamsForm/empty.xhtml";
		formPanel1 = "/xsamsForm/submittedXSAMSQueries.xhtml";
		formPanel1A = "/xsamsForm/emptyWithWidth.xhtml";
		formPanel2 = "/xsamsForm/emptyWithWidth.xhtml";
		formPanel3 = "/xsamsForm/emptyWithWidth.xhtml";
		formPanel4 = "/xsamsForm/emptyWithWidth.xhtml";
		formButtonsPanel = "/xsamsForm/emptyWithWidth.xhtml";
		this.rightPanel = "/xsamsForm/emptyWithWidthHeight.xhtml";
		try {
		Thread.sleep(100);
			new Thread() { 
				public void run(){
					resetQueryBuilder();
				}
			};
		}catch (Exception e){
			
		}
		// For Testing Purposes
		//resetQueryBuilder();
	}
	
	public void cancelQueryBuilder(){
		log.info("cancelQueryBuilder called");
		xsamsQueryNew.enableEditable();
		this.stage1Display = false;
		xsamsQueryNew.setAtomsForm(false);
		xsamsQueryNew.setMoleculesForm(false);
		xsamsQueryNew.setTransitionsForm(false);
		xsamsQueryNew.setCollisionsForm(false);
		mainOptionPanel = "/xsamsForm/XSAMSMainOption.xhtml";		
		formPanel1 = "/xsamsForm/emptyWithWidth.xhtml";
		formPanel1A = "/xsamsForm/emptyWithWidth.xhtml";
		formPanel2 = "/xsamsForm/emptyWithWidth.xhtml";
		formPanel3 = "/xsamsForm/emptyWithWidth.xhtml";
		formPanel4 = "/xsamsForm/emptyWithWidth.xhtml";
		rightPanel = "/xsamsForm/restrictableTable.xhtml";
		formButtonsPanel = "/xsamsForm/emptyWithWidth.xhtml";
		clearForm();
		selectedRestrictables.resetSelectedRestrictablesList();
	}
	
	public void resetQueryBuilder(){
		log.info("enableQueryBuilder called");
		//xsamsQueryNew.toggleEditable();
		this.stage1Display = false;
		xsamsQueryNew.enableEditable();
		//xsamsQueryNew.setSpeciesForm("---");
		//xsamsQueryNew.setWavelenthForm("---");
		mainOptionPanel = "/xsamsForm/XSAMSMainOption.xhtml";		
		//formPanel1 = "/xsamsForm/empty.xhtml";
		//formPanel1A = "/xsamsForm/empty.xhtml";
		//formPanel2 = "/xsamsForm/empty.xhtml";
		//formPanel3 = "/xsamsForm/empty.xhtml";
		//formPanel4 = "/xsamsForm/empty.xhtml";
		//this.rightPanel = "/xsamsForm/emptyWithWidthHeight.xhtml";
		formButtonsPanel = "/xsamsForm/xsamsFormButtons.xhtml";
	}
	
	public void enableQueryBuilder(){
		log.info("enableQueryBuilder called");
		xsamsQueryNew.toggleEditable();
		this.stage1Display = false;
		//xsamsQueryNew.setSpeciesForm("---");
		//xsamsQueryNew.setWavelenthForm("---");
		mainOptionPanel = "/xsamsForm/XSAMSMainOption.xhtml";		
		//formPanel1 = "/xsamsForm/empty.xhtml";
		//formPanel1A = "/xsamsForm/empty.xhtml";
		//formPanel2 = "/xsamsForm/empty.xhtml";
		//formPanel3 = "/xsamsForm/empty.xhtml";
		//formPanel4 = "/xsamsForm/empty.xhtml";
		rightPanel = "/xsamsForm/restrictableTable.xhtml";
		formButtonsPanel = "/xsamsForm/xsamsFormButtons.xhtml";
	}
	
	public void clearAtomsForm(){
		formPanel1 = "/xsamsForm/emptyWithWidth.xhtml";
		atomsForm = false;
		xsamsQueryNew.setAtomsForm(false);
		log.info("displayButtonsForm(): " + displayButtonsForm());
		if(displayButtonsForm() == false){
			formButtonsPanel = "/xsamsForm/empty.xhtml";
		}
	}
	public void clearMoleculesForm(){
		formPanel1A = "/xsamsForm/emptyWithWidth.xhtml";
		moleculesForm = false;
		xsamsQueryNew.setMoleculesForm(false);
		log.info("displayButtonsForm(): " + displayButtonsForm());
		if(displayButtonsForm() == false){
			formButtonsPanel = "/xsamsForm/empty.xhtml";
		}
	}
	public void clearTransitionsForm(){
		formPanel2 = "/xsamsForm/emptyWithWidth.xhtml";
		transtionsForm = false;		
		xsamsQueryNew.setTransitionsForm(false);
		log.info("displayButtonsForm(): " + displayButtonsForm());
		if(displayButtonsForm() == false){
			formButtonsPanel = "/xsamsForm/empty.xhtml";
		}
	}
	
	public void clearCollisionsForm(){
		formPanel3 = "/xsamsForm/emptyWithWidth.xhtml";
		collisionsForm = false;
		xsamsQueryNew.setCollisionsForm(false);
		log.info("displayButtonsForm(): " + displayButtonsForm());
		if(displayButtonsForm() == false){
			formButtonsPanel = "/xsamsForm/empty.xhtml";
		}
	}
	
	public void clearForm() {
		selectedRestrictables.resetSelectedRestrictablesList();
		xsamsQueryNew.clearForm();
	}
	
	public boolean displayButtonsForm(){
		if(atomsForm == true){
			return true;
		} else if (moleculesForm == true){
			return true;
		} else if (transtionsForm == true){
			return true;
		} else if (collisionsForm == true){
			return true;
		} else {
			return false;
		}
	}

}
