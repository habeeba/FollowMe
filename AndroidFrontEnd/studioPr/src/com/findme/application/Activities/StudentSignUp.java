package com.findme.application.Activities;



import com.findme.application.R;
import com.findme.application.Controllers.Application;
import com.findme.application.Controllers.StudentControllers;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class StudentSignUp extends ActionBarActivity implements OnClickListener{

	Button signup2 ;
	EditText EmailEditText;
	EditText passwordEditText;
	EditText idEditText;
	EditText nameEditText;
	public static String Na="ll";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getSupportActionBar().setTitle("Sign Up");
		setContentView(R.layout.activity_student_sign_up);
		signup2 =(Button)findViewById(R.id.SIGNUP2);
		EmailEditText = (EditText) findViewById(R.id.Email);
		passwordEditText = (EditText) findViewById(R.id.passwordsignup2);
		idEditText = (EditText) findViewById(R.id.studentID);
		nameEditText = (EditText) findViewById(R.id.nameID);
		signup2.setOnClickListener(this);
	}

			
				@Override
				public void onClick(View arg0) {
					String pass = passwordEditText.getText().toString();
					String NEmail = EmailEditText.getText().toString().trim();

					String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
					if (!NEmail.matches(emailPattern) || !(NEmail.length() > 0)) {
						Toast.makeText(StudentSignUp.this, "Please your correct email ",
								Toast.LENGTH_LONG).show();
					}

					else if (pass.length() < 6) {
						Toast.makeText(StudentSignUp.this,
								"Please enter password more than 6 digits ",
								Toast.LENGTH_LONG).show();
					}

					else if (EmailEditText.length() != 0 && passwordEditText.length() != 0
							&& idEditText.length() != 0 && nameEditText.length() != 0) {

						Log.i("ID", idEditText.getText().toString());
						Log.i("NAME", nameEditText.getText().toString());
						Log.i("EMAIL", EmailEditText.getText().toString());
						Log.i("PASS", passwordEditText.getText().toString());

						StudentControllers.signUpStudent(idEditText.getText().toString(),
								nameEditText.getText().toString(), EmailEditText.getText()
										.toString(), passwordEditText.getText().toString());

					} else {
						Toast.makeText(StudentSignUp.this,
								"Please Make sure All fields are filled correctly ",
								Toast.LENGTH_LONG).show();
					}

				}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sign_up, menu);
		return true;
	}
	


}


