package com.findme.application.Models;

import java.util.ArrayList;

public class CheckinEntity {
    public static ArrayList<String> locations = new ArrayList<String>();
    
    
    public static ArrayList<String> getLocations() {
		return locations;
	}


	public static void setLocations(ArrayList<String> locations) {
		CheckinEntity.locations = locations;
	}

	public static void add(String location){
    	locations.add(location);
    }
	public static void destroy(){
    	locations.removeAll(locations);
    	locations = new ArrayList<String>();
    }
}
