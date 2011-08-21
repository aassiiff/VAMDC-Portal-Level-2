package org.vamdc.portal.session.controller;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.log.Log;
import org.jboss.seam.faces.FacesMessages;

@Name("componentHolder")
@Scope(ScopeType.SESSION)
public class ComponentHolder {
	
    @Logger private Log log;
	
    @In FacesMessages facesMessages;
    
    private net.ivoa.xml.registryInterface.v10.Resource resource;
    
    public String componentHolder(net.ivoa.xml.registryInterface.v10.Resource resourceTemp)
    {
        //implement your business logic here
        log.info("componentHolder.componentHolder() action called");
        //facesMessages.add("componentHolder");
        this.resource = resourceTemp;
        System.out.println(resourceTemp.getIdentifier());
        return "/componentHolder.xhtml";
    }

	public net.ivoa.xml.registryInterface.v10.Resource getResource() {
		System.out.println("Get Resource is called: " + resource.getIdentifier());
		return resource;
	}

	public void setResource(net.ivoa.xml.registryInterface.v10.Resource resource) {
		this.resource = resource;
	}
	
	public String resourceIdentifier(){
		return resource.getIdentifier();
	}
	
   
	
}
