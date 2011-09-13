package org.vamdc.portal.session.query;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.concurrent.Callable;


public class XSAMSQueryHeadRequestThread implements Callable<XSAMSQueryHeadResponse> {
	
	//private int extendedRegistryListIndex;
	
	private String URLString;
	private String queryString;
	private String nodeTitle;
	
	//private XSAMSQueryHeadResponse xsamsQueryHeadResponse;
	
	public XSAMSQueryHeadRequestThread(String nodeTitleValue, String URLStringValue, String queryStringValue){
		this.nodeTitle = nodeTitleValue;
		this.URLString = URLStringValue;
		this.queryString = queryStringValue;
	}
	public XSAMSQueryHeadResponse call(){
		XSAMSQueryHeadResponse xsamsQueryHeadResponse = new XSAMSQueryHeadResponse();
		
		//System.out.println("XSAMSQueryHeadRequestThread call(): " + queryString);
		try {
			URL u = new URL(URLString + "sync?" + queryString);
			//System.out.println("XSAMSQueryHeadRequestThread call(): " + u.toString());
			HttpURLConnection http = (HttpURLConnection) u.openConnection();
            http.setRequestMethod("HEAD");
            http.setReadTimeout(30 * 1000);
            //System.out.println("\n\n" + URLString);
            //System.out.println(((HttpURLConnection) http).getResponseCode());
            
            xsamsQueryHeadResponse.setNodeTitle(nodeTitle);
            xsamsQueryHeadResponse.setURL(URLString);
            xsamsQueryHeadResponse.setStatusCode("" + ((HttpURLConnection) http).getResponseCode());
            xsamsQueryHeadResponse.setStatusMessage(((HttpURLConnection) http).getResponseMessage());
            xsamsQueryHeadResponse.setCountRadiative(http.getHeaderField("VAMDC-COUNT-RADIATIVE"));
            xsamsQueryHeadResponse.setCountStates(http.getHeaderField("VAMDC-COUNT-STATES"));
            xsamsQueryHeadResponse.setCountSpecies(http.getHeaderField("VAMDC-COUNT-SPECIES"));
            xsamsQueryHeadResponse.setCountAtoms(http.getHeaderField("VAMDC-COUNT-ATOMS"));
            xsamsQueryHeadResponse.setCountMolecules(http.getHeaderField("VAMDC-COUNT-MOLECULES"));
            xsamsQueryHeadResponse.setCountSources(http.getHeaderField("VAMDC-COUNT-SOURCES"));
            xsamsQueryHeadResponse.setCountCollisions(http.getHeaderField("VAMDC-COUNT-COLLISIONS"));
            xsamsQueryHeadResponse.setCountNonRadiative(http.getHeaderField("VAMDC-COUNT-NONRADIATIVE"));
            
            /*
            Map<String, List<String>> hf = http.getHeaderFields();
            for (String key : hf.keySet()) {
            	System.out.println(key + ": " + http.getHeaderField(key));
            	
            	if(key.equals("VAMDC-COUNT-RADIATIVE")){
            		xsamsQueryHeadResponse.setCountRadiative(http.getHeaderField(key));
            		System.out.println(key + ": " + http.getHeaderField(key));
            	}
            	
            	if(key.equals("VAMDC-COUNT-STATES")){
            		xsamsQueryHeadResponse.setCountStates(http.getHeaderField(key));
            		System.out.println(key + ": " + http.getHeaderField(key));
            	}
            }*/
		} catch (MalformedURLException ex) {
            //Logger.getLogger(HTTP_HEAD_Test.class.getName()).log(Level.SEVERE, null, ex);
        } catch(SocketTimeoutException ex){
        	ex.printStackTrace();
        } catch (IOException ex) {
            //Logger.getLogger(HTTP_HEAD_Test.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        return xsamsQueryHeadResponse;
	}

}
