package com.findme.application.Activities;


import com.findme.application.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class StaffPage extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_staff_page);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.staff_page, menu);
		return true;
	}

}
