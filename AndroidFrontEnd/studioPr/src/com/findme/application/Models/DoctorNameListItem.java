package com.findme.application.Models;

public class DoctorNameListItem
{
	private String drName;
	private String ID;
	
	public DoctorNameListItem() {
		
		drName = " ";
		ID = " ";
	}
	
	public DoctorNameListItem(String drName, String ID) {
		super();
		this.drName = drName;
		this.ID = ID;
	}
	public String getDrName() {
		return drName;
	}
	public void setDrName(String drName) {
		this.drName = drName;
	}
	public String getID() {   
		return ID;
	}
	public void setPost(String ID) {
		this.ID = ID;
	}
	

}
