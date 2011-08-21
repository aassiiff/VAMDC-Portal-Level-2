package org.domain.vamdcportallevel2.session;

import org.jboss.seam.annotations.Name;

@Name("wavelengthWaveNumber")
public class WavelengthWaveNumber {
	
	private String radTransWavenumberFrom;
	private String radTransWavenumberTo;
	
	public String getRadTransWavenumberFrom() {
		return radTransWavenumberFrom;
	}
	public void setRadTransWavenumberFrom(String radTransWavenumberFrom) {
		this.radTransWavenumberFrom = radTransWavenumberFrom;
	}
	public String getRadTransWavenumberTo() {
		return radTransWavenumberTo;
	}
	public void setRadTransWavenumberTo(String radTransWavenumberTo) {
		this.radTransWavenumberTo = radTransWavenumberTo;
	}
	
	public void clearFields(){
		radTransWavenumberFrom = "";
		radTransWavenumberTo = "";
	}
	
	public String getQueryString(){
		String xsamsQuery = ""; 
		
		boolean firstEntry = true;
		
		if (radTransWavenumberFrom != null
				&& radTransWavenumberFrom.trim().length() > 0) {
			if(firstEntry != true){
				xsamsQuery = xsamsQuery + " AND ";
			} else {
				firstEntry = false;
			}
			xsamsQuery = xsamsQuery + " RadTransWavenumber >= "
					+ radTransWavenumberFrom;
			
			if (radTransWavenumberTo != null
					&& radTransWavenumberTo.trim().length() > 0) {
				if(firstEntry != true){
					xsamsQuery = xsamsQuery + " AND ";
				} else {
					firstEntry = false;
				}
				xsamsQuery = xsamsQuery + " RadTransWavenumber <= "
						+ radTransWavenumberTo;
			}
		}
		
		return xsamsQuery;
	}
}
