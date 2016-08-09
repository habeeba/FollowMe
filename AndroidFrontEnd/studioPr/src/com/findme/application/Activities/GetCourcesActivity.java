package com.findme.application.Activities;

import com.findme.application.R;
import com.findme.application.Controllers.AgendaController;
import com.findme.application.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class GetCourcesActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_get_cources);
		AgendaController controller = AgendaController.getInstance();
		controller.GetCources();
		
		
	}


}
