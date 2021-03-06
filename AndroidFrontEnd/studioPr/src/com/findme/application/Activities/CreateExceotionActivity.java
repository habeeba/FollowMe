package com.findme.application.Activities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.findme.application.R;
import com.findme.application.Controllers.AgendaController;
import com.findme.application.Controllers.Application;

//already exist (need implementation)
public class CreateExceotionActivity extends ActionBarActivity implements
		OnClickListener {

	TableLayout agendaTable;
	String excdate;
	Button CreateButton;
	int flage = 0;
	ArrayList<String> contents = new ArrayList<String>();
	ArrayList<String> bookCounts = new ArrayList<String>();
	ArrayList<String> maxBookers = new ArrayList<String>();
	String[] weekdays = { "saturady", "sunday", "monday", "tuesday", "wensday",
			"tharsday" };
	ArrayList<String> cources; // = { "Project", "ERP", "Paralel", "Compiler",

	// "Analysis2", "DB2" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getSupportActionBar().setTitle("Add Exception");
		setContentView(R.layout.activity_create_exceotion);
		AgendaController controller = AgendaController.getInstance();
		cources = controller.GetAgenda().getCources();
		agendaTable = (TableLayout) findViewById(R.id.Agenda);
		CreateButton = (Button) findViewById(R.id.Create);
		CreateButton.setOnClickListener(this);
		try {
			ShowAgenda();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void ShowAgenda() throws JSONException {
		int count = 0;
		Bundle extras = getIntent().getExtras();
		String result = extras.getString("result");
		SharedPreferences pref = getApplicationContext().getSharedPreferences(
				"MyPref", 0);
		excdate = pref.getString("exceptionDate", excdate);
		Editor editor = pref.edit();
		editor.remove("exceptionDate");
		editor.commit();
		JSONArray slots = new JSONArray(result);

		for (int j = 0; j < 6; j++) {
			TableRow tr = new TableRow(this);
			TextView slot1 = new TextView(this);
			slot1.setBackground(getResources().getDrawable(
					R.drawable.first_column));
			slot1.setText(weekdays[j]);
			slot1.setPadding(10, 61, 10, 68);
			slot1.setGravity(1);
			slot1.setTextColor(Color.WHITE);
			tr.addView(slot1);
			for (int i = 0; i < 6; i++) {
				JSONObject obj = new JSONObject();
				obj = (JSONObject) slots.get(count);
				LinearLayout l = new LinearLayout(this);
				l.setOrientation(1);
				l.setBackground(getResources().getDrawable(
						R.drawable.data_square));
				bookCounts.add(obj.getString("bookCount"));
				contents.add(obj.getString("content"));
				maxBookers.add(obj.getString("maxBookers"));
				TextView slot = new TextView(this);
				slot.setText(" " + bookCounts.get(count));
				EditText slot3 = new EditText(this);
				slot3.setBackgroundColor(getResources().getColor(
						android.R.color.transparent));
				slot3.setText(maxBookers.get(count));
				slot3.setPadding(10, 10, 10, 0);
				slot3.setGravity(1);
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
						android.R.layout.simple_list_item_1, cources);
				AutoCompleteTextView slot2 = new AutoCompleteTextView(this);
				slot2.setBackgroundColor(getResources().getColor(
						android.R.color.transparent));
				slot2.setAdapter(adapter);
				slot2.setText(contents.get(count));
				slot2.setPadding(10, 10, 10, 10);
				slot2.setGravity(1);
				l.addView(slot);
				l.addView(slot3);
				l.addView(slot2);
				tr.addView(l);
				count++;
			}
			agendaTable.addView(tr);
		}

	}

	public void CreateException() {

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		ArrayList<String> newcontents = new ArrayList<String>();
		ArrayList<String> newmaxBookers = new ArrayList<String>();
		ArrayList<String> slotnums = new ArrayList<String>();
		int count = 0;
		int counter = 0;
		// agendaTable.getChildCount() bld el 6
		for (int i = 1; i < 6; i++) {
			View view = agendaTable.getChildAt(i);
			if (view instanceof TableRow) {
				TableRow row = (TableRow) view;
				for (int k = 1; k < 7; k++) {
					LinearLayout l = (LinearLayout) row.getChildAt(k);
					TextView tex = (TextView) l.getChildAt(1);
					String newbook = tex.getText().toString();
					tex = (TextView) l.getChildAt(2);
					String newcontent = tex.getText().toString();
					if (maxBookers.get(counter).equals(newbook)
							&& contents.get(counter).equals(newcontent)
							|| (newbook.equals("") && newcontent.equals(""))) {
						counter++;
						continue;
					}

					if (Integer.parseInt(newbook) < Integer.parseInt(bookCounts
							.get(counter))) {
						String error = "slot " + (k) + "at " + weekdays[i - 1]
								+ " have # bookers more than new limit";

						Toast.makeText(Application.getAppContext(), error,
								Toast.LENGTH_LONG).show();
						counter++;
						continue;
					}
					if (newbook.equals("")) {
						newmaxBookers.add(count, maxBookers.get(counter));
						newcontents.add(count, newcontent);
						slotnums.add(count, Integer.toString(counter));
					} else if (newcontent.equals("")) {
						newmaxBookers.add(count, newbook);
						newcontents.add(count, contents.get(counter));
						slotnums.add(count, Integer.toString(counter));
					} else {

						newmaxBookers.add(count, newbook);
						newcontents.add(count, newcontent);
						slotnums.add(count, Integer.toString(counter));
					}
					count++;
					counter++;
				}
			}
		}

		JSONArray mJSONArray = new JSONArray(newcontents);
		String contents = mJSONArray.toString();
		mJSONArray = new JSONArray(newmaxBookers);
		String maxbooker = mJSONArray.toString();
		mJSONArray = new JSONArray(slotnums);
		String slotnum = mJSONArray.toString();

		AgendaController controller = Application.getAgendaController();
		String owner = controller.GetAgenda().getOwnter();
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		c.setFirstDayOfWeek(Calendar.SATURDAY);
		c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		String currentDate = df.format(c.getTime());

		if (excdate.equals(currentDate)) {
			controller.createCurrException(owner, excdate, contents, maxbooker,
					slotnum);
		} else {
			controller.createException(owner, excdate, contents, maxbooker,
					slotnum);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show_agenda, menu);
		MenuItem item = menu.add(Menu.NONE, 1, 1, "Home");
		item = menu.add(Menu.NONE, 2, 2, "Update My Agenda");
		item = menu.add(Menu.NONE, 3, 3, "Show Agenda");
		item = menu.add(Menu.NONE, 4, 4, "Show Exceptions");
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
			AgendaController controller = AgendaController.getInstance();
			controller.showAgendaForUpdate();
		}
		if (id == 3) {
			AgendaController controller = AgendaController.getInstance();
			controller.showAgenda();
		}
		if (id == 4) {
			AgendaController controller = AgendaController.getInstance();
			String owner = controller.GetAgenda().getOwnter();
			controller.GetExceptions(owner);
		}
		return super.onOptionsItemSelected(item);
	}

}
