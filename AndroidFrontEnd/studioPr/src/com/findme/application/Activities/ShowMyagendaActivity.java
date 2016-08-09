package com.findme.application.Activities;

import java.util.ArrayList;

import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.ViewAnimator;

import com.findme.application.BuildConfig;
import com.findme.application.R;
import com.findme.application.Activities.StaffHomePage.SendEmailAsyncTask;
import com.findme.application.Adapters.BookContentAdapter;
import com.findme.application.Controllers.AgendaController;
import com.findme.application.Controllers.Application;
import com.findme.application.Controllers.BookController;
import com.findme.application.Controllers.BookController.Connection.BookListener;
import com.findme.application.Models.Mail;

public class ShowMyagendaActivity extends ActionBarActivity implements
		OnClickListener, BookListener,
		android.widget.CompoundButton.OnCheckedChangeListener {

	Button ChooseDateButton;
	ListView list;
	String SlotID;
	BookContentAdapter bcAdapter;
	ArrayList<String> allbookers;
	ArrayList<String> allcancelled;
	ArrayList<String> allBookerID;
	ArrayList<String> allBookersEmails;
	ArrayList<String> AllCancelledemails;
	public static String SendPost =" ";

	Button done;
	TableLayout agendaTable;
	String[] weekdays = { "saturady", "sunday", "monday", "tuesday", "wensday",
			"tharsday" };
	ViewAnimator viewAnimator;
	ArrayList<String> slotIDs = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getSupportActionBar().setTitle("Agenda");
		setContentView(R.layout.activity_show_myagenda);
		agendaTable = (TableLayout) findViewById(R.id.Agenda);
		ChooseDateButton = (Button) findViewById(R.id.changedate);

		// back = (Button) findViewById(R.id.back);
		try {
			ShowAgenda();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ChooseDateButton.setOnClickListener(this);
		//
		// back.setOnClickListener(this);
		viewAnimator = (ViewAnimator) findViewById(R.id.viewAnimator1);

		final Animation inAnim = AnimationUtils.loadAnimation(this,
				android.R.anim.slide_in_left);
		final Animation outAnim = AnimationUtils.loadAnimation(this,
				android.R.anim.slide_out_right);
		viewAnimator.setInAnimation(inAnim);
		viewAnimator.setOutAnimation(outAnim);
		viewAnimator.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		if (v.getId() == R.id.changedate) {

			Intent ChangeDateIntent = new Intent(Application.getAppContext(),
					ChooseDateActivity.class);
			ChangeDateIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			Application.getAppContext().startActivity(ChangeDateIntent);
		} else if (v.getId() == R.id.done) {
			// do your code
			// call remove srvices and return to this page again after changes
			JSONArray mJSONArray = new JSONArray(allcancelled);

			String bIds = mJSONArray.toString();
			BookController controller = BookController.getInstance();
			AgendaController c = AgendaController.getInstance();
			controller.DCancelBooks(SlotID, c.GetAgenda().getOlastUpdate(),
					bIds);
			BookController.Arr=new String[AllCancelledemails.size()];

			for(int i =0 ;i<AllCancelledemails.size();i++)
			{
				BookController.Arr[i]=AllCancelledemails.get(i);
			}

//*************************************************			
		//	save array AllCancelledemails at static var and then use it in d-cancel-book 
	   // function in AgendaController to send emails befor calling show agenda 
			
			
			
		}
		else if (v.getId() >= 0 && v.getId() < 36)
		{
			// call services to get all contents
			BookController controller = BookController.getInstance();
			AgendaController c = AgendaController.getInstance();
			SlotID = slotIDs.get(v.getId());
			controller.getContents(slotIDs.get(v.getId()), c.GetAgenda()
					.getOlastUpdate(), this);
		}

	}

	public void ShowAgenda() throws JSONException {

		Bundle extras = getIntent().getExtras();
		String result = extras.getString("result");

		ArrayList<String> myslots = new ArrayList();
		JSONArray slots = new JSONArray(result);
		int count = 0;
		int booked = 0;
		for (int j = 0; j < 6; j++) {
			TableRow tr = new TableRow(this);
			TextView slot1 = new TextView(this);
			slot1.setBackground(getResources().getDrawable(
					R.drawable.first_column));
			slot1.setText(weekdays[j]);
			slot1.setPadding(10, 30, 10, 29);
			slot1.setGravity(1);
			slot1.setTextColor(Color.WHITE);
			tr.addView(slot1);

			for (int i = 0; i < 6; i++) {
				JSONObject obj = new JSONObject();
				obj = (JSONObject) slots.get(count);
				String maxbook = obj.getString("maxBookers");
				String bookCount = obj.getString("bookCount");
				String content = obj.getString("content");
				String exception = obj.getString("exception");
				LinearLayout l = new LinearLayout(this);

				if (exception.equals("1")){
					l.setBackground(getResources().getDrawable(
							R.drawable.exception));
				}
				else{
				l.setBackground(getResources().getDrawable(
						R.drawable.data_square));
				}
				l.setOrientation(1);
				TextView slot = new TextView(this);

				if (maxbook.equals("0")) {
					slot.setText("");
				} else {

					slot.setText(bookCount);
					slot.setClickable(true);
					slotIDs.add(obj.getString("slotID"));
					slot.setOnClickListener(this);
					slot.setId(booked);
					booked++;
				}
				// viewAnimator.showNext();
				TextView slot2 = new TextView(this);
				slot2.setText(content);
				slot2.setPadding(10, 10, 10, 10);
				slot2.setGravity(1);
				l.addView(slot);
				l.addView(slot2);
				tr.addView(l);
				count++;
			}
			agendaTable.addView(tr);

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show_agenda, menu);
		MenuItem item = menu.add(Menu.NONE, 1, 1, "Home");
		item = menu.add(Menu.NONE, 2, 2, "Update My Agenda");
		item = menu.add(Menu.NONE, 3, 3, "Add Exception");
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
			// intent to home
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
			Intent HomeIntent = new Intent(Application.getAppContext(),
					ChooseExceptionDateActivity.class);
			HomeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			Application.getAppContext().startActivity(HomeIntent);
		}
		if (id == 4) {
			AgendaController controller = AgendaController.getInstance();
			String owner = controller.GetAgenda().getOwnter();
			controller.GetExceptions(owner);
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onGetContentComplete(String response) throws JSONException {
		// TODO Auto-generated method stub
		// display all contents with labe
		done = (Button) findViewById(R.id.done);
		done.setOnClickListener(this);
		list = (ListView) findViewById(R.id.listview);
		displayContents(response);
		viewAnimator.showNext();

	}

	private void displayContents(String response) throws JSONException {
		JSONArray jarray = new JSONArray(response);
		allbookers = new ArrayList<String>();
		allcancelled = new ArrayList<String>();
		allBookerID = new ArrayList<String>();
		allBookersEmails =new ArrayList<String>();
		AllCancelledemails = new ArrayList<String>();
		for (int i = 0; i < jarray.length(); i++) {
			JSONObject object = null;
			object = (JSONObject) jarray.get(i);
			String content = (String) object.get("content");
			String ID = (String) object.get("studentID");
			String email = (String) object.get("studentMail");
			allBookerID.add(ID);
			allbookers.add(content);
			allBookersEmails.add(email);
		}
		bcAdapter = new BookContentAdapter(allbookers, this);
		list.setAdapter(bcAdapter);

	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		int pos = list.getPositionForView(buttonView);
		if (pos != ListView.INVALID_POSITION) {
			String contents = allBookerID.get(pos);
			String mail = allBookersEmails.get(pos);
			if (allcancelled.contains(contents)) {
				allcancelled.remove(contents);
				AllCancelledemails.remove(mail);
			
			} else {

				allcancelled.add(contents);
				AllCancelledemails.add(mail);
			}
		}

	}

	 
	
	
	
}
