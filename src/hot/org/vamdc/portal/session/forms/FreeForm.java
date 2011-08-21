package org.vamdc.portal.session.forms;

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
