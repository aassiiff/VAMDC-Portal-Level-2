package org.vamdc.portal.session.query;

import java.io.*;
import java.net.*;
import java.util.logging.Level;

import javax.xml.transform.TransformerException;

import org.jboss.seam.annotations.Logger;
import org.jboss.seam.log.Log;

public class XSAMSQueryAdaptor extends AbstractQuery implements Runnable {
	
	@Logger
	private Log log;
	
	private FileOutputStream outputStream = null; // Stream to destination file

	//private String xsamsQuery = null; // Only Query part
	
	//private QueryString xsamsQueryString = null; // Query String encoded
	//private String queryResult = "";

	//private String xsamsResource = null; // XSAMS URL with /sync/?

	//private boolean status = false;

	public XSAMSQueryAdaptor(int indexValue, String xsamsResourceValue,
			QueryString xsamsQueryStringValue, String xsamsQueryAsString) {
		super.queryType = "XSAMS";
		super.indexInList = indexValue;
		super.encodedQuery = xsamsQueryStringValue;
		super.resoourceURL = xsamsResourceValue;
		super.queryAsString = xsamsQueryAsString;
		System.out.println("XSAMSQueryAdaptor(): " + resoourceURL + " " + encodedQuery + " " + queryAsString);
	}

	@SuppressWarnings("deprecation")
	public void run() {

		if (uuid == null) {
			generateUUID();
		}

		try {
			/**/
			// open the connection and prepare it to POST
			// URL u = new
			// URL("http://vamdc.fysast.uu.se:8888/node/vald/tap/sync?");
			System.out.println("resoourceURL: " + resoourceURL);
			URL u = new URL(resoourceURL);
			System.out.println("resoourceURL: " + resoourceURL + u.toString()) ;

			URLConnection uc = u.openConnection();
			uc.setDoOutput(true);
			uc.setDoInput(true);
			uc.setAllowUserInteraction(false);
			DataOutputStream dos = new DataOutputStream(uc.getOutputStream());
			// The POST line, the Accept line, and
			// the content-type headers are sent by the URLConnection.
			// We just need to send the data
			System.out.println("encodedQuery.getQuery(): " + encodedQuery.getQuery());
			dos.writeBytes(encodedQuery.getQuery());
			dos.close();
			// Read the response
			DataInputStream dis = new DataInputStream(uc.getInputStream());
			String nextline;
			while ((nextline = dis.readLine()) != null) {
				//System.out.print(nextline);
				queryResult = queryResult + nextline + "\n";
				
			}
			dis.close();
			//System.out.println("\t" + queryResult);
			outputStream = new FileOutputStream("/opt/queryResults/server/default/deploy/vamdcstatic.war/xsams/" + uuid + ".xml");
			//outputStream.write(queryResult.getBytes());
			
			Writer XMLWriter = new OutputStreamWriter(outputStream, "UTF-8"); 
			XMLWriter.write(queryResult);
			//outputStream.write(queryResult.getBytes());

			XMLWriter.flush();
			XMLWriter.close();
			
			outputStream.flush();
			outputStream.close();
			

			XslTransformer transformer = new XslTransformer();

	        File xmlFile = new File("/opt/queryResults/server/default/deploy/vamdcstatic.war/xsams/" + uuid + ".xml");
	        File xslFile = new File("/opt/queryResults/xsamXSLT1.xsl");
	        File htmlFile = new File("/opt/queryResults/server/default/deploy/vamdcstatic.war/html/" + uuid + ".html");

	        try {
	            FileWriter writer = new FileWriter(htmlFile);
	           //transformer.process(xmlFile, xslFile, System.out);
	            transformer.process(xmlFile, xslFile, writer);
	        } catch (TransformerException ex) {
	        	log.error(Level.SEVERE, null, ex);
	        }catch (IOException ex) {
	        	log.error(Level.SEVERE, null, ex);
	        }
		} catch (Exception e) {
			System.err.println(e);
		}
		
		queryStatus = true;
	}
	
	public String getQueryResult(){ return queryResult; }

}
