package org.vamdc.portal.session.query;


import java.util.ArrayList;
import java.util.List;


/*
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Scope(ScopeType.SESSION)
@Name("submittedQuery")
*/
public class SubmittedQuery {
	
	private String queryString;
	
	//private List<String> nodeList;
	private List<AbstractQuery> submittedQueryList;

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}
/*
	public List<String> getNodeList() {
		return nodeList;
	}

	public void setNodeList(List<String> nodeList) {
		this.nodeList = nodeList;
	}
*/
	public List<AbstractQuery> getSubmittedQueryList() {
		if(submittedQueryList == null) {
			submittedQueryList = new ArrayList<AbstractQuery>();
		}
		return submittedQueryList;
	}

	public void setSubmittedQueryList(List<AbstractQuery> submittedQueryList) {
		this.submittedQueryList = submittedQueryList;
	}	
	
}
