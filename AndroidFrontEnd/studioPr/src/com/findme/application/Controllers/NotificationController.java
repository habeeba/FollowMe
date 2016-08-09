package com.findme.application.Controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import com.findme.application.Controllers.NotificationController.Connection.GetMyTaskListenerP;



import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;


public class NotificationController
{
	private static NotificationController notificationController;

	public static NotificationController getInstance() {
		if (notificationController == null)
			notificationController = new NotificationController();
		return notificationController;
	}
	private NotificationController() 
	{

	}
	public static void Setnotification (String senderID , String typeID ,String type)
	{
		new Connection (null).execute("http://192.168.42.211:7777/basic/web/index.php?r=notification/setnotification",
				senderID, typeID , type, "setnotification");
	}
	public static void GetPostnotification (String studentID  , GetMyTaskListenerP listener_ )
	{
		new Connection (listener_).execute("http://192.168.42.211:7777/basic/web/index.php?r=notification/getpostnotification",
				studentID, "getpostnotification");
	}
	
	 public static class Connection extends AsyncTask<String, String, String> {
	
	 		public interface GetMyTaskListenerP {
	 			public void onGetMyTaskComplet(String response)
	 					throws JSONException;
	 		}

	 		GetMyTaskListenerP listener_;
	 		String serviceType;
	 		public Connection(GetMyTaskListenerP listener_) {
	 			this.listener_ = listener_;  
	 		}
	 		
	 		
	 		@Override 
			protected String doInBackground(String... params) {
				// TODO Auto-generated method stub 
				URL url; 
				serviceType = params[params.length - 1];
				String urlParameters = "";
				
				
				 if(serviceType.equals("setnotification"))
				{
					urlParameters="&senderID=" + params[1] +"&typeID=" + params[2] +"&type=" + params[3];

				}
				
				 //// delete post  
				else if (serviceType.equals("getpostnotification"))
				{ 
					urlParameters = "&studentID=" + params[1] ;
				}
				
				
				HttpURLConnection connection;
				try {
					// url = new URL(((params[0]+urlParameters).replace(" ", "%20")));
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
					Log.i("Posts",params[0]+urlParameters);

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
								"status -> failed",
								Toast.LENGTH_LONG).show();
						Toast.makeText(Application.getAppContext(),
								"Error occured", Toast.LENGTH_LONG).show();								
						return;
					}
	               
					
					
					if(serviceType.equals("setnotification"))
							{
						JSONObject o = new JSONObject(result);
						String status =o.getString("status");
	             Log.i("status Notify ", status);
						
							}
					
					else if(serviceType.equals("getpostnotification"))
					{
						Log.i("LOLO", "Aliaa");
						try {
Log.i("Notification", listener_.toString());
							listener_.onGetMyTaskComplet(result);
					
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
