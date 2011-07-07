package org.domain.vamdcportallevel2.session;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("transitions")
@Scope(ScopeType.SESSION)
public class Transitions {
	
	private String radTransWavelengthFrom;
	private String radTransWavelengthTo;
	
	private String radTransFrequencyFrom;	
	private String radTransFrequencyTo;

	private String radTransEnergyFrom;
	private String radTransEnergyTo;
	
	private String radTransWavenumberFrom;
	private String radTransWavenumberTo;
	
	private String atomStateEnergyFrom;
	private String atomStateEnergyTo;
	
	private String moleculeStateEnergyFrom;
	private String moleculeStateEnergyTo;
	
	private String radTransProbabilityAFrom;
	private String radTransProbabilityATo;
	
	private boolean radTransBroadeningDoppler = false;
	private boolean radTransBroadeningInstrument = false;
	private boolean radTransBroadeningNatural = false;
	private boolean radTransBroadeningPressure = false;
	
	private boolean editable = true;
	
	public void toggleEditable() {
		editable = !editable;
	}
	
	public void clearFields() {
		radTransWavelengthFrom = "";
		radTransWavelengthTo = "";
		
		radTransFrequencyFrom = "";
		radTransFrequencyTo = "";
		
		radTransEnergyFrom = "";
		radTransEnergyTo = "";
		
		radTransWavenumberFrom = "";
		radTransWavenumberTo = "";
		
		atomStateEnergyFrom = "";
		atomStateEnergyTo = "";
		
		moleculeStateEnergyFrom = "";
		moleculeStateEnergyTo = "";
		
		radTransProbabilityAFrom = "";
		radTransProbabilityATo = "";
		
		radTransBroadeningDoppler = false;
		radTransBroadeningInstrument = false;
		radTransBroadeningNatural = false;
		radTransBroadeningPressure = false;
	}
	
	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public String getQueryString() {
		String xsamsQuery = "";

		boolean firstEntry = true;
		
		if ((radTransWavelengthFrom != null && radTransWavelengthFrom.trim().length() > 0)
				|| (radTransWavelengthTo != null && radTransWavelengthTo.trim()
						.length() > 0)) {
			if (firstEntry != true) {
				xsamsQuery = xsamsQuery + " AND ";
			} else {
				firstEntry = false;
			}
			xsamsQuery = xsamsQuery
					+ getRangeQuery(radTransWavelengthFrom, radTransWavelengthTo,
							"RadTransWavelength");
		}
		
		if ((radTransFrequencyFrom != null && radTransFrequencyFrom.trim().length() > 0)
				|| (radTransFrequencyTo != null && radTransFrequencyTo.trim()
						.length() > 0)) {
			if (firstEntry != true) {
				xsamsQuery = xsamsQuery + " AND ";
			} else {
				firstEntry = false;
			}
			xsamsQuery = xsamsQuery
					+ getRangeQuery(radTransFrequencyFrom, radTransFrequencyTo,
							"RadTransFrequency");
		}
		
		if ((radTransEnergyFrom != null && radTransEnergyFrom.trim().length() > 0)
				|| (radTransEnergyTo != null && radTransEnergyTo.trim()
						.length() > 0)) {
			if (firstEntry != true) {
				xsamsQuery = xsamsQuery + " AND ";
			} else {
				firstEntry = false;
			}
			xsamsQuery = xsamsQuery
					+ getRangeQuery(radTransEnergyFrom, radTransEnergyTo,
							"RadTransEnergy");
		}
		
		if ((radTransWavenumberFrom != null && radTransWavenumberFrom.trim().length() > 0)
				|| (radTransWavenumberTo != null && radTransWavenumberTo.trim()
						.length() > 0)) {
			if (firstEntry != true) {
				xsamsQuery = xsamsQuery + " AND ";
			} else {
				firstEntry = false;
			}
			xsamsQuery = xsamsQuery
					+ getRangeQuery(radTransWavenumberFrom, radTransWavenumberTo,
							"RadTransWavenumber");
		}
		
		if ((moleculeStateEnergyFrom != null && moleculeStateEnergyFrom.trim().length() > 0)
				|| (moleculeStateEnergyTo != null && moleculeStateEnergyTo.trim()
						.length() > 0)) {
			if (firstEntry != true) {
				xsamsQuery = xsamsQuery + " AND ";
			} else {
				firstEntry = false;
			}
			xsamsQuery = xsamsQuery
					+ getRangeQuery(moleculeStateEnergyFrom, moleculeStateEnergyTo,
							"MoleculeStateEnergy");
		}
		
		if ((atomStateEnergyFrom != null && atomStateEnergyFrom.trim().length() > 0)
				|| (atomStateEnergyTo != null && atomStateEnergyTo.trim()
						.length() > 0)) {
			if (firstEntry != true) {
				xsamsQuery = xsamsQuery + " AND ";
			} else {
				firstEntry = false;
			}
			xsamsQuery = xsamsQuery
					+ getRangeQuery(atomStateEnergyFrom, atomStateEnergyTo,
							"AtomStateEnergy");
		}
		
		if ((radTransProbabilityAFrom != null && radTransProbabilityAFrom.trim().length() > 0)
				|| (radTransProbabilityATo != null && radTransProbabilityATo.trim()
						.length() > 0)) {
			if (firstEntry != true) {
				xsamsQuery = xsamsQuery + " AND ";
			} else {
				firstEntry = false;
			}
			xsamsQuery = xsamsQuery
					+ getRangeQuery(radTransProbabilityAFrom, radTransProbabilityATo,
							"RadTransProbabilityA");
		}
		
		if(radTransBroadeningDoppler == true){
			if (firstEntry != true) {
				xsamsQuery = xsamsQuery + " AND ";
			} else {
				firstEntry = false;
			}
			xsamsQuery = xsamsQuery + " RadTransBroadeningDoppler <> NULL" ;
		}
		
		if(radTransBroadeningInstrument == true){
			if (firstEntry != true) {
				xsamsQuery = xsamsQuery + " AND ";
			} else {
				firstEntry = false;
			}
			xsamsQuery = xsamsQuery + " RadTransBroadeningInstrument <> NULL" ;
		}
		
		if(radTransBroadeningNatural == true){
			if (firstEntry != true) {
				xsamsQuery = xsamsQuery + " AND ";
			} else {
				firstEntry = false;
			}
			xsamsQuery = xsamsQuery + " RadTransBroadeningNatural <> NULL" ;
		}
		
		if(radTransBroadeningPressure == true){
			if (firstEntry != true) {
				xsamsQuery = xsamsQuery + " AND ";
			} else {
				firstEntry = false;
			}
			xsamsQuery = xsamsQuery + " RadTransBroadeningPressure <> NULL" ;
		}
		
		return xsamsQuery;
	}
	
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
	public String getRadTransProbabilityAFrom() {
		return radTransProbabilityAFrom;
	}
	public void setRadTransProbabilityAFrom(String radTransProbabilityAFrom) {
		this.radTransProbabilityAFrom = radTransProbabilityAFrom;
	}
	public String getRadTransProbabilityATo() {
		return radTransProbabilityATo;
	}
	public void setRadTransProbabilityATo(String radTransProbabilityATo) {
		this.radTransProbabilityATo = radTransProbabilityATo;
	}
	public boolean isRadTransBroadeningDoppler() {
		return radTransBroadeningDoppler;
	}
	public void setRadTransBroadeningDoppler(boolean radTransBroadeningDoppler) {
		this.radTransBroadeningDoppler = radTransBroadeningDoppler;
	}
	public boolean isRadTransBroadeningInstrument() {
		return radTransBroadeningInstrument;
	}
	public void setRadTransBroadeningInstrument(boolean radTransBroadeningInstrument) {
		this.radTransBroadeningInstrument = radTransBroadeningInstrument;
	}
	public boolean isRadTransBroadeningNatural() {
		return radTransBroadeningNatural;
	}
	public void setRadTransBroadeningNatural(boolean radTransBroadeningNatural) {
		this.radTransBroadeningNatural = radTransBroadeningNatural;
	}
	public boolean isRadTransBroadeningPressure() {
		return radTransBroadeningPressure;
	}
	public void setRadTransBroadeningPressure(boolean radTransBroadeningPressure) {
		this.radTransBroadeningPressure = radTransBroadeningPressure;
	}
	
	private String getRangeQuery(String value1, String value2, String columnName) {
		if ((value1 != null && value1.trim().length() > 0)
				&& (value2 != null && value2.trim().length() > 0)) {
			//return columnName + " BETWEEN " + value1 + " AND " + value2;
			
			return columnName + " >= " + value1 + " AND " + columnName + " <= " + value2;
		} else if (value1 != null && value1.trim().length() > 0) {
			return columnName + " >= " + value1;
		} else if (value2 != null && value2.trim().length() > 0) {
			return columnName + " <= " + value2;
		}
		return "";
	}
}
