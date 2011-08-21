package org.vamdc.portal.session.controller;

import org.jboss.seam.annotations.Name;

@Name("restrictableDescription")
public class RestrictableDescription {
	
	private String keyword;
	private String shortDescription;
	private String description;
	
	public String getKeyword() {
		return keyword;
	}


	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}


	public String getShortDescription() {
		return shortDescription;
	}


	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}

	public void setRestrictable(String restrictableValue) {
		if(restrictableValue.equalsIgnoreCase("moleculestoichiometricformula")){
			description = "Molecular stoichiometric formula";
		} else if(restrictableValue.equalsIgnoreCase("moleculechemicalname")){
			description = "Conventional molecule name, e.g. CO2, NH3, Feh (may not be unique)";
		} else if(restrictableValue.equalsIgnoreCase("moleculeinchikey")){
			description = ".";
		} else if(restrictableValue.equalsIgnoreCase("moleculestateenergy")){
			description = ".";
		} else if(restrictableValue.equalsIgnoreCase("moleculestatenuclearspinisomer")){
			description = "Nuclear spin isomer (symetry) of a molecular state.";
		} else if(restrictableValue.equalsIgnoreCase("moleculeordinarystructuralformula")){
			description = ".";
		} else if(restrictableValue.equalsIgnoreCase("radtransfrequency")){
			description = ".";
		} else if(restrictableValue.equalsIgnoreCase("radtranswavenumber")){
			description = ".";
		} else if(restrictableValue.equalsIgnoreCase("radtransprobabilitya")){
			description = ".";
		} else if(restrictableValue.equalsIgnoreCase("radtransprobabilitylinestrength")){
			description = ".";
		} else if(restrictableValue.equalsIgnoreCase("radtranswavelength")){
			description = ".";
		} else if(restrictableValue.equalsIgnoreCase("atomstateenergy")){
			description = "Energy of the level";
		} else if(restrictableValue.equalsIgnoreCase("atomioncharge")){
			description = "Ionization stage with 0 for neutral";
		} else if(restrictableValue.equalsIgnoreCase("atomnuclearcharge")){
			description = "Atomic number or nuclear charge";
		} else if(restrictableValue.equalsIgnoreCase("atomsymbol")){
			description = "Atomic name";
		} else if(restrictableValue.equalsIgnoreCase("atomstateenergy")){
			description = "Energy of the level";
		} else if(restrictableValue.equalsIgnoreCase("inchikey")){
			description = "International Chemical Identifier (InChI) key (27-character or 14-character first part)";
		} 
	}

}
