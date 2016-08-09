package com.findme.application.Activities;




import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.findme.application.R;

import com.findme.application.Adapters.DnameAdapter;
import com.findme.application.Adapters.DrawerListAdapter;
import com.findme.application.Adapters.PostAdapter;
import com.findme.application.Controllers.Application;
import com.findme.application.Controllers.CheckinController;
import com.findme.application.Controllers.PostController;
import com.findme.application.Controllers.StudentControllers;
import com.findme.application.Controllers.PostController.Connection.GetMyTaskListener;
import com.findme.application.Models.DoctorNameListItem;
import com.findme.application.Models.NavItem;
import com.findme.application.Models.PostListItem;

//import com.Gp.findme.Controllers.Application;
//import com.Gp.findme.Controllers.UserController;
//import com.Gp.findme.Controllers.UserController.Connection.GetMyTaskListener;



import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.Log;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;


public class HomePage extends ActionBarActivity implements SearchView.OnQueryTextListener , GetMyTaskListener{

	

	  private SearchView mSearchView;
	    private ListView mListView;
	    private ArrayList<DoctorNameListItem> team_;
	    private ArrayList<PostListItem> Post_follower;

        ListView mDrawerList;
        RelativeLayout mDrawerPane;
        TextView username;
        private ActionBarDrawerToggle mDrawerToggle;
        private DrawerLayout mDrawerLayout;
        private String mActivityTitle;



    ArrayList<NavItem> mNavItems = new ArrayList<NavItem>();


public static String FormalEmail;
public static String Name = " ";

	    private DnameAdapter DrnameAdapter;
	    private PostAdapter postsAdapter;

     Boolean check =false;
 String selectedValue=""; 

   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //     getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        mActivityTitle = "Home";
        getSupportActionBar().setTitle(mActivityTitle);
        setContentView(R.layout.activity_home_page);


        mSearchView=(SearchView) findViewById(R.id.searchView1);
        mListView=(ListView) findViewById(R.id.listView1);

        Post_follower=new ArrayList<PostListItem>();
        
    	
        team_=new ArrayList<DoctorNameListItem>();
    	team_.addAll(StudentControllers.StaffInfo);
    
		PostController.GetFollowerPost(StudentControllers.currentActiveStudent.getId(), this);
     

//******************************************************** Menu *********************************************************************/


		TextView userName = (TextView) findViewById(R.id.userName);
		userName.setText(StudentControllers.currentActiveStudent.getName());
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

    private Menu  mMenu;
    @Override

    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_page, menu);

        // Save the menu reference
        mMenu = menu;
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

     



        if(id == R.id.action_refresh) {
            Intent home = new Intent(getApplicationContext(),HomePage.class);
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
//  **************************************************** Service ****************************************************
    @Override 
	public void onGetMyTaskComplete(String response) throws JSONException {
		// TODO Auto-generated method stub
		
		Post_follower.removeAll(Post_follower);
		JSONObject o= new JSONObject(response);
String STATUS  = o.getString("status");
String POST  = o.getString("post");

if(STATUS.equals("null") | POST.equals("null"))
{

}
else{
 JSONArray A_content=o.getJSONArray("content");
	JSONArray A_owner=o.getJSONArray("owner");
		JSONArray A_name=o.getJSONArray("name");
		JSONArray A_time=o.getJSONArray("time");

		  for(int i=0;i<A_content.length();i++)
            {
			  PostListItem p =new PostListItem();

            	String NaDrName;
            	String postcontent;
            	NaDrName=	A_name.getString(i);
            	postcontent= A_content.getString(i);
            	p.setDrName(NaDrName);
            	p.setPost(postcontent);
            	p.setSID(A_owner.getString(i));
            	Log.i("Name", NaDrName);
            	 SimpleDateFormat dateFormat =new SimpleDateFormat("MM/dd/yyyy hh:mm aa");
            	 Date ConvertDate = null;
                 try {
            		 ConvertDate =dateFormat.parse(A_time.getString(i));
            	} catch (ParseException e) {
            		// TODO Auto-generated catch block
            		e.printStackTrace();
            	}
             	Log.i("Time  ", A_time.getString(i));
             	p.setTime(A_time.getString(i));
                 long now =System.currentTimeMillis();
                 CharSequence relavetime = DateUtils.getRelativeTimeSpanString(ConvertDate.getTime(), now,  DateUtils.SECOND_IN_MILLIS);
            	 p.setTime(relavetime.toString());
            	Post_follower.add(p);
            	//drNameArray.add(NaDrName);
            }

	}

postsAdapter=new PostAdapter(HomePage.this, Post_follower);
mListView.setAdapter(postsAdapter);
mListView.setTextFilterEnabled(true);
setupSearchView();
mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

  

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
	                int position, long id) {
				// TODO Auto-generated method stub
				if(check==false)
				{
					 
				
			 FormalEmail=	postsAdapter.getItem(position).getSID();
			 Name =postsAdapter.getItem(position).getDrName();
			 Log.i("tttttttttttttttttttttttt", Name);
			 Intent Doctor_intent = new Intent(Application.getAppContext(),
						
						DoctorTimLine.class);
				//Doctor_intent.putExtra("email", StudentEmail);
				Doctor_intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

			Application.getAppContext().startActivity(Doctor_intent);
		/*	StaffController controller = Application.getUserController();
  		//controller.Get_Status(StaffController.StudentEmail, FormalEmail);
 StaffController.Getmyposts(FormalEmail ,null); */
			}
				else
				{
					 FormalEmail=	DrnameAdapter.getItem(position).getID();
					 Name = DrnameAdapter.getItem(position).getDrName();
					 Log.i("uuuuuuuuuuuuuuuuuuuuuu", Name);
					 Intent Doctor_intent = new Intent(Application.getAppContext(),
								
								DoctorTimLine.class);
						//Doctor_intent.putExtra("email", StudentEmail);
						Doctor_intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

					Application.getAppContext().startActivity(Doctor_intent);
	  				/*StaffController controller = Application.getUserController();
	          		//controller.Get_Status(StaffController.StudentEmail, FormalEmail);
	         StaffController.Getmyposts(FormalEmail,null); */
				}
			} 
         });	
		
	}

	private void setupSearchView()
    {
        mSearchView.setIconifiedByDefault(false);
        mSearchView.setOnQueryTextListener(this);
        mSearchView.setSubmitButtonEnabled(true);
        mSearchView.setQueryHint("Search Here");
    }

    @Override
    public boolean onQueryTextChange(String newText)
    {

        if (TextUtils.isEmpty(newText)) {
            mListView.clearTextFilter();
            mListView.setAdapter(postsAdapter);
            
            
            
        

        } 
        else {
        	  DrnameAdapter=new DnameAdapter(HomePage.this, team_);
              mListView.setAdapter(DrnameAdapter);

            mListView.setFilterText(newText);
            check =true;
        }
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query)
    {
        return false;
    }

}