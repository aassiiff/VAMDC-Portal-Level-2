package org.domain.vamdcportallevel2.session;

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
import uk.ac.cam.ast.vamdc.portal.session.IsotopologuesHome;
import uk.ac.cam.ast.vamdc.portal.session.MoleculeNamesHome;

@Name("speciesMolecules")
@Scope(ScopeType.SESSION)
public class SpeciesMolecules {
	
	/*
	@In(create = true)
	private RegistryBrowser registryBrowser;
	*/
	
	private String moleculeChemicalName = null;	
	private String moleculeInchiKey = null;
	private String moleculeInchi = null;
	private String moleculeOrdinaryStructuralFormula = null;
	
	//private boolean chemicalNameImage = false;
	
	// Molecule Ion Charge
	private String moleculeIonChargeFrom = null;
	private String moleculeIonChargeTo = null;
	
	// Molecule Ion Charge
	private String moleculeProtonationFrom = null;
	private String moleculeProtonationTo = null;
	
	private boolean editable = true;
	
	private String fixedURlForImage = "http://localhost:8080/vamdcstatic/images/";
	
	private String imageFromChemicalName = fixedURlForImage + "false.jpeg";
	//private String imageFromInChI = fixedURlForImage + "false.jpeg";
	//private String imageFromInChIKey = fixedURlForImage + "false.jpeg";
	
	private List<Isotopologues> isotopologuesList = new ArrayList<Isotopologues>();
	
	public void toggleEditable() {
		editable = !editable;
	}
	
	public void clearFields(){
		moleculeChemicalName = "";
		moleculeInchiKey = "";
		moleculeInchi = "";
		moleculeOrdinaryStructuralFormula = "";
		moleculeIonChargeFrom = "";
		moleculeIonChargeTo = "";
		moleculeProtonationFrom = "";
		moleculeProtonationTo = "";
		imageFromChemicalName = fixedURlForImage + "false.jpeg";
		
		isotopologuesList = new ArrayList<Isotopologues>();
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
	
	public List<Isotopologues> getIsotopologuesList() {
		return isotopologuesList;
	}

	public void setIsotopologuesList(List<Isotopologues> isotopologuesList) {
		this.isotopologuesList = isotopologuesList;
	}

	/*
	public String getImageFromInChI() {
		return imageFromInChI;
	}

	public void setImageFromInChI(String imageFromInChI) {
		this.imageFromInChI = imageFromInChI;
	}

	public String getImageFromInChIKey() {
		return imageFromInChIKey;
	}

	public void setImageFromInChIKey(String imageFromInChIKey) {
		this.imageFromInChIKey = imageFromInChIKey;
	}
*/
	public void setImageFromChemicalName(String imageFromChemicalName) {
		this.imageFromChemicalName = imageFromChemicalName;
	}
	public String getImageFromChemicalName() {
		 return imageFromChemicalName;
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
	
	public void getInChI(String value){
		System.out.println("getInChIFromChemcialName()");
		String urlString = "http://cactus.nci.nih.gov/chemical/structure/";
		urlString = urlString + value + "/stdinchi";
		try {
		    // Create a URL for the desired page
		    URL url = new URL(urlString);

		    // Read all the text returned by the server
		    BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
		    String inchiString = "";
		    String str;
		    while ((str = in.readLine()) != null) {
		        // str is one line of text; readLine() strips the newline character(s)
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
	
	public void getInChIKey(String value){
		System.out.println("getInChIKeyFromChemcialName()");
		String urlString = "http://cactus.nci.nih.gov/chemical/structure/";
		urlString = urlString + value + "/stdinchikey";
		try {
		    // Create a URL for the desired page
		    URL url = new URL(urlString);

		    // Read all the text returned by the server
		    BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
		    String inchiKeyString = "";
		    String str;
		    while ((str = in.readLine()) != null) {
		        // str is one line of text; readLine() strips the newline character(s)
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
	
	public void getMoleculeFormula(String value){
		System.out.println("getInChIKeyFromChemcialName()");
		String urlString = "http://cactus.nci.nih.gov/chemical/structure/";
		urlString = urlString + value + "/formula";
		try {
		    // Create a URL for the desired page
		    URL url = new URL(urlString);

		    // Read all the text returned by the server
		    BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
		    String moleculeFormulaString = "";
		    String str;
		    while ((str = in.readLine()) != null) {
		        // str is one line of text; readLine() strips the newline character(s)
		    	moleculeFormulaString = str;
		    	System.out.println(str);
		    }
		    //int index = inchiKeyString.lastIndexOf("=");
		    //inchiKeyString = inchiKeyString.substring(index + 1);
		    moleculeOrdinaryStructuralFormula = moleculeFormulaString;
		    System.out.println("moleculeInchiKey: " + moleculeInchiKey);
		    in.close();
		} catch (MalformedURLException e) {
		} catch (IOException e) {
		}
	}
	
	public void getImageFromChemicalNameFunction(/*OutputStream out, Object data*/){
		System.out.println("getImageFromChemicalName()");
		imageFromChemicalName = fixedURlForImage + "false.jpeg";
		
		BufferedImage image = null;
		String urlString = "http://cactus.nci.nih.gov/chemical/structure/";
		urlString = urlString + moleculeChemicalName + "/image?columns=1";
		try {
			URL url = new URL(urlString);
			if(moleculeChemicalName != null && moleculeChemicalName.trim().length() > 0){
				getInChI(moleculeChemicalName);
				getInChIKey(moleculeChemicalName);
				getMoleculeFormula(moleculeChemicalName);
				image = ImageIO.read(url);
				//ImageIO.write(image, "jpeg", out);
				
				System.out.println(image.getHeight() + "  " + image.getWidth());
				
				int cropHeight = (int)(image.getHeight() * 0.50);
				int cropWidth = (int)(image.getWidth() * 0.50);
				int cropStartX = (int)(image.getWidth() * 0.25);
				int cropStartY = (int)(image.getHeight() * 0.22);
				BufferedImage imageClipped = image.getSubimage(cropStartX, cropStartY, cropWidth, cropHeight);
				
				File outputfile = new File("/opt/queryResults/server/default/deploy/vamdcstatic.war/images/" + 
						moleculeChemicalName + ".jpeg");
			    ImageIO.write(imageClipped, "jpeg", outputfile);
			    imageFromChemicalName = fixedURlForImage + moleculeChemicalName + ".jpeg";
			} 		
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getImageFromInchiFunction(/*OutputStream out, Object data*/){
		System.out.println("getImageFromChemicalName()");
		imageFromChemicalName = fixedURlForImage +  "false.jpeg";
		
		BufferedImage image = null;
		String urlString = "http://cactus.nci.nih.gov/chemical/structure/";
		
		try {
			
			if(moleculeInchi != null && moleculeInchi.trim().length() > 0){
				if(!moleculeInchi.startsWith("InChI=")){
					moleculeInchi = "InChI=" + moleculeInchi;
				}
				/*
				 * This is working ... */
				urlString = urlString + moleculeInchi + "/image?columns=1";
				URL url = new URL(urlString);
				System.out.println(urlString);
						
				getMoleculeFormula(moleculeInchi);
				getInChIKey(moleculeInchi);
				
				// Better to use moleculeInchiKey 
				// Not good logic ... can be improved further
				//urlString = urlString + moleculeInchiKey + "/image?columns=1";
				//System.out.println(urlString);
				image = ImageIO.read(url);
				//ImageIO.write(image, "jpeg", out);
				
				System.out.println(image.getHeight() + "  " + image.getWidth());
				
				int cropHeight = (int)(image.getHeight() * 0.50);
				int cropWidth = (int)(image.getWidth() * 0.50);
				int cropStartX = (int)(image.getWidth() * 0.25);
				int cropStartY = (int)(image.getHeight() * 0.22);
				BufferedImage imageClipped = image.getSubimage(cropStartX, cropStartY, cropWidth, cropHeight);
				
				File outputfile = new File("/opt/queryResults/server/default/deploy/vamdcstatic.war/images/" + 
						moleculeOrdinaryStructuralFormula + ".jpeg");
			    ImageIO.write(imageClipped, "jpeg", outputfile);
			    imageFromChemicalName = fixedURlForImage +  moleculeOrdinaryStructuralFormula + ".jpeg";
			} 		
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void getImageFromInchiKeyFunction(/*OutputStream out, Object data*/){
		System.out.println("getImageFromChemicalName()");
		imageFromChemicalName = fixedURlForImage + "false.jpeg";
		
		BufferedImage image = null;
		String urlString = "http://cactus.nci.nih.gov/chemical/structure/";
		urlString = urlString + moleculeInchiKey + "/image?columns=3";
		try {
			URL url = new URL(urlString);
			if(moleculeInchiKey != null && moleculeInchiKey.trim().length() > 0){
				getInChI(moleculeInchiKey);
				getMoleculeFormula(moleculeInchiKey);
				image = ImageIO.read(url);
				//ImageIO.write(image, "jpeg", out);
				
				System.out.println(image.getHeight() + "  " + image.getWidth());
				/*
				int cropHeight = (int)(image.getHeight() * 0.50);
				int cropWidth = (int)(image.getWidth() * 0.50);
				int cropStartX = (int)(image.getWidth() * 0.25);
				int cropStartY = (int)(image.getHeight() * 0.22);
				BufferedImage imageClipped = image.getSubimage(cropStartX, cropStartY, cropWidth, cropHeight);*/
				
				File outputfile = new File("/opt/queryResults/server/default/deploy/vamdcstatic.war/images/" + 
						moleculeOrdinaryStructuralFormula + ".jpeg");
			    ImageIO.write(image, "jpeg", outputfile);
			    imageFromChemicalName = fixedURlForImage + moleculeOrdinaryStructuralFormula + ".jpeg";
			} 		
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public void getMoleculeNameQuery(){
		System.out.println("getMoleculeNameQuery");
		List<MoleculeNames> moleculeNamesList = new MoleculeNamesHome().findByMolecName(moleculeChemicalName);
		if(moleculeNamesList.size() > 0){
			System.out.println(moleculeNamesList.get(0).getMolecName() + "  " + moleculeNamesList.get(0).getMolecId());
			isotopologuesList = new IsotopologuesHome().findByMolecName(moleculeNamesList.get(0).getMolecId());
			System.out.println(isotopologuesList.size() + "  " + isotopologuesList.get(0).getInChIkey());
		}
		
	}

}
