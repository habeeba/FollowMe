package com.findme.application.Activities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Currency;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.findme.application.R;
import com.findme.application.Adapters.DrawerListAdapter;
import com.findme.application.Controllers.AgendaController;
import com.findme.application.Controllers.Application;
import com.findme.application.Controllers.CheckinController;
import com.findme.application.Controllers.StaffController;
import com.findme.application.Models.CheckinEntity;
import com.findme.application.Models.NavItem;

import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.ViewAnimator;

//call services at controller
//give function CheckinEntity.setLocations() array that should be displayed to user

public class ChechinActivity extends ActionBarActivity implements
		OnClickListener {

	Spinner locations;
	ArrayList<String> StaticLocations;
	ArrayList<String> mylocations;
	String myLocation;
	EditText et;
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
		mActivityTitle = "Check-IN";
		getSupportActionBar().setTitle(mActivityTitle);
		setContentView(R.layout.activity_checkin);
		
		locations = (Spinner) findViewById(R.id.locations);
		e = (TextView) findViewById(R.id.e);
		b2 = (Button) findViewById(R.id.b1);
		b2.setOnClickListener(this);
		StaticLocations = new ArrayList<String>();
		StaticLocations.add("New Building");
		StaticLocations.add("TA Room");
		try {
			get_location();
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		mylocations = CheckinEntity.getLocations();

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, mylocations);

		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		locations.setAdapter(adapter);
		locations
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {
						// TODO Auto-generated method stub
						String selected = parent.getItemAtPosition(position)
								.toString();
						if (!selected.equals("Your Exception")) {
							myLocation = selected;
						}
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}
				});
		
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
		DrawerListAdapter adapter2 = new DrawerListAdapter(this, mNavItems);
		mDrawerList.setAdapter(adapter2);

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
		getMenuInflater().inflate(R.menu.checkin, menu);

		// Save the menu reference
		mMenu = menu;
		MenuItem item = menu.add(Menu.NONE, 1, 1, "Show My Agenda");
		item = menu.add(Menu.NONE, 2, 2, "Update My Agenda");
		item = menu.add(Menu.NONE, 3, 3, "Add Exception");
		item = menu.add(Menu.NONE, 4, 4, "Show Exceptions");



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
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.b1) {
			// set my location with myLocation
			Log.d("taggggggg", myLocation);

			// chhosen variable is my location
			CheckinController C = CheckinController.getInstance();
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat dateFormat = new SimpleDateFormat(
					"MM/dd/yyyy hh:mm aa");
			String strDate = dateFormat.format(cal.getTime());
			Log.i("Time ", strDate);
			Log.i("myLocation ", myLocation);
			Log.i("Staff ", StaffController.currentActiveStaff.getFormalemaill());

			C.Add_checkin(StaffController.currentActiveStaff.getFormalemaill(),
					myLocation, strDate);
			Intent home = new Intent(getApplicationContext(),
					StaffHomePage.class);
			startActivity(home);
			mDrawerLayout.closeDrawers();
		}

	}

	public void get_location() throws JSONException {
		Bundle extras = getIntent().getExtras();
		String result = extras.getString("result");

		JSONObject o = new JSONObject(result);
		String STATUS = o.getString("status");
		Log.d("check",STATUS );
		if (STATUS.equalsIgnoreCase("null")) {
			Log.i("check", "true");
			
			CheckinEntity.setLocations(StaticLocations);
		} else {
			CheckinEntity.destroy();

			JSONArray a = o.getJSONArray("location");

			for (int i = 0; i < a.length(); i++) {
				//CheckinEntity.destroy();
				CheckinEntity.add(a.getString(i));
			}

		}

	}

}
