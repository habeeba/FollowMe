package com.findme.application.Activities;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CalendarView;

import com.findme.application.R;
import com.findme.application.Controllers.AgendaController;
import com.findme.application.Controllers.Application;

public class ChooseExceptionDateActivity extends ActionBarActivity implements
		OnClickListener {

	CalendarView dateview;
	Button okButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getSupportActionBar().setTitle("Choose Exception Date");
		setContentView(R.layout.activity_choose_exception_date);
		dateview = (CalendarView) findViewById(R.id.Date);
		okButton = (Button) findViewById(R.id.OK);
		okButton.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.choose_exception_date, menu);
		// getMenuInflater().inflate(R.menu.choose_exception_date, menu);
		MenuItem item = menu.add(Menu.NONE, 1, 0, "Home");
		item = menu.add(Menu.NONE, 2, 1, "Add Exception");
		item = menu.add(Menu.NONE, 3, 2, "Show Exceptions");
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == 1) {
			Intent HomeIntent = new Intent(Application.getAppContext(),
					StaffHomePage.class);
			HomeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			Application.getAppContext().startActivity(HomeIntent);
		}
		if (id == 2) {
			Intent ShowExceptionAgendaIntent = new Intent(
					Application.getAppContext(),
					ChooseExceptionDateActivity.class);
			ShowExceptionAgendaIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			Application.getAppContext()
					.startActivity(ShowExceptionAgendaIntent);
		}
		if (id == 3) {
			AgendaController controller = AgendaController.getInstance();
			String owner = controller.GetAgenda().getOwnter();
			controller.GetExceptions(owner);
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Calendar selected = Calendar.getInstance();
		selected.setFirstDayOfWeek(Calendar.SATURDAY);

		selected.setTimeInMillis(dateview.getDate());
		int dayOfWeek = selected.get(Calendar.DAY_OF_WEEK);
		selected.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		String selectedDate = sdf.format(selected.getTime());

		SharedPreferences pref = getApplicationContext().getSharedPreferences(
				"MyPref", 0); // 0 - for private modeF
		Editor editor = pref.edit();
		editor.putString("exceptionDate", selectedDate);
		editor.commit();
		AgendaController controller = AgendaController.getInstance();
		controller.isException(selectedDate);

		/*
		 * Bundle extras = getIntent().getExtras(); String result =
		 * extras.getString("result"); Intent CreateExceptionAgendaIntent = new
		 * Intent( Application.getAppContext(), CreateExceotionActivity.class);
		 * 
		 * if (selectedDate == today){ AgendaController controller =
		 * Application.getAgendaController(); controller.flag = 1; }
		 * CreateExceptionAgendaIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		 * Log.d("yy", result); CreateExceptionAgendaIntent.putExtra("result",
		 * result); CreateExceptionAgendaIntent.putExtra("date", selectedDate);
		 * Application
		 * .getAppContext().startActivity(CreateExceptionAgendaIntent);
		 */

	}

}
