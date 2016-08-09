package Controllers;


import org.json.JSONException;
import org.json.JSONObject;

public class StaffEntityModel {
	private String name;
	private String Formalemail;
	private String password;

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

	private StaffEntityModel(String name, String email, String password) {
		this.name = name;
		this.Formalemail = email;
		this.password = password;

	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return Formalemail;
	}

	public String getPass() {
		return password;
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
	public static StaffEntityModel createLoginStaff(String json) {

		JSONObject object;
		try {
			object = new JSONObject(json);
			return new StaffEntityModel(object.get("name").toString(), object.get(
					"formalemail").toString(), object.get("email").toString());

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

}
