package com.findme.application.Activities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.findme.application.BuildConfig;
import com.findme.application.R;
import com.findme.application.Adapters.DrawerListAdapter;
import com.findme.application.Adapters.PostAdapter;
import com.findme.application.Adapters.PostTimeAdapter;
import com.findme.application.Controllers.AgendaController;
import com.findme.application.Controllers.Application;
import com.findme.application.Controllers.CheckinController;
import com.findme.application.Controllers.FollowController.Connection.GetMyTaskListenerF;
import com.findme.application.Controllers.FollowController;
import com.findme.application.Controllers.NotificationController;
import com.findme.application.Controllers.PostController;
import com.findme.application.Controllers.StaffController;
import com.findme.application.Controllers.StudentControllers;
import com.findme.application.Controllers.PostController.Connection.GetMyTaskListener;
import com.findme.application.Controllers.PostController.Connection.GetMyTaskListenerP;
import com.findme.application.Models.Mail;
import com.findme.application.Models.NavItem;
import com.findme.application.Models.PostListItem;
import com.findme.application.Models.PostTime;

//import com.Gp.findme.Controllers.Application;
//import com.Gp.findme.Controllers.UserController;

import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.text.format.DateUtils;
import android.text.format.Time;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class StaffHomePage extends ActionBarActivity implements
		GetMyTaskListener, GetMyTaskListenerP ,GetMyTaskListenerF {
	ImageView post;

	Button show;
	Button update;
	ListView lv;

	String strDate;
	String idstaff;
	CharSequence relavetime;
	String con;
	final Context context = this;
public static String SendPost = " ";
	String Post = "";
	ArrayList<String> pp = new ArrayList<String>();
	EditText postcontent;
	// ArrayList<String>postss=new ArrayList<String>();
	ArrayList<PostTime> Postss = new ArrayList<PostTime>();
	ArrayList<String> PostsID = new ArrayList<String>();

	String content;
	String Name = " ";
	PostTimeAdapter Post_Adapter;
public static String[]Arr;


	ListView mDrawerList;
	RelativeLayout mDrawerPane;
	TextView username;
	private ActionBarDrawerToggle mDrawerToggle;
	private DrawerLayout mDrawerLayout;

	ArrayList<NavItem> mNavItems = new ArrayList<NavItem>();
	public String mActivityTitle;

	// ////////////////delete//////////////////
	ArrayList<PostListItem> Posts = new ArrayList<PostListItem>();

	ArrayList<String> posts = new ArrayList<String>();

	// //////////////////////////////////////////////////

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mActivityTitle = "Home";
		getSupportActionBar().setTitle(mActivityTitle);
		setContentView(R.layout.activity_staff_home_page);
		// Post=StaffController.posts;

		PostController.Getstaffpost(
				StaffController.currentActiveStaff.getFormalemaill(), this);
		TextView userName = (TextView) findViewById(R.id.userName);
		userName.setText(StaffController.currentActiveStaff.getName());
		post = (ImageView) findViewById(R.id.PostId);

		lv = (ListView) findViewById(R.id.listView1);
		postcontent = (EditText) findViewById(R.id.TextPostId1);
		content = postcontent.getText().toString();
		
		mNavItems.add(new NavItem("Home", "Your home page",
				R.drawable.ic_action_home));
		
		mNavItems.add(new NavItem("Check-IN", "Share your location",
				R.drawable.ic_action_checkin));
		
		mNavItems.add(new NavItem("Add Location ", "Share your location",
				R.drawable.ic_action_checkin));
		
		mNavItems.add(new NavItem("Settings", "Change your settings",
				R.drawable.ic_action_settings));
		
		mNavItems.add(new NavItem("About", "Get to know about us",
				R.drawable.ic_action_about1));
		
		
		
		mNavItems.add(new NavItem("Logout", "You'll have to login next time",
				R.drawable.ic_action_logout));

		// DrawerLayout
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		// Populate the Navigtion Drawer with options
		mDrawerPane = (RelativeLayout) findViewById(R.id.drawerPane);
		mDrawerList = (ListView) findViewById(R.id.navList);
		DrawerListAdapter adapter = new DrawerListAdapter(this, mNavItems);
		mDrawerList.setAdapter(adapter);

		// Drawer Item click listeners
		mDrawerList
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						mDrawerList.setItemChecked(position, true);

						mDrawerLayout.closeDrawer(mDrawerPane);

						if (position == 0) {
							Intent home = new Intent(getApplicationContext(),
									StaffHomePage.class);
							startActivity(home);
							mDrawerLayout.closeDrawers();
						}
						if (position == 1) {
						/*	Intent home = new Intent(getApplicationContext(),
									StaffHomePage.class);
							startActivity(home);*/
							CheckinController c =CheckinController.getInstance();
							c.Get_Location(StaffController.currentActiveStaff.getFormalemaill());
							mDrawerLayout.closeDrawers();
						}
						if (position == 2) {
							
							Intent AddLocatinActivity = new Intent(getApplicationContext(),
									AddLocatinActivity.class);
							startActivity(AddLocatinActivity);
							mDrawerLayout.closeDrawers();
							
							
						}
						
						if (position == 3) {
							Intent profile = new Intent(
									getApplicationContext(),
									StaffProfileActivity.class);
							startActivity(profile);
							mDrawerLayout.closeDrawers();
						}
						
						

						if (position == 4) {
							Intent About = new Intent(getApplicationContext(),
									StaffAboutActivity.class);
							startActivity(About);
							mDrawerLayout.closeDrawers();
						}
						
						if (position == 5) {
							Intent logout = new Intent(getApplicationContext(),
									MainActivity.class);
							startActivity(logout);
							mDrawerLayout.closeDrawers();
						}
						
					}
				});

		setupDrawer();

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
	}

	private void setupDrawer() {
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_action_name, R.string.drawer_open,
				R.string.drawer_close) {

			// Called when a drawer has settled in a completely open state.
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				getSupportActionBar().setTitle("Menu");
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}

			// Called when a drawer has settled in a completely closed state.
			public void onDrawerClosed(View view) {
				super.onDrawerClosed(view);
				getSupportActionBar().setTitle(mActivityTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu();

			}
		};

		mDrawerToggle.setDrawerIndicatorEnabled(true);
		mDrawerLayout.setDrawerListener(mDrawerToggle);
	}

	/*
	 * @Override public boolean onPrepareOptionsMenu(Menu menu) { // if nav
	 * drawer is opened, hide the action items boolean drawerOpen =
	 * mDrawerLayout.isDrawerOpen(mDrawerList);
	 * menu.findItem(R.id.action_settings).setVisible(!drawerOpen); return
	 * super.onPrepareOptionsMenu(menu); }
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	private Menu mMenu;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.refresh, menu);

		// Save the menu reference
		mMenu = menu;
		MenuItem item = menu.add(Menu.NONE, 1, 1, "Show My Agenda");
		item = menu.add(Menu.NONE, 2, 2, "Update My Agenda");
		item = menu.add(Menu.NONE, 3, 3, "Add Exception");
		item = menu.add(Menu.NONE, 4, 4, "Show Exceptions");
	//	item = menu.add(Menu.NONE, 5, 5, "Add Location");


		return super.onCreateOptionsMenu(menu);
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

	

		if (id == R.id.action_refresh) {
			Intent home = new Intent(getApplicationContext(),
					StaffHomePage.class);
			startActivity(home);
			mDrawerLayout.closeDrawers();
			return true;
		}

		// Activate the navigation drawer toggle
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}

		if (id == 1) {
			AgendaController controller = AgendaController.getInstance();
			controller.showAgenda();

			// AgendaController controller = AgendaController.getInstance();
			// controller.showAgendaForUpdate();
			/*
			 * Intent home = new
			 * Intent(getApplicationContext(),ShowAgendaActivity.class);
			 * startActivity(home);
			 */
		}
		
		if (id == 2) {
			AgendaController c =  AgendaController.getInstance();
			c.showAgendaForUpdate();
		}
		if (id == 3) {
			Intent ShowExceptionAgendaIntent = new Intent(
					Application.getAppContext(),
					ChooseExceptionDateActivity.class);
			ShowExceptionAgendaIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			Application.getAppContext()
					.startActivity(ShowExceptionAgendaIntent);
			
		}
		if (id == 4) {
			AgendaController controller = AgendaController.getInstance();
			String owner = controller.GetAgenda().getOwnter();
			controller.GetExceptions(owner);
		}
		if (id == 5) {
			Intent AddLocatinActivity = new Intent(
					Application.getAppContext(),
					AddLocatinActivity.class);
			AddLocatinActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			Application.getAppContext()
					.startActivity(AddLocatinActivity);	
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onGetMyTaskComplete(String response) throws JSONException {
		// TODO Auto-generated method stub
		JSONObject oo = new JSONObject(response);

		String STATUS = oo.getString("status");
		Log.i("status", STATUS);
		if (!(STATUS.equals("null"))) {

			Log.i("Da5l", "POST");
			Postss.removeAll(Postss);
			JSONObject o = new JSONObject(response);
			JSONArray a = o.getJSONArray("content");
			JSONArray b = o.getJSONArray("time");
			JSONArray ID = o.getJSONArray("id");
			JSONArray Name = o.getJSONArray("name");
			Log.i("Da5l", "bA3D");

			for (int i = 0; i < a.length(); i++) {
				Log.i("kABL", "pOST");

				PostTime P = new PostTime();
				P.setPost(a.getString(i));
				Log.i("Content", a.getString(i));
				P.setPostID(ID.getString(i));
				Log.i("ID", ID.getString(i));

				P.setName(Name.getString(i));
				Log.i("Name", Name.getString(i));

				SimpleDateFormat dateFormat = new SimpleDateFormat(
						"MM/dd/yyyy hh:mm aa");
				Date ConvertDate = null;
				try {
					ConvertDate = dateFormat.parse(b.getString(i));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				long now = System.currentTimeMillis();
				CharSequence relavetime = DateUtils.getRelativeTimeSpanString(
						ConvertDate.getTime(), now, DateUtils.SECOND_IN_MILLIS);
				P.setTime(relavetime.toString());
				Log.i("Time", relavetime.toString());

				Postss.add(P);
				// PostsID.add(b.getString(i));

			}
		}
		Post_Adapter = new PostTimeAdapter(StaffHomePage.this, Postss);
		Log.i("Size Of", Integer.toString(Postss.size()));
		Log.i("IN set ", "ok");
		lv.setAdapter(Post_Adapter);
		Log.i("After_set ", "ok");

		lv.setTextFilterEnabled(true);

		post.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// follow.setText("Following");
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat dateFormat = new SimpleDateFormat(
						"MM/dd/yyyy hh:mm aa");
				 strDate = dateFormat.format(cal.getTime());
				Date ConvertDate = null;
				try {
					ConvertDate = dateFormat.parse(strDate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				long now = System.currentTimeMillis();
				relavetime = DateUtils.getRelativeTimeSpanString(
						ConvertDate.getTime(), now, DateUtils.SECOND_IN_MILLIS);
				content = postcontent.getText().toString();
				Name = StaffController.currentActiveStaff.getName();
				StaffController controller = Application.getStaffController();
				// String con = "المحاضره اتلغت تمام ";
				SendPost ="Post From: " +StaffController.currentActiveStaff.getName() + "\n"
						+ "Content : " + content +"\n" 
						+ "Time : " + strDate
						;
				if(content.length()>0)
				{
					PostController.CreatePost(
							controller.currentActiveStaff.getFormalemaill(),
							content, strDate, StaffHomePage.this);	
				}
				
				

			}
		});
		// lv.setAdapter(adapter);
		// Log.i("Staffhome", PostController.PostID);
		registerForContextMenu(lv);

	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {

		// Get the list

		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.staff_home_page, menu);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
				.getMenuInfo();
		final int ID = info.position;

		switch (item.getItemId()) {

		case R.id.deletepost:
			Log.i("IDOut : ", Postss.get(ID).getPostID());

			PostController.DeletePost(Postss.get(ID).getPostID());
			Postss.remove(info.position);

			Post_Adapter.notifyDataSetChanged();
			break;

		case R.id.updatepost:
			LayoutInflater li = LayoutInflater.from(context);
			View promptsView = li.inflate(R.layout.prompts, null);

			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
					context);
			alertDialogBuilder.setView(promptsView);
			final EditText userInput = (EditText) promptsView
					.findViewById(R.id.editTextDialogUserInput);
			userInput.setText(Postss.get(ID).getPost());

			// set dialog message
			alertDialogBuilder
					.setCancelable(false)
					.setPositiveButton("Edit",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									// get user input and set it to result
									// edit text
									// result.setText(userInput.getText());
									Calendar cal = Calendar.getInstance();
									SimpleDateFormat dateFormat = new SimpleDateFormat(
											"MM/dd/yyyy hh:mm aa");
									strDate = dateFormat.format(cal.getTime());
									Date ConvertDate = null;
									try {
										ConvertDate = dateFormat.parse(strDate);
									} catch (ParseException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									long now = System.currentTimeMillis();
									CharSequence relavetime = DateUtils
											.getRelativeTimeSpanString(
													ConvertDate.getTime(), now,
													DateUtils.SECOND_IN_MILLIS);
									String PostID = Postss.get(ID).getPostID();
									// PostController.UpdatePost(Postss.get(ID).getPostID(),
									// userInput.getText().toString(),
									// Postss.get(ID).getTime());
									PostController.UpdatePost(Postss.get(ID)
											.getPostID(), userInput.getText()
											.toString(), Postss.get(ID)
											.getSTRING_Time());
	                                  Log.i("Time CHECK ", Postss.get(ID).getTime());

                                  Log.i("Time Is ", Postss.get(ID).getSTRING_Time());
									Postss.remove(ID);
									PostTime P = new PostTime();
									P.setPost(userInput.getText().toString());
									P.setPostID(PostID);
									P.setName(StaffController.currentActiveStaff.getName());
									P.setTime(relavetime.toString());
									Postss.add(ID, P);
									Post_Adapter.notifyDataSetChanged();

								}
							})
					.setNegativeButton("Cancel",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									dialog.cancel();
								}
							});

			// create alert dialog
			AlertDialog alertDialog = alertDialogBuilder.create();

			// show it
			alertDialog.show();
		default:
			return super.onContextItemSelected(item);

		}
		return false;

	}

	@Override
	public void onGetMyTaskComplet(String response) throws JSONException {
		JSONObject oo = new JSONObject(response);

		String POSTID = oo.getString("postID");
		PostTime P = new PostTime();
		// P.setPost(content);
		P.setPost(content);
		String TIME = relavetime.toString();
		P.setTime(TIME);
		P.setPostID(POSTID);
		P.setName(StaffController.currentActiveStaff.getName());
		P.setSTRING_Time(strDate);
		Postss.add(0, P);
		lv.setAdapter(Post_Adapter);
		postcontent.setText("");

		// NotificationController.Setnotification(StaffController.currentActiveStaff.getFormalemaill(),POSTID,
		// "POST");
        FollowController.Getfollowing(StaffController.currentActiveStaff.getFormalemaill(), this);
	}
	
	@Override
	public void onGetMyTaskCompletF(String response) throws JSONException {
		// TODO Auto-generated method stub
		JSONObject oo = new JSONObject(response);
		String STATUS = oo.getString("status");
		Log.i("status", STATUS);

		if (!(STATUS.equals("null"))) {
           
			JSONObject o = new JSONObject(response);
			JSONArray a = o.getJSONArray("studentEmail");
		
		     Arr=new String[a.length()];

			for (int i = 0; i < a.length(); i++) {

				Arr[i]=a.getString(i);
			}
	        new SendEmailAsyncTask().execute();

		}
		

		
	}
	
	  class SendEmailAsyncTask extends AsyncTask <Void, Void, Boolean> {
          Mail m = new Mail("aliaaalifci@gmail.com", "aliaalolo20120245"); 

          public SendEmailAsyncTask() {
              if (BuildConfig.DEBUG) Log.v(SendEmailAsyncTask.class.getName(), "SendEmailAsyncTask()");
            // String[] toArr = {"engaliaaali16@gmail.com", "hahabeeba_baioumy@fci-cu.edu.eg" ,"amal.khaled3@yahoo.com"}; 
              for(int i =0 ;i <StaffHomePage.Arr.length;i++)
              {
            	  Log.i("Email", StaffHomePage.Arr[i]);
              }
              m.setTo(Arr);
              m.setFrom("aliaaalifci@gmail.com"); 
              m.setSubject("Email from Follow Me App");
              Log.i("Body", "Tmaaaam");
              Log.i("Message", StaffHomePage.SendPost);
              m.setBody(StaffHomePage.SendPost);
          }

          @Override
          protected Boolean doInBackground(Void... params) {
              if (BuildConfig.DEBUG) Log.v(SendEmailAsyncTask.class.getName(), "doInBackground()");
              try {
                  m.send();
                  return true;
              } catch (AuthenticationFailedException e) {
                  Log.e(SendEmailAsyncTask.class.getName(), "Bad account details");
                  e.printStackTrace();
                  return false;
              } catch (MessagingException e) {
                  Log.e(SendEmailAsyncTask.class.getName(), m.getTo() + "failed");
                  e.printStackTrace();
                  return false;
              } catch (Exception e) {
                  e.printStackTrace();
                  return false;
              }
          }
  
      
      
      
}



	
	
	
	
	
	
	
	
	
	

}
