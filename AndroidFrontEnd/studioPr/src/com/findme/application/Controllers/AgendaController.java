package com.findme.application.Controllers;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.findme.application.Activities.Bookme;
import com.findme.application.Activities.CreateAgendaActivity;
import com.findme.application.Activities.CreateExceotionActivity;
import com.findme.application.Activities.LoginActivity;
import com.findme.application.Activities.MainActivity;
import com.findme.application.Activities.StaffHomePage;
import com.findme.application.Activities.UpdateExceptionAgenda;
import com.findme.application.Activities.ShowMyagendaActivity;
import com.findme.application.Activities.ShowExceptionActivity;
import com.findme.application.Activities.UpdateAgendaActivity;
import com.findme.application.Controllers.AgendaController.Connection.GetMyTaskListener;
import com.findme.application.Models.AgendaEntity;
import com.findme.application.Controllers.NotificationController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Ahmed on 3/28/2016.
 */
public class AgendaController {
	private static AgendaEntity currentActiveAgenda;
	private static AgendaController agendaController;
	public static String Date;
    public static String agendaID;
    
	public static AgendaController getInstance() {
		if (agendaController == null) {
			agendaController = new AgendaController();
		}
		return agendaController;
	}

	public AgendaEntity GetAgenda() {
		return currentActiveAgenda;
	}

	private AgendaController() {

	}

	public static void showAgendaS(String AgendID) {

		new Connection(null)
				.execute(
						"http://192.168.42.211:7777/basic/web/index.php?r=agenda/show-agenda",
						AgendID, "shows-sagenda");
	}
   
	public void showAgenda() {
		String agendaID = currentActiveAgenda.getAgendaID();
		Log.d("set IDDDDD", agendaID);
		new Connection(null)
				.execute(
						"http://192.168.42.211:7777/basic/web/index.php?r=agenda/show-agenda",
						agendaID, "show-agenda");
	}

	public void getSAgendaInfo(String owner, String date) {
		new Connection(null)
				.execute(
						"http://192.168.42.211:7777/basic/web/index.php?r=agenda/get-agenda-info",
						owner, date, "get-sagenda-info");
	}

	public void isException(String date) {
		String owner = currentActiveAgenda.getOwnter();
		new Connection(null)
				.execute(
						"http://192.168.42.211:7777/basic/web/index.php?r=agenda/is-exception",
						owner, date, "is-exception");
	}

	public void showAgendaForUpdate() {
		String agendaID = currentActiveAgenda.getAgendaID();
		Log.d("set IDDDDD", agendaID);
		new Connection(null)
				.execute(
						"http://192.168.42.211:7777/basic/web/index.php?r=agenda/show-agenda",
						agendaID, "show-agenda-for-update");
	}

	public void showAgendaForUpdate(String agendaID) {
		Log.d("set IDDDDD", agendaID);
		new Connection(null)
				.execute(
						"http://192.168.42.211:7777/basic/web/index.php?r=agenda/show-agenda",
						agendaID, "show-agenda-for-update-exception");
	}

	public void showAgendaForException() {
		String agendaID = currentActiveAgenda.getAgendaID();
		Log.d("set IDDDDD", agendaID);
		new Connection(null)
				.execute(
						"http://192.168.42.211:7777/basic/web/index.php?r=agenda/show-agenda",
						agendaID, "show-agenda-for-Exception");
	}

	public void showCreateExceptionAgenda() {
		String agendaID = currentActiveAgenda.getAgendaID();
		Log.d("set IDDDDD", agendaID);
		new Connection(null)
				.execute(
						"http://192.168.42.211:7777/basic/web/index.php?r=agenda/show-agenda",
						agendaID, "show-create-exception-agenda");
	}

	public void showChosenAgenda(String agendaID) {
		new Connection(null)
				.execute(
						"http://192.168.42.211:7777/basic/web/index.php?r=agenda/show-agenda",
						agendaID, "show-agenda");
	}

	public void getAgendaInfo(String owner, String date) {
		new Connection(null)
				.execute(
						"http://192.168.42.211:7777/basic/web/index.php?r=agenda/get-agenda-info",
						owner, date, "get-agenda-info");
	}

	public void getOtherAgendaInfo(String owner, String date) {
		new Connection(null)
				.execute(
						"http://192.168.42.211:7777/basic/web/index.php?r=agenda/get-agenda-info",
						owner, date, "get-agenda-other-info");
	}

	/**/
	public void showExceptionAgenda(String agendaID, GetMyTaskListener listener) {
		new Connection(listener)
				.execute(
						"http://192.168.42.211:7777/basic/web/index.php?r=agenda/show-agenda",
						agendaID, "show-exception-agenda");
	}

	public void GetCources() {
		new Connection(null)
				.execute(
						"http://192.168.42.211:7777/basic/web/index.php?r=agenda/get-cources",
						"get-cources");
	}

	/*
	 * public void getAgendaInfo(String owner) { new Connection(null).execute(
	 * "http://192.168.42.211:7777/basic/web/index.php?r=agenda/get-agenda-info"
	 * , owner, "get-agenda-info"); }
	 */

	public void UpdatAgenda(String data, String slotbuff, String slotnum) {
		String agendaID = currentActiveAgenda.getAgendaID();
		Log.d("set IDDDDD", agendaID);
		new Connection(null)
				.execute(
						"http://192.168.42.211:7777/basic/web/index.php?r=agenda/update-agenda",
						agendaID, data, slotbuff, slotnum, "update-agenda");
	}

	// services update exception
	public void UpdatExceptionAgenda(String agendaID, String data,
			String slotbuff, String slotnum) {
		new Connection(null)
				.execute(
						"http://192.168.42.211:7777/basic/web/index.php?r=agenda/update-exception-agenda",
						agendaID, data, slotbuff, slotnum,
						"update-exception-agenda");
	}

	public void createAgenda(String owner, String data, String slotbuff) {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		c.setFirstDayOfWeek(Calendar.SATURDAY);
		c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		String currentDate = df.format(c.getTime());
		Log.d("date", currentDate);
		new Connection(null)
				.execute(
						"http://192.168.42.211:7777/basic/web/index.php?r=agenda/create-agenda",
						owner, data, slotbuff, currentDate, "create-agenda");
	}

	public void createException(String owner, String lastUpdate, String data,
			String slotbuff, String slotnum) {
		new Connection(null)
				.execute(
						"http://192.168.42.211:7777/basic/web/index.php?r=agenda/create-exception",
						owner, lastUpdate, data, slotbuff, slotnum,
						"create-exception");
	}

	public void createCurrException(String owner, String lastUpdate,
			String data, String slotbuff, String slotnum) {
		new Connection(null)
				.execute(
						"http://192.168.42.211:7777/basic/web/index.php?r=agenda/create-exception",
						owner, lastUpdate, data, slotbuff, slotnum,
						"create-curr-exception");
	}

	public void GetExceptions(String owner) {
		new Connection(null)
				.execute(
						"http://192.168.42.211:7777/basic/web/index.php?r=agenda/get-exceptions",
						owner, "get-exceptions");
		Log.d("Create agenda", "get-exceptions");
	}

	public static class Connection extends AsyncTask<String, String, String> {

		public interface GetMyTaskListener {
			public void onGetMyTaskComplete(String response)
					throws JSONException;
		}

		GetMyTaskListener listener;
		String serviceType;

		public Connection(GetMyTaskListener listener) {
			this.listener = listener;
		}

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			URL url;
			serviceType = params[params.length - 1];

			String urlParameters = "";
			if (serviceType.equals("show-agenda")) {
				urlParameters = "agendaID=" + params[1];
			} else if (serviceType.equals("get-agenda-info")) {
				urlParameters = "owner=" + params[1] + "&date=" + params[2];
			} else if (serviceType.equals("get-agenda-other-info")) {
				urlParameters = "owner=" + params[1] + "&date=" + params[2];
			} else if (serviceType.equals("create-agenda")) {
				urlParameters = "owner=" + params[1] + "&data=" + params[2]
						+ "&slotbuff=" + params[3] + "&date=" + params[4];
				Log.d("Create agenda param", "create agenda param");

			} else if (serviceType.equals("get-exceptions")) {
				urlParameters = "owner=" + params[1];
				Log.d("Create agenda param", "create agenda param");
				// show-agenda-for-update-exception
			} else if (serviceType.equals("show-agenda-for-update")) {
				urlParameters = "agendaID=" + params[1];
			} else if (serviceType.equals("show-agenda-for-update-exception")) {
				urlParameters = "agendaID=" + params[1];
			} else if (serviceType.equals("show-exception-agenda")) {
				urlParameters = "agendaID=" + params[1];
			} else if (serviceType.equals("show-agenda-for-Exception")) {
				urlParameters = "agendaID=" + params[1];
			} else if (serviceType.equals("update-agenda")) {
				urlParameters = "agendaID=" + params[1] + "&data=" + params[2]
						+ "&slotbuff=" + params[3] + "&slotnum=" + params[4];
			} else if (serviceType.equals("show-create-exception-agenda")) {
				urlParameters = "agendaID=" + params[1];
			} else if (serviceType.equals("create-exception")) {
				urlParameters = "owner=" + params[1] + "&lastUpdate="
						+ params[2] + "&data=" + params[3] + "&slotbuff="
						+ params[4] + "&slotnum=" + params[5];
			} else if (serviceType.equals("update-exception-agenda")) {
				urlParameters = "agendaID=" + params[1] + "&data=" + params[2]
						+ "&slotbuff=" + params[3] + "&slotnum=" + params[4];
			} else if (serviceType.equals("is-exception")) {
				urlParameters = "owner=" + params[1] + "&date=" + params[2];
			} else if (serviceType.equals("create-curr-exception")) {
				urlParameters = "owner=" + params[1] + "&lastUpdate="
						+ params[2] + "&data=" + params[3] + "&slotbuff="
						+ params[4] + "&slotnum=" + params[5];
			} else if (serviceType.equals("shows-sagenda")) {
				urlParameters = "agendaID=" + params[1];
			} else if (serviceType.equals("get-sagenda-info")) {
				urlParameters = "owner=" + params[1] + "&date=" + params[2];
			}
			//
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
			// try {

			if (result != null) {
				if (serviceType.equals("show-agenda")) {
					Intent ShowAgendaIntent = new Intent(
							Application.getAppContext(),
							ShowMyagendaActivity.class);
					ShowAgendaIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					ShowAgendaIntent.putExtra("result", result);
					Application.getAppContext().startActivity(ShowAgendaIntent);

				} else if (serviceType.equals("get-agenda-info")) {
					try {
						JSONObject obj = new JSONObject(result);
						String agendaID = (String) obj.getString("agendaID");
						if (agendaID.equals("notAgenda")) {
							Intent updateAgendaIntent = new Intent(
									Application.getAppContext(),
									CreateAgendaActivity.class);
							updateAgendaIntent
									.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							updateAgendaIntent.putExtra("result", result);
							Application.getAppContext().startActivity(
									updateAgendaIntent);
						} else {
							String agendaOwner = (String) obj.get("owner");
							String date = (String) obj.getString("lastUpdate");
							String type = (String) obj.get("type");
							currentActiveAgenda.setAgendaID(agendaID);
							currentActiveAgenda.setLastUpdate(date);
							currentActiveAgenda.setOwnter(agendaOwner);
							currentActiveAgenda.setType(type);
							currentActiveAgenda.setAgendaForShow(agendaID);
							currentActiveAgenda.setOlastUpdate(date);

							Intent StaffHomePage = new Intent(
									Application.getAppContext(),
									StaffHomePage.class);
							StaffHomePage
									.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							StaffHomePage.putExtra("status",
									"Registered successfully");
							Application.getAppContext().startActivity(
									StaffHomePage);
						}
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else if (serviceType.equals("create-agenda")) {
					try {
						JSONObject obj = new JSONObject(result);
						String agendaID = (String) obj.getString("status");
						String owner = StaffController.currentActiveStaff
								.getFormalemaill();
						Calendar c = Calendar.getInstance();
						SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
						c.setFirstDayOfWeek(Calendar.SATURDAY);
						c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
						String currentDate = df.format(c.getTime());
						currentActiveAgenda.setAgendaID(agendaID);
						currentActiveAgenda.setLastUpdate(currentDate);
						currentActiveAgenda.setOwnter(owner);
						currentActiveAgenda.setType("perm");
						currentActiveAgenda.setAgendaForShow(agendaID);
						currentActiveAgenda.setOlastUpdate(currentDate);
						AgendaController controller = Application
								.getAgendaController();
						controller.showAgenda();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				else if (serviceType.equals("get-exceptions")) {
					Intent ShowExceptionAgendaIntent = new Intent(
							Application.getAppContext(),
							ShowExceptionActivity.class);
					ShowExceptionAgendaIntent
							.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					ShowExceptionAgendaIntent.putExtra("result", result);
					Application.getAppContext().startActivity(
							ShowExceptionAgendaIntent);

				}

				else if (serviceType.equals("show-exception-agenda")) {
					try {
						listener.onGetMyTaskComplete(result);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}// show-agenda-for-update-exception
				} else if (serviceType.equals("show-agenda-for-update")) {

					Intent updateAgendaIntent = new Intent(
							Application.getAppContext(),
							UpdateAgendaActivity.class);
					updateAgendaIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					updateAgendaIntent.putExtra("result", result);
					Application.getAppContext().startActivity(
							updateAgendaIntent);
				} else if (serviceType
						.equals("show-agenda-for-update-exception")) {

					Intent updateAgendaIntent = new Intent(
							Application.getAppContext(),
							UpdateExceptionAgenda.class);
					updateAgendaIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					updateAgendaIntent.putExtra("result", result);
					Application.getAppContext().startActivity(
							updateAgendaIntent);
				} else if (serviceType.equals("show-agenda-for-Exception")) {

					Intent updateAgendaIntent = new Intent(
							Application.getAppContext(),
							CreateExceotionActivity.class);
					updateAgendaIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					updateAgendaIntent.putExtra("result", result);
					Application.getAppContext().startActivity(
							updateAgendaIntent);
				} else if (serviceType.equals("update-agenda")) {

					AgendaController controller = Application
							.getAgendaController();
					controller.showAgenda();

					// NotificationController.SendUpdateNoti(
					// currentActiveAgenda.getAgendaID(),
					// currentActiveAgenda.getOwnter(), "updateAgenda");

				} else if (serviceType.equals("show-create-exception-agenda")) {
					Intent chooseExceptionDate = new Intent(
							Application.getAppContext(),
							CreateExceotionActivity.class);
					chooseExceptionDate.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					chooseExceptionDate.putExtra("result", result);
					Application.getAppContext().startActivity(
							chooseExceptionDate);
				} else if (serviceType.equals("create-exception")) {
					/*
					 * try { JSONObject obj = new JSONObject(result); String
					 * agendaID = (String) obj.getString("status");
					 * Log.d("tage", agendaID);
					 * NotificationController.SendCreateENoti(agendaID,
					 * currentActiveAgenda.getOwnter(), "createException"); }
					 * catch (JSONException e) { // TODO Auto-generated catch //
					 * block e.printStackTrace(); }
					 */

					AgendaController controller = AgendaController
							.getInstance();
					String owner = controller.GetAgenda().getOwnter();
					controller.GetExceptions(owner);

				} else if (serviceType.equals("create-curr-exception")) {

					AgendaController controller = AgendaController
							.getInstance();
					String owner = controller.GetAgenda().getOwnter();
					Calendar c = Calendar.getInstance();
					SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
					c.setFirstDayOfWeek(Calendar.SATURDAY);
					c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
					String currentDate = df.format(c.getTime());

					controller.getAgendaInfo(owner, currentDate);

				} else if (serviceType.equals("update-exception-agenda")) {
					AgendaController controller = AgendaController
							.getInstance();

					String owner = currentActiveAgenda.getOwnter();
					controller.GetExceptions(owner);

					/*
					 * try { JSONObject obj = new JSONObject(result); String
					 * agendaID = (String) obj.getString("status");
					 * NotificationController.SendUpdateENoti(agendaID,
					 * currentActiveAgenda.getOwnter(), "updateException"); }
					 * catch (JSONException e) { // TODO Auto-generated catch //
					 * block e.printStackTrace(); }
					 */
				} else if (serviceType.equals("is-exception")) {
					Log.d("huhuhu", result);
					if (result.equals("[]")) {

						AgendaController controller = AgendaController
								.getInstance();
						controller.showCreateExceptionAgenda();
					} else {
						// toast you this exception already exist you can update
						// it
						AgendaController controller = AgendaController
								.getInstance();
						String owner = currentActiveAgenda.getOwnter();
						controller.GetExceptions(owner);

					}
				} else if (serviceType.equals("get-cources")) {
					try {
						currentActiveAgenda = new AgendaEntity();
						JSONArray cources = new JSONArray(result);
						ArrayList<String> courc = new ArrayList<String>();
						for (int i = 0; i < cources.length(); i++) {
							JSONObject obj = new JSONObject();
							obj = (JSONObject) cources.get(i);
							String cource = obj.getString("status");
							courc.add(cource);
						}
						currentActiveAgenda.setCources(courc);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Intent updateAgendaIntent = new Intent(
							Application.getAppContext(), LoginActivity.class);
					updateAgendaIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					Application.getAppContext().startActivity(
							updateAgendaIntent);

				} else if (serviceType.equals("get-agenda-other-info")) {
					try {
						JSONObject obj = new JSONObject(result);
						String agendaID = (String) obj.getString("agendaID");
						currentActiveAgenda.setAgendaForShow(agendaID);
						String date = (String) obj.getString("lastUpdate");
						currentActiveAgenda.setOlastUpdate(date);
						AgendaController controller = Application
								.getAgendaController();
						controller.showChosenAgenda(agendaID);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else if (serviceType.equals("get-sagenda-info")) {
					try {
						JSONObject obj = new JSONObject(result);
						 agendaID = (String) obj.getString("agendaID");
						if (agendaID.equals("notAgenda")) {
							Toast.makeText(Application.getAppContext(),
									"status -> failed to book Staff Member Dont Make Agenda ",
									Toast.LENGTH_LONG).show();
							// toast
						} else {
							Log.i("First", "OK");
							String date = (String) obj.getString("lastUpdate");

							Date = date;
							Intent Bookme = new Intent(Application.getAppContext(),
									Bookme.class);
							Bookme.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							Bookme.putExtra("agendaID", agendaID);
							Application.getAppContext().startActivity(Bookme);
						//	showAgendaS(agendaID);
						}
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			

			}
		}
	}

}
