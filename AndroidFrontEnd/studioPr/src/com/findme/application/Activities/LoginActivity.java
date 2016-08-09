package com.findme.application.Activities;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.findme.application.R;
import com.findme.application.Controllers.AgendaController;
import com.findme.application.Controllers.Application;
import com.findme.application.Controllers.StaffController;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;



public class LoginActivity extends ActionBarActivity implements OnClickListener  {
	
	
	Button login2 ;
	EditText EmailEditText;
	EditText passwordEditText;
	TextView signup;
	public static String mActivityTitle ="Login";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getSupportActionBar().setTitle(mActivityTitle);
		setContentView(R.layout.activity_login);
		login2 =(Button)findViewById(R.id.LOGIN2);

	//	final LinearLayout layout = (LinearLayout)findViewById(R.id.yourlayout);


		EmailEditText = (EditText) findViewById(R.id.IdEmail);

	/*	EmailEditText.setOnClickListener(new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			Animation slideUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
			Animation slideDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);

			if (layout.getVisibility() == View.INVISIBLE) {

				layout.startAnimation(slideUp);
				layout.setVisibility(View.VISIBLE);
			}
		}});
*/
		passwordEditText = (EditText) findViewById(R.id.passwordlogin);
		signup = (TextView) findViewById(R.id.signupStaff);
		signup.setPaintFlags(signup.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
		login2.setOnClickListener(this); 
		signup.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent signupIntent = new Intent(getApplicationContext(),
					SignUpActivity.class);
				startActivity(signupIntent);

			}
		});

		SharedPreferences prefs = getSharedPreferences("STAFF", MODE_PRIVATE);
		String userID = prefs.getString("STAFF_ID", "");
		String userPass = prefs.getString("STAFF_Password", "");

		if (userID.length() > 0){
			(EmailEditText).setText(userID);


		}
		if (userPass.length() > 0){
			(passwordEditText).setText(userPass);

		}
	
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	//	UserController controller = Application.getUserController();
		


		/*controller.signUp(idEditText.getText().toString(),nameEditText.getText().toString(),EmailEditText.getText().toString(), passwordEditText
						.getText().toString());*/


		SharedPreferences prefs = getSharedPreferences("STAFF", MODE_PRIVATE);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString("STAFF_ID", (EmailEditText).getText().toString());
		editor.putString("STAFF_Password", (passwordEditText).getText().toString());
		editor.commit();

		StaffController controller = Application.getStaffController();
		
		controller.login(EmailEditText.getText().toString(), passwordEditText
				.getText().toString());	
		
		}

		
	
	


}



