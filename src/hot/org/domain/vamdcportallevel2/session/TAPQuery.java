package org.domain.vamdcportallevel2.session;

import java.io.File;
import java.io.StringWriter;



import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;


import org.domain.vamdcportallevel2.session.TAPHTTPGet;

public class TAPQuery extends AbstractQuery implements Runnable{
	
	
	//private String tapQuery 			= null; // Only Query part	
	//private QueryString queryString 	= null; // Query String encoded
	//private String queryResult 			= null;
	
	//private String resoourceURL 			= null; // TAP URL 
	
	
	//private UUID uuid 					= null;
	//private String queryID 				= null;
	//private boolean status 				= false;
	
	// Not sure about use of IndexInList
	//private int indexInList = 0;
	
	
	public TAPQuery(int indexValue, String tapQueryValue, String tapResourceValue){
		super.queryType = "TAP";
		this.indexInList = indexValue;
		this.queryAsString = tapQueryValue;
		this.resoourceURL = tapResourceValue + "/sync?";
		
		this.encodedQuery = new QueryString("REQUEST", "doQuery");
		this.encodedQuery.add("LANG", "ADQL");
		this.encodedQuery.add("QUERY", this.queryAsString); 
		//System.out.println("TAPQuery this.indexInList: " + this.indexInList);
	}
	
	public void run(){
		
		if(uuid == null) {
            generateUUID();
        }
		
		String encodedQueryWithURL = this.resoourceURL + this.encodedQuery.toString();
		
		TAPHTTPGet tapGet = null;
		// There is only one query so pass -99
		tapGet = new TAPHTTPGet(encodedQueryWithURL, uuid, -99);
		
        Thread temp = new Thread(tapGet);
        temp.start();
            
        
        while(this.queryStatus != true){
        	System.out.println("this.status: " + this.queryStatus + " : " + tapGet.isDone());
        	this.queryStatus = tapGet.isDone();
        	try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
        System.out.println("TAPQuery is finished successfully");
	}

	public String getQueryResult() {
		try {
			  File file = new File("/opt/queryResults/" + queryID + ".xml");
			  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			  DocumentBuilder db = dbf.newDocumentBuilder();
			  Document doc = db.parse(file);
			  doc.getDocumentElement().normalize();
			  
			  Transformer transformer = TransformerFactory.newInstance().newTransformer();
			  transformer.setOutputProperty(OutputKeys.INDENT, "yes");

			  //initialize StreamResult with File object to save to file
			  StreamResult result = new StreamResult(new StringWriter());
			  DOMSource source = new DOMSource(doc);
			  transformer.transform(source, result);

			  String xmlString = result.getWriter().toString();
			  System.out.println(xmlString);

			  queryResult = xmlString;
		}catch (Exception e) {
		    e.printStackTrace();
			
		}
		return queryResult;
	}

	public void setQueryResult(String queryResult) {
		this.queryResult = queryResult;
	}

}