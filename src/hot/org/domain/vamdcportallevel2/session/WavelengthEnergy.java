package org.domain.vamdcportallevel2.session;

import org.jboss.seam.annotations.Name;

@Name("wavelengthEnergy")

public class WavelengthEnergy {
	
	private String radTransEnergyFrom;
	private String radTransEnergyTo;
	
	private String nonRadTranEnergyFrom;
	private String nonRadTranEnergyTo;
	
	private String atomStateEnergyFrom;
	private String atomStateEnergyTo;
	
	private String aAtomStateIonizationEnergyFrom;
	private String aAtomStateIonizationEnergyTo;

	private String moleculeStateEnergyFrom;
	private String moleculeStateEnergyTo;

	private String moleculeStateEnergyOriginFrom;
	private String moleculeStateEnergyOriginTo;
	
	public String getRadTransEnergyFrom() {
		return radTransEnergyFrom;
	}
	public void setRadTransEnergyFrom(String radTransEnergyFrom) {
		this.radTransEnergyFrom = radTransEnergyFrom;
	}
	public String getRadTransEnergyTo() {
		return radTransEnergyTo;
	}
	public void setRadTransEnergyTo(String radTransEnergyTo) {
		this.radTransEnergyTo = radTransEnergyTo;
	}
	public String getNonRadTranEnergyFrom() {
		return nonRadTranEnergyFrom;
	}
	public void setNonRadTranEnergyFrom(String nonRadTranEnergyFrom) {
		this.nonRadTranEnergyFrom = nonRadTranEnergyFrom;
	}
	public String getNonRadTranEnergyTo() {
		return nonRadTranEnergyTo;
	}
	public void setNonRadTranEnergyTo(String nonRadTranEnergyTo) {
		this.nonRadTranEnergyTo = nonRadTranEnergyTo;
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
	public String getaAtomStateIonizationEnergyFrom() {
		return aAtomStateIonizationEnergyFrom;
	}
	public void setaAtomStateIonizationEnergyFrom(
			String aAtomStateIonizationEnergyFrom) {
		this.aAtomStateIonizationEnergyFrom = aAtomStateIonizationEnergyFrom;
	}
	public String getaAtomStateIonizationEnergyTo() {
		return aAtomStateIonizationEnergyTo;
	}
	public void setaAtomStateIonizationEnergyTo(String aAtomStateIonizationEnergyTo) {
		this.aAtomStateIonizationEnergyTo = aAtomStateIonizationEnergyTo;
	}
	public String getMoleculeStateEnergyFrom() {
		return moleculeStateEnergyFrom;
	}
	public void setMoleculeStateEnergyFrom(String moleculeStateEnergyFrom) {
		this.moleculeStateEnergyFrom = moleculeStateEnergyFrom;
	}
	public String getMoleculeStateEnergyTo() {
		return moleculeStateEnergyTo;
	}
	public void setMoleculeStateEnergyTo(String moleculeStateEnergyTo) {
		this.moleculeStateEnergyTo = moleculeStateEnergyTo;
	}
	public String getMoleculeStateEnergyOriginFrom() {
		return moleculeStateEnergyOriginFrom;
	}
	public void setMoleculeStateEnergyOriginFrom(
			String moleculeStateEnergyOriginFrom) {
		this.moleculeStateEnergyOriginFrom = moleculeStateEnergyOriginFrom;
	}
	public String getMoleculeStateEnergyOriginTo() {
		return moleculeStateEnergyOriginTo;
	}
	
	public void setMoleculeStateEnergyOriginTo(String moleculeStateEnergyOriginTo) {
		this.moleculeStateEnergyOriginTo = moleculeStateEnergyOriginTo;
	}
	
	public String getQueryString(){
		//System.out.println("Species Molecules get Query String");
		String xsamsQuery = ""; 
		
		boolean firstEntry = true;
		
		if (radTransEnergyFrom != null
				&& radTransEnergyFrom.trim().length() > 0) {
			if(firstEntry != true){
				xsamsQuery = xsamsQuery + " AND ";
			} else {
				firstEntry = false;
			}
			xsamsQuery = xsamsQuery + " RadTransEnergy >= "
					+ radTransEnergyFrom;
			
			if (radTransEnergyTo != null
					&& radTransEnergyTo.trim().length() > 0) {
				if(firstEntry != true){
					xsamsQuery = xsamsQuery + " AND ";
				} else {
					firstEntry = false;
				}
				xsamsQuery = xsamsQuery + " RadTransEnergy <= "
						+ radTransEnergyTo;
			}
		}
		
		if (nonRadTranEnergyFrom != null
				&& nonRadTranEnergyFrom.trim().length() > 0) {
			if(firstEntry != true){
				xsamsQuery = xsamsQuery + " AND ";
			} else {
				firstEntry = false;
			}
			xsamsQuery = xsamsQuery + " NonRadTranEnergy >= "
					+ nonRadTranEnergyFrom;
			
			if (nonRadTranEnergyTo != null
					&& nonRadTranEnergyTo.trim().length() > 0) {
				if(firstEntry != true){
					xsamsQuery = xsamsQuery + " AND ";
				} else {
					firstEntry = false;
				}
				xsamsQuery = xsamsQuery + " NonRadTranEnergy <= "
						+ nonRadTranEnergyTo;
			}
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
			
			if (atomStateEnergyTo != null
					&& atomStateEnergyTo.trim().length() > 0) {
				if(firstEntry != true){
					xsamsQuery = xsamsQuery + " AND ";
				} else {
					firstEntry = false;
				}
				xsamsQuery = xsamsQuery + " AtomStateEnergy <= "
						+ atomStateEnergyTo;
			}
		}
		
		if (aAtomStateIonizationEnergyFrom != null
				&& aAtomStateIonizationEnergyFrom.trim().length() > 0) {
			if(firstEntry != true){
				xsamsQuery = xsamsQuery + " AND ";
			} else {
				firstEntry = false;
			}
			xsamsQuery = xsamsQuery + " AtomStateIonizationEnergy >= "
					+ aAtomStateIonizationEnergyFrom;
			
			if (aAtomStateIonizationEnergyTo != null
					&& aAtomStateIonizationEnergyTo.trim().length() > 0) {
				if(firstEntry != true){
					xsamsQuery = xsamsQuery + " AND ";
				} else {
					firstEntry = false;
				}
				xsamsQuery = xsamsQuery + " AtomStateIonizationEnergy <= "
						+ aAtomStateIonizationEnergyTo;
			}
		}
		
		if (moleculeStateEnergyFrom != null
				&& moleculeStateEnergyFrom.trim().length() > 0) {
			if(firstEntry != true){
				xsamsQuery = xsamsQuery + " AND ";
			} else {
				firstEntry = false;
			}
			xsamsQuery = xsamsQuery + " MoleculeStateEnergy >= "
					+ moleculeStateEnergyFrom;
			
			if (moleculeStateEnergyTo != null
					&& moleculeStateEnergyTo.trim().length() > 0) {
				if(firstEntry != true){
					xsamsQuery = xsamsQuery + " AND ";
				} else {
					firstEntry = false;
				}
				xsamsQuery = xsamsQuery + " MoleculeStateEnergy <= "
						+ moleculeStateEnergyTo;
			}
		}
		
		if (moleculeStateEnergyOriginFrom != null
				&& moleculeStateEnergyOriginFrom.trim().length() > 0) {
			if(firstEntry != true){
				xsamsQuery = xsamsQuery + " AND ";
			} else {
				firstEntry = false;
			}
			xsamsQuery = xsamsQuery + " MoleculeStateEnergyOrigin >= "
					+ moleculeStateEnergyOriginFrom;
			
			if (moleculeStateEnergyOriginTo != null
					&& moleculeStateEnergyOriginTo.trim().length() > 0) {
				if(firstEntry != true){
					xsamsQuery = xsamsQuery + " AND ";
				} else {
					firstEntry = false;
				}
				xsamsQuery = xsamsQuery + " MoleculeStateEnergyOrigin <= "
						+ moleculeStateEnergyOriginTo;
			}
		}
		
		return xsamsQuery;
	}
	
	public void clearFields(){
		radTransEnergyFrom = "";
		radTransEnergyTo = "";
		nonRadTranEnergyFrom = "";
		nonRadTranEnergyTo = "";
		
		atomStateEnergyFrom = "";
		atomStateEnergyTo = "";
		aAtomStateIonizationEnergyFrom = "";
		aAtomStateIonizationEnergyTo = "";
		
		moleculeStateEnergyFrom = "";
		moleculeStateEnergyTo = "";
		moleculeStateEnergyOriginFrom = "";
		moleculeStateEnergyOriginTo = "";
	}
}
