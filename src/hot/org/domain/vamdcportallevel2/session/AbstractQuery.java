package org.domain.vamdcportallevel2.session;

import java.util.UUID;

import org.domain.vamdcportallevel2.entity.ExtendedRegistry;

public abstract class AbstractQuery {
	
	protected String resoourceURL;			// URL of the resource i.e. TAP or XSAMS
	
	protected String queryType;				// Query Type i.e. TAP or XSAMS
	
	protected String queryAsString; 		// Only Query As A String
	
	protected QueryString encodedQuery;		// Query with supporting parameters encoded
	
	protected boolean queryStatus = false;
	
	protected ExtendedRegistry extendedRegistry;
	
	protected String queryResult 			= "";
	
	protected String queryID;
	protected UUID uuid 					= null;
	
	protected int indexInList = 0;
	
	protected void generateUUID() {
        this.uuid = UUID.randomUUID();
        this.queryID = this.uuid.toString();
    }
	
	public abstract String getQueryResult();

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public String getQueryAsString() {
		return queryAsString;
	}

	public void setQueryAsString(String queryAsString) {
		this.queryAsString = queryAsString;
	}

	public QueryString getEncodedQuery() {
		return encodedQuery;
	}

	public void setEncodedQuery(QueryString encodedQuery) {
		this.encodedQuery = encodedQuery;
	}

	public boolean isQueryStatus() {
		return queryStatus;
	}

	public void setQueryStatus(boolean queryStatus) {
		this.queryStatus = queryStatus;
	}

	public String getQueryID() {
		return queryID;
	}

	public void setQueryID(String queryID) {
		this.queryID = queryID;
	}

	public String getResoourceURL() {
		return resoourceURL;
	}

	public void setResoourceURL(String resoourceURL) {
		this.resoourceURL = resoourceURL;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public int getIndexInList() {
		return indexInList;
	}

	public void setIndexInList(int indexInList) {
		this.indexInList = indexInList;
	}

	public void setQueryResult(String queryResult) {
		this.queryResult = queryResult;
	}

	public ExtendedRegistry getExtendedRegistry() {
		return extendedRegistry;
	}

	public void setExtendedRegistry(ExtendedRegistry extendedRegistry) {
		this.extendedRegistry = extendedRegistry;
	}
	
	
	
}
