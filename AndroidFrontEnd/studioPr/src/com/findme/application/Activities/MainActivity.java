package com.findme.application.Activities;


import com.findme.application.R;
import com.findme.application.Controllers.AgendaController;
import com.findme.application.Controllers.Application;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends ActionBarActivity {
  
	ImageButton staff , student ;

	String mActivityTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityTitle = "Follow Me";
        getSupportActionBar().setTitle(mActivityTitle);
        setContentView(R.layout.activity_main);
	
		staff=(ImageButton)findViewById(R.id.Staff1);
        student =(ImageButton)findViewById(R.id.Student1);
        staff.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			/*	Intent staffIntent = new Intent(getApplicationContext(),
						LoginActivity.class);
				startActivity(staffIntent);*/
				AgendaController c = AgendaController.getInstance();
				c.GetCources();

			}
		});
        student.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			Intent studentIntent = new Intent(getApplicationContext(),
					StudentLogin.class);
				startActivity(studentIntent);
			/*	 Intent Doctor_intent = new Intent(Application.getAppContext(),
	 						
	 						DoctorTimLine.class);
	 				//Doctor_intent.putExtra("email", StudentEmail);
	 				Doctor_intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

	 			Application.getAppContext().startActivity(Doctor_intent);*/

			}
		});
		
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
