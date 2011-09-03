package org.vamdc.portal.session.controller;

public class ProcessCodeManagerThread implements Runnable{

	private ProcessCodeManager processCodeManager;
	
	public ProcessCodeManagerThread(ProcessCodeManager processCodeManagerValue){
		processCodeManager = processCodeManagerValue;
	}
	
	public void run(){
		processCodeManager.readProcessSpreadSheet();
	}
}
