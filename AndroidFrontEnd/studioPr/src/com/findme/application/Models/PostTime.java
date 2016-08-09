package com.findme.application.Models;

public class PostTime {
	private String Time;
	private String post;
	private String PostID;
	private String Name;
	private String SID;
	private String STRING_Time;
	
	public PostTime() {
		
		Time = " ";
		post = " ";
		PostID =" ";
		Name = " ";
		SID=" ";
		STRING_Time=" ";
	}
	
	public PostTime(String Time, String post ,String PostID ,String Name  ,String SID , String STRING_Time) {
		super();
		this.Time = Time;
		this.post = post;
		this.PostID=PostID;
		this.Name=Name;
		this.SID=SID;
		this.STRING_Time=STRING_Time;
		
	}
	public String getTime() {
		return Time;
	}
	public void setTime(String Time) {
		this.Time = Time;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}

	public String getPostID() {
		return PostID;
	}

	public void setPostID(String postID) {
		PostID = postID;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getSID() {
		return SID;
	}

	public void setSID(String sID) {
		SID = sID;
	}

	public String getSTRING_Time() {
		return STRING_Time;
	}

	public void setSTRING_Time(String sTRING_Time) {
		STRING_Time = sTRING_Time;
	}
	

}
