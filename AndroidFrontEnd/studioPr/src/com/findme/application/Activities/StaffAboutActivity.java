package com.findme.application.Activities;


import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.findme.application.R;
import com.findme.application.Adapters.DrawerListAdapter;
import com.findme.application.Controllers.CheckinController;
import com.findme.application.Controllers.StaffController;
import com.findme.application.Models.NavItem;


public class StaffAboutActivity extends ActionBarActivity
{
    private static final String LOGTAG = "AboutScreen";

    private WebView mAboutWebText;
    private Button mStartButton;
    private TextView mAboutTextTitle;
    private String mClassToLaunch;
    private String mClassToLaunchPackage;

    ListView mDrawerList;
    RelativeLayout mDrawerPane;
    TextView username;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private String mActivityTitle;
    ArrayList<NavItem> mNavItems = new ArrayList<NavItem>();


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_ACTION_BAR);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mActivityTitle = "About";
        getSupportActionBar().setTitle(mActivityTitle);
        setContentView(R.layout.activity_about);

        String webText = "ImageTargets/Staff_about.html";

        mClassToLaunchPackage = getPackageName();
        mClassToLaunch = mClassToLaunchPackage + "."
                + "app.ImageTargets.ImageTargets";

        mAboutWebText = (WebView) findViewById(R.id.about_html_text);

        StaffAboutActivity.AboutWebViewClient aboutWebClient = new StaffAboutActivity.AboutWebViewClient();
        mAboutWebText.setWebViewClient(aboutWebClient);

        String aboutText = "";
        try
        {
            InputStream is = getAssets().open(webText);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(is));
            String line;

            while ((line = reader.readLine()) != null)
            {
                aboutText += line;
            }
        } catch (IOException e)
        {
            Log.e(LOGTAG, "About html loading failed");
        }

        mAboutWebText.loadData(aboutText, "text/html", "UTF-8");


        mAboutTextTitle = (TextView) findViewById(R.id.about_text_title);
        mAboutTextTitle.setText("Find Me Application");

        ////////////////////////Menu//////////////////////////////////

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


    // Starts the chosen activity
    private void startActivity()
    {
        Intent i = new Intent();
        i.setClassName(mClassToLaunchPackage, mClassToLaunch);
        startActivity(i);
    }




    private class AboutWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
            return true;
        }
    }

    ///////////////////////////////////////// Menu //////////////////////////////////////////////////////////////////

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




    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        // Activate the navigation drawer toggle
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

}
