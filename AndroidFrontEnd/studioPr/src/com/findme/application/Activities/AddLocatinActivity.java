package com.findme.application.Activities;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import com.findme.application.R;
import com.findme.application.Adapters.CheckInAdapter;
import com.findme.application.Adapters.DrawerListAdapter;
import com.findme.application.Controllers.AgendaController;
import com.findme.application.Controllers.Application;
import com.findme.application.Controllers.CheckinController;
import com.findme.application.Controllers.StaffController;
import com.findme.application.Models.CheckinEntity;
import com.findme.application.Models.NavItem;
import com.findme.application.Models.Slot;
import com.findme.application.R.layout;
import com.findme.application.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class AddLocatinActivity  extends ActionBarActivity implements OnClickListener,
android.widget.CompoundButton.OnCheckedChangeListener{
	ArrayList<String>location  =new ArrayList<String>();
ArrayList<String>Location  =new ArrayList<String>();
ListView list;
CheckInAdapter bcAdapter = null;
ArrayList<String> allcancelled =  new ArrayList<String>();
EditText et;
Button b;
TextView e;
Button b2;

ListView mDrawerList;
RelativeLayout mDrawerPane;
TextView username;
private ActionBarDrawerToggle mDrawerToggle;
private DrawerLayout mDrawerLayout;

ArrayList<NavItem> mNavItems = new ArrayList<NavItem>();
public String mActivityTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mActivityTitle = "Add Location";
		getSupportActionBar().setTitle(mActivityTitle);
		setContentView(R.layout.activity_add_locatin);
		list = (ListView) findViewById(R.id.listview);
		et = (EditText) findViewById(R.id.et);
		b = (Button) findViewById(R.id.b);
		e = (TextView) findViewById(R.id.e);
		b2 = (Button) findViewById(R.id.b1);
		b2.setOnClickListener(this);
		b.setOnClickListener(this);
		CheckinEntity.destroy();
		CheckinEntity.locations.add("New building");
		CheckinEntity.locations.add("TA Room");
		CheckinEntity.locations.add("My Room");
		CheckinEntity.locations.add("Big Hall");
		
		
		/// when call  Get_location
		

		
	

		displayContents();
		
		
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
		getMenuInflater().inflate(R.menu.add_locatin, menu);

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

	

		/*if (id == R.id.action_refresh) {
			Intent home = new Intent(getApplicationContext(),
					StaffHomePage.class);
			startActivity(home);
			mDrawerLayout.closeDrawers();
			return true;
		}*/

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

	
	
	private void displayContents() {
		bcAdapter = new CheckInAdapter(CheckinEntity.locations, this);
		list.setAdapter(bcAdapter);

	}


	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		int pos = list.getPositionForView(buttonView);
		if (pos != ListView.INVALID_POSITION) {
			String contents = CheckinEntity.locations.get(pos);
			if (allcancelled.contains(contents)) {
				allcancelled.remove(contents);
			} else {

				allcancelled.add(contents);
			}
		}

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		if (v.getId() == R.id.b) {
			CheckinEntity.locations.add(et.getText().toString());
			et.setText(" ");
			displayContents();
			
		} else if (v.getId() == R.id.b1) {
			Log.d("jjjj", Integer.toString(allcancelled.size()));
			if (allcancelled.size() != 0) {
				JSONArray mJSONArray = new JSONArray(allcancelled);

				String content = mJSONArray.toString();
				Log.d("yyyyy", content);
				// call services
				CheckinController c = CheckinController.getInstance();
				
				c.Set_Location(StaffController.currentActiveStaff.getFormalemaill(),content );
				
				

			}
		}
		
		
	}
	
	

	/*public void get_location() throws JSONException
	{
		Bundle extras = getIntent().getExtras();
		String result = extras.getString("result");

		JSONObject o = new JSONObject(result);
		JSONArray a = o.getJSONArray("location");		
		  String STATUS  = o.getString("status");
		  if(STATUS.equalsIgnoreCase("null"))
		  {
			  location.addAll(Location);
		  }
		  else
		  {
				for(int i =0 ; i<a.length();i++ )
				{
					location.add(a.getString(i));
				}
  
		  }

			  
		
	}*/

}
