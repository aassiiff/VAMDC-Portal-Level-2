package org.vamdc.portal.session.controller;

import java.util.ArrayList;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;


@Name("selectedRestrictables")
@Scope(ScopeType.SESSION)
public class SelectedRestrictables {
	
	private List<String> selectedRestrictableList = new ArrayList<String>();

	public List<String> getSelectedRestrictableList() {
		return selectedRestrictableList;
	}

	public void setSelectedRestrictableList(List<String> selectedRestrictableList) {
		this.selectedRestrictableList = selectedRestrictableList;
	}
	
	// value from may not be valid for many enteries
	public void keyEventOnBlur(String key, String valueTo, String valueFrom){
		//System.out.println("Key: " + key + " ValueTo: " + valueTo);
		
		if(valueTo.trim().length() > 0 || valueFrom.trim().length() > 0){
			if(!selectedRestrictableList.contains(key.toLowerCase())){
				selectedRestrictableList.add(key.toLowerCase());
			}
		} else if(valueTo.trim().length() == 0 && valueFrom.trim().length() == 0){
			if(selectedRestrictableList.contains(key.toLowerCase())){
				selectedRestrictableList.remove(key.toLowerCase());
			}
		}
		
		//System.out.println("Key: " + key + " Value: " + valueTo + "selectedRestrictableList size:  " + selectedRestrictableList.size());
	}
	
	public void resetSelectedRestrictablesList(){
		selectedRestrictableList = new ArrayList<String>();
	}
	
	public boolean containsSingleRestrictable(String restrictable){
		//System.out.println("containsSingleRestrictable(String restrictable)");	
		return selectedRestrictableList.contains(restrictable.toLowerCase());	
	}
	
	public boolean containsWholeRestrictableList(List<String> restrictableList){
		if(selectedRestrictableList.size() > 0){
			return restrictableList.containsAll(selectedRestrictableList);
		}
		return false;     
	}

}
