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
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	public String getCountRadiative() {
		return countRadiative;
	}
	public void setCountRadiative(String countRadiative) {
		this.countRadiative = countRadiative;
	}
	public String getCountStates() {
		return countStates;
	}
	public void setCountStates(String countStates) {
		this.countStates = countStates;
	}
	public String getNodeTitle() {
		return nodeTitle;
	}
	public void setNodeTitle(String nodeTitle) {
		this.nodeTitle = nodeTitle;
	}
	public String getCountSpecies() {
		return countSpecies;
	}
	public void setCountSpecies(String countSpecies) {
		this.countSpecies = countSpecies;
	}
	public String getCountAtoms() {
		return countAtoms;
	}
	public void setCountAtoms(String countAtoms) {
		this.countAtoms = countAtoms;
	}
	public String getCountMolecules() {
		return countMolecules;
	}
	public void setCountMolecules(String countMolecules) {
		this.countMolecules = countMolecules;
	}
	public String getCountSources() {
		return countSources;
	}
	public void setCountSources(String countSources) {
		this.countSources = countSources;
	}
	public String getCountCollisions() {
		return countCollisions;
	}
	public void setCountCollisions(String countCollisions) {
		this.countCollisions = countCollisions;
	}
	public String getCountNonRadiative() {
		return countNonRadiative;
	}
	public void setCountNonRadiative(String countNonRadiative) {
		this.countNonRadiative = countNonRadiative;
	}
	public boolean checkBoxDisabled(){
		//System.out.println("statusCode: " +  statusCode);
		if(statusCode.equals("500") || statusCode.equals("204") || statusCode.trim().equals("")){
			return true;
		}
		return false;
	}
}
