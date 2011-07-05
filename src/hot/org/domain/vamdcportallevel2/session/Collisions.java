package org.domain.vamdcportallevel2.session;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("collisions")
@Scope(ScopeType.SESSION)
public class Collisions {
	
	private String collisionIAEACode = null;
	private String collisionCode = null;
	
	private boolean reactants = false;
	private boolean products = false;
	
	public String getQueryString() {
		String xsamsQuery = "";

		boolean firstEntry = true;
		
		if (collisionIAEACode != null && collisionIAEACode.trim().length() > 0) {
			xsamsQuery = xsamsQuery + " CollisionIAEACode = '" + collisionIAEACode + "'";
			firstEntry = false;
		}
		
		if (collisionCode != null && collisionCode.trim().length() > 0) {
			if (firstEntry != true) {
				xsamsQuery = xsamsQuery + " AND ";
			} else {
				firstEntry = false;
			}	
			xsamsQuery = xsamsQuery + " CollisionCode = '" + collisionCode + "'";		
		}
		
		if(reactants == true){
			if (firstEntry != true) {
				xsamsQuery = xsamsQuery + " AND ";
			} else {
				firstEntry = false;
			}
			xsamsQuery = xsamsQuery + " Reactants <> NULL" ;
		}
		
		if(products == true){
			if (firstEntry != true) {
				xsamsQuery = xsamsQuery + " AND ";
			} else {
				firstEntry = false;
			}
			xsamsQuery = xsamsQuery + " Products <> NULL" ;
		}	
		return xsamsQuery;
	}
	
	public void clearFields() {
		collisionIAEACode = "";
		collisionCode = "";
		
		reactants = false;
		products = false;
	}
	
	public String getCollisionIAEACode() {
		return collisionIAEACode;
	}
	public void setCollisionIAEACode(String collisionIAEACode) {
		this.collisionIAEACode = collisionIAEACode;
	}
	public String getCollisionCode() {
		return collisionCode;
	}
	public void setCollisionCode(String collisionCode) {
		this.collisionCode = collisionCode;
	}
	public boolean isReactants() {
		return reactants;
	}
	public void setReactants(boolean reactants) {
		this.reactants = reactants;
	}
	public boolean isProducts() {
		return products;
	}
	public void setProducts(boolean products) {
		this.products = products;
	}
}
