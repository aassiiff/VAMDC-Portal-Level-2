package org.domain.vamdcportallevel2.session;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("speciesAtoms")
@Scope(ScopeType.SESSION)
public class SpeciesAtoms {

	// Atom Name
	private String atomSymbol = null;

	private String atomInchiKey = null;

	private String atomInchi = null;

	// Atomic Mass
	private String atomMassNumberFrom = null;
	private String atomMassNumberTo = null;

	// Atomic Ion Charge
	private String atomIonChargeFrom = null;
	private String atomIonChargeTo = null;

	// Atomic Nuclear Charge
	private String atomNuclearChargeFrom = null;
	private String atomNuclearChargeTo = null;
	
	private boolean editable = true;
	
	public void toggleEditable() {
		editable = !editable;
	}

	public void clearFields() {
		atomSymbol = "";
		atomInchiKey = "";
		atomInchi = "";
		atomMassNumberFrom = "";
		atomMassNumberTo = "";
		atomIonChargeFrom = "";
		atomIonChargeTo = "";
		atomNuclearChargeFrom = "";
		atomNuclearChargeTo = "";
	}

	public boolean isEditable() {
		return editable;
	}



	public void setEditable(boolean editable) {
		this.editable = editable;
	}



	public String getAtomSymbol() {
		return atomSymbol;
	}

	public void setAtomSymbol(String atomSymbol) {
		this.atomSymbol = atomSymbol;
		System.out.println("this.atomSymbol: " + this.atomSymbol);
	}

	public String getAtomMassNumberFrom() {
		return atomMassNumberFrom;
	}

	public void setAtomMassNumberFrom(String atomMassNumberFrom) {
		this.atomMassNumberFrom = atomMassNumberFrom;
	}

	public String getAtomMassNumberTo() {
		return atomMassNumberTo;
	}

	public void setAtomMassNumberTo(String atomMassNumberTo) {
		this.atomMassNumberTo = atomMassNumberTo;
	}

	public String getAtomIonChargeFrom() {
		return atomIonChargeFrom;
	}

	public void setAtomIonChargeFrom(String atomIonChargeFrom) {
		this.atomIonChargeFrom = atomIonChargeFrom;
	}

	public String getAtomIonChargeTo() {
		return atomIonChargeTo;
	}

	public void setAtomIonChargeTo(String atomIonChargeTo) {
		this.atomIonChargeTo = atomIonChargeTo;
	}

	public String getAtomInchiKey() {
		return atomInchiKey;
	}

	public void setAtomInchiKey(String atomInchiKey) {
		this.atomInchiKey = atomInchiKey;
	}

	public String getAtomInchi() {
		return atomInchi;
	}

	public void setAtomInchi(String atomInchi) {
		this.atomInchi = atomInchi;
	}

	public String getAtomNuclearChargeFrom() {
		return atomNuclearChargeFrom;
	}

	public void setAtomNuclearChargeFrom(String atomNuclearChargeFrom) {
		this.atomNuclearChargeFrom = atomNuclearChargeFrom;
	}

	public String getAtomNuclearChargeTo() {
		return atomNuclearChargeTo;
	}

	public void setAtomNuclearChargeTo(String atomNuclearChargeTo) {
		this.atomNuclearChargeTo = atomNuclearChargeTo;
	}

	public String getQueryString() {
		String xsamsQuery = "";

		boolean firstEntry = true;

		if (atomSymbol != null && atomSymbol.trim().length() > 0) {
			xsamsQuery = xsamsQuery + " AtomSymbol = '" + atomSymbol + "'";
			firstEntry = false;
		}

		if (atomInchi != null && atomInchi.trim().length() > 0) {

			if (firstEntry != true) {
				xsamsQuery = xsamsQuery + " AND ";
			} else {
				firstEntry = false;
			}

			xsamsQuery = xsamsQuery + " AtomInchi LIKE '%" + atomInchi + "%'";

		}

		if (atomInchiKey != null && atomInchiKey.trim().length() > 0) {

			if (firstEntry != true) {
				xsamsQuery = xsamsQuery + " AND ";
			} else {
				firstEntry = false;
			}
			xsamsQuery = xsamsQuery + " AtomInchiKey LIKE '%" + atomInchiKey
					+ "%'";

		}

		if ((atomMassNumberFrom != null && atomMassNumberFrom.trim().length() > 0)
				|| (atomMassNumberTo != null && atomMassNumberTo.trim()
						.length() > 0)) {
			if (firstEntry != true) {
				xsamsQuery = xsamsQuery + " AND ";
			} else {
				firstEntry = false;
			}
			xsamsQuery = xsamsQuery
					+ getRangeQuery(atomMassNumberFrom, atomMassNumberTo,
							"AtomMassNumber");
		}

		if ((atomIonChargeFrom != null && atomIonChargeFrom.trim().length() > 0)
				|| (atomIonChargeTo != null && atomIonChargeTo.trim().length() > 0)) {
			if (firstEntry != true) {
				xsamsQuery = xsamsQuery + " AND ";
			} else {
				firstEntry = false;
			}
			xsamsQuery = xsamsQuery
					+ getRangeQuery(atomIonChargeFrom, atomIonChargeTo,
							"AtomIonCharge");
		}

		if ((atomNuclearChargeFrom != null && atomNuclearChargeFrom.trim()
				.length() > 0)
				|| (atomNuclearChargeTo != null && atomNuclearChargeTo.trim()
						.length() > 0)) {
			if (firstEntry != true) {
				xsamsQuery = xsamsQuery + " AND ";
			} else {
				firstEntry = false;
			}
			xsamsQuery = xsamsQuery
					+ getRangeQuery(atomNuclearChargeFrom, atomNuclearChargeTo,
							"AtomNuclearCharge");
		}

		return xsamsQuery;
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
