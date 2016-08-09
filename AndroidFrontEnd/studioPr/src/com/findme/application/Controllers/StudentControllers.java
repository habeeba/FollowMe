package com.findme.application.Controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;



import com.findme.application.Activities.HomePage;
import com.findme.application.Controllers.BookController.Connection;
import com.findme.application.Controllers.PostController.Connection.GetMyTaskListenerP;
import com.findme.application.Controllers.StudentControllers.Connection.GetMyTaskListener;
import com.findme.application.Models.DoctorNameListItem;


public class StudentControllers
{
	public static   ArrayList<DoctorNameListItem>StaffInfo =new ArrayList<DoctorNameListItem>() ;
	public static   ArrayList<String>SlotsBooks =new ArrayList<String>() ;
    public static String STATUS =" ";
	public static StudentEntityModel currentActiveStudent;
	private static StudentControllers studentController;
	public static StudentControllers getInstance() {
		if (studentController == null) 
			studentController = new StudentControllers();
		return studentController;
	}
	public void StudentLogin(String email  ,String Password)
	{
		 GetMyTaskListener listener;
	      listener =null;
		new Connection(listener).execute(
				"http://192.168.42.211:7777/basic/web/index.php?r=student/loginstudent",
				email, Password, "loginstudent");
        Log.i("after", "no");	
	}
	public static void signUpStudent(String id,String Name, String email, String password) {
		GetMyTaskListener listener;
	      listener =null;
		new Connection(listener)
				.execute(
						"http://192.168.42.211:7777/basic/web/index.php?r=student/signupstudent",
						id,Name, email, password, "signupstudent");
	}
	
	 public static void GetStaffInfo()
     {
    	 GetMyTaskListener listener;
	      listener =null;
    	 new Connection(listener)
			.execute(
					"http://192.168.42.211:7777/basic/web/index.php?r=staff/getstaffinfo",
					"getstaffinfo"); 
  
     }
	public static void book(String slotID, String Date  ,String  content ,String booker )
	{
		GetMyTaskListener listener;
	      listener =null;
		new Connection(listener).execute(
				"http://192.168.42.211:7777/basic/web/index.php?r=book/create-book",
				slotID ,Date , content,booker , "create-book");
	}
	public static void cancelbook(String slotID, String Date   ,String booker)
	{
		GetMyTaskListener listener;
	      listener =null;
		new Connection(listener).execute(
				"http://192.168.42.211:7777/basic/web/index.php?r=book/cancel-book",
				slotID ,Date ,booker , "cancel-book");
	}
	 public static void getBookslots(String slotID)
	 {
		 GetMyTaskListener listener;
	      listener =null;
		 new Connection(listener).execute(
					"http://192.168.42.211:7777/basic/web/index.php/?r=book/getslotids",
					slotID, "getslotids"); 
	 }
    public static class Connection extends AsyncTask<String, String, String> {

		public interface GetMyTaskListener {
			public void onGetMyTaskComplete(String response)
					throws JSONException;


		}
		public interface GetMyTaskListenerP {
	public void onGetMyTaskComplet(String response)
					throws JSONException; 
		/*	public void onGetMyTaskComplet2(String response)
					throws JSONException;*/
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
			 if (serviceType.equals("signupstudent"))
			{

			
			urlParameters = "&id=" + params[1] +"&name=" + params[2] +"&email=" + params[3]
				+ "&pass=" + params[4];

			}
			
			 else if (serviceType.equals("loginstudent"))
			{
				
			
				urlParameters = "&email=" + params[1] + "&pass=" + params[2];

			}
			
			else if (serviceType.equals("create-book"))
			{

			
				urlParameters = "&slotID=" + params[1] + "&Date=" + params[2] +  "&content=" + params[3] + "&booker=" + params[4];


			}
			else if (serviceType.equals("cancel-book"))
			{

			
				urlParameters = "&slotID=" + params[1] + "&Date=" + params[2] +  "&booker=" + params[3];


			}
		
			 //getslotids
	
			
				else if (serviceType.equals("getslotids"))
				{

				
					urlParameters = "&sid=" + params[1] ;


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
					if(serviceType.equals("signupstudent"))
					{
						Toast.makeText(Application.getAppContext(),
								"This ID Is already taken Please Enter another ID",
								Toast.LENGTH_LONG).show();	
					}
					else if (serviceType.equals("loginstudent"))
					{
						Toast.makeText(Application.getAppContext(),
								"incorrect password Or  ID  , Enter Correct Data ",
								Toast.LENGTH_LONG).show();	
					}
					else if(serviceType.equals("create-book"))
					{
						Toast.makeText(Application.getAppContext(),
								" Error occured in Book This Slot",
								Toast.LENGTH_LONG).show();		
					}
					
					Toast.makeText(Application.getAppContext(),
							"Error occured", Toast.LENGTH_LONG).show();								
					return;
				}
              
				 if (serviceType.equals("signupstudent")) {
					Log.i("Student", result);
               			//GetStaffInfo(listener);
					currentActiveStudent = StudentEntityModel.createLoginStudent(result);

					GetStaffInfo();				

					
						
					
               				   }
				
				
				 else if (serviceType.equals("loginstudent")) {
					 
					currentActiveStudent = StudentEntityModel.createLoginStudent(result);

					
					GetStaffInfo();
/*Intent HomePage = new Intent(Application.getAppContext(),
							
							Testttttts.class);
										HomePage.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

										Application.getAppContext().startActivity(HomePage);	*/
														}
				
				
				
				else if(serviceType.equals("getstaffinfo"))
				{
					StaffInfo.removeAll(StaffInfo);
    
					JSONObject o = new JSONObject(result);
					if(!o.getString("status").equalsIgnoreCase("null"))
							{
						
							
					JSONArray a = o.getJSONArray("id");
					JSONArray b = o.getJSONArray("name");

					for (int i = 0; i < a.length(); i++) {  
						DoctorNameListItem S =new DoctorNameListItem();
					    S.setPost(a.getString(i));
					    S.setDrName(b.getString(i));
					    StaffInfo.add(S);

					}
				}
					Intent HomePage = new Intent(Application.getAppContext(),
							
							HomePage.class);
										HomePage.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

										Application.getAppContext().startActivity(HomePage);				
				}
		  
				
				else if(serviceType.equals("create-book"))
				{
					
					
					
				}
				else if(serviceType.equals("getslotids"))
				{
					
					SlotsBooks.removeAll(SlotsBooks);
				    
					JSONObject o = new JSONObject(result);
					STATUS=o.getString("status");
					if(!o.getString("status").equalsIgnoreCase("null"))
							{
						
							
					JSONArray a = o.getJSONArray("slotID");

					for (int i = 0; i < a.length(); i++) {  
					   SlotsBooks.add((a.getString(i)));
					    

					}
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
