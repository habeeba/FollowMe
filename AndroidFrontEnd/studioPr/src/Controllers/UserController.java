
/*
package Controllers;


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

import android.R.integer;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;



public class UserController { 
	public static String FormalMail ="";
    public static String StudentEmail ="" ; 

	public static StaffEntityModel currentActiveUser;
	private static UserController userController;
   public static   ArrayList<String>Posts =new ArrayList<String>() ;
   public static   ArrayList<String>PostsID =new ArrayList<String>() ;

   public static   ArrayList<DoctorNameListItem>StaffInfo =new ArrayList<DoctorNameListItem>() ;
   public static ArrayList <PostListItem> PostOfFollowers =new ArrayList<PostListItem>();
   public static ArrayList <String> drNameArray =new ArrayList<String>();
  public static String Status_json_follow;
  public static GetMyTaskListener listener_;
	public static UserController getInstance() {
		if (userController == null)
			userController = new UserController();
		return userController;
	}
	public static ArrayList<String> getArray() 
	{ 
	        return Posts; 
	}

	private UserController() {

	}

	public void login(String formalemail, String password) {
      System.out.println("after");
		new Connection(null).execute(
				"http://10.0.3.2:7777/basic/web/index.php?r=staff/login",
				formalemail, password, "login");  
        Log.i("after", "no");
 
	}
	public void follows(String Student_Id, String Staff_Id)
	{
		new Connection (null).execute("http://10.0.3.2:7777/basic/web/index.php?r=follow/add",
				Student_Id, Staff_Id, "add");
			
	}
	public void unfollows(String Student_Id, String Staff_Id)
	{
		new Connection (null).execute("http://10.0.3.2:7777/basic/web/index.php?r=follow/remove",
				Student_Id, Staff_Id, "remove");
			
	}
	public void Get_Status(String Student_Id, String Staff_Id,GetMyTaskListener listener)
	{
		new Connection (listener).execute("http://10.0.3.2:7777/basic/web/index.php?r=follow/getstatus",
				Student_Id, Staff_Id, "getstatus");
			
	}
	public void Test() {
	      System.out.println("after");
			new Connection(null).execute(
					"http://10.0.3.2:7777/basic/web/index.php?r=staff/test",
					"test");
	        Log.i("after", "no");

		}
	public void StudentLogin(String email  ,String Password)
	{
		new Connection(null).execute(
				"http://10.0.3.2:7777/basic/web/index.php?r=student/loginstudent",
				email, Password, "loginstudent");
        Log.i("after", "no");	
	}
	public void CreatePost(String owner ,String content ,String time)
	{
		new Connection(null).execute(
				"http://10.0.3.2:7777/basic/web/index.php?r=post/creatpost",
				owner, content,time, "creatpost"); 
        Log.i("after", "no");
	}
	public static void Getmyposts(String id)
	{

		new Connection(null).execute("http://10.0.3.2:7777/basic/web/index.php?r=post/getmyposts",
				id, "getmyposts");

	}
	/// function delete post /// param post ID 
	
	public static void DeletePost(String id)
	{

		new Connection(null).execute("http://10.0.3.2:7777/basic/web/index.php?r=post/delete",
				id, "delete");

	}
	
	
	public static void GetFollowerPost(String id ,GetMyTaskListener listener)
	{
Log.i("follower test","oks");
		new Connection(listener).execute("http://10.0.3.2:7777/basic/web/index.php?r=post/getfollowerpost",
				id, "getfollowerpost");

	}
	
	public void signUp(String formalemail,String Name, String email, String password) {

		new Connection(null)
				.execute(
						"http://10.0.3.2:7777/basic/web/index.php?r=staff/signup",
						//"http://10.0.3.2:7777/basic/web/index.php?r=staff/test",
						formalemail,Name, email, password, "signup");
	}
	
	public void signUpStudent(String id,String Name, String email, String password) {

		new Connection(null)
				.execute(
						"http://10.0.3.2:7777/basic/web/index.php?r=student/signupstudent",
						//"http://10.0.3.2:7777/basic/web/index.php?r=staff/test",
						id,Name, email, password, "signupstudent");
	}
	
     public static void GetStaffInfo()
     {
    	 new Connection(null)
			.execute(
					"http://10.0.3.2:7777/basic/web/index.php?r=staff/getstaffinfo",
					//"http://10.0.3.2:7777/basic/web/index.php?r=staff/test",
					"getstaffinfo"); 
  
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
			if (serviceType.equals("login"))
			{
				
			
				urlParameters = "&id=" + params[1] + "&pass=" + params[2];
				Log.i("kkkkkkkkkkk","oo");

			}
			if (serviceType.equals("loginstudent"))
			{
				
			
				urlParameters = "&email=" + params[1] + "&pass=" + params[2];

			}
			else if (serviceType.equals("signup"))
			{
				Log.i("Da5l", "oks");

			
			urlParameters = "&id=" + params[1] +"&name=" + params[2] +"&email=" + params[3]
				+ "&pass=" + params[4];
			Log.i("Sign TTTT", urlParameters);

			}
			
			else if (serviceType.equals("signupstudent"))
			{

			
			urlParameters = "&id=" + params[1] +"&name=" + params[2] +"&email=" + params[3]
				+ "&pass=" + params[4];

			}
			
			else if(serviceType.equals("creatpost"))
			{
				urlParameters="&owner=" + params[1] +"&content=" + params[2] +"&time=" + params[3];

			}
			else if (serviceType.equals("add"))
			{
				urlParameters="&sID="+params[1]+"&dID="+params[2];
			}
			else if (serviceType.equals("remove"))
			{
				urlParameters="&sID="+params[1]+"&dID="+params[2];
			}
			else if (serviceType.equals("getstatus"))
			{
				urlParameters="&sID="+params[1]+"&dID="+params[2];
			}
			 //// delete post  
			else if (serviceType.equals("delete"))
			{ 
				urlParameters = "&postID=" + params[1] ;
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
               
				if (serviceType.equals("login")) { 
 
					  currentActiveUser = StaffEntityModel.createLoginStaff(result);
     					
           		   FormalMail = currentActiveUser.getEmail();
				 
					
					Getmyposts(FormalMail);
				

				
				}
				if (serviceType.equals("loginstudent")) {
					 
					JSONObject objectj = new JSONObject(result);  
               StudentEmail =objectj.getString("email");
					
					GetStaffInfo();				
				}
				else if(serviceType.equals("add"))
				{
					Log.i("Follow ","el follow tam ya kper ");

				}
				else if(serviceType.equals("getstatus"))
				{
				//	JSONObject st =new JSONObject(result);
				//Status_json_follow=st.getString("follow");	
				try {
					Log.i("kapl", "oks");

					listener.onGetMyTaskComplete(result);
					Log.i("b33333333333333d", "oks");
			
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
				else if(serviceType.equals("test"))
				{
					Intent homeIntent = new Intent(Application.getAppContext(),
					     	Testttttts.class);
					homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					homeIntent.putExtra("status", "Registered successfully");
					Application.getAppContext().startActivity(homeIntent);
				}
				else if (serviceType.equals("signup")) {
                  
                		   currentActiveUser = StaffEntityModel.createLoginStaff(result);
       					
                		   FormalMail = currentActiveUser.getEmail();
                		   Log.i("Sign Up", "Okkp");
				Intent DoctorIntent = new Intent(Application.getAppContext(),
							StaffHomePage.class);
				DoctorIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				DoctorIntent.putExtra("status", "Registered successfully");
					Application.getAppContext().startActivity(DoctorIntent);
                				   }
				
				else if (serviceType.equals("signupstudent")) {
					
                			//GetStaffInfo(listener);
				Intent homeIntent = new Intent(Application.getAppContext(),
						StudentSignUp.class);
					homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					homeIntent.putExtra("status", "Registered successfully");
					Application.getAppContext().startActivity(homeIntent);
                				   }
				
				
				else if(serviceType.equals("getmyposts"))
						{
					
					JSONObject oo= new JSONObject(result);

					  String STATUS  = oo.getString("status");
				         if(!(STATUS.equals("null")))
				         {
				        	 
				         
				        	 Posts.removeAll(Posts);
					JSONObject o = new JSONObject(result);
					JSONArray a = o.getJSONArray("content");
					JSONArray b = o.getJSONArray("id");

					for (int i = 0; i < a.length(); i++) { 
					 
					    Posts.add(a.getString(i)); 
					    PostsID.add(b.getString(i));

					}
				         }
				if(StudentEmail=="")
				{
					Intent staff_intent = new Intent(Application.getAppContext(),
							
							StaffHomePage.class);
				staff_intent.putExtra("id", FormalMail);
staff_intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
  
				Application.getAppContext().startActivity(staff_intent);
				}
				else
				{
					Intent Doctor_intent = new Intent(Application.getAppContext(),
							
							DoctorTimLine.class);
					Doctor_intent.putExtra("email", StudentEmail);
					Doctor_intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

				Application.getAppContext().startActivity(Doctor_intent);
				}

					
						}
				else if(serviceType.equals("getstaffinfo"))
				{
					StaffInfo.removeAll(StaffInfo);

					JSONObject o = new JSONObject(result);
					JSONArray a = o.getJSONArray("id");
					JSONArray b = o.getJSONArray("name");

					for (int i = 0; i < a.length(); i++) {  
						DoctorNameListItem S =new DoctorNameListItem();
					    S.setPost(a.getString(i));
					    S.setDrName(b.getString(i));
					    StaffInfo.add(S);

					}
					Intent HomePage = new Intent(Application.getAppContext(),
							
							HomePage.class);
	        	 HomePage.putExtra("FormalEmail", FormalMail);
										HomePage.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

										Application.getAppContext().startActivity(HomePage);
			//	GetFollowerPost(StudentEmail);
  
 
				
				}
		  
				else if(serviceType.equals("getfollowerpost"))       
				{

					
         try {
				Log.i("kapl", "oks");

				listener.onGetMyTaskComplete(result);
				Log.i("b3d", "oks");
		
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}// show-agenda-for-update-exception
			}
				else if(serviceType.equals("write"))
				{
					System.out.println("Ka Hona sALMAN");
					JSONObject o = new JSONObject(result);
             String out =o.getString("status");
             Log.i("Create Post ", out);
				}
				
							} catch (JSONException e) {
								String er = "errrrrrror";
								Log.i("errrrrrrrrrrrrrrrrror ", er);
								e.printStackTrace();
							}
				            
						}
				
		}

}
*/
