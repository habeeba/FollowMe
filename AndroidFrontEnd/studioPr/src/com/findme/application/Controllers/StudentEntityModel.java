package com.findme.application.Controllers;

import org.json.JSONException;
import org.json.JSONObject;

public class StudentEntityModel {
	private String name;
	private String email;
    private String id ;
	/**
	 * Constructor accepts user data
	 * 
	 * @param name
	 *            user name
	 * @param email
	 *            user Formalemail
	 * @param password
	 *            user provided password
	 */

	private StudentEntityModel(String name, String email,String id) {
		this.name = name;
		this.email = email;
        this.id=id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 
	 * This static method will form StaffEntityModel class using json format contains
	 * user data
	 * 
	 * @param json
	 *            String in json format contains user data
	 * @return Constructed user entity
	 */
	public static StudentEntityModel createLoginStudent(String json) {

		JSONObject object;
		try {
			object = new JSONObject(json);
			return new StudentEntityModel(object.get("name").toString(), object.get(
					"email").toString(), object.get("id").toString());

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

}
