package org.vamdc.portal.session.controller;

import org.jboss.seam.annotations.Name;

@Name("wavelengthWavelength")
public class WavelengthWavelength {
	
	private String radTransWavelengthFrom;
	private String radTransWavelengthTo;
	
	
	public String getRadTransWavelengthFrom() {
		return radTransWavelengthFrom;
	}
	public void setRadTransWavelengthFrom(String radTransWavelengthFrom) {
		this.radTransWavelengthFrom = radTransWavelengthFrom;
	}
	public String getRadTransWavelengthTo() {
		return radTransWavelengthTo;
	}
	public void setRadTransWavelengthTo(String radTransWavelengthTo) {
		this.radTransWavelengthTo = radTransWavelengthTo;
	}	
	
	public void clearFields(){
		radTransWavelengthFrom = "";
		radTransWavelengthTo = "";
	}
	
	public String getQueryString(){
		String xsamsQuery = ""; 
		
		boolean firstEntry = true;
		
		if (radTransWavelengthFrom != null
				&& radTransWavelengthFrom.trim().length() > 0) {
			if(firstEntry != true){
				xsamsQuery = xsamsQuery + " AND ";
			} else {
				firstEntry = false;
			}
			xsamsQuery = xsamsQuery + " RadTransWavelength >= "
					+ radTransWavelengthFrom;
			
			if (radTransWavelengthTo != null
					&& radTransWavelengthTo.trim().length() > 0) {
				if(firstEntry != true){
					xsamsQuery = xsamsQuery + " AND ";
				} else {
					firstEntry = false;
				}
				xsamsQuery = xsamsQuery + " RadTransWavelength <= "
						+ radTransWavelengthTo;
			}
		}
		
		return xsamsQuery;
	}

}
