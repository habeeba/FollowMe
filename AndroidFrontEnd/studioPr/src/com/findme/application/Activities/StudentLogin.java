package com.findme.application.Activities;




import com.findme.application.R;
import com.findme.application.Controllers.Application;
import com.findme.application.Controllers.StudentControllers;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class StudentLogin extends ActionBarActivity {

	Button login2 ;
	EditText EmailEditText;
	EditText passwordEditText;
	TextView signup;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getSupportActionBar().setTitle("Login");
		setContentView(R.layout.activity_student_login);
		login2 =(Button)findViewById(R.id.LOGIN2);
		EmailEditText = (EditText) findViewById(R.id.IdEmail);
		
		passwordEditText = (EditText) findViewById(R.id.passwordsignup);
		
		signup = (TextView) findViewById(R.id.signupStudent);
		signup.setPaintFlags(signup.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
		
		signup.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent signupIntent = new Intent(getApplicationContext(),
						StudentSignUp.class);
				startActivity(signupIntent);
				
			}
		});


		SharedPreferences prefs = getSharedPreferences("STUDENT", MODE_PRIVATE);
		String userID = prefs.getString("STUDENT_ID", "");
		String userPass = prefs.getString("STUDENT_Password", "");

		if (userID.length() > 0){
			(EmailEditText).setText(userID);


		}
		if (userPass.length() > 0){
			(passwordEditText).setText(userPass);

		}
	login2.setOnClickListener(new OnClickListener(){
			@Override
				public void onClick(View arg0) {

				if(EmailEditText.length()!=0 && passwordEditText.length()!=0 ){

				//	StudentControllers.StudentLogin(EmailEditText.getText().toString(),passwordEditText.getText().toString());






				SharedPreferences prefs = getSharedPreferences("STUDENT", MODE_PRIVATE);
				SharedPreferences.Editor editor = prefs.edit();
				editor.putString("STUDENT_ID", (EmailEditText).getText().toString());
				editor.putString("STUDENT_Password", (passwordEditText).getText().toString());
				editor.commit();
				StudentControllers controller = Application.getStudentController();
				
				controller.StudentLogin(EmailEditText.getText().toString(),passwordEditText.getText().toString());

					/*	Intent homepageIntent = new Intent(getApplicationContext(),
									HomePage.class);
							startActivity(homepageIntent);*/
					}
					  else
					  {
						  Toast.makeText(StudentLogin.this, "Please Make sure that All fields are filled correctly" ,
									Toast.LENGTH_LONG).show();
							
					  }

			}
	});
	}
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sign_up, menu);
		return true;
	}
	
}
