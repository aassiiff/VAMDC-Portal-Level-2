package org.domain.vamdcportallevel2.entity;

import org.jboss.seam.annotations.Name;

import net.ivoa.xml.registryInterface.v10.Interface;

@Name("extendedInterface")
public class ExtendedInterface {
	
	
	private int interfaceLocation = 0;
	
	private Interface interfaceTemp;
	
	private String type = null;
	
	public ExtendedInterface(int index, Interface interfaceT, String typeValue){
		this.interfaceLocation = index;
		this.interfaceTemp = interfaceT;
		this.type = typeValue;	
	}

	public int getInterfaceLocation() {
		return interfaceLocation;
	}

	public void setInterfaceLocation(int interfaceLocation) {
		this.interfaceLocation = interfaceLocation;
	}

	public Interface getInterfaceTemp() {
		return interfaceTemp;
	}

	public void setInterfaceTemp(Interface interfaceTemp) {
		this.interfaceTemp = interfaceTemp;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
