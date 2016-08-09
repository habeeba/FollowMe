package com.findme.application.Controllers;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import com.findme.application.Controllers.FollowController.Connection.GetMyTaskListener;
import com.findme.application.Controllers.FollowController.Connection.GetMyTaskListenerF;



import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class FollowController {

	public static GetMyTaskListener listener_;
	private static FollowController followController;

	public static FollowController getInstance() {
		if (followController == null)
			followController = new FollowController();
		return followController;
	}

	private FollowController() {

	}

	public void follows(String Student_Id, String Staff_Id) {
		GetMyTaskListener listener;
		listener = null;
		new Connection(listener).execute(
				"http://192.168.42.211:7777/basic/web/index.php?r=follow/add",
				Student_Id, Staff_Id, "add");

	}

	public void unfollows(String Student_Id, String Staff_Id) {
		GetMyTaskListener listener;
		listener = null;
		new Connection(listener).execute(
				"http://192.168.42.211:7777/basic/web/index.php?r=follow/remove",
				Student_Id, Staff_Id, "remove");

	}

	public void Get_Status(String Student_Id, String Staff_Id,
			GetMyTaskListener listener) {
		new Connection(listener).execute(
				"http://192.168.42.211:7777/basic/web/index.php?r=follow/getstatus",
				Student_Id, Staff_Id, "getstatus");

	}

	public static void Getfollowers(String StudentID, GetMyTaskListener listener) {
		new Connection(listener)
				.execute(
						"http://192.168.42.211:7777/basic/web/index.php?r=follow/getfollowers",
						StudentID, "getfollowers");
	}

	public  static void Getfollowing(String StaffID, GetMyTaskListenerF listener__) {
		new Connection(listener__)
				.execute(
						"http://192.168.42.211:7777/basic/web/index.php?r=follow/getfollowing",
						StaffID, "getfollowing");
		Log.i("check", "Dallllllll");

	}

	public static class Connection extends AsyncTask<String, String, String> {

		public interface GetMyTaskListener {
 			public void onGetMyTaskComplete(String response)
 					throws JSONException;


 		}
 		public interface GetMyTaskListenerP {
 			public void onGetMyTaskComplet(String response)
 					throws JSONException;
 		}
 		public interface GetMyTaskListenerF {
 			public void onGetMyTaskCompletF(String response)
 					throws JSONException;
 		}
 		GetMyTaskListener listener;
 		GetMyTaskListenerP listener_;
 		GetMyTaskListenerF listener__;

 		String serviceType;

 		public Connection(GetMyTaskListener listener) {
 			this.listener = listener;  
 		}
 		public Connection(GetMyTaskListenerP listener_) {
 			this.listener_ = listener_;  
 		}
 		public Connection(GetMyTaskListenerF listener__) {
 			this.listener__ = listener__;  
 		}

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			URL url;
			serviceType = params[params.length - 1];
			String urlParameters = "";

			if (serviceType.equals("add")) {
				urlParameters = "&sID=" + params[1] + "&dID=" + params[2];
			} else if (serviceType.equals("remove")) {
				urlParameters = "&sID=" + params[1] + "&dID=" + params[2];
			} else if (serviceType.equals("getstatus")) {
				urlParameters = "&sID=" + params[1] + "&dID=" + params[2];
			} else if (serviceType.equals("getfollowers")) {
				urlParameters = "&sID=" + params[1];
			} else if (serviceType.equals("getfollowing")) {
				urlParameters = "&staffID=" + params[1];
			}
			HttpURLConnection connection;
			try {
				// url = new URL(((params[0]+urlParameters).replace(" ",
				// "%20")));
				url = new URL(params[0]);

				connection = (HttpURLConnection) url.openConnection();
				connection.setDoOutput(true);
				connection.setDoInput(true);
				connection.setInstanceFollowRedirects(false);
				connection.setRequestMethod("POST");

				connection.setConnectTimeout(60000); // 60 Seconds
				connection.setReadTimeout(60000); // 60 Seconds

				connection.setRequestProperty("Content-Type",
						"application/x-www-form-urlencoded;charset=UTF-8");
				OutputStreamWriter writer = new OutputStreamWriter(
						connection.getOutputStream());
				writer.write(urlParameters);
				writer.flush();
				String line, retJson = "";
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(connection.getInputStream()));
				while ((line = reader.readLine()) != null) {
					retJson += line;
				}
				Log.i("Posts", params[0] + urlParameters);

				Log.i("retJson", retJson);

				return retJson;

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;

		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			Log.i("test", result);
			try {

				JSONObject object = new JSONObject(result);

				if (!object.has("status")
						|| object.getString("status").equals("failed")) {
					Toast.makeText(Application.getAppContext(),
							"status -> failed", Toast.LENGTH_LONG).show();
					Toast.makeText(Application.getAppContext(),
							"Error occured", Toast.LENGTH_LONG).show();
					return;
				}

				if (serviceType.equals("add")) {
					Log.i("Follow ", "el follow tam ya kper ");

				} else if (serviceType.equals("getstatus")) {

					try {
						Log.i("kapl", "oks");

						listener.onGetMyTaskComplete(result);
						Log.i("b33333333333333d", "oks");

					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} 
				else if (serviceType.equals("getfollowers")) {
					try {

						listener.onGetMyTaskComplete(result);

					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				else if (serviceType.equals("getfollowing")) {
					try {
						Log.i("Send", "Tmama");

						listener__.onGetMyTaskCompletF(result);

					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			} catch (JSONException e) {
				String er = "errrrrrror";
				Log.i("errrrrrrrrrrrrrrrrror ", er);
				e.printStackTrace();
			}

		}

	}

}
