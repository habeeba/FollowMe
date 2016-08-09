package com.findme.application.Activities;

import com.findme.application.R;
import com.findme.application.R.layout;
import com.findme.application.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class CheckinActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_checkin);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.checkin, menu);
		return true;
	}

}
