package org.domain.vamdcportallevel2.entity;

import org.jboss.seam.annotations.Name;

import net.ivoa.xml.registryInterface.v10.Capability;

@Name("extendedCapability")
public class ExtendedCapability {
	
	private int capabilityLocation = 0;
	
	private Capability capability;
	
	public ExtendedCapability(int index, Capability capabilityTemp){
		this.capabilityLocation = index;
		this.capability = capabilityTemp;
	}

	public int getCapabilityLocation() {
		return capabilityLocation;
	}

	public void setCapabilityLocation(int capabilityLocation) {
		this.capabilityLocation = capabilityLocation;
	}

	public Capability getCapability() {
		return capability;
	}

	public void setCapability(Capability capability) {
		this.capability = capability;
	}
	
	

}
