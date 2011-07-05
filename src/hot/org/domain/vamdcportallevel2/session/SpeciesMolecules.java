package org.domain.vamdcportallevel2.session;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("speciesMolecules")
@Scope(ScopeType.SESSION)
public class SpeciesMolecules {
	
	private String moleculeChemicalName = null;	
	private String moleculeInchiKey = null;
	private String moleculeInchi = null;
	private String moleculeOrdinaryStructuralFormula = null;
	
	// Molecule Ion Charge
	private String moleculeIonChargeFrom = null;
	private String moleculeIonChargeTo = null;
	
	// Molecule Ion Charge
	private String moleculeProtonationFrom = null;
	private String moleculeProtonationTo = null;
	
	public void clearFields(){
		moleculeChemicalName = "";
		moleculeInchiKey = "";
		moleculeInchi = "";
		moleculeOrdinaryStructuralFormula = "";
		moleculeIonChargeFrom = "";
		moleculeIonChargeTo = "";
		moleculeProtonationFrom = "";
		moleculeProtonationTo = "";
	}
	
	public String getMoleculeChemicalName() {
		return moleculeChemicalName;
	}
	public void setMoleculeChemicalName(String moleculeChemicalName) {
		this.moleculeChemicalName = moleculeChemicalName;
	}
	public String getMoleculeInchiKey() {
		return moleculeInchiKey;
	}
	public void setMoleculeInchiKey(String moleculeInchiKey) {
		this.moleculeInchiKey = moleculeInchiKey;
	}
	public String getMoleculeInchi() {
		return moleculeInchi;
	}
	public void setMoleculeInchi(String moleculeInchi) {
		this.moleculeInchi = moleculeInchi;
	}
	public String getMoleculeOrdinaryStructuralFormula() {
		return moleculeOrdinaryStructuralFormula;
	}
	public void setMoleculeOrdinaryStructuralFormula(
			String moleculeOrdinaryStructuralFormula) {
		this.moleculeOrdinaryStructuralFormula = moleculeOrdinaryStructuralFormula;
	}
	
	public String getMoleculeIonChargeFrom() {
		return moleculeIonChargeFrom;
	}

	public void setMoleculeIonChargeFrom(String moleculeIonChargeFrom) {
		this.moleculeIonChargeFrom = moleculeIonChargeFrom;
	}

	public String getMoleculeIonChargeTo() {
		return moleculeIonChargeTo;
	}

	public void setMoleculeIonChargeTo(String moleculeIonChargeTo) {
		this.moleculeIonChargeTo = moleculeIonChargeTo;
	}

	public String getMoleculeProtonationFrom() {
		return moleculeProtonationFrom;
	}

	public void setMoleculeProtonationFrom(String moleculeProtonationFrom) {
		this.moleculeProtonationFrom = moleculeProtonationFrom;
	}

	public String getMoleculeProtonationTo() {
		return moleculeProtonationTo;
	}

	public void setMoleculeProtonationTo(String moleculeProtonationTo) {
		this.moleculeProtonationTo = moleculeProtonationTo;
	}

	public String getQueryString(){
		//System.out.println("Species Molecules get Query String");
		String xsamsQuery = ""; 
		
		boolean firstEntry = true;
		
		if (moleculeChemicalName != null && moleculeChemicalName.trim().length() > 0) {
			if(firstEntry != true){
				xsamsQuery = xsamsQuery + " AND ";
			} else {
				firstEntry = false;
			}
			xsamsQuery = xsamsQuery + " MoleculeChemicalName = '" + moleculeChemicalName + "'";
			
		}

		if (moleculeInchiKey != null && moleculeInchiKey.trim().length() > 0) {
			if(firstEntry != true){
				xsamsQuery = xsamsQuery + " AND ";
			} else {
				firstEntry = false;
			}
			//xsamsQuery = xsamsQuery + " MoleculeInchiKey LIKE '%" + moleculeInchiKey + "%'";
			xsamsQuery = xsamsQuery + " MoleculeInchiKey='" + moleculeInchiKey + "'";
			
		}
		
		if (moleculeInchi != null && moleculeInchi.trim().length() > 0) {
			if(firstEntry != true){
				xsamsQuery = xsamsQuery + " AND ";
			} else {
				firstEntry = false;
			}
			//xsamsQuery = xsamsQuery + " MoleculeInchi LIKE '%" + moleculeInchi + "%'";
			xsamsQuery = xsamsQuery + " MoleculeInchi='" + moleculeInchi + "'";			
		}
		
		if (moleculeOrdinaryStructuralFormula != null && moleculeOrdinaryStructuralFormula.trim().length() > 0) {
			if(firstEntry != true){
				xsamsQuery = xsamsQuery + " AND ";
			} else {
				firstEntry = false;
			}
			xsamsQuery = xsamsQuery + " MoleculeStoichiometricFormula = '" + moleculeOrdinaryStructuralFormula + "'";		
		}
		
		System.out.println(xsamsQuery);		
		return xsamsQuery;
	}

}
