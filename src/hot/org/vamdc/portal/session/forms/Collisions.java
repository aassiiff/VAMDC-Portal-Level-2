package org.vamdc.portal.session.forms;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.vamdc.portal.session.controller.ProcessCodeManager;
import org.vamdc.portal.session.controller.ProcessDefinition;

@Name("collisions")
@Scope(ScopeType.SESSION)
public class Collisions {
	
	@In(create = true)
	private ProcessCodeManager processCodeManager;
	
	private String collisionIAEACode = null;
	private String collisionCode = null;
	
	private String processName;
	private String processDescription;
	
	private boolean editable = true;
	
	public void toggleEditable() {
		editable = !editable;
	}
	
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
		
		return xsamsQuery;
	}
	
	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public void clearFields() {
		collisionIAEACode = "";
		collisionCode = "";

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

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getProcessDescription() {
		return processDescription;
	}

	public void setProcessDescription(String processDescription) {
		this.processDescription = processDescription;
	}

	public List<ProcessDefinition> autoCompleteForProcessName(Object suggest) {
		String pref = (String)suggest;
		System.out.println("autoCompleteForProcessName: " + pref);
		List<ProcessDefinition> localProcessCodesList = new ArrayList<ProcessDefinition>();
		
		 Iterator<ProcessDefinition> iterator = processCodeManager.getProcessCodesList().iterator();
	        while (iterator.hasNext()) {
	        	
	        	ProcessDefinition elem = ((ProcessDefinition) iterator.next());
	            if ((elem.getProcessName() != null && elem.getProcessName().toLowerCase().indexOf(pref.toLowerCase()) == 0) || "".equals(pref))
	            {
	            	localProcessCodesList.add(elem);
	            }
	        }		
		return localProcessCodesList;
	}
	
	public List<ProcessDefinition> autoCompleteForProcessCode(Object suggest) {
		String pref = (String)suggest;
		System.out.println("autoCompleteForProcessCode: " + pref);
		List<ProcessDefinition> localProcessCodesList = new ArrayList<ProcessDefinition>();
		
		 Iterator<ProcessDefinition> iterator = processCodeManager.getProcessCodesList().iterator();
	        while (iterator.hasNext()) {
	        	
	        	ProcessDefinition elem = ((ProcessDefinition) iterator.next());
	            if ((elem.getProcessCode() != null && elem.getProcessCode().toLowerCase().indexOf(pref.toLowerCase()) == 0) || "".equals(pref))
	            {
	            	localProcessCodesList.add(elem);
	            }
	        }		
		return localProcessCodesList;
	}
	
	public List<ProcessDefinition> autoCompleteForIAEAsCode(Object suggest) {
		String pref = (String)suggest;
		System.out.println("autoCompleteForIAEAsCode: " + pref);
		List<ProcessDefinition> localProcessCodesList = new ArrayList<ProcessDefinition>();
		
		 Iterator<ProcessDefinition> iterator = processCodeManager.getProcessCodesList().iterator();
	        while (iterator.hasNext()) {
	        	
	        	ProcessDefinition elem = ((ProcessDefinition) iterator.next());
	            if ((elem.getIaeaCode() != null && elem.getIaeaCode().toLowerCase().indexOf(pref.toLowerCase()) == 0) || "".equals(pref))
	            {
	            	localProcessCodesList.add(elem);
	            }
	        }		
		return localProcessCodesList;
	}
	
	public void onSelectForProcessName(){
		String tempProcessName = processName;
		//System.out.println("onSelectForProcessName: " + tempProcessName);
		Iterator<ProcessDefinition> iterator = processCodeManager.getProcessCodesList().iterator();
        while (iterator.hasNext()) {
        	ProcessDefinition elem = ((ProcessDefinition) iterator.next());
        	String temProcessName1 = elem.getProcessName();
        	//System.out.print(elem.getProcessName() + " ,,,,  " + tempProcessName);
        	/**/
        	if (elem.getProcessName() != null && temProcessName1.equalsIgnoreCase(tempProcessName))  {
        		this.processName = tempProcessName;
        		this.collisionCode = elem.getProcessCode();
        		this.processDescription = elem.getProcessDescription();
        		this.collisionIAEACode = elem.getIaeaCode();
        		
        		return;
            }
        }
	}
	
	public void onSelectForProcessCode(){
		String tempProcessCode = this.collisionCode;
		//System.out.println("onSelectForProcessName: " + tempProcessName);
		Iterator<ProcessDefinition> iterator = processCodeManager.getProcessCodesList().iterator();
        while (iterator.hasNext()) {
        	ProcessDefinition elem = ((ProcessDefinition) iterator.next());
        	//String temProcessName1 = elem.getProcessCode();
        	//System.out.print(elem.getProcessName() + " ,,,,  " + tempProcessName);
        	/**/
        	if (elem.getProcessCode() != null && elem.getProcessCode().equalsIgnoreCase(tempProcessCode))  {
        		this.processName = elem.getProcessName();
        		this.collisionCode = tempProcessCode;
        		this.processDescription = elem.getProcessDescription();
        		this.collisionIAEACode = elem.getIaeaCode();
        		
        		return;
            }
        }
	}
	
	public void onSelectForIAEAsCode(){
		String tempIAEACode = this.collisionIAEACode;
		//System.out.println("onSelectForProcessName: " + tempProcessName);
		Iterator<ProcessDefinition> iterator = processCodeManager.getProcessCodesList().iterator();
        while (iterator.hasNext()) {
        	ProcessDefinition elem = ((ProcessDefinition) iterator.next());
        	//String temProcessName1 = elem.getProcessCode();
        	//System.out.print(elem.getProcessName() + " ,,,,  " + tempProcessName);
        	/**/
        	if (elem.getIaeaCode() != null && elem.getIaeaCode().equalsIgnoreCase(tempIAEACode))  {
        		this.processName = elem.getProcessName();
        		this.collisionCode = elem.getProcessCode();
        		this.processDescription = elem.getProcessDescription();
        		this.collisionIAEACode = elem.getIaeaCode();
        		
        		return;
            }
        }
	}
	
}
