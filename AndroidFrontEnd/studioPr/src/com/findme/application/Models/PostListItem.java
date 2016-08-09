package com.findme.application.Models;

public class PostListItem {
	private String drName;
	private String post;
	private String Time;
	private String PostID;
	private String SID;
	private String String_Time;
	
	public PostListItem(String drName, String post, String Time ,String PostID ,String SID, String String_Time) {
		super();
		this.drName = drName;
		this.post = post;
		this.Time = Time;
		this.PostID=PostID;
		this.SID=SID;
		this.String_Time=String_Time;
		
	}
	
	public PostListItem() {
		//super();
		// TODO Auto-generated constructor stub
		drName =" ";
		Time = " ";
		post = " ";
		PostID= " ";
		SID=" ";
		String_Time= " ";
	}

	public String getDrName() {
		return drName;
	}
	public void setDrName(String drName) {
		this.drName = drName;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getTime() {
		return Time;
	}
	public void setTime(String time) {
		Time = time;
	}

	public String getPostID() {
		return PostID;
	}

	public void setPostID(String postID) {
		PostID = postID;
	}

	public String getSID() {
		return SID;
	}

	public void setSID(String sID) {
		SID = sID;
	}

	public String getString_Time() {
		return String_Time;
	}

	public void setString_Time(String string_Time) {
		String_Time = string_Time;
	}
	
	

}
