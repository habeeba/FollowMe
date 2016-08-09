package com.findme.application.Activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.findme.application.Adapters.CheckedinAdapter;
import com.findme.application.Adapters.DrawerListAdapter;
import com.findme.application.Adapters.PostAdapter;
import com.findme.application.Controllers.Application;
import com.findme.application.Controllers.CheckinController;
import com.findme.application.Controllers.StudentControllers;
import com.findme.application.Models.CheckedinListItem;
import com.findme.application.Models.DoctorNameListItem;
import com.findme.application.Models.NavItem;
import com.findme.application.R;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GetStaffCheckinActivity extends ActionBarActivity {


    private SearchView mSearchView;
    private ListView mListView;
    private ArrayList<CheckedinListItem> checkedin;


    ListView mDrawerList;
    RelativeLayout mDrawerPane;
    TextView username;
    private CheckedinAdapter checkedinAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private String mActivityTitle;

    ArrayList<NavItem> mNavItems = new ArrayList<NavItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityTitle = "Checked IN";
        getSupportActionBar().setTitle(mActivityTitle);
        setContentView(R.layout.activity_get_staff_checkin);
        mListView = (ListView) findViewById(R.id.listView1);
        checkedin = new ArrayList<CheckedinListItem>();
        
        try {
			get_location();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
   checkedinAdapter = new CheckedinAdapter(GetStaffCheckinActivity.this, checkedin);
        
        mListView.setAdapter(checkedinAdapter);
        checkedinAdapter.notifyDataSetChanged();  
        //****************************************** Menu***********************************************
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

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // TODO Auto-generated method stub


            }
        });

        setupDrawer();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this,
                mDrawerLayout, R.drawable.ic_action_name,
                R.string.drawer_open, R.string.drawer_close) {

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


    private Menu mMenu;

    @Override


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.getstaffcheckedin, menu);

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

        if (id == R.id.action_refresh) {
            Intent home = new Intent(getApplicationContext(), GetStaffCheckinActivity.class);
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
    public void get_location() throws JSONException {
		Bundle extras = getIntent().getExtras();
		String result = extras.getString("result");

		JSONObject o = new JSONObject(result);
		String STATUS = o.getString("status");
		Log.d("check",STATUS );
		if (!STATUS.equalsIgnoreCase("no checkin")) {
			Log.i("check", "true");
			JSONArray a = o.getJSONArray("mylocation");
			JSONArray b = o.getJSONArray("Time");
			JSONArray c = o.getJSONArray("name");

			for (int i = 0; i < a.length(); i++) {
				//add student 
				CheckedinListItem checked = new CheckedinListItem();
				checked.setCheckedin(a.getString(i));
				checked.setTime(b.getString(i));
				checked.setDrName(c.getString(i));
				checkedin.add(checked);
				
			}
		} 
}
}