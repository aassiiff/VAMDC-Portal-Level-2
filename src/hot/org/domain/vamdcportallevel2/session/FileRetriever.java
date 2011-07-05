package org.domain.vamdcportallevel2.session;

import org.jboss.seam.annotations.Name;

@Name("fileRetriever")
public class FileRetriever {
	
	private byte[] fileData;

	public byte[] getFileData() {
		//return fileData;
		return "This is a test".getBytes();

	}

	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}
	
	

}
