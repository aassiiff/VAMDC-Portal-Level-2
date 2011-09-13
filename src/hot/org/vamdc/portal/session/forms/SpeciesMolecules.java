package org.vamdc.portal.session.forms;

/*
 import java.awt.GraphicsConfiguration;
 import java.awt.GraphicsDevice;
 import java.awt.GraphicsEnvironment;
 import java.awt.Image;
 import java.awt.Transparency;
 import javax.swing.ImageIcon;
 import java.io.InputStream;
 import java.io.OutputStream;
 import org.jboss.seam.annotations.In;
 */

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

import java.io.InputStreamReader;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.jboss.seam.ScopeType;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import uk.ac.cam.ast.vamdc.portal.entity.Isotopologues;
import uk.ac.cam.ast.vamdc.portal.entity.MoleculeNames;
import uk.ac.cam.ast.vamdc.portal.entity.Molecules;
import uk.ac.cam.ast.vamdc.portal.session.IsotopologuesHome;
import uk.ac.cam.ast.vamdc.portal.session.MoleculeNamesHome;
import uk.ac.cam.ast.vamdc.portal.session.MoleculesHome;

@Name("speciesMolecules")
@Scope(ScopeType.SESSION)
public class SpeciesMolecules {

	/*
	 * @In(create = true) private RegistryBrowser registryBrowser;
	 */

	private String moleculeChemicalName = null;
	private String moleculeInchiKey = null;
	private String moleculeInchi = null;
	private String moleculeStoichiometricFormula = null;

	private boolean moleculeChemicalNameBoolean = true;
	private boolean moleculeInchiKeyBoolean = true;
	private boolean moleculeInchiBoolean = true;
	private boolean moleculeOrdinaryStructuralFormulaBoolean = true;

	// private boolean chemicalNameImage = false;

	// Molecule Ion Charge
	private String moleculeIonChargeFrom = null;
	private String moleculeIonChargeTo = null;

	// Molecule Protonation
	private String moleculeProtonationFrom = null;
	private String moleculeProtonationTo = null;

	private boolean editable = true;

	private String fixedURlForImage = "http://localhost:8080/vamdcstatic/images/";

	private String imageFromChemicalName = fixedURlForImage + "false.jpeg";
	// private String imageFromInChI = fixedURlForImage + "false.jpeg";
	// private String imageFromInChIKey = fixedURlForImage + "false.jpeg";

	private List<Isotopologues> isotopologuesList = new ArrayList<Isotopologues>();

	private List<String> selectedIsotopesFromCheckBox = new ArrayList<String>();
	private List<String> selectedIsotopesFromCheckBox2 = new ArrayList<String>();

	public void toggleEditable() {
		editable = !editable;
	}

	public void clearFields() {
		moleculeChemicalName = "";
		moleculeInchiKey = "";
		moleculeInchi = "";
		moleculeStoichiometricFormula = "";
		moleculeIonChargeFrom = "";
		moleculeIonChargeTo = "";
		moleculeProtonationFrom = "";
		moleculeProtonationTo = "";

		moleculeChemicalNameBoolean = true;
		moleculeInchiBoolean = true;
		moleculeInchiKeyBoolean = true;
		moleculeOrdinaryStructuralFormulaBoolean = true;

		imageFromChemicalName = fixedURlForImage + "false.jpeg";

		isotopologuesList = new ArrayList<Isotopologues>();
		selectedIsotopesFromCheckBox = new ArrayList<String>();
		selectedIsotopesFromCheckBox2 = new ArrayList<String>();
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
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

	public String getMoleculeStoichiometricFormula() {
		return moleculeStoichiometricFormula;
	}

	public void setMoleculeStoichiometricFormula(
			String moleculeStoichiometricFormula) {
		this.moleculeStoichiometricFormula = moleculeStoichiometricFormula;
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

	public List<Isotopologues> getIsotopologuesList() {
		return isotopologuesList;
	}

	public void setIsotopologuesList(List<Isotopologues> isotopologuesList) {
		this.isotopologuesList = isotopologuesList;
	}

	public List<String> getSelectedIsotopesFromCheckBox() {
		return selectedIsotopesFromCheckBox;
	}

	public boolean isMoleculeChemicalNameBoolean() {
		return (moleculeChemicalNameBoolean && this.editable);
	}

	public void setMoleculeChemicalNameBoolean(
			boolean moleculeChemicalNameBoolean) {
		this.moleculeChemicalNameBoolean = moleculeChemicalNameBoolean;
	}

	public boolean isMoleculeInchiKeyBoolean() {
		return (moleculeInchiKeyBoolean && this.editable);
	}

	public void setMoleculeInchiKeyBoolean(boolean moleculeInchiKeyBoolean) {
		this.moleculeInchiKeyBoolean = moleculeInchiKeyBoolean;
	}

	public boolean isMoleculeInchiBoolean() {
		return (moleculeInchiBoolean && this.editable);
	}

	public void setMoleculeInchiBoolean(boolean moleculeInchiBoolean) {
		this.moleculeInchiBoolean = moleculeInchiBoolean;
	}

	public boolean isMoleculeOrdinaryStructuralFormulaBoolean() {
		System.out.println("isMoleculeOrdinaryStructuralFormulaBoolean: "
				+ (moleculeOrdinaryStructuralFormulaBoolean && this.editable));
		return (moleculeOrdinaryStructuralFormulaBoolean && this.editable);
	}

	public void setMoleculeOrdinaryStructuralFormulaBoolean(
			boolean moleculeOrdinaryStructuralFormulaBoolean) {
		this.moleculeOrdinaryStructuralFormulaBoolean = moleculeOrdinaryStructuralFormulaBoolean;
	}

	public void setSelectedIsotopesFromCheckBox(
			List<String> selectedIsotopesFromCheckBox) {
		System.out.println("selectedIsotopesFromCheckBox: "
				+ selectedIsotopesFromCheckBox.size()
				+ "   selectedIsotopesFromCheckBox2: "
				+ selectedIsotopesFromCheckBox2.size());

		for (int i = 0; i < selectedIsotopesFromCheckBox.size(); i++) {
			System.out.println("selectedIsotopesFromCheckBox: "
					+ selectedIsotopesFromCheckBox.get(i) + ": "
					+ selectedIsotopesFromCheckBox.size());
			// Due to table each selected check box is sent separately
			// The last check box overwrite previous values
			// Created another check box to overcome this issue.
			/**/
			if (!this.selectedIsotopesFromCheckBox2
					.contains(selectedIsotopesFromCheckBox.get(0))) {
				this.selectedIsotopesFromCheckBox2
						.add(selectedIsotopesFromCheckBox.get(0));
			}
		}

		this.selectedIsotopesFromCheckBox = selectedIsotopesFromCheckBox2;
	}

	/*
	 * public String getImageFromInChI() { return imageFromInChI; }
	 * 
	 * public void setImageFromInChI(String imageFromInChI) {
	 * this.imageFromInChI = imageFromInChI; }
	 * 
	 * public String getImageFromInChIKey() { return imageFromInChIKey; }
	 * 
	 * public void setImageFromInChIKey(String imageFromInChIKey) {
	 * this.imageFromInChIKey = imageFromInChIKey; }
	 */
	public void setImageFromChemicalName(String imageFromChemicalName) {
		this.imageFromChemicalName = imageFromChemicalName;
	}

	public String getImageFromChemicalName() {
		return imageFromChemicalName;
	}

	public String getQueryString() {
		System.out.println("Species Molecules get Query String: "
				+ selectedIsotopesFromCheckBox.size() + " "
				+ selectedIsotopesFromCheckBox2.size());
		String xsamsQuery = "";

		boolean firstEntry = true;

		if (moleculeChemicalName != null
				&& moleculeChemicalName.trim().length() > 0) {
			if (isotopologuesList.size() == 0) {
				if (firstEntry != true) {
					xsamsQuery = xsamsQuery + " AND ";
				} else {
					firstEntry = false;
				}
				xsamsQuery = xsamsQuery + " MoleculeChemicalName = '"
						+ moleculeChemicalName + "'";

			} else {
				for (int i = 0; i < selectedIsotopesFromCheckBox2.size(); i++) {
					if (i > 0)
						xsamsQuery = xsamsQuery + " OR ";
					xsamsQuery = xsamsQuery + " MoleculeInchiKey='"
							+ selectedIsotopesFromCheckBox2.get(i) + "'";
				}
			}

		}

		if (moleculeStoichiometricFormula != null
				&& moleculeStoichiometricFormula.trim().length() > 0) {
			if (isotopologuesList.size() == 0) {
				if (firstEntry != true) {
					xsamsQuery = xsamsQuery + " AND ";
				} else {
					firstEntry = false;
				}
				xsamsQuery = xsamsQuery + " MoleculeStoichiometricFormula = '"
						+ moleculeStoichiometricFormula + "'";
			} else {
				for (int i = 0; i < selectedIsotopesFromCheckBox2.size(); i++) {
					if (i > 0)
						xsamsQuery = xsamsQuery + " OR ";
					xsamsQuery = xsamsQuery + " MoleculeInchiKey='"
							+ selectedIsotopesFromCheckBox2.get(i) + "'";
				}
			}
		}

		if (moleculeInchiKey != null && moleculeInchiKey.trim().length() > 0) {
			if (firstEntry != true) {
				xsamsQuery = xsamsQuery + " AND ";
			} else {
				firstEntry = false;
			}
			// xsamsQuery = xsamsQuery + " MoleculeInchiKey LIKE '%" +
			// moleculeInchiKey + "%'";
			xsamsQuery = xsamsQuery + " MoleculeInchiKey='" + moleculeInchiKey
					+ "'";
		} else {
			/*
			 * for (int i = 0; i < selectedIsotopesFromCheckBox2.size(); i++) {
			 * xsamsQuery = xsamsQuery + " OR "; xsamsQuery = xsamsQuery +
			 * " MoleculeInchiKey='" + selectedIsotopesFromCheckBox2.get(i) +
			 * "'"; }
			 */
		}

		if (moleculeInchi != null && moleculeInchi.trim().length() > 0) {
			if (firstEntry != true) {
				xsamsQuery = xsamsQuery + " AND ";
			} else {
				firstEntry = false;
			}
			// xsamsQuery = xsamsQuery + " MoleculeInchi LIKE '%" +
			// moleculeInchi + "%'";
			xsamsQuery = xsamsQuery + " MoleculeInchi='" + moleculeInchi + "'";
		}

		selectedIsotopesFromCheckBox = selectedIsotopesFromCheckBox2;
		selectedIsotopesFromCheckBox2 = new ArrayList<String>();
		// System.out.println(xsamsQuery);
		// selectedIsotopesFromCheckBox.add("AfterPreview");
		return xsamsQuery;
	}

	/*
	 * It is Hack only to clear the check Box after Refine Query is clicked.
	 */
	public void emptySelectedIsotopesFromCheckBox2() {
		selectedIsotopesFromCheckBox2 = new ArrayList<String>();
	}

	public void getInChI(String value) {
		System.out.println("getInChIFromChemcialName()");
		String urlString = "http://cactus.nci.nih.gov/chemical/structure/";
		urlString = urlString + value + "/stdinchi";
		try {
			// Create a URL for the desired page
			URL url = new URL(urlString);

			// Read all the text returned by the server
			BufferedReader in = new BufferedReader(new InputStreamReader(
					url.openStream()));
			String inchiString = "";
			String str;
			while ((str = in.readLine()) != null) {
				// str is one line of text; readLine() strips the newline
				// character(s)
				inchiString = str;
			}
			in.close();
			int index = inchiString.lastIndexOf("=");
			inchiString = inchiString.substring(index + 1);
			moleculeInchi = inchiString;
			System.out.println("moleculeInchi: " + moleculeInchi);
		} catch (MalformedURLException e) {
		} catch (IOException e) {
		}
	}

	public void getInChIKey(String value) {
		System.out.println("getInChIKeyFromChemcialName()");
		String urlString = "http://cactus.nci.nih.gov/chemical/structure/";
		urlString = urlString + value + "/stdinchikey";
		try {
			// Create a URL for the desired page
			URL url = new URL(urlString);

			// Read all the text returned by the server
			BufferedReader in = new BufferedReader(new InputStreamReader(
					url.openStream()));
			String inchiKeyString = "";
			String str;
			while ((str = in.readLine()) != null) {
				// str is one line of text; readLine() strips the newline
				// character(s)
				inchiKeyString = str;
				System.out.println(str);
			}
			int index = inchiKeyString.lastIndexOf("=");
			inchiKeyString = inchiKeyString.substring(index + 1);
			moleculeInchiKey = inchiKeyString;
			System.out.println("moleculeInchiKey: " + moleculeInchiKey);
			in.close();
		} catch (MalformedURLException e) {
		} catch (IOException e) {
		}
	}

	public void getMoleculeFormula(String value) {
		System.out.println("getInChIKeyFromChemcialName()");
		String urlString = "http://cactus.nci.nih.gov/chemical/structure/";
		urlString = urlString + value + "/formula";
		try {
			// Create a URL for the desired page
			URL url = new URL(urlString);

			// Read all the text returned by the server
			BufferedReader in = new BufferedReader(new InputStreamReader(
					url.openStream()));
			String moleculeFormulaString = "";
			String str;
			while ((str = in.readLine()) != null) {
				// str is one line of text; readLine() strips the newline
				// character(s)
				moleculeFormulaString = str;
				System.out.println(str);
			}
			// int index = inchiKeyString.lastIndexOf("=");
			// inchiKeyString = inchiKeyString.substring(index + 1);
			moleculeStoichiometricFormula = moleculeFormulaString;
			System.out.println("moleculeInchiKey: " + moleculeInchiKey);
			in.close();
		} catch (MalformedURLException e) {
		} catch (IOException e) {
		}
	}

	public void getImageFromChemicalNameFunction(/* OutputStream out, Object data */) {
		System.out.println("getImageFromChemicalName()");
		imageFromChemicalName = fixedURlForImage + "false.jpeg";

		BufferedImage image = null;
		String urlString = "http://cactus.nci.nih.gov/chemical/structure/";
		urlString = urlString + moleculeChemicalName + "/image?columns=1";
		try {
			URL url = new URL(urlString);
			if (moleculeChemicalName != null
					&& moleculeChemicalName.trim().length() > 0) {
				getInChI(moleculeChemicalName);
				getInChIKey(moleculeChemicalName);
				getMoleculeFormula(moleculeChemicalName);
				image = ImageIO.read(url);
				// ImageIO.write(image, "jpeg", out);

				System.out.println(image.getHeight() + "  " + image.getWidth());

				int cropHeight = (int) (image.getHeight() * 0.50);
				int cropWidth = (int) (image.getWidth() * 0.50);
				int cropStartX = (int) (image.getWidth() * 0.25);
				int cropStartY = (int) (image.getHeight() * 0.22);
				BufferedImage imageClipped = image.getSubimage(cropStartX,
						cropStartY, cropWidth, cropHeight);

				File outputfile = new File(
						"/opt/queryResults/server/default/deploy/vamdcstatic.war/images/"
								+ moleculeChemicalName + ".jpeg");
				ImageIO.write(imageClipped, "jpeg", outputfile);
				imageFromChemicalName = fixedURlForImage + moleculeChemicalName
						+ ".jpeg";
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getImageFromInchiFunction(/* OutputStream out, Object data */) {
		System.out.println("getImageFromChemicalName()");
		imageFromChemicalName = fixedURlForImage + "false.jpeg";

		BufferedImage image = null;
		String urlString = "http://cactus.nci.nih.gov/chemical/structure/";

		try {

			if (moleculeInchi != null && moleculeInchi.trim().length() > 0) {
				if (!moleculeInchi.startsWith("InChI=")) {
					moleculeInchi = "InChI=" + moleculeInchi;
				}
				/*
				 * This is working ...
				 */
				urlString = urlString + moleculeInchi + "/image?columns=1";
				URL url = new URL(urlString);
				System.out.println(urlString);

				getMoleculeFormula(moleculeInchi);
				getInChIKey(moleculeInchi);

				// Better to use moleculeInchiKey
				// Not good logic ... can be improved further
				// urlString = urlString + moleculeInchiKey +
				// "/image?columns=1";
				// System.out.println(urlString);
				image = ImageIO.read(url);
				// ImageIO.write(image, "jpeg", out);

				System.out.println(image.getHeight() + "  " + image.getWidth());

				int cropHeight = (int) (image.getHeight() * 0.50);
				int cropWidth = (int) (image.getWidth() * 0.50);
				int cropStartX = (int) (image.getWidth() * 0.25);
				int cropStartY = (int) (image.getHeight() * 0.22);
				BufferedImage imageClipped = image.getSubimage(cropStartX,
						cropStartY, cropWidth, cropHeight);

				File outputfile = new File(
						"/opt/queryResults/server/default/deploy/vamdcstatic.war/images/"
								+ moleculeStoichiometricFormula + ".jpeg");
				ImageIO.write(imageClipped, "jpeg", outputfile);
				imageFromChemicalName = fixedURlForImage
						+ moleculeStoichiometricFormula + ".jpeg";
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void getImageFromInchiKeyFunction(/* OutputStream out, Object data */) {
		System.out.println("getImageFromChemicalName()");
		imageFromChemicalName = fixedURlForImage + "false.jpeg";

		BufferedImage image = null;
		String urlString = "http://cactus.nci.nih.gov/chemical/structure/";
		urlString = urlString + moleculeInchiKey + "/image?columns=3";
		try {
			URL url = new URL(urlString);
			if (moleculeInchiKey != null
					&& moleculeInchiKey.trim().length() > 0) {
				getInChI(moleculeInchiKey);
				getMoleculeFormula(moleculeInchiKey);
				image = ImageIO.read(url);
				// ImageIO.write(image, "jpeg", out);

				System.out.println(image.getHeight() + "  " + image.getWidth());
				/*
				 * int cropHeight = (int)(image.getHeight() * 0.50); int
				 * cropWidth = (int)(image.getWidth() * 0.50); int cropStartX =
				 * (int)(image.getWidth() * 0.25); int cropStartY =
				 * (int)(image.getHeight() * 0.22); BufferedImage imageClipped =
				 * image.getSubimage(cropStartX, cropStartY, cropWidth,
				 * cropHeight);
				 */

				File outputfile = new File(
						"/opt/queryResults/server/default/deploy/vamdcstatic.war/images/"
								+ moleculeStoichiometricFormula + ".jpeg");
				ImageIO.write(image, "jpeg", outputfile);
				imageFromChemicalName = fixedURlForImage
						+ moleculeStoichiometricFormula + ".jpeg";
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public List<MoleculeNames> getMoleculeNameQueryWildcard(Object suggest) {
		String pref = (String) suggest;
		List<MoleculeNames> moleculeNamesList = new MoleculeNamesHome()
				.findByMolecNameWildcard(pref);
		return moleculeNamesList;

	}
	
	public List<Molecules> getStoichiometricFormulaQueryWildcard(Object suggest) {		
		String pref = (String) suggest;
		//System.out.println("getStoichiometricFormulaQueryWildcard: " + pref);
		List<Molecules> moleculeNamesList = new MoleculesHome().findByStoichiometricFormulaWildcard(pref);
		return moleculeNamesList;
	}

	public void getMoleculeNameQuery() {
		System.out.println("getMoleculeNameQuery");
		if (moleculeChemicalName != null
				&& moleculeChemicalName.trim().length() > 0) {
			// moleculeOrdinaryStructuralFormulaBoolean = false;
			selectedIsotopesFromCheckBox = new ArrayList<String>();
			selectedIsotopesFromCheckBox2 = new ArrayList<String>();
			isotopologuesList = new ArrayList<Isotopologues>();
			List<MoleculeNames> moleculeNamesList = new MoleculeNamesHome()
					.findByMolecName(moleculeChemicalName);
			if (moleculeNamesList.size() > 0) {
				System.out.println(moleculeNamesList.get(0).getMolecName()
						+ "  " + moleculeNamesList.get(0).getMolecId());
				isotopologuesList = new IsotopologuesHome()
						.findByMolecName(moleculeNamesList.get(0).getMolecId());
				if (isotopologuesList.size() > 0) {
					for (int i = 0; i < isotopologuesList.size(); i++) {
						//selectedIsotopesFromCheckBox.add(isotopologuesList.get(i).getInChIkey());
					}
					System.out.println(isotopologuesList.size() + "  "
							+ isotopologuesList.get(0).getInChIkey());
				}
			}
		} else {
			// moleculeOrdinaryStructuralFormulaBoolean = true;
		}
	}

	public void getStoichiometricFormulaQuery() {
		System.out.println("getStoichiometricFormulaQuery");
		selectedIsotopesFromCheckBox = new ArrayList<String>();
		selectedIsotopesFromCheckBox2 = new ArrayList<String>();
		isotopologuesList = new ArrayList<Isotopologues>();

		List<Molecules> moleculesList = new MoleculesHome()
				.findByStoichiometricFormula(moleculeStoichiometricFormula);
		if (moleculesList.size() > 0) {
			System.out.println(moleculesList.get(0).getMolecName() + "  "
					+ moleculesList.get(0).getId());
			isotopologuesList = new IsotopologuesHome()
					.findByMolecName(moleculesList.get(0).getId());
			if (isotopologuesList.size() > 0) {
				for (int i = 0; i < isotopologuesList.size(); i++) {
					// selectedIsotopesFromCheckBox.add(isotopologuesList.get(i).getInChIkey());
				}
				System.out.println(isotopologuesList.size() + "  "
						+ isotopologuesList.get(0).getInChIkey());
			}
		}
	}

	public void disableIndividualFieldsOnFocus(String fieldName) {
		System.out.println("disableIndividualFieldsOnFocus");
		if (fieldName.equalsIgnoreCase("moleculeChemicalName")) {

			moleculeOrdinaryStructuralFormulaBoolean = false;
			// moleculeOrdinaryStructuralFormula = "disabled";

			moleculeInchiKeyBoolean = false;
			// moleculeInchiKey = "disabled";

			moleculeInchiBoolean = false;
			// moleculeInchi = "disabled";

		} else if (fieldName.equalsIgnoreCase("moleculeStoichiometricFormula")) {
			moleculeChemicalNameBoolean = false;
			moleculeInchiKeyBoolean = false;
			moleculeInchiBoolean = false;

		} else if (fieldName.equalsIgnoreCase("moleculeInchi")) {
			moleculeOrdinaryStructuralFormulaBoolean = false;
			moleculeChemicalNameBoolean = false;
			moleculeInchiKeyBoolean = false;
		} else if (fieldName.equalsIgnoreCase("moleculeInchiKey")) {
			moleculeOrdinaryStructuralFormulaBoolean = false;
			moleculeChemicalNameBoolean = false;
			moleculeInchiBoolean = false;
		}
	}

	public void disableIndividualFieldsOnBlur(String fieldName) {
		System.out.println("disableIndividualFieldsOnBlur");
		if (fieldName.equalsIgnoreCase("moleculeChemicalName")) {
			if (moleculeChemicalName != null
					&& moleculeChemicalName.trim().length() > 0) {
				moleculeOrdinaryStructuralFormulaBoolean = false;
				moleculeInchiKeyBoolean = false;
				moleculeInchiBoolean = false;
				getMoleculeNameQuery();
			} else {
				this.moleculeStoichiometricFormula = "";
				moleculeOrdinaryStructuralFormulaBoolean = true;

				moleculeInchiKey = "";
				moleculeInchiKeyBoolean = true;

				moleculeInchi = "";
				moleculeInchiBoolean = true;

				selectedIsotopesFromCheckBox = new ArrayList<String>();
				selectedIsotopesFromCheckBox2 = new ArrayList<String>();
				isotopologuesList = new ArrayList<Isotopologues>();
			}
		} else if (fieldName.equalsIgnoreCase("moleculeStoichiometricFormula")) {
			if (moleculeStoichiometricFormula != null
					&& moleculeStoichiometricFormula.trim().length() > 0) {
				getStoichiometricFormulaQuery();
			} else {
				moleculeChemicalNameBoolean = true;
				moleculeInchiKeyBoolean = true;
				moleculeInchiBoolean = true;

				selectedIsotopesFromCheckBox = new ArrayList<String>();
				selectedIsotopesFromCheckBox2 = new ArrayList<String>();
				isotopologuesList = new ArrayList<Isotopologues>();
			}

		} else if (fieldName.equalsIgnoreCase("moleculeInchi")) {
			if (moleculeInchi != null && moleculeInchi.trim().length() > 0) {

			} else {
				moleculeChemicalNameBoolean = true;
				moleculeInchiKeyBoolean = true;
				moleculeOrdinaryStructuralFormulaBoolean = true;
			}

		} else if (fieldName.equalsIgnoreCase("moleculeInchiKey")) {
			if (moleculeInchiKey != null
					&& moleculeInchiKey.trim().length() > 0) {

			} else {
				moleculeChemicalNameBoolean = true;
				moleculeInchiBoolean = true;
				moleculeOrdinaryStructuralFormulaBoolean = true;
			}
		}
	}
	
	private boolean selectAllBoolean = true;
	
	
	public boolean isSelectAllBoolean() {
		return selectAllBoolean;
	}

	public void setSelectAllBoolean(boolean selectAllBoolean) {
		this.selectAllBoolean = selectAllBoolean;
	}

	public void selectAllAction(){
		selectedIsotopesFromCheckBox = new ArrayList<String>();
		selectedIsotopesFromCheckBox2 = new ArrayList<String>();
		for (int i = 0; i < isotopologuesList.size(); i++) {
			selectedIsotopesFromCheckBox.add(isotopologuesList.get(i).getInChIkey());
		}
		selectAllBoolean = false;
	}
	
	public void deSelectAllAction(){
		selectedIsotopesFromCheckBox = new ArrayList<String>();
		selectedIsotopesFromCheckBox2 = new ArrayList<String>();		
		selectAllBoolean = true;
	}
	
	private String getRangeQuery(String value1, String value2, String columnName) {

		if ((value1 != null && value1.trim().length() > 0)
				&& (value2 != null && value2.trim().length() > 0)) {
			// return columnName + " BETWEEN " + value1 + " AND " + value2;
			
			try {
				if (Double.parseDouble(value1) < Double.parseDouble(value2)) {
					return columnName + " >= " + value1 + " AND " + columnName
							+ " <= " + value2;
				} else {
					return columnName + " >= " + value2 + " AND " + columnName
							+ " <= " + value1;
				}
			} catch (Exception e) {

			}

			return columnName + " >= " + value1 + " AND " + columnName + " <= "
					+ value2;
		} else if (value1 != null && value1.trim().length() > 0) {
			return columnName + " >= " + value1;
		} else if (value2 != null && value2.trim().length() > 0) {
			return columnName + " <= " + value2;
		}
		return "";
	}
}
