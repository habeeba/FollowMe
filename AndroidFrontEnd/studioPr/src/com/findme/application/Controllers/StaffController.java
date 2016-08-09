package com.findme.application.Controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.json.JSONException;
import org.json.JSONObject;

import com.findme.application.Activities.CreateAgendaActivity;
import com.findme.application.Activities.StaffHomePage;



import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;


public class StaffController { 
	public static String FormalMail ="";
    public static String StudentEmail ="" ; 

	public static StaffEntityModel currentActiveStaff;
	private static StaffController staffController;

  

	public static StaffController getInstance() {
		if (staffController == null)
			staffController = new StaffController();
		return staffController;
	}
	
	private StaffController() {

	}

 
	public void signUp(String formalemail,String Name, String email, String password) 
	{
	
		new Connection()
				.execute(
						"http://192.168.42.211:7777/basic/web/index.php?r=staff/signup",
						//"http://192.168.42.211:7777/basic/web/index.php?r=staff/test",
						formalemail,Name, email, password, "signup");
	}
	
	
	public void login(String formalemail, String password) {
      System.out.println("after");
      
		new Connection().execute(
				"http://192.168.42.211:7777/basic/web/index.php?r=staff/login",
				formalemail, password, "login");  
        Log.i("after", "no"); 
 
	}
	
     public static class Connection extends AsyncTask<String, String, String> {
  		String serviceType;

 	
		@Override 
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub 
			URL url; 
			serviceType = params[params.length - 1];
			String urlParameters = "";
			
			if (serviceType.equals("login"))
			{
				
			
				urlParameters = "&id=" + params[1] + "&pass=" + params[2];
				Log.i("kkkkkkkkkkk","oo");

			}
			else if (serviceType.equals("signup"))
			{
				Log.i("Da5l", "oks");

			
			urlParameters = "&id=" + params[1] +"&name=" + params[2] +"&email=" + params[3]
				+ "&pass=" + params[4];
			Log.i("Sign TTTT", urlParameters);

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
			Log.i(" Test iiiiiiiiiiiii ", result);
		try {
				
				JSONObject object = new JSONObject(result);
      
				if (!object.has("status")
						|| object.getString("status").equals("failed")) {
					if(serviceType.equals("login"))
					{
						Toast.makeText(Application.getAppContext(),
								"incorrect password Or  Email  , Enter Correct Data ",
								Toast.LENGTH_LONG).show();
					}
					if(serviceType.equals("signup"))
					{
						Toast.makeText(Application.getAppContext(),
								"This Email Is already taken Please Enter another Email ",
								Toast.LENGTH_LONG).show();
					}					
					Toast.makeText(Application.getAppContext(),
							"Error occured", Toast.LENGTH_LONG).show();								
					return;
				}
              
				if (serviceType.equals("login")) { 
 
					currentActiveStaff = StaffEntityModel.createLoginStaff(result);
     					
				 
           	/*	Intent StaffHomePage = new Intent(Application.getAppContext(),
				     	StaffHomePage.class);
           		StaffHomePage.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
           		StaffHomePage.putExtra("status", "Registered successfully");
				Application.getAppContext().startActivity(StaffHomePage);*/
					Calendar c = Calendar.getInstance();
					SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
					c.setFirstDayOfWeek(Calendar.SATURDAY);
					c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
					String currentDate = df.format(c.getTime());
					AgendaController controller = AgendaController.getInstance();
					controller.getAgendaInfo(StaffController.currentActiveStaff.getFormalemaill(),currentDate );
				

				/*	AgendaController controller = AgendaController.getInstance();
					controller.showAgenda();
					
				*/
				}
				else if (serviceType.equals("signup")) 
				{ 
					  
					currentActiveStaff = StaffEntityModel.createLoginStaff(result);
   					
         		   
         	/*Intent StaffHomePage = new Intent(Application.getAppContext(),
				     	StaffHomePage.class);
         		StaffHomePage.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
         		StaffHomePage.putExtra("status", "Registered successfully");
				Application.getAppContext().startActivity(StaffHomePage);*/
				/*	Intent checkin = new Intent(Application.getAppContext(),
							SetStaffCheckinActivity.class);
					checkin.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					checkin.putExtra("status", "Registered successfully");
					Application.getAppContext().startActivity(checkin);				
			
    */
					Intent CreatAgendaIntent = new Intent(Application.getAppContext(),
							CreateAgendaActivity.class);
					CreatAgendaIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					CreatAgendaIntent.putExtra("status", "Registered successfully");
					Application.getAppContext().startActivity(CreatAgendaIntent);	
				} 
				
				
			
			
				
							} catch (JSONException e) {
								String er = "errrrrrror";
								Log.i("errrrrrrrrrrrrrrrrror ", er);
								e.printStackTrace();
							}
				            
						}
				
		}

}
