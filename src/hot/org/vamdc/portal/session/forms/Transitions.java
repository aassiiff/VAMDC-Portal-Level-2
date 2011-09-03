package org.vamdc.portal.session.forms;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import javax.measure.Measure;
import javax.measure.converter.UnitConverter;
import static javax.measure.unit.NonSI.*;
import static javax.measure.unit.SI.*;

@Name("transitions")
@Scope(ScopeType.SESSION)
public class Transitions {

	private String radTransWavelengthFrom;
	private String radTransWavelengthTo;
	private String radTransWavelengthStdUnit = "�";
	private String radTransWavelengthSelectedUnit = "";

	private String radTransFrequencyFrom;
	private String radTransFrequencyTo;
	private String radTransFrequencyStdUnit = "Hz";
	private String radTransFrequencySelectedUnit = "";

	private String radTransEnergyFrom;
	private String radTransEnergyTo;
	private String radTransEnergyStdUnit = "eV";
	private String radTransEnergySelectedUnit = "";

	private String radTransWavenumberFrom;
	private String radTransWavenumberTo;
	private String radTransWavenumberStdUnit = "1/cm";
	private String radTransWavenumberSelectedUnit = "";

	private String atomStateEnergyFrom;
	private String atomStateEnergyTo;
	private String atomStateEnergyStdUnit = "eV";
	private String atomStateEnergySelectedUnit = "";

	private String moleculeStateEnergyFrom;
	private String moleculeStateEnergyTo;
	private String moleculeStateEnergyStdUnit = "eV";
	private String moleculeStateEnergySelectedUnit = "";

	private String radTransProbabilityAFrom;
	private String radTransProbabilityATo;
	// private String radTransProbabilityAStdUnit;
	// private String radTransProbabilityASelectedUnit = "";

	private boolean radTransBroadeningDoppler = false;
	private boolean radTransBroadeningInstrument = false;
	private boolean radTransBroadeningNatural = false;
	private boolean radTransBroadeningPressure = false;

	private boolean editable = true;

	private double speedOfLightInMeter = 299792458;
	private double speedOfLightInCM = 29979245800d;
	private double planksConstantIneVs = 4.135667E-15d;

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
		System.out.println("this.radTransFrequencySelectedUnit: "
				+ this.radTransFrequencySelectedUnit);
		String xsamsQuery = "";

		boolean firstEntry = true;

		if ((radTransWavelengthFrom != null && radTransWavelengthFrom.trim()
				.length() > 0)
				|| (radTransWavelengthTo != null && radTransWavelengthTo.trim()
						.length() > 0)) {
			if (firstEntry != true) {
				xsamsQuery = xsamsQuery + " AND ";
			} else {
				firstEntry = false;
			}

			xsamsQuery = xsamsQuery
					+ getRangeQuery(
							unitConversion(radTransWavelengthFrom,
									radTransWavelengthStdUnit,
									radTransWavelengthSelectedUnit,
									"WAVELENGTH"),
							unitConversion(radTransWavelengthTo,
									radTransWavelengthStdUnit,
									radTransWavelengthSelectedUnit,
									"WAVELENGTH"), "RadTransWavelength");
		}
/*
		if ((radTransFrequencyFrom != null && radTransFrequencyFrom.trim()
				.length() > 0)
				|| (radTransFrequencyTo != null && radTransFrequencyTo.trim()
						.length() > 0)) {
			if (firstEntry != true) {
				xsamsQuery = xsamsQuery + " AND ";
			} else {
				firstEntry = false;
			}

			xsamsQuery = xsamsQuery
					+ getRangeQuery(
							unitConversion(radTransFrequencyFrom,
									radTransFrequencyStdUnit,
									radTransFrequencySelectedUnit, "FREQUENCY"),
							unitConversion(radTransFrequencyTo,
									radTransFrequencyStdUnit,
									radTransFrequencySelectedUnit, "FREQUENCY"),
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
					+ getRangeQuery(
							unitConversion(radTransEnergyFrom,
									radTransEnergyStdUnit,
									radTransEnergySelectedUnit, "ENERGY"),
							unitConversion(radTransEnergyTo,
									radTransEnergyStdUnit,
									radTransEnergySelectedUnit, "ENERGY"),
							"RadTransEnergy");
		}

		if ((radTransWavenumberFrom != null && radTransWavenumberFrom.trim()
				.length() > 0)
				|| (radTransWavenumberTo != null && radTransWavenumberTo.trim()
						.length() > 0)) {
			if (firstEntry != true) {
				xsamsQuery = xsamsQuery + " AND ";
			} else {
				firstEntry = false;
			}
			xsamsQuery = xsamsQuery
					+ getRangeQuery(
							unitConversion(radTransWavenumberFrom,
									radTransWavenumberStdUnit,
									radTransWavenumberSelectedUnit,
									"WAVENUMBER"),
							unitConversion(radTransWavenumberTo,
									radTransWavenumberStdUnit,
									radTransWavenumberSelectedUnit,
									"WAVENUMBER"), "RadTransWavenumber");
		}
*/
		if ((moleculeStateEnergyFrom != null && moleculeStateEnergyFrom.trim()
				.length() > 0)
				|| (moleculeStateEnergyTo != null && moleculeStateEnergyTo
						.trim().length() > 0)) {
			if (firstEntry != true) {
				xsamsQuery = xsamsQuery + " AND ";
			} else {
				firstEntry = false;
			}
			xsamsQuery = xsamsQuery
					+ getRangeQuery(
							unitConversion(moleculeStateEnergyFrom,
									moleculeStateEnergyStdUnit,
									moleculeStateEnergySelectedUnit, "ENERGY"),
							unitConversion(moleculeStateEnergyTo,
									moleculeStateEnergyStdUnit,
									moleculeStateEnergySelectedUnit, "ENERGY"),
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
					+ getRangeQuery(
							unitConversion(atomStateEnergyFrom,
									atomStateEnergyStdUnit,
									atomStateEnergySelectedUnit, "ENERGY"),
							unitConversion(atomStateEnergyTo,
									atomStateEnergyStdUnit,
									atomStateEnergySelectedUnit, "ENERGY"),
							"AtomStateEnergy");
		}

		if ((radTransProbabilityAFrom != null && radTransProbabilityAFrom
				.trim().length() > 0)
				|| (radTransProbabilityATo != null && radTransProbabilityATo
						.trim().length() > 0)) {
			if (firstEntry != true) {
				xsamsQuery = xsamsQuery + " AND ";
			} else {
				firstEntry = false;
			}
			xsamsQuery = xsamsQuery
					+ getRangeQuery(radTransProbabilityAFrom,
							radTransProbabilityATo, "RadTransProbabilityA");
		}

		if (radTransBroadeningDoppler == true) {
			if (firstEntry != true) {
				xsamsQuery = xsamsQuery + " AND ";
			} else {
				firstEntry = false;
			}
			xsamsQuery = xsamsQuery + " RadTransBroadeningDoppler <> NULL";
		}

		if (radTransBroadeningInstrument == true) {
			if (firstEntry != true) {
				xsamsQuery = xsamsQuery + " AND ";
			} else {
				firstEntry = false;
			}
			xsamsQuery = xsamsQuery + " RadTransBroadeningInstrument <> NULL";
		}

		if (radTransBroadeningNatural == true) {
			if (firstEntry != true) {
				xsamsQuery = xsamsQuery + " AND ";
			} else {
				firstEntry = false;
			}
			xsamsQuery = xsamsQuery + " RadTransBroadeningNatural <> NULL";
		}

		if (radTransBroadeningPressure == true) {
			if (firstEntry != true) {
				xsamsQuery = xsamsQuery + " AND ";
			} else {
				firstEntry = false;
			}
			xsamsQuery = xsamsQuery + " RadTransBroadeningPressure <> NULL";
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
		System.out.println(this.radTransFrequencyFrom);

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

	public void setRadTransBroadeningInstrument(
			boolean radTransBroadeningInstrument) {
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

	public String getRadTransFrequencyStdUnit() {
		return radTransFrequencyStdUnit;
	}

	public void setRadTransFrequencyStdUnit(String radTransFrequencyStdUnit) {
		this.radTransFrequencyStdUnit = radTransFrequencyStdUnit;
	}

	public String getRadTransFrequencySelectedUnit() {
		return radTransFrequencySelectedUnit;
	}

	public void setRadTransFrequencySelectedUnit(
			String radTransFrequencySelectedUnit) {
		this.radTransFrequencySelectedUnit = radTransFrequencySelectedUnit;
		System.out.println("this.radTransFrequencySelectedUnit: "
				+ this.radTransFrequencySelectedUnit);
	}

	public String getRadTransWavelengthStdUnit() {
		return radTransWavelengthStdUnit;
	}

	public void setRadTransWavelengthStdUnit(String radTransWavelengthStdUnit) {
		this.radTransWavelengthStdUnit = radTransWavelengthStdUnit;
	}

	public String getRadTransWavelengthSelectedUnit() {
		return radTransWavelengthSelectedUnit;
	}

	public void setRadTransWavelengthSelectedUnit(
			String radTransWavelengthSelectedUnit) {
		this.radTransWavelengthSelectedUnit = radTransWavelengthSelectedUnit;
	}

	public String getRadTransEnergyStdUnit() {
		return radTransEnergyStdUnit;
	}

	public void setRadTransEnergyStdUnit(String radTransEnergyStdUnit) {
		this.radTransEnergyStdUnit = radTransEnergyStdUnit;
	}

	public String getRadTransEnergySelectedUnit() {
		return radTransEnergySelectedUnit;
	}

	public void setRadTransEnergySelectedUnit(String radTransEnergySelectedUnit) {
		this.radTransEnergySelectedUnit = radTransEnergySelectedUnit;
	}

	public String getRadTransWavenumberStdUnit() {
		return radTransWavenumberStdUnit;
	}

	public void setRadTransWavenumberStdUnit(String radTransWavenumberStdUnit) {
		this.radTransWavenumberStdUnit = radTransWavenumberStdUnit;
	}

	public String getRadTransWavenumberSelectedUnit() {
		return radTransWavenumberSelectedUnit;
	}

	public void setRadTransWavenumberSelectedUnit(
			String radTransWavenumberSelectedUnit) {
		this.radTransWavenumberSelectedUnit = radTransWavenumberSelectedUnit;
	}

	public String getAtomStateEnergyStdUnit() {
		return atomStateEnergyStdUnit;
	}

	public void setAtomStateEnergyStdUnit(String atomStateEnergyStdUnit) {
		this.atomStateEnergyStdUnit = atomStateEnergyStdUnit;
	}

	public String getAtomStateEnergySelectedUnit() {
		return atomStateEnergySelectedUnit;
	}

	public void setAtomStateEnergySelectedUnit(
			String atomStateEnergySelectedUnit) {
		this.atomStateEnergySelectedUnit = atomStateEnergySelectedUnit;
	}

	public String getMoleculeStateEnergyStdUnit() {
		return moleculeStateEnergyStdUnit;
	}

	public void setMoleculeStateEnergyStdUnit(String moleculeStateEnergyStdUnit) {
		this.moleculeStateEnergyStdUnit = moleculeStateEnergyStdUnit;
	}

	public String getMoleculeStateEnergySelectedUnit() {
		return moleculeStateEnergySelectedUnit;
	}

	public void setMoleculeStateEnergySelectedUnit(
			String moleculeStateEnergySelectedUnit) {
		this.moleculeStateEnergySelectedUnit = moleculeStateEnergySelectedUnit;
	}

	private String getRangeQuery(String value1, String value2, String columnName) {

		if ((value1 != null && value1.trim().length() > 0)
				&& (value2 != null && value2.trim().length() > 0)) {
			// return columnName + " BETWEEN " + value1 + " AND " + value2;

			return columnName + " >= " + value1 + " AND " + columnName + " <= "
					+ value2;
		} else if (value1 != null && value1.trim().length() > 0) {
			return columnName + " >= " + value1;
		} else if (value2 != null && value2.trim().length() > 0) {
			return columnName + " <= " + value2;
		}
		return "";
	}

	private String unitConversion(String value, String stdUnit,
			String selectedUnit, String elementType) {
		double valueDouble;

		try {
			valueDouble = Double.parseDouble(value);
			if (elementType.toUpperCase().equals("WAVELENGTH")) {
				if (stdUnit.equals(selectedUnit)) {
					return value;
				} else if (selectedUnit.equals("nm")) {
					return "" + valueDouble * 10;
				} else if (selectedUnit.equals("mum")) {
					return value;
				} else if (selectedUnit.equals("mm")) {
					UnitConverter toArmstrong2 = MILLIMETER
							.getConverterTo(ANGSTROM);
					double angstrom2 = toArmstrong2.convert(Measure.valueOf(
							valueDouble, MILLIMETER).doubleValue(MILLIMETER));
					return "" + angstrom2;
				} else if (selectedUnit.equals("m")) {
					UnitConverter toArmstrong = METER.getConverterTo(ANGSTROM);
					double angstrom = toArmstrong.convert(Measure.valueOf(
							valueDouble, METER).doubleValue(METER));
					return "" + angstrom;
				}

			} else if (elementType.toUpperCase().equals("FREQUENCY")) {
				if (stdUnit.equals(selectedUnit)) {
					// frequencyToWavelength(valueDouble);
					return value;
				} else if (selectedUnit.equals("kHz")) {
					// frequencyToWavelength(valueDouble * 1e3);
					return "" + valueDouble * 1e3;
				} else if (selectedUnit.equals("MHz")) {
					// frequencyToWavelength(valueDouble * 1e6);
					return "" + valueDouble * 1e6;
				} else if (selectedUnit.equals("GHz")) {
					// frequencyToWavelength(valueDouble * 1e9);
					return "" + valueDouble * 1e9;
				} else if (selectedUnit.equals("THz")) {
					// frequencyToWavelength(valueDouble * 1e12);
					return "" + valueDouble * 1e12;

				}

			} else if (elementType.toUpperCase().equals("WAVENUMBER")) {
				if (stdUnit.equals(selectedUnit)) {
					return value;
				} else if (selectedUnit.equals("1/m")) {
					return "" + valueDouble * 100;
				}

			} else if (elementType.toUpperCase().equals("ENERGY")) {
				if (stdUnit.equals(selectedUnit)) {
					return value;
				} else if (selectedUnit.equals("J")) {
					UnitConverter toEV = JOULE.getConverterTo(ELECTRON_VOLT);
					double ev = toEV.convert(Measure
							.valueOf(valueDouble, JOULE).doubleValue(JOULE));
					return "" + ev;
				} else if (selectedUnit.equals("erg")) {
					UnitConverter toEV = ERG.getConverterTo(ELECTRON_VOLT);
					double ev = toEV.convert(Measure.valueOf(valueDouble, ERG)
							.doubleValue(ERG));
					return "" + ev;
				}
			}
		} catch (NumberFormatException exception) {
			return null;
		}
		return value;
	}

	private double frequencyToWavelength(double frequencyInHertz) {
		double wavelengthInMeter = speedOfLightInMeter / frequencyInHertz;

		UnitConverter toArmstrong = METER.getConverterTo(ANGSTROM);
		double wavelengthInAngstrom = toArmstrong.convert(Measure.valueOf(
				wavelengthInMeter, METER).doubleValue(METER));
		return wavelengthInAngstrom;
	}

	private double frequencyToWavenumber(double frequencyInHertz) {
		double wavenumber = frequencyInHertz / speedOfLightInCM;
		return wavenumber;
	}

	private double frequencyToEnergy(double frequencyInHertz) {
		double energy = frequencyInHertz * planksConstantIneVs;
		return energy;
	}

	private double energyToFrequency(double energyIneV) {
		double frequency = energyIneV / planksConstantIneVs;
		return frequency;
	}

	private double energyToWavelength(double energyIneV) {
		double frequency = energyToFrequency(energyIneV);
		double wavelength = frequencyToWavelength(frequency);
		return wavelength;
	}

	private double energyToWavenumber(double energyIneV) {
		double frequency = energyToFrequency(energyIneV);
		double wavenumber = frequencyToWavenumber(frequency);
		return wavenumber;
	}

	private double wavenumberToFrequency(double wavenumberInCM) {
		double frequency = wavenumberInCM * speedOfLightInCM;
		return frequency;
	}

	private double wavenumberToWavelength(double wavenumberInCM) {
		double frequency = wavenumberToFrequency(wavenumberInCM);
		double wavelength = frequencyToWavelength(frequency);
		return wavelength;
	}

	private double wavenumberToEnergy(double wavenumberInCM) {
		double frequency = wavenumberToFrequency(wavenumberInCM);
		double energy = frequencyToEnergy(frequency);
		return energy;
	}

	public void frequncyConverterFrom() {
		if ((radTransFrequencyFrom != null && radTransFrequencyFrom.trim()
				.length() > 0)) {
			String frequencyStringInHertz = unitConversion(
					radTransFrequencyFrom, radTransFrequencyStdUnit,
					radTransFrequencySelectedUnit, "FREQUENCY");

			double frequencyInHertz = Double
					.parseDouble(frequencyStringInHertz);
			
			String wavelength = "" + frequencyToWavelength(frequencyInHertz);
			this.radTransWavelengthFrom = wavelength;

			String wavenumber = "" + frequencyToWavenumber(frequencyInHertz);
			this.radTransWavenumberFrom = wavenumber;

			String energy = "" + frequencyToEnergy(frequencyInHertz);
			radTransEnergyFrom = energy;

			radTransFrequencyFrom = frequencyStringInHertz;
		}
	}

	public void frequncyConverterTo() {
		if ((radTransFrequencyTo != null && radTransFrequencyTo.trim().length() > 0)) {
			String frequencyStringInHertz = unitConversion(radTransFrequencyTo,
					radTransFrequencyStdUnit, radTransFrequencySelectedUnit,
					"FREQUENCY");

			double frequencyInHertz = Double
					.parseDouble(frequencyStringInHertz);

			String wavelength = "" + frequencyToWavelength(frequencyInHertz);
			this.radTransWavelengthTo = wavelength;

			String wavenumber = "" + frequencyToWavenumber(frequencyInHertz);
			this.radTransWavenumberTo = wavenumber;

			String energy = "" + frequencyToEnergy(frequencyInHertz);
			radTransEnergyTo = energy;

			radTransFrequencyTo = frequencyStringInHertz;
		}
	}

	public void energyConverterFrom() {
		if ((radTransEnergyFrom != null && radTransEnergyFrom.trim().length() > 0)) {
			String energyStringIneV = unitConversion(radTransEnergyFrom,
					radTransEnergyStdUnit, radTransEnergySelectedUnit, "ENERGY");

			double energyIneV = Double.parseDouble(energyStringIneV);

			String wavelength = "" + energyToWavelength(energyIneV);
			this.radTransWavelengthFrom = wavelength;

			String frequency = "" + energyToFrequency(energyIneV);
			this.radTransFrequencyFrom = frequency;

			String wavenumber = "" + energyToWavenumber(energyIneV);
			radTransWavenumberFrom = wavenumber;

			radTransEnergyFrom = energyStringIneV;
		}
	}

	public void energyConverterTo() {
		if ((radTransEnergyTo != null && radTransEnergyTo.trim().length() > 0)) {
			String energyStringIneV = unitConversion(radTransEnergyTo,
					radTransEnergyStdUnit, radTransEnergySelectedUnit, "ENERGY");

			double energyIneV = Double.parseDouble(energyStringIneV);

			String wavelength = "" + energyToWavelength(energyIneV);
			this.radTransWavelengthTo = wavelength;

			String frequency = "" + energyToFrequency(energyIneV);
			this.radTransFrequencyTo = frequency;

			String wavenumber = "" + energyToWavenumber(energyIneV);
			radTransWavenumberTo = wavenumber;

			radTransEnergyTo = energyStringIneV;
		}
	}

	public void wavenumberConverterFrom() {
		if ((radTransWavenumberFrom != null && radTransWavenumberFrom.trim()
				.length() > 0)) {
			String wavenumberStringInCM = unitConversion(
					radTransWavenumberFrom, radTransWavenumberStdUnit,
					radTransWavenumberSelectedUnit, "WAVENUMBER");

			double wavenumberInCM = Double.parseDouble(wavenumberStringInCM);

			String wavelength = "" + wavenumberToWavelength(wavenumberInCM);
			this.radTransWavelengthFrom = wavelength;

			String frequency = "" + wavenumberToFrequency(wavenumberInCM);
			this.radTransFrequencyFrom = frequency;

			String energy = "" + wavenumberToEnergy(wavenumberInCM);
			radTransEnergyFrom = energy;

			radTransWavenumberFrom = "" + wavenumberInCM;
		}
	}

	public void wavenumberConverterTo() {
		if ((radTransWavenumberTo != null && radTransWavenumberTo.trim()
				.length() > 0)) {

			String wavenumberStringInCM = unitConversion(radTransWavenumberTo,
					radTransWavenumberStdUnit, radTransWavenumberSelectedUnit,
					"WAVENUMBER");

			double wavenumberInCM = Double.parseDouble(wavenumberStringInCM);

			String wavelength = "" + wavenumberToWavelength(wavenumberInCM);
			this.radTransWavelengthTo = wavelength;

			String frequency = "" + wavenumberToFrequency(wavenumberInCM);
			this.radTransFrequencyTo = frequency;

			String energy = "" + wavenumberToEnergy(wavenumberInCM);
			radTransEnergyTo = energy;

			radTransWavenumberTo = "" + wavenumberInCM;
		}
	}

	/*
	public void wavelengthUnitChange() {
		wavenumberConverterTo();
		wavenumberConverterFrom();
	}
*/
	public void frequencyUnitChange() {
		frequncyConverterTo();
		frequncyConverterFrom();
	}

	public void wavenumberUnitChange() {
		wavenumberConverterTo();
		wavenumberConverterFrom();
	}

	public void energyUnitChange() {
		energyConverterTo();
		energyConverterFrom();
	}
}