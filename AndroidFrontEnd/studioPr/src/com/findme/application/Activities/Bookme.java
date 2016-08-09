package com.findme.application.Activities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.findme.application.R;
import com.findme.application.Adapters.DrawerListAdapter;
import com.findme.application.Controllers.AgendaController;
import com.findme.application.Controllers.Application;
import com.findme.application.Controllers.BookController;
import com.findme.application.Controllers.CheckinController;
import com.findme.application.Controllers.BookController.Connection.GetMyTaskListenerSB;
import com.findme.application.Controllers.StudentControllers;
import com.findme.application.Models.NavItem;
import com.findme.application.Models.Slot;

import android.content.res.Configuration;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class Bookme extends ActionBarActivity implements GetMyTaskListenerSB {
	// Bundle extras = getIntent().getExtras();

	public static ArrayList<Slot> myslots = new ArrayList();
	final Context context = this;

	// final String s = extras.getString("name");
	Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15,
			b16, b17, b18, b19, b20, b21, b22, b23, b24, b25, b26, b27, b28,
			b29, b30, b31, b32, b33, b34, b35, b36;
	Button[] btnWord = new Button[36];
	ArrayAdapter<String> adapter = null;
	Button[] pp = new Button[36];
	ArrayList<String> nn = new ArrayList<String>();

	public static String mActivityTitle = "Book";

	ListView mDrawerList;
	RelativeLayout mDrawerPane;
	TextView username;
	private ActionBarDrawerToggle mDrawerToggle;
	private DrawerLayout mDrawerLayout;
	ArrayList<NavItem> mNavItems = new ArrayList<NavItem>();

	// nn=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getSupportActionBar().setTitle("Book");
		setContentView(R.layout.activity_bookme);

		b1 = (Button) findViewById(R.id.button1);
		pp[0] = b1;
		b2 = (Button) findViewById(R.id.button2);
		pp[1] = b2;
		b3 = (Button) findViewById(R.id.button3);
		pp[2] = b3;
		b4 = (Button) findViewById(R.id.button4);
		pp[3] = b4;
		b5 = (Button) findViewById(R.id.button5);
		pp[4] = b5;
		b6 = (Button) findViewById(R.id.button6);
		pp[5] = b6;
		b7 = (Button) findViewById(R.id.button7);
		pp[6] = b7;
		b8 = (Button) findViewById(R.id.button8);
		pp[7] = b8;
		b9 = (Button) findViewById(R.id.button9);
		pp[8] = b9;
		b10 = (Button) findViewById(R.id.button10);
		pp[9] = b10;
		b11 = (Button) findViewById(R.id.button11);
		pp[10] = b11;
		b12 = (Button) findViewById(R.id.button12);
		pp[11] = b12;
		b13 = (Button) findViewById(R.id.button13);
		pp[12] = b13;
		b14 = (Button) findViewById(R.id.button14);
		pp[13] = b14;
		b15 = (Button) findViewById(R.id.button15);
		pp[14] = b15;
		b16 = (Button) findViewById(R.id.button16);
		pp[15] = b16;
		b17 = (Button) findViewById(R.id.button17);
		pp[16] = b17;
		b18 = (Button) findViewById(R.id.button18);
		pp[17] = b18;
		b19 = (Button) findViewById(R.id.button19);
		pp[18] = b19;
		b20 = (Button) findViewById(R.id.button20);
		pp[19] = b20;
		b21 = (Button) findViewById(R.id.button21);
		pp[20] = b21;
		b22 = (Button) findViewById(R.id.button22);
		pp[21] = b22;
		b23 = (Button) findViewById(R.id.button23);
		pp[22] = b23;
		b24 = (Button) findViewById(R.id.button24);
		pp[23] = b24;
		b25 = (Button) findViewById(R.id.button25);
		pp[24] = b25;
		b26 = (Button) findViewById(R.id.button26);
		pp[25] = b26;
		b27 = (Button) findViewById(R.id.button27);
		pp[26] = b27;
		b28 = (Button) findViewById(R.id.button28);
		pp[27] = b28;
		b29 = (Button) findViewById(R.id.button29);
		pp[28] = b29;
		b30 = (Button) findViewById(R.id.button30);
		pp[29] = b30;
		b31 = (Button) findViewById(R.id.button31);
		pp[30] = b31;
		b32 = (Button) findViewById(R.id.button32);
		pp[31] = b32;
		b33 = (Button) findViewById(R.id.button33);
		pp[32] = b33;
		b34 = (Button) findViewById(R.id.button34);
		pp[33] = b34;
		b35 = (Button) findViewById(R.id.button35);
		pp[34] = b35;
		b36 = (Button) findViewById(R.id.button36);
		pp[35] = b36;

		String agendaID = AgendaController.agendaID;
		Log.i("------------------------", "-------------------------------");
		BookController
				.ShowForBook(StudentControllers.currentActiveStudent.getId(),
						agendaID, this);

		Log.i("------------------------", "-------------------------------");

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
		getMenuInflater().inflate(R.menu.bookme, menu);

		// Save the menu reference
		mMenu = menu;
		return super.onCreateOptionsMenu(menu);
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		// noinspection SimplifiableIfStatement

		if (id == R.id.action_refresh) {
			Intent home = new Intent(getApplicationContext(), Bookme.class);
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

	/*
	 * public static boolean check_slot(String slotID) {
	 * 
	 * if(!StudentControllers.STATUS.equalsIgnoreCase("null")) { for(int i =0 ;i
	 * < StudentControllers.SlotsBooks.size();i++) {
	 * if(StudentControllers.SlotsBooks.get(i).equalsIgnoreCase(slotID)) {
	 * return true; } } } return false;
	 * 
	 * }
	 */

	@Override
	public void onGetMyTaskCompletSB(String result) throws JSONException {
		// TODO Auto-generated method stub
		myslots.removeAll(myslots);

		Log.i("TESTBook", "Da5l");
		JSONArray slots = new JSONArray(result);
		Log.i("SizeBook", Integer.toString(slots.length()));

		int count = 0;
		for (int j = 0; j < 6; j++) {

			for (int i = 0; i < 6; i++) {
				JSONObject obj = new JSONObject();
				obj = (JSONObject) slots.get(count);
				Slot S = new Slot();

				S.setMaxBookers(obj.getString("maxBookers"));
				S.setBookCount(obj.getString("bookCount"));
				S.setContent(obj.getString("content"));
				S.setSlotID(obj.getString("slotID"));
				S.setCheck_book(obj.getString("booked"));

				myslots.add(S);

				count++;
			}

		}
		for (int index = 0; index < pp.length; index++) {
			Log.i("TTTTTTT", myslots.get(index).getContent());
			int Bcount = Integer.parseInt(myslots.get(index).getBookCount());
			int MaxB = Integer.parseInt(myslots.get(index).getMaxBookers());
			if ((Bcount == MaxB) && MaxB != 0) {
				/*
				 * if (index != 0) { if (pp[index].getWidth() < pp[index -
				 * 1].getWidth()) { pp[index].setLayoutParams(new
				 * TableRow.LayoutParams
				 * (pp[index].getHeight(),pp[index-1].getWidth()));
				 * 
				 * } }
				 */
				pp[index].setText("Complete");
				pp[index].setBackgroundResource(R.drawable.roundedbutton2);
				// pp[index].setBackgroundColor(Color.parseColor("#a30000"));

			}

			else if (myslots.get(index).getCheck_book().equals("1")) {
				/*
				 * if (index != 0) { if (pp[index].getWidth() < pp[index -
				 * 1].getWidth()) { pp[index].setLayoutParams(new
				 * LinearLayout.LayoutParams
				 * (pp[index].getHeight(),pp[index-1].getWidth()));
				 * 
				 * }
				 * 
				 * }
				 */
				pp[index].setText(myslots.get(index).getContent());
				pp[index]
						.setBackgroundResource(R.drawable.roundedbutton_clicked);

			} else {
				/*
				 * if (index != 0) { if (pp[index].getWidth() < pp[index -
				 * 1].getWidth()) { pp[index].setLayoutParams(new
				 * LinearLayout.LayoutParams
				 * (pp[index].getHeight(),pp[index-1].getWidth()));
				 * 
				 * }
				 * 
				 * }
				 */
				pp[index].setText(myslots.get(index).getContent());

			}
		}

		String check = pp[0].getText().toString();
		for (int index = 0; index < pp.length; index++) {
			int Bcount = Integer.parseInt(myslots.get(index).getBookCount());
			int MaxB = Integer.parseInt(myslots.get(index).getMaxBookers());
			final int finalI = index;

			if (!(myslots.get(index).getMaxBookers().equalsIgnoreCase("0"))
					&& (Bcount < MaxB)) { // StaffController controller =
											// Application.getStaffController();
				// controller.book("mona", "7");
				pp[finalI].setOnClickListener(new View.OnClickListener() {

					int check = 0;

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						if (myslots.get(finalI).getCheck_book()
										.equals("0") ) {

							// get prompts.xml view
							LayoutInflater li = LayoutInflater.from(context);
							View promptsView = li.inflate(R.layout.booklayout,
									null);

							AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
									context);

							// set prompts.xml to alertdialog builder
							alertDialogBuilder.setView(promptsView);

							final EditText userInput = (EditText) promptsView
									.findViewById(R.id.editTextDialogUserInput);

							// set dialog message
							alertDialogBuilder
									.setCancelable(false)
									.setPositiveButton(
											"Book",
											new DialogInterface.OnClickListener() {
												public void onClick(
														DialogInterface dialog,
														int id) {
													// get user input and set it
													// to result
													// edit text
													// result.setText(userInput.getText());
													String CONTENT = userInput
															.getText()
															.toString();
													Log.i("TEstBook", CONTENT);
													Calendar cal = Calendar
															.getInstance();
													SimpleDateFormat dateFormat = new SimpleDateFormat(
															"MM/dd/yyyy hh:mm aa");
													String strDate = dateFormat
															.format(cal
																	.getTime());
													Date ConvertDate = null;

													try {
														ConvertDate = dateFormat
																.parse(strDate);
													} catch (ParseException e) {
														// TODO Auto-generated
														// catch block
														e.printStackTrace();
													}
													StudentControllers
															.book(myslots
																	.get(finalI)
																	.getSlotID(),
																	AgendaController.Date,
																	CONTENT,
																	StudentControllers.currentActiveStudent
																			.getId());
													pp[finalI]
															.setBackgroundResource(R.drawable.roundedbutton_clicked);

													check = -1;
												}
											})
									.setNegativeButton(
											"Cancel",
											new DialogInterface.OnClickListener() {
												public void onClick(
														DialogInterface dialog,
														int id) {
													dialog.cancel();
												}
											});

							// create alert dialog
							AlertDialog alertDialog = alertDialogBuilder
									.create();

							// show it
							alertDialog.show();

						}

						else if ( myslots.get(finalI).getCheck_book()
										.equals("1")) {
							// call cancel book
							StudentControllers.cancelbook(myslots.get(finalI)
									.getSlotID(), AgendaController.Date,
									StudentControllers.currentActiveStudent
											.getId());

							pp[finalI]
									.setBackgroundResource(R.drawable.roundedbutton);

							check = -2;
						}

					}

				});
			}
			/*
			 * else if
			 * (myslots.get(index).getContent().equalsIgnoreCase("officehoure")
			 * && (Bcount ==MaxB ) ) {
			 * pp[finalI].setBackgroundColor(Color.GREEN); }
			 */

		}

	}

}
