package com.findme.application.Activities;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import com.findme.application.R;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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

import com.findme.application.Controllers.AgendaController;
import com.findme.application.Controllers.Application;
import com.findme.application.Controllers.StaffController;

public class CreateAgendaActivity extends Activity implements OnClickListener {
	Button SaveAgendaButton;
	TableLayout agendaTable;
	String[] weekdays = { "saturady", "sunday", "monday", "tuesday", "wensday",
			"tharsday" };
	ArrayList<String> cources;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().setTitle("Create Agenda");
		setContentView(R.layout.activity_create_agenda);
		
		AgendaController controller = AgendaController.getInstance();
		cources = controller.GetAgenda().getCources();
		agendaTable = (TableLayout) findViewById(R.id.Agenda);
		SaveAgendaButton = (Button) findViewById(R.id.saveAgenda);
		try {
			CreateAgenda();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SaveAgendaButton.setOnClickListener(this);

	}

	public void CreateAgenda() throws JSONException {

		for (int j = 0; j < 6; j++) {
			TableRow tr = new TableRow(this);
			TextView slot1 = new TextView(this);
			slot1.setBackground(getResources().getDrawable(
					R.drawable.first_column));
			slot1.setText(weekdays[j]);
			slot1.setPadding(10, 60, 10, 29);
			slot1.setGravity(1);
			slot1.setTextColor(Color.WHITE);
			tr.addView(slot1);
			for (int i = 0; i < 6; i++) {
				LinearLayout l = new LinearLayout(this);
				l.setOrientation(1);
				l.setBackground(getResources().getDrawable(
						R.drawable.data_square));
				EditText slot3 = new EditText(this);

				slot3.setHint("0");
				slot3.setPadding(10, 10, 10, 0);
				slot3.setGravity(1);
				slot3.setBackgroundColor(getResources().getColor(
						android.R.color.transparent));

				ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
						android.R.layout.simple_list_item_1, cources);
				AutoCompleteTextView slot = new AutoCompleteTextView(this);
				slot.setAdapter(adapter);
				slot.setHint("closed");
				slot.setBackgroundColor(getResources().getColor(
						android.R.color.transparent));

				slot.setPadding(10, 10, 10, 10);
				slot.setGravity(1);

				l.addView(slot3);
				l.addView(slot);
				tr.addView(l);
			}
			agendaTable.addView(tr);
		}

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		ArrayList<String> contents = new ArrayList<String>();
		ArrayList<String> maxBookers = new ArrayList<String>();
		int count = 0;
		

		for (int i = 1; i < agendaTable.getChildCount(); i++) {
			View view = agendaTable.getChildAt(i);
			if (view instanceof TableRow) {
				TableRow row = (TableRow) view;
				for (int k = 1; k < 7; k++) {
					LinearLayout l = (LinearLayout) row.getChildAt(k);
					EditText tex = (EditText) l.getChildAt(0);
					maxBookers.add(count, tex.getText().toString());
					tex = (EditText) l.getChildAt(1);
					contents.add(count, tex.getText().toString());
					if (maxBookers.get(count).equals("") ) {
						maxBookers.set(count, "0");

					}
					if (contents.get(count).equals("")) {
						contents.set(count, "closed");
					}
					count++;
				}
			}

		}

		JSONArray mJSONArray = new JSONArray(contents);

		String content = mJSONArray.toString();

		mJSONArray = new JSONArray(maxBookers);

		String maxbooker = mJSONArray.toString();

		AgendaController controller = Application.getAgendaController();
		String owner = StaffController.currentActiveStaff.getFormalemaill();
		Log.d("llllllllll",owner);
		controller.createAgenda(owner, content, maxbooker);

	}

}
