package org.vamdc.portal.session.controller;

import org.jboss.seam.annotations.Name;

@Name("wavelengthFrequency")
public class WavelengthFrequency {
	
	private String radTransFrequencyFrom;	
	private String radTransFrequencyTo;

	public String getRadTransFrequencyFrom() {
		return radTransFrequencyFrom;
	}

	public void setRadTransFrequencyFrom(String radTransFrequencyFrom) {
		this.radTransFrequencyFrom = radTransFrequencyFrom;
	}

	public String getRadTransFrequencyTo() {
		return radTransFrequencyTo;
	}

	public void setRadTransFrequencyTo(String radTransFrequencyTo) {
		this.radTransFrequencyTo = radTransFrequencyTo;
	}
	
	public void clearFields(){
		radTransFrequencyFrom = "";	
		radTransFrequencyTo = "";
	}
	
	public String getQueryString(){
		String xsamsQuery = ""; 
		
		boolean firstEntry = true;
		
		if (radTransFrequencyFrom != null
				&& radTransFrequencyFrom.trim().length() > 0) {
			if(firstEntry != true){
				xsamsQuery = xsamsQuery + " AND ";
			} else {
				firstEntry = false;
			}
			xsamsQuery = xsamsQuery + " RadTransFrequency >= "
					+ radTransFrequencyFrom;
			
			if (radTransFrequencyTo != null
					&& radTransFrequencyTo.trim().length() > 0) {
				if(firstEntry != true){
					xsamsQuery = xsamsQuery + " AND ";
				} else {
					firstEntry = false;
				}
				xsamsQuery = xsamsQuery + " RadTransFrequency <= "
						+ radTransFrequencyTo;
			}
		}
		
		return xsamsQuery;
	}

}
