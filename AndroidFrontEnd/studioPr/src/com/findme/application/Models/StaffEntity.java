package com.findme.application.Models;

import org.json.JSONException;
import org.json.JSONObject;

public class StaffEntity {

	private String id;
	private String name;
	private String email;
	/**
	 * Constructor accepts user data
	 * 
	 * @param name
	 *            user name
	 * @param email
	 *            user email
	 * @param password
	 *            user provided password
	 */

	private StaffEntity(String name, String email, String string) {
		this.name = name;
		this.email = email;
		this.id = string;

	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getid() {
		return id;
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
	public static StaffEntity createLoginUser(String json) {

		JSONObject object;
		try {
			object = new JSONObject(json);
			return new StaffEntity(object.get("name").toString(), object.get(
					"email").toString(), object.get("id").toString());

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

}
