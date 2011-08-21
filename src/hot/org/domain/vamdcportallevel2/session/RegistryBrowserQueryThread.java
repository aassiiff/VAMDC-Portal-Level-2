package org.domain.vamdcportallevel2.session;

import java.util.ArrayList;

import org.domain.vamdcportallevel2.entity.ExtendedRegistry;

public class RegistryBrowserQueryThread implements Runnable{
	
	private RegistryBrowser registryBrowser;
	private ArrayList<ExtendedRegistry> extendedRegistryList;
	
	public RegistryBrowserQueryThread(RegistryBrowser registryBrowserValue){
		this.registryBrowser = registryBrowserValue;
	}
	
	
	public ArrayList<ExtendedRegistry> getExtendedRegistryList() {
		return extendedRegistryList;
	}

	public void run(){
		extendedRegistryList = registryBrowser.getExtendedRegistryList();
	}

}
