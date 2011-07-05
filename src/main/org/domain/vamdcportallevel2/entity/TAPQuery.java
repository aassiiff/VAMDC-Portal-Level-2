package org.domain.vamdcportallevel2.entity;

import java.util.UUID;

import org.domain.vamdcportallevel2.session.TAPHTTPGet;

public class TAPQuery implements Runnable{
	
	
	private String tapQuery 			= null; // Only Query part	
	private QueryString queryString 	= null; // Query String encoded
	private String queryResult 			= null;
	
	private String tapResource 			= null; // TAP URL 
	private TAPHTTPGet tapGet = null;
	
	private UUID uuid 					= null;
	private String queryID 				= null;
	private boolean status 				= false;
	
	public TAPQuery(String tapQueryValue, String tapResourceValue, TAPHTTPGet tapGetValue){
		this.tapQuery = tapQueryValue;
		this.tapGet = tapGetValue;
		
		this.tapResource = tapResourceValue + "/sync?";
		
		this.queryString = new QueryString("REQUEST", "doQuery");
		this.queryString.add("LANG", "ADQL");
		this.queryString.add("QUERY", this.tapQuery); 
	}
	
	private void generateUUID() {
        this.uuid = UUID.randomUUID();
        this.queryID = this.uuid.toString();
    }
	
	public void run(){
		
		if(uuid == null) {
            generateUUID();
        }
		
		String encodedQueryWithURL = this.tapResource + this.queryString.toString();
		
		
		// There is only one query so pass -99
		tapGet = new TAPHTTPGet(encodedQueryWithURL, uuid, -99);
		
        Thread temp = new Thread(tapGet);
        temp.start();
            
        
        while(!this.status){
        	this.status = tapGet.isDone();
        }
        
        System.out.println("TAPQuery is finished successfully");
        this.tapGet = null;
	}

	public String getTapQuery() {
		return tapQuery;
	}

	public void setTapQuery(String tapQuery) {
		this.tapQuery = tapQuery;
	}

	public QueryString getQueryString() {
		return queryString;
	}

	public void setQueryString(QueryString queryString) {
		this.queryString = queryString;
	}

	public String getTapResource() {
		return tapResource;
	}

	public void setTapResource(String tapResource) {
		this.tapResource = tapResource;
	}

	public String getQueryResult() {
		return queryResult;
	}

	public void setQueryResult(String queryResult) {
		this.queryResult = queryResult;
	}

	public String getQueryID() {
		return queryID;
	}

	public void setQueryID(String queryID) {
		this.queryID = queryID;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}	
	
	

}
