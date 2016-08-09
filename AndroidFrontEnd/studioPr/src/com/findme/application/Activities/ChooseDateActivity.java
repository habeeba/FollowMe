package com.findme.application.Activities;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CalendarView;

import com.findme.application.R;
import com.findme.application.Controllers.AgendaController;
import com.findme.application.Controllers.Application;

public class ChooseDateActivity extends ActionBarActivity implements OnClickListener {

	CalendarView dateview;
	Button okButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getSupportActionBar().setTitle("Choose Date");
		setContentView(R.layout.activity_choose_date);
		dateview = (CalendarView) findViewById(R.id.Date);
		okButton = (Button) findViewById(R.id.OK);
		okButton.setOnClickListener(this);
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
		AgendaController controller = Application.getAgendaController();
		
		String owner = controller.GetAgenda().getOwnter();
		controller.getOtherAgendaInfo(owner, selectedDate);

	}
}
