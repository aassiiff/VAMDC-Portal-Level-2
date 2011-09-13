package org.vamdc.portal.session.query;

@SuppressWarnings("unused")
public class XSAMSQueryHeadResponse {
	
	private int extendedRegistryListIndex;
	
	private String nodeTitle = "";
	private String URL  = "";
	private String statusCode  = "";
	private String statusMessage  = "";
	private String countRadiative = "";
	private String countStates  = "";
	private String countSpecies = "";
	private String countAtoms = "";
	private String countMolecules = "";
	private String countSources = "";
	private String countCollisions = "";
	private String countNonRadiative = "";
	
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusMessage() {
		System.out.println("getStatusMessage: " + statusMessage);
		//if(statusMessage.equalsIgnoreCase("OK")){
		/*if(statusMessage.equalsIgnoreCase("OK") || statusMessage.equalsIgnoreCase("No Content")){
			statusMessage = "Online";
		} else {
			statusMessage = "Offline";
		}*/
		return statusMessage;
	}
	public void setStatusMessage(String statusMessageValue) {	
		System.out.println("setStatusMessage: " + statusMessageValue);
		/* */
		if(statusMessageValue != null)
			if(statusMessageValue.trim().equalsIgnoreCase("OK") || statusMessageValue.trim().equalsIgnoreCase("No Content")){
				statusMessage = "Online";
			} else {
				statusMessage = "Offline";
			}
		this.statusMessage = statusMessageValue;
	}
	public String getCountRadiative() {
		if(countRadiative.trim().length() == 0){
			countRadiative = "0";
		}
		return countRadiative;
	}
	public void setCountRadiative(String countRadiativeValue) {
		if(countRadiativeValue != null) {
			if(countRadiativeValue.trim().length() > 0)
				this.countRadiative = countRadiativeValue;
		} else {
			this.countRadiative = "0";
		}
	}
	public String getCountStates() {
		if(countStates.trim().length() == 0){
			countStates = "0";
		}
		return countStates;
	}
	public void setCountStates(String countStatesValue) {
		if(countStatesValue != null) {
			if(countStatesValue.trim().length() > 0)		
				this.countStates = countStatesValue;
		} else {
			this.countStates = "0";
		}		
	}
	public String getNodeTitle() {
		return nodeTitle;
	}
	public void setNodeTitle(String nodeTitle) {
		this.nodeTitle = nodeTitle;
	}
	public String getCountSpecies() {
		if(countSpecies.trim().length() == 0){
			countSpecies = "0";
		}
		return countSpecies;
	}
	public void setCountSpecies(String countSpeciesValue) {
		if(countSpeciesValue != null) {
			if(countSpeciesValue.trim().length() > 0)
				this.countSpecies = countSpeciesValue;
		} else {
			this.countSpecies = "0";
		}
		
	}
	public String getCountAtoms() {
		if(countAtoms.trim().length() == 0){
			countAtoms = "0";
		}
		return countAtoms;
	}
	public void setCountAtoms(String countAtomsValue) {
		if(countAtomsValue != null)
			if(countAtomsValue.trim().length() > 0){
				this.countAtoms = countAtomsValue;
			} else {
				this.countAtoms = "0";
			}
		
	}
	public String getCountMolecules() {
		if(countMolecules.trim().length() == 0){
			countMolecules = "0";
		}
		return countMolecules;
	}
	public void setCountMolecules(String countMoleculesValue) {
		if(countMoleculesValue != null) { 
			if(countMoleculesValue.trim().length() > 0)		
			this.countMolecules = countMoleculesValue;
		} else {
			this.countMolecules = "0";
		}
		
	}
	public String getCountSources() {
		if(countSources.trim().length() == 0){
			countSources = "0";
		}
		return countSources;
	}
	public void setCountSources(String countSourcesValue) {
		if(countSourcesValue != null) { 
			if(countSourcesValue.trim().length() > 0)		
			this.countSources = countSourcesValue;
		} else {
			this.countSources = "0";
		}
		
	}
	public String getCountCollisions() {
		if(countCollisions.trim().length() == 0){
			countCollisions = "0";
		}
		return countCollisions;
	}
	public void setCountCollisions(String countCollisionsValue) {
		if(countCollisionsValue != null) { 
			if(countCollisionsValue.trim().length() > 0)
			this.countCollisions = countCollisionsValue;
		} else {
			this.countCollisions = "0";
		}		
	}
	public String getCountNonRadiative() {
		if(countNonRadiative.trim().length() == 0){
			countNonRadiative = "0";
		}
		return countNonRadiative;
	}
	public void setCountNonRadiative(String countNonRadiativeValue) {
		if(countNonRadiativeValue != null){
			if (countNonRadiativeValue.trim().length() > 0)
			this.countNonRadiative = countNonRadiativeValue;
		} else {
			this.countNonRadiative = "0";
		}
	}
	public boolean checkBoxDisabled(){
		//System.out.println("statusCode: " +  statusCode);
		if(statusCode.equals("500") || statusCode.equals("204") || statusCode.trim().equals("")){
			return true;
		}
		return false;
	}
}
