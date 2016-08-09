package com.findme.application.Controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.findme.application.Controllers.PostController.Connection.GetMyTaskListener;
import com.findme.application.Controllers.PostController.Connection.GetMyTaskListenerP;


public class PostController
{
	private static PostController postController;
   public static String PostID ;
	public static PostController getInstance() {
		if (postController == null)
			postController = new PostController();
		return postController;
	}
	private PostController() {

	}
	public static void CreatePost(String owner ,String content ,String time , GetMyTaskListenerP listener_)
	{
		
		new Connection(listener_).execute(
				"http://192.168.42.211:7777/basic/web/index.php?r=post/creatpost",
				owner, content,time, "creatpost"); 
        Log.i("after", "no");
	}
	public static void DeletePost(String id)
	{
		GetMyTaskListener listener;
	      listener =null;

		new Connection(listener).execute("http://192.168.42.211:7777/basic/web/index.php?r=post/delete",
				id, "delete");

	}
	
	public static void UpdatePost(String PostID ,String PostContent ,String NewTime)
	{
		GetMyTaskListener listener;
		listener =null;
		new Connection(listener).execute("http://192.168.42.211:7777/basic/web/index.php?r=post/update",
				PostID,PostContent,NewTime, "update");
	}
	public static void Getmyposts(String id ,GetMyTaskListenerP listener_)
	{

		new Connection(listener_).execute("http://192.168.42.211:7777/basic/web/index.php?r=post/getmyposts",
				id, "getmyposts");

	}
	public static void Getstaffpost(String id ,GetMyTaskListener listener)
	{

		new Connection(listener).execute("http://192.168.42.211:7777/basic/web/index.php?r=post/getstaffpost",
				id, "getstaffpost");

	}
	public static void GetFollowerPost(String id ,GetMyTaskListener listener)
	{
		new Connection(listener).execute("http://192.168.42.211:7777/basic/web/index.php?r=post/getfollowerpost",
				id, "getfollowerpost");

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
	 		public Connection(GetMyTaskListenerP listener_) {
	 			this.listener_ = listener_;  
	 		}
	 		public Connection(GetMyTaskListener listener) {
	 			this.listener = listener;  
	 		}
	 		
			@Override 
			protected String doInBackground(String... params) {
				// TODO Auto-generated method stub 
				URL url; 
				serviceType = params[params.length - 1];
				String urlParameters = "";
				
				
				 if(serviceType.equals("creatpost"))
				{
					urlParameters="&owner=" + params[1] +"&content=" + params[2] +"&time=" + params[3];

				}
				
				 //// delete post  
				else if (serviceType.equals("delete"))
				{ 
					urlParameters = "&postID=" + params[1] ;
				}
				else if  (serviceType.equals("update"))
				{
					urlParameters="&postID=" + params[1]+"&newcontent=" + params[2] + "&newtime=" + params[3];
				}
				else if (serviceType.equals("getstaffpost")) 
				{
					urlParameters = "&id=" + params[1] ;
				} 
				else if (serviceType.equals("getmyposts")) 
				{
					urlParameters = "&id=" + params[1] ;
				}
				else if (serviceType.equals("getfollowerpost"))
				{
					urlParameters = "&id=" + params[1] ;
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
	               
					
					
					 if(serviceType.equals("getmyposts"))
							{
						
						try {

							listener_.onGetMyTaskComplet(result);
					
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
							}
					
					else if(serviceType.equals("getstaffpost"))
					{
				
				try {

					listener.onGetMyTaskComplete(result);
			
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					}
					
			  
					else if(serviceType.equals("getfollowerpost"))       
					{

						
	         try {

					listener.onGetMyTaskComplete(result);
			
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}// show-agenda-for-update-exception
				}
					else if(serviceType.equals("creatpost"))
					{
						Log.i("LOLO", "Aliaa");
						try {
Log.i("RES", listener_.toString());
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
