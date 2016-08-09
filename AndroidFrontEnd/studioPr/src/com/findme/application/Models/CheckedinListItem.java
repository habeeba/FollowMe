package com.findme.application.Models;

/**
 * Created by Habeeba on 6/29/2016.
 */

public class CheckedinListItem {

    private String drName;
    private String Checkedin;
    private String Time;
    private String DID;
    private String String_Time;

    public CheckedinListItem(String drName, String checkedin, String Time ,String DID, String String_Time) {
        super();
        this.drName = drName;
        this.Checkedin = Checkedin;
        this.Time = Time;
        this.DID=DID;
        this.String_Time=String_Time;

    }

    public CheckedinListItem() {
        //super();
        // TODO Auto-generated constructor stub
        drName =" ";
        Time = " ";
        Checkedin = " ";
        DID=" ";
        String_Time= " ";
    }

    public String getDrName() {
        return drName;
    }
    public void setDrName(String drName) {
        this.drName = drName;
    }
    public String getCheckedin() {
        return Checkedin;
    }
    public void setCheckedin(String Checkedin) {
        this.Checkedin = Checkedin;
    }
    public String getTime() {
        return Time;
    }
    public void setTime(String time) {
        Time = time;
    }


    public String getDID() {
        return DID;
    }

    public void setDID(String DID) {
        DID = DID;
    }

    public String getString_Time() {
        return String_Time;
    }

    public void setString_Time(String string_Time) {
        String_Time = string_Time;
    }




}
