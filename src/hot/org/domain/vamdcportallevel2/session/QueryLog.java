package org.domain.vamdcportallevel2.session;

import java.util.List;
import java.util.ArrayList;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Scope(ScopeType.SESSION)
@Name("queryLog")
public class QueryLog {
	
	private List<SubmittedQuery> submittedQueryList;

	public List<SubmittedQuery> getSubmittedQueryList() {
		if(submittedQueryList == null){
			submittedQueryList = new ArrayList<SubmittedQuery>();
		}
		return submittedQueryList;
	}

	public void setSubmittedQueryList(List<SubmittedQuery> submittedQueryList) {
		this.submittedQueryList = submittedQueryList;
	}
	
}
