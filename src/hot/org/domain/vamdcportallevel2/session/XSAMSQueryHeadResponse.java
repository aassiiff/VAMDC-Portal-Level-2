package org.domain.vamdcportallevel2.session;

public class XSAMSQueryHeadResponse {
	
	private int extendedRegistryListIndex;
	
	private String nodeTitle = "";
	private String URL  = "";
	private String statusCode  = "";
	private String statusMessage  = "";
	private String countRadiative = "";
	private String countStates  = "";
	
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
	
	public boolean checkBoxDisabled(){
		//System.out.println("statusCode: " +  statusCode);
		if(statusCode.equals("500") || statusCode.equals("204") || statusCode.trim().equals("")){
			return true;
		}
		return false;
	}
}
