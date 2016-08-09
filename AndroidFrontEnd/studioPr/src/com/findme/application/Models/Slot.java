package com.findme.application.Models;

public class Slot 
{
	private String SlotID;
	private String SlotNumber;
	private String content;
	private String maxBookers;
	private String bookCount; 
	private String  check_book;
	
	public Slot (){
		 SlotID  =  " ";
		 SlotNumber = " ";
		 content = " ";
		maxBookers =" ";
		 bookCount = " ";
		 check_book =" ";
		
	}
	public String getSlotID() {
		return SlotID;
	}
	public void setSlotID(String slotID) {
		SlotID = slotID;
	}
	public String getSlotNumber() {
		return SlotNumber;
	}
	public void setSlotNumber(String slotNumber) {
		SlotNumber = slotNumber;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getMaxBookers() {
		return maxBookers;
	}
	public void setMaxBookers(String maxBookers) {
		this.maxBookers = maxBookers;
	}
	public String getBookCount() {
		return bookCount;
	}
	public void setBookCount(String bookCount) {
		this.bookCount = bookCount;
	}
	public String getCheck_book() {
		return check_book;
	}
	public void setCheck_book(String check_book) {
		this.check_book = check_book;
	}


}
