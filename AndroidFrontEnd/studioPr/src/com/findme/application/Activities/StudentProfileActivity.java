package com.findme.application.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.findme.application.R;
import com.findme.application.Models.NavItem;
import com.findme.application.R.id;
import com.findme.application.R.layout;
import com.findme.application.R.menu;

import java.util.ArrayList;

import com.findme.application.Adapters.DrawerListAdapter;

public class StudentProfileActivity extends PreferenceActivity {

    ActionBarActivity actionbar = new ActionBarActivity();
    ListView mDrawerList;
    RelativeLayout mDrawerPane;
    TextView username;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private String mActivityTitle;


    ArrayList<NavItem> mNavItems = new ArrayList<NavItem>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityTitle = "Profile";
        getActionBar().setTitle(mActivityTitle);
        addPreferencesFromResource(R.layout.activity_student_profile);
        getActionBar().setDisplayHomeAsUpEnabled(true);

    }


    private Menu  mMenu;
    @Override

    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_page, menu);

        // Save the menu reference
        mMenu = menu;
        mMenu = menu;
       // MenuItem item = menu.add(Menu.NONE, 1, 1, "back");

        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       

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

    }
