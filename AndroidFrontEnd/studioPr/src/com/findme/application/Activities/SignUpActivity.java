package com.findme.application.Activities;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import com.findme.application.R;
import com.findme.application.Controllers.Application;
import com.findme.application.Controllers.StaffController;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class SignUpActivity extends ActionBarActivity implements
		OnClickListener {
	Button signup2;
	EditText EmailEditText;
	EditText passwordEditText;
	EditText idEditText;
	EditText nameEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getSupportActionBar().setTitle("SignUp");
		setContentView(R.layout.activity_sign_up);
		signup2 = (Button) findViewById(R.id.SIGNUP2);
		EmailEditText = (EditText) findViewById(R.id.passwordsignup);
		passwordEditText = (EditText) findViewById(R.id.passwordsignup2);
		idEditText = (EditText) findViewById(R.id.EmailFormalsignup);
		nameEditText = (EditText) findViewById(R.id.nameID);
		signup2.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		StaffController controller = Application.getStaffController();

		String pass = passwordEditText.getText().toString();
		String Femail = idEditText.getText().toString();

		String email = Femail.trim();

		String NEmail = EmailEditText.getText().toString().trim();

		int x = Femail.indexOf('@');

		String FemailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
		String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
		if (!NEmail.matches(emailPattern) || !(NEmail.length() > 0)) {
			Toast.makeText(SignUpActivity.this, "Please enter Vailed email ",
					Toast.LENGTH_LONG).show();
		} /*else if (!email.matches(FemailPattern) || !(Femail.length() > 0)) {
			Toast.makeText(SignUpActivity.this,
					"Please enter Vailed  FCI email  ", Toast.LENGTH_LONG)
					.show();
		} else if (x > 1) {
			String FCIForm = Femail.substring(x).toLowerCase();
			if (!FCIForm.equals("fci-cu.edu.eg")) {
				Toast.makeText(SignUpActivity.this,
						"Please enter Vailed  FCI email  ", Toast.LENGTH_LONG)
						.show();
			}

		} */else if (pass.length() < 6) {
			Toast.makeText(SignUpActivity.this,
					"Please enter password more than 6 digits",
					Toast.LENGTH_LONG).show();
		} else {

			controller.signUp(idEditText.getText().toString(), nameEditText
					.getText().toString(), EmailEditText.getText().toString(),
					pass);
		}

	}

}
