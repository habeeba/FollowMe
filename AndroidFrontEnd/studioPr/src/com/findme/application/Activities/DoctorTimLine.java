package com.findme.application.Activities;

import com.findme.application.R;
import com.findme.application.Adapters.DrawerListAdapter;
import com.findme.application.Adapters.PostAdapter;
import com.findme.application.Controllers.AgendaController;
import com.findme.application.Controllers.Application;
import com.findme.application.Controllers.CheckinController;
import com.findme.application.Controllers.FollowController;
import com.findme.application.Controllers.PostController;
import com.findme.application.Controllers.StudentControllers;
import com.findme.application.Controllers.FollowController.Connection.GetMyTaskListener;
import com.findme.application.Controllers.PostController.Connection.GetMyTaskListenerP;
import com.findme.application.Models.NavItem;
import com.findme.application.Models.PostListItem;
import com.findme.application.Models.PostTime;


import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
//import org.json.simple.parser.JSONParser;



import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.graphics.Color;
import android.net.ParseException;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.format.DateUtils;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;



public class DoctorTimLine extends ActionBarActivity implements GetMyTaskListener , GetMyTaskListenerP  {
	ProgressDialog pd;
	ImageView follow;
	ListView lv;
	ImageView agenda;
	Button bake;
    ArrayList<PostListItem>posts=new ArrayList<PostListItem>();


  
    PostAdapter adapter=null;
   String Follow_Status;
	public static String sID;
	public static String dID;
	public String follows=" ";
	public static String mActivityTitle =HomePage.Name;

	ListView mDrawerList;
	RelativeLayout mDrawerPane;
	TextView followText;
	TextView username;
	private ActionBarDrawerToggle mDrawerToggle;
	private DrawerLayout mDrawerLayout;

	ArrayList<NavItem> mNavItems = new ArrayList<NavItem>();


	///////////// delete//////////////
	private ListView mListView;
	/////////////////////////////////////////////////////////
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getSupportActionBar().setTitle(HomePage.Name);
		setContentView(R.layout.activity_doctor_tim_line);
		follow =(ImageView)findViewById(R.id.followBid);
		agenda =(ImageView)findViewById(R.id.Show);
		followText = (TextView)findViewById(R.id.followText);
		followText.setText("Follow");
		dID=HomePage.FormalEmail;

		 
		 
		PostController.Getmyposts(dID, this);
		FollowController controller = Application.getFollowController();

		controller.Get_Status(StudentControllers.currentActiveStudent.getId(), dID,this);
		///////////////////////
		 Log.i("after interface  : ",follows);

		 
	
 
        lv=(ListView) findViewById(R.id.ListId);
       

		//******************************************************** Menu *********************************************************************/
        TextView userName = (TextView) findViewById(R.id.userName);
		userName.setText(StudentControllers.currentActiveStudent.getName());
		TextView drName = (TextView) findViewById(R.id.drName);
		drName.setText(HomePage.Name);
		mNavItems.add(new NavItem("Home", "Your home page", R.drawable.ic_action_home));
		mNavItems.add(new NavItem("Show Checked-IN", "Show doctors' location", R.drawable.ic_action_checkin));
		mNavItems.add(new NavItem("Settings", "Change your settings", R.drawable.ic_action_settings));
		mNavItems.add(new NavItem("About", "Get to know about us", R.drawable.ic_action_about1));
		mNavItems.add(new NavItem("Logout", "You'll have to login next time", R.drawable.ic_action_logout));


		// DrawerLayout
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		// Populate the Navigtion Drawer with options
		mDrawerPane = (RelativeLayout) findViewById(R.id.drawerPane);
		mDrawerList = (ListView) findViewById(R.id.navList);
		DrawerListAdapter adapter = new DrawerListAdapter(this, mNavItems);
		mDrawerList.setAdapter(adapter);

		// Drawer Item click listeners
		mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				mDrawerList.setItemChecked(position, true);

				mDrawerLayout.closeDrawer(mDrawerPane);

				if ( position ==0 ) {
					Intent home = new Intent(getApplicationContext(),HomePage.class);
					startActivity(home);
					mDrawerLayout.closeDrawers();
				}

				if (position == 1)  {
					/*Intent checkin = new Intent(getApplicationContext(),GetStaffCheckinActivity.class);
					startActivity(checkin);*/
					CheckinController c= CheckinController.getInstance();
					c.Getstudentcheckin(StudentControllers.currentActiveStudent.getId());
					mDrawerLayout.closeDrawers();
				}

				if (position == 2){
					Intent profile = new Intent(getApplicationContext(),StudentProfileActivity.class);
					startActivity(profile);
					mDrawerLayout.closeDrawers();
				}
				if ( position == 3 ) {
					Intent About = new Intent(getApplicationContext(), StudentAboutActivity.class);
					startActivity(About);
					mDrawerLayout.closeDrawers();
				}

				if ( position == 4 ) {
					Intent logout = new Intent(getApplicationContext(), MainActivity.class);
					startActivity(logout);
					mDrawerLayout.closeDrawers();
				}

				/*if (id == R.id.avatar){
					Intent profile = new Intent(getApplicationContext(),StudentProfileActivity.class);
					startActivity(profile);
					mDrawerLayout.closeDrawers();
				}*/
			}
		});

		setupDrawer();


		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
	}

	private void setupDrawer() {
		mDrawerToggle = new ActionBarDrawerToggle(this,
				mDrawerLayout,R.drawable.ic_action_name,
				R.string.drawer_open,R.string.drawer_close) {

			// Called when a drawer has settled in a completely open state.
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				getSupportActionBar().setTitle("Menu");
				invalidateOptionsMenu(); // creates call to  onPrepareOptionsMenu()
			}

			// Called when a drawer has settled in a completely closed state.
			public void onDrawerClosed(View view) {
				super.onDrawerClosed(view);
				getSupportActionBar().setTitle(mActivityTitle);
				invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu();

			}
		};

		mDrawerToggle.setDrawerIndicatorEnabled(true);
		mDrawerLayout.setDrawerListener(mDrawerToggle);
	}

   /* @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // if nav drawer is opened, hide the action items
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }*/


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


	private  Menu mMenu;
	@Override


	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.doctor_tim_line, menu);

		// Save the menu reference
		mMenu = menu;
		return super.onCreateOptionsMenu(menu);
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement


		if(id == R.id.action_refresh) {
			Intent home = new Intent(getApplicationContext(),DoctorTimLine.class);
			startActivity(home);
			mDrawerLayout.closeDrawers();
			return true;
		}
		// Activate the navigation drawer toggle
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}


		return super.onOptionsItemSelected(item);
	}
	@Override
	public void onGetMyTaskComplete(String response) 
	{
			JSONObject st = null;
			try {
				st = new JSONObject(response);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				follows=st.getString("follow");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			Log.i(" interface", follows);

			Log.i("after interface  : ",follows);

		
		
			followText.setText(follows);

        //7[
        //Bundle extras = getIntent().getExtras();
        follow.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//if (follow.getText().toString() == "follow"){
				
			String checkStatus=followText.getText().toString();
			Log.i("toastss", checkStatus);
			if(checkStatus.equals("Following"))
			{
				FollowController controller = Application.getFollowController();
				controller.unfollows(StudentControllers.currentActiveStudent.getId(), dID);
				
				follow.setImageResource(R.drawable.ic_action_following);
				followText.setText("Follow");
			}
			else if(checkStatus.equals("Follow"))
			{
				FollowController controller = Application.getFollowController();
				controller.follows(StudentControllers.currentActiveStudent.getId(), dID);
				
				
				followText.setText("Following");

				follow.setImageResource(R.drawable.ic_action_follow2);			}
				
		
			}
		});
		agenda.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			/*	Intent Bookme = new Intent(getApplicationContext(),
						Bookme.class);
				startActivity(Bookme);*/
				Calendar c = Calendar.getInstance();
				SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
				c.setFirstDayOfWeek(Calendar.SATURDAY);
				c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
				String currentDate = df.format(c.getTime());
			AgendaController controller = Application.getAgendaController();
			Log.i("DoBOOK", dID);
			Log.i("CurrentDate", currentDate);

				controller.getSAgendaInfo(dID, currentDate);
				
				}
		});
	}

	@Override
	public void onGetMyTaskComplet(String response) throws JSONException 
	{
		// TODO Auto-generated method stub
		JSONObject oo= new JSONObject(response);

		  String STATUS  = oo.getString("status");
	         if(!(STATUS.equals("null")))
	         {
	        	 
	         
	        	 posts.removeAll(posts);
		JSONObject o = new JSONObject(response);
		JSONArray a = o.getJSONArray("content");
		JSONArray A_time =o.getJSONArray("time");
		JSONArray b = o.getJSONArray("name");

		for (int i = 0; i < a.length(); i++) { 
		 PostListItem P = new PostListItem();
		 P.setPost(a.getString(i));
		 SimpleDateFormat dateFormat =new SimpleDateFormat("MM/dd/yyyy hh:mm aa");
    	 Date ConvertDate = null;
         try {
    		 try {
				ConvertDate =dateFormat.parse(A_time.getString(i));
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	} catch (ParseException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
     	Log.i("Time  ", A_time.getString(i));

         long now =System.currentTimeMillis();
         CharSequence relavetime = DateUtils.getRelativeTimeSpanString(ConvertDate.getTime(), now,  DateUtils.SECOND_IN_MILLIS);
    	 P.setTime(relavetime.toString());
    	 P.setDrName(b.getString(i));
			posts.add(P);
		  //  PostsID.add(b.getString(i));

		}
	         }
	
	
        Log.i("Size of post", Integer.toString(posts.size()));
        adapter = new PostAdapter(DoctorTimLine.this, posts);
        
        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
	
	}
	


	
}
