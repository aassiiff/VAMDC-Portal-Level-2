package org.domain.vamdcportallevel2.session;

import org.jboss.seam.annotations.Name;

@Name("freeForm")
public class FreeForm {
	private String queryString = "SELECT ALL WHERE ";

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		System.out.println("setQueryString: " +  queryString);
		this.queryString = queryString;
	}
		
}
