package com.findme.application.Controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.findme.application.Activities.AddLocatinActivity;
import com.findme.application.Activities.Bookme;
import com.findme.application.Activities.ChechinActivity;
import com.findme.application.Activities.GetStaffCheckinActivity;
import com.findme.application.Activities.HomePage;
import com.findme.application.Activities.StaffHomePage;
import com.findme.application.Activities.UpdateExceptionAgenda;
import com.findme.application.Controllers.CheckinController.Connection.GetMyTaskListener;
import com.findme.application.Models.CheckinEntity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class CheckinController {

	public static GetMyTaskListener listener_;
	private static CheckinController checkController;

	public static CheckinController getInstance() {
		if (checkController == null)
			checkController = new CheckinController();
		return checkController;
	}

	private CheckinController() {

	}

	public void Set_Location(String staffID, String location) {
		GetMyTaskListener listener;
		listener = null;
		new Connection(listener)
				.execute(
						"http://192.168.42.211:7777/basic/web/index.php?r=location/setlocations",
						staffID, location, "setlocations");

	}

	public void Get_Location(String staffID) {
		GetMyTaskListener listener;
		listener = null;
		new Connection(listener)
				.execute(
						"http://192.168.42.211:7777/basic/web/index.php?r=location/getlocations",
						staffID, "getlocations");

	}

	public void Add_checkin(String staffID, String mylocation, String time) {
		GetMyTaskListener listener;
		listener = null;
		new Connection(listener)
				.execute(
						"http://192.168.42.211:7777/basic/web/index.php?r=checkin/addcheckin",
						staffID, mylocation, time, "addcheckin");

	}

	public void Getstudentcheckin(String sID) {
		GetMyTaskListener listener;
		listener = null;
		new Connection(listener)
				.execute(
						"http://192.168.42.211:7777/basic/web/index.php?r=checkin/getstudentcheckin",
						sID, "getstudentcheckin");

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

		GetMyTaskListener listener;
		GetMyTaskListenerP listener_;
		String serviceType;

		public Connection(GetMyTaskListener listener) {
			this.listener = listener;
		}

		public Connection(GetMyTaskListenerP listener_) {
			this.listener_ = listener_;
		}

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			URL url;
			serviceType = params[params.length - 1];
			String urlParameters = "";

			if (serviceType.equals("setlocations")) {
				urlParameters = "&staffID=" + params[1] + "&location="
						+ params[2];
			} else if (serviceType.equals("getlocations")) {
				urlParameters = "&staffID=" + params[1];
			} else if (serviceType.equals("addcheckin")) {
				urlParameters = "&staffID=" + params[1] + "&mylocation="
						+ params[2] + "&time=" + params[3];
				Log.i("Da5l", "okkkkkkkkkkkkk");
			} else if (serviceType.equals("getstudentcheckin")) {
				urlParameters = "&sID=" + params[1];

			}
			HttpURLConnection connection;
			try {
				url = new URL(params[0]);
				Log.d("connnnn", "l");
				connection = (HttpURLConnection) url.openConnection();
				Log.d("connnnn", "2");
				connection.setDoOutput(true);
				Log.d("connnnn", "3");
				connection.setDoInput(true);
				Log.d("connnnn", "4");
				connection.setInstanceFollowRedirects(false);
				Log.d("connnnn", "5");
				connection.setRequestMethod("POST");
				Log.d("connnnn", "5");
				connection.setConnectTimeout(60000); // 60 Seconds
				connection.setReadTimeout(60000); // 60 Seconds
				Log.d("connnnn", "6");
				connection.setRequestProperty("Content-Type",
						"application/x-www-form-urlencoded;charset=UTF-8");
				OutputStreamWriter writer = new OutputStreamWriter(
						connection.getOutputStream());
				Log.d("connnnn", "8");
				writer.write(urlParameters);
				Log.d("connnnn", "9");
				writer.flush();
				Log.d("connnnn", "l0");
				String line, retJson = "";
				Log.d("connnnn", "l1");
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(connection.getInputStream()));
				Log.d("connnnn", "l2");
				while ((line = reader.readLine()) != null) {
					retJson += line;
					Log.d("connnnn", "l3");
				}
				Log.d("connnnn", "14");
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
				Log.d("iiiiiiiiiiiiii", object.getString("status"));
				if (!object.has("status")
						|| object.getString("status").equals("failed")) {
					Toast.makeText(Application.getAppContext(),
							"status -> failed", Toast.LENGTH_LONG).show();
					Toast.makeText(Application.getAppContext(),
							"Error occured", Toast.LENGTH_LONG).show();
					return;
				}

				else if (serviceType.equals("setlocations")) {
					Intent StudentCheckInIntent = new Intent(
							Application.getAppContext(),
							StaffHomePage.class);
					StudentCheckInIntent
							.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					Application.getAppContext().startActivity(
							StudentCheckInIntent);
				} else if (serviceType.equals("getlocations")) {
					Log.i("Da5l agenda ", "YES");
					Intent checkin = new Intent(Application.getAppContext(),
							ChechinActivity.class);
					checkin.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					checkin.putExtra("result", result);
					Application.getAppContext().startActivity(checkin);
				}

				else if (serviceType.equals("addcheckin")) {

					Log.i("check in ", "Done ");
				} else if (serviceType.equals("getstudentcheckin")) {
					Intent StudentCheckInIntent = new Intent(
							Application.getAppContext(),
							GetStaffCheckinActivity.class);
					StudentCheckInIntent
							.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					StudentCheckInIntent.putExtra("result", result);
					Application.getAppContext().startActivity(
							StudentCheckInIntent);

					/*
					 * public void get_location() throws JSONException { Bundle
					 * extras = getIntent().getExtras(); String result =
					 * extras.getString("result");
					 * 
					 * JSONObject o = new JSONObject(result); String STATUS =
					 * o.getString("status"); Log.d("check",STATUS ); if
					 * (!STATUS.equalsIgnoreCase("no checkin")) { Log.i("check",
					 * "true"); JSONArray a = o.getJSONArray("mylocation");
					 * JSONArray b = o.getJSONArray("Time"); JSONArray c =
					 * o.getJSONArray("name");
					 * 
					 * for (int i = 0; i < a.length(); i++) { //add student }
					 * CheckinEntity.setLocations(StaticLocations); }
					 * 
					 * }
					 */

				}

			} catch (JSONException e) {
				String er = "errrrrrror";
				Log.i("errrrrrrrrrrrrrrrrror ", er);
				e.printStackTrace();
			}

		}

	}

}
