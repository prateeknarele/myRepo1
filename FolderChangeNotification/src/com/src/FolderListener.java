package com.src;

public class FolderListener implements Observer{

	String personName;

	public FolderListener(String personName) {
		this.personName = personName;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	@Override
	public void update() {
		System.out.println("Hey! "+ personName +" Folder is changed ");
		
	}
}
