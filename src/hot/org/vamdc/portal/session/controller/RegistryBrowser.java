package org.vamdc.portal.session.controller;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import javax.xml.namespace.QName;

import net.ivoa.wsdl.registrySearch.v10.XQuerySearchResponseDocument;
import net.ivoa.wsdl.registrySearch.v10.XQuerySearchResponseDocument.XQuerySearchResponse;
import net.ivoa.xml.registryInterface.v10.Capability;
import net.ivoa.xml.registryInterface.v10.Interface;
import net.ivoa.xml.registryInterface.v10.Resource;

import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.astrogrid.registry.RegistryException;
import org.astrogrid.registry.client.RegistryDelegateFactory;
import org.astrogrid.registry.client.query.v1_0.RegistryService;
import org.astrogrid.util.DomHelper;
import org.domain.vamdcportallevel2.entity.ExtendedCapability;
import org.domain.vamdcportallevel2.entity.ExtendedInterface;
import org.domain.vamdcportallevel2.entity.ExtendedRegistry;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.log.Log;
import org.vamdc.portal.session.tap.TAPQuery;

@Name("registryBrowser")
@Scope(ScopeType.SESSION)
public class RegistryBrowser {

	@Logger
	private Log log;
	
	private int resourceIndex = 0;

	private String fileServerURL;
	private String staticApplicationURL;
	private String processSpreadSheet;

	private int capabilityIndex = 0;
	private int interfaceIndex = 0;
	private int tapQueryIndex = 0;

	private String stringURL = "";

	private String tapQuery = "";

	private int listIndex = 0;

	private String queryResult = "EMPTY";

	private org.w3c.dom.Document tempDoc = null;

	private boolean queryInputBox = true;

	// private ArrayList<Resource> resourceList;
	private ArrayList<ExtendedCapability> capabilityList;
	private ArrayList<ExtendedInterface> interfaceList;
	private ArrayList<ExtendedRegistry> extendedRegistryList;

	private ExtendedRegistry selectedExtendedRegistry;
	
	private Date lastRegistryQueryTime;
/*
	private ArrayList<XmlObject> tableList;
	private ArrayList<XmlObject> columnList;

	private ArrayList<String> tableNamesList;
	@SuppressWarnings("unused")
	private ArrayList<String> columnNamesList;
*/
	//private ArrayList<AbstractQuery> submittedTAPQueryList;

	private Map<String, String> xsamsListForCheckBoxes = new HashMap<String, String>();

	TAPQuery selectedTAQPQuery = null;

	//@SuppressWarnings("rawtypes")
	//private TreeNodeImpl databaseNode = new TreeNodeImpl();

	public String getQueryResult() {
		return queryResult;
	}

	public void setQueryResult(String queryResult) {
		this.queryResult = queryResult;
	}

	public Map<String, String> getXsamsListForCheckBoxes() {
		return xsamsListForCheckBoxes;
	}

	public void setXsamsListForCheckBoxes(
			Map<String, String> xsamsListForCheckBoxes) {
		this.xsamsListForCheckBoxes = xsamsListForCheckBoxes;
	}

	public void registryBrowser() {
		listIndex = 0;

		log.info("registryBrowser.registryBrowser() action called");
		//facesMessages.add("registryBrowser");

		System.setProperty("return.soapbody", "true");

		String registryURL = System.getProperty("registryURL");
		String queryString = System.getProperty("queryString_0");
		fileServerURL = System.getProperty("fileServerURL");
		staticApplicationURL = System.getProperty("staticApplicationURL");
		processSpreadSheet = System.getProperty("processSpreadSheet");

		log.info("registryBrowser.registryBrowser() System.getProperty(registryURL): "
						+ registryURL);
		log.info("registryBrowser.registryBrowser() System.getProperty(queryString): "
						+ queryString);
		log.info("registryBrowser.registryBrowser() System.getProperty(fileServerURL): "
						+ fileServerURL);
		log.info("registryBrowser.registryBrowser() System.getProperty(processSpreadSheet): "
				+ processSpreadSheet);

		try {
			RegistryService registryTemp = RegistryDelegateFactory
					.createQueryv1_0(new URL(registryURL));
			try {

				tempDoc = registryTemp.xquerySearch(queryString);
				queryResult = DomHelper.DocumentToString(tempDoc);

				// System.out.println(queryResult);
			} catch (RegistryException ex) {
				log.error(Level.SEVERE, null, ex);
			}
		} catch (MalformedURLException ex) {
			log.error(Level.SEVERE, null, ex);
		}
	}

	public void buildResourceList() {

		extendedRegistryList = new ArrayList<ExtendedRegistry>();

		int extendedRegistryListCounter = 0;

		if (tempDoc == null) {
			registryBrowser();
		}

		try {

			XQuerySearchResponseDocument responseDoc = XQuerySearchResponseDocument.Factory
					.parse(tempDoc);
			XQuerySearchResponse response = responseDoc
					.getXQuerySearchResponse();

			/*
			 * xmlns:ri="http://www.ivoa.net/xml/RegistryInterface/v1.0"
			 * <ri:Resource> </ri:Resource>
			 */

			XmlObject xmlObject[] = response.selectChildren(new QName(
					"http://www.ivoa.net/xml/RegistryInterface/v1.0",
					"Resource"));

			log.info("Number of Resources: " + xmlObject.length);

			Resource resourceTemp = null;
			ExtendedRegistry extendedRegistry = null;

			for (int i = 0; i < xmlObject.length; i++) {

				resourceTemp = Resource.Factory.parse(xmlObject[i].xmlText());

				/*
				 * xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
				 * xsi:type="vr:Service"
				 */
				String resourceTypeComplete = resourceTemp.selectAttribute(
						"http://www.w3.org/2001/XMLSchema-instance", "type")
						.getDomNode().getNodeValue();

				int index = resourceTypeComplete.indexOf(':') + 1;
				String resourceType = resourceTypeComplete.substring(index);

				//log.info("Resource Type: " + resourceType);
				/*
				 * Ignore Resource Type Registry, Authority CeaApplication
				 * reduced only 3 resources Organisation reduced only 1 resource
				 * Service reduced 16 resources
				 */
				if (!resourceType.equals("Registry")
						&& !resourceType.equals("Authority")
						&& !resourceType.equals("CeaApplication")
						&& !resourceType.equals("Organisation")) {
					if (resourceTemp.getStatus().toString().equals("active")) {
						//System.out.println(resourceTemp.getIdentifier() + "  resourceType: " + resourceType);
						// resourceList.add(resourceTemp);

						// Here Logic needs to be changed .....!
						// I need to capture TAP-XSAMS also ....!
						String[] accessURLs = buildCapabilityList(resourceTemp);

						if (accessURLs[1] != null
								&& accessURLs[1].trim().length() > 0) {
							extendedRegistry = new ExtendedRegistry(
									extendedRegistryListCounter++, resourceTemp);
							
							extendedRegistry.setXsamURL(accessURLs[1]);
							xsamsListForCheckBoxes.put(resourceTemp.getTitle(),
									accessURLs[1]);
						
							extendedRegistry.setRestrictableList(this.getRestrictableList(resourceTemp));
							String webURL = resourceTemp.getContent()
									.getReferenceURL();
							//log.info("web URL: " + webURL);
							if (webURL != null && webURL.trim().length() > 0) {
								extendedRegistry.setWebURL(webURL);
							}

							//log.info("Tap URL: " + accessURLs[0]);
							//log.info("Tap XSAMs URL: " + accessURLs[1]);

							extendedRegistry.setTapURL(accessURLs[0]);

							if (resourceTemp.getCuration().getContactArray().length > 0) {
								extendedRegistry.setContactEmail(resourceTemp
										.getCuration().getContactArray()[0]
										.getEmail());
								extendedRegistry.setContactID(resourceTemp
										.getCuration().getContactArray()[0]
										.getName().getIvoId());
							}

							extendedRegistryList.add(extendedRegistry);
						}
					}
				}
			}

		} catch (XmlException ex) {

		}
	}
	
	private List<String> getRestrictableList(Resource resourceValue){
		Resource tempResource = resourceValue;
		List<String> restrictableArray = new ArrayList<String>();
		
		XmlObject tempObject = tempResource.copy();
		XmlObject xmlObjectCapabilityChild[] = tempObject
				.selectChildren(new QName("", "capability"));

		Capability capabilityTemp = null;
		
		for (int j = 0; j < xmlObjectCapabilityChild.length; j++) {
			try {
				capabilityTemp = Capability.Factory
						.parse(xmlObjectCapabilityChild[j].xmlText());
				if (capabilityTemp.getStandardID() != null) {
					XmlObject xmlObjectRestrictableChild[] = xmlObjectCapabilityChild[j].selectChildren(new QName("", "restrictable"));
					for (int m = 0; m < xmlObjectRestrictableChild.length; m++) {
						restrictableArray.add(xmlObjectRestrictableChild[m].newCursor().getTextValue().toLowerCase());
					}
				}
			} catch (XmlException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		return restrictableArray;
	}

	private String[] buildCapabilityList(Resource resourceValue) {

		Resource tempResource = resourceValue;
		// First for Tap and second for Tap-XSAMs
		String[] accessURLs = new String[] { null, null };

		XmlObject tempObject = tempResource.copy();
		XmlObject xmlObjectCapabilityChild[] = tempObject
				.selectChildren(new QName("", "capability"));

		Capability capabilityTemp = null;

		for (int j = 0; j < xmlObjectCapabilityChild.length; j++) {
			try {
				capabilityTemp = Capability.Factory
						.parse(xmlObjectCapabilityChild[j].xmlText());
				
				if (capabilityTemp.getStandardID() != null) {
					//log.info(tempResource.getIdentifier() + "  " + capabilityTemp.getStandardID().toUpperCase());
					/*
					if (capabilityTemp.getStandardID().toUpperCase().contains(
							"ivo://vamdc/std/VAMDC-TAP") || capabilityTemp.getStandardID().toUpperCase().contains(
							"ivo://vamdc/std/TAP-XSAMS")) {
						accessURLs[1] = buildInterfaceList(capabilityTemp);
						log.info(capabilityTemp.getStandardID().toUpperCase());
					}*/
					if (capabilityTemp.getStandardID().toUpperCase().contains(
							"VAMDC")) {

						if (capabilityTemp.getStandardID().toUpperCase()
								.contains("VAMDC-TAP") || capabilityTemp.getStandardID().toUpperCase()
								.contains("TAP-XSAMS") ) {
							accessURLs[1] = buildInterfaceList(capabilityTemp);
							//log.info(capabilityTemp.getStandardID().toUpperCase());
						} else {
							accessURLs[0] = buildInterfaceList(capabilityTemp);
							//log.info("Else {0}", capabilityTemp.getStandardID().toUpperCase());
						}
					}
				}
			} catch (XmlException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return accessURLs;
	}

	private String buildInterfaceList(Capability capabilityValue) {

		Capability capabilityTemp = capabilityValue;

		Interface[] interfaceArray = capabilityTemp.getInterfaceArray();
		// System.out.println("Interface List for capability: " +
		// interfaceArray.length);
		String interfaceAccessURL = interfaceArray[0].getAccessURLArray(0)
				.getStringValue();
		/*
		 * Interface interfaceTemp = null;
		 * 
		 * for (int m = 0; m < interfaceArray.length; m++) { interfaceTemp =
		 * interfaceArray[m];
		 * 
		 * // This assumes URL will include TAP .... key word which may not be
		 * the case ...!
		 * if(interfaceTemp.getAccessURLArray(0).getStringValue().toUpperCase()
		 * .contains("TAP")){ return
		 * interfaceTemp.getAccessURLArray(0).getStringValue(); } }
		 */
		return interfaceAccessURL;

	}

	private void buildCapabilityList() {

		capabilityList = new ArrayList<ExtendedCapability>();
		// System.out.println("Selected Resource: " + resourceIndex);

		ExtendedRegistry tempExtendedRegistry = this.extendedRegistryList
				.get(resourceIndex);
		Resource tempResource = tempExtendedRegistry.getResource();

		XmlObject tempObject = tempResource.copy();
		XmlObject xmlObjectCapabilityChild[] = tempObject
				.selectChildren(new QName("", "capability"));

		Capability capabilityTemp = null;
		ExtendedCapability extendedCapabilityTemp = null;

		for (int j = 0; j < xmlObjectCapabilityChild.length; j++) {
			try {
				capabilityTemp = Capability.Factory
						.parse(xmlObjectCapabilityChild[j].xmlText());

				extendedCapabilityTemp = new ExtendedCapability(j,
						capabilityTemp);
			} catch (XmlException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			capabilityList.add(extendedCapabilityTemp);
		}
	}

	private void buildInterfaceList() {
		interfaceList = new ArrayList<ExtendedInterface>();
		ExtendedCapability extendedCapabilityTemp = capabilityList
				.get(capabilityIndex);
		Capability capabilityTemp = extendedCapabilityTemp.getCapability();

		/* */
		Interface[] interfaceArray = capabilityTemp.getInterfaceArray();
		Interface interfaceTemp = null;

		for (int m = 0; m < interfaceArray.length; m++) {
			interfaceTemp = interfaceArray[m];

			String interfaceTypeComplete = interfaceTemp.selectAttribute(
					"http://www.w3.org/2001/XMLSchema-instance", "type")
					.getDomNode().getNodeValue();
			int index = interfaceTypeComplete.indexOf(':') + 1;
			String resourceType = interfaceTypeComplete.substring(index);

			interfaceList.add(new ExtendedInterface(m, interfaceTemp,
					resourceType));
			//System.out.println(interfaceTemp.type);
		}
	}
	
	public ArrayList<ExtendedCapability> getCapabilityList() {

		// if (capabilityList == null) {
		this.buildCapabilityList();
		// }

		return capabilityList;
	}

	public ArrayList<ExtendedInterface> getInterfaceList() {

		// if (interfaceList == null) {
		buildInterfaceList();
		// }
		return interfaceList;
	}

	public ArrayList<ExtendedRegistry> getExtendedRegistryList() {
		
		if (extendedRegistryList == null) {
			lastRegistryQueryTime = new Date();
			buildResourceList();
		} else if((new Date().getTime() - lastRegistryQueryTime.getTime()) > (1000 * 60 * 5)){
			System.out.println("new Date().getTime() - lastRegistryQueryTime.getTime(): " + (new Date().getTime() - lastRegistryQueryTime.getTime()));
			lastRegistryQueryTime = new Date();
			buildResourceList();
		}
		return extendedRegistryList;
	}

	public void setResourceIndex(int resourceIndex) {
		System.out.println("Selected Resource: " + resourceIndex);
		selectedExtendedRegistry = extendedRegistryList.get(resourceIndex);
		System.out.println(selectedExtendedRegistry.getResource().getTitle());
		this.resourceIndex = resourceIndex;
	}
	
	public ExtendedRegistry getExtendedResource(String nodeURL){
		for(int i =0; i < extendedRegistryList.size(); i++){
			if(extendedRegistryList.get(i).getXsamURL().equals(nodeURL)){
				return extendedRegistryList.get(i);
			}
		}
		return null;
	}

	public ExtendedRegistry getSelectedExtendedRegistry() {
		return selectedExtendedRegistry;
	}

	public void setSelectedExtendedRegistry(
			ExtendedRegistry selectedExtendedRegistry) {
		this.selectedExtendedRegistry = selectedExtendedRegistry;
	}

	public int getResourceIndex() {
		return resourceIndex;
	}

	public int getListIndex() {
		return listIndex++;
	}

	public void setListIndex(int listIndex) {
		this.listIndex = listIndex;
	}

	public int getCapabilityIndex() {
		return capabilityIndex;
	}

	public void setCapabilityIndex(int capabilityIndex) {
		this.capabilityIndex = capabilityIndex;
	}

	public int getInterfaceIndex() {
		return interfaceIndex;
	}

	public void setInterfaceIndex(int interfaceIndex) {
		this.interfaceIndex = interfaceIndex;
	}

	public String getStringURL() {
		return stringURL;
	}

	public void setStringURL(String stringURLValue) {
		stringURL = stringURLValue;
	}

	public String getTapQuery() {
		return tapQuery;
	}

	public void setTapQuery(String tapQuery) {
		System.out.println("setTapQuery: " + tapQuery);
		this.tapQuery = tapQuery;
	}
/*
	public ArrayList<AbstractQuery> getSubmittedTAPQueryList() {
		if (submittedTAPQueryList == null) {
			submittedTAPQueryList = new ArrayList<AbstractQuery>();
		}
		return submittedTAPQueryList;
	}

	public void setSubmittedTAPQueryList(
			ArrayList<AbstractQuery> submittedTAPQueryList) {
		this.submittedTAPQueryList = submittedTAPQueryList;
	}
*/
	/*
	public void executeQuery() {

		if (submittedTAPQueryList == null) {
			submittedTAPQueryList = new ArrayList<AbstractQuery>();
		}
		System.out.println("RegistryBrowser executeQuery(): "
				+ submittedTAPQueryList.size());

		TAPQuery tempTAPQuery = new TAPQuery(submittedTAPQueryList.size(),
				tapQuery, stringURL);
		submittedTAPQueryList.add(0, tempTAPQuery);

		Thread temp = new Thread(tempTAPQuery);
		temp.start();

		queryResult = "";
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		queryResult = tempTAPQuery.getQueryResult();

		this.queryInputBox = false;

	}
*/
	public boolean isQueryInputBox() {
		return queryInputBox;
	}

	public void setQueryInputBox(boolean queryInputBox) {
		this.queryInputBox = queryInputBox;
	}

	public void queryInputBoxToggler() {
		this.tapQuery = "";
		this.queryInputBox = true;
		this.queryResult = "";
	}

	/*
	public AbstractQuery getSelectedTAQPQuery() {
		return this.submittedTAPQueryList.get(this.tapQueryIndex);
	}
*/
	@SuppressWarnings("unused")
	private org.w3c.dom.Document loadXMLFrom(java.io.InputStream is)
			throws org.xml.sax.SAXException, java.io.IOException {
		javax.xml.parsers.DocumentBuilderFactory factory = javax.xml.parsers.DocumentBuilderFactory
				.newInstance();
		factory.setNamespaceAware(true);
		javax.xml.parsers.DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
		} catch (javax.xml.parsers.ParserConfigurationException ex) {
		}
		org.w3c.dom.Document doc = builder.parse(is);
		is.close();
		return doc;
	}

	public int getTapQueryIndex() {
		return tapQueryIndex;
	}

	public void setTapQueryIndex(int tapQueryIndex) {
		this.tapQueryIndex = tapQueryIndex;
	}

	public String getFileServerURL() {
		return fileServerURL;
	}

	public void setFileServerURL(String fileServerURL) {
		this.fileServerURL = fileServerURL;
	}

	public String getStaticApplicationURL() {
		return staticApplicationURL;
	}

	public void setStaticApplicationURL(String staticApplicationURL) {
		this.staticApplicationURL = staticApplicationURL;
	}

	public String getProcessSpreadSheet() {
		return processSpreadSheet;
	}

	public void setProcessSpreadSheet(String processSpreadSheet) {
		this.processSpreadSheet = processSpreadSheet;
	}
	
}
