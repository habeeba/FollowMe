package com.findme.application.Controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;

import org.json.JSONException;

import com.findme.application.BuildConfig;
import com.findme.application.Activities.ShowMyagendaActivity;
import com.findme.application.Activities.StaffHomePage;
import com.findme.application.Controllers.BookController.Connection.BookListener;
import com.findme.application.Controllers.BookController.Connection.GetMyTaskListenerSB;
import com.findme.application.Controllers.StudentControllers.Connection;
import com.findme.application.Controllers.StudentControllers.Connection.GetMyTaskListener;
import com.findme.application.Models.Mail;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

public class BookController {
	public static String Arr[];
	private static BookController bookController;
  
	public static BookController getInstance() {
		if (bookController == null) {
			bookController = new BookController();

		}
		return bookController;
	}

	private BookController() {

	}

	public static void ShowForBook(String studentID, String agendaID,
			GetMyTaskListenerSB listenerSB) {

		new Connection(listenerSB)
				.execute(
						"http://192.168.42.211:7777/basic/web/index.php?r=agenda/show-for-book",
						studentID, agendaID, "show-for-book");
		Log.i("Da5l", "SB");

	}

	public void getContents(String slotID, String date, BookListener listener) {
		// String agendaID = currentActiveAgenda.getAgendaID();
		// Log.d("set IDDDDD", agendaID);
		new Connection(listener)
				.execute(
						"http://192.168.42.211:7777/basic/web/index.php/?r=book/get-book-content",
						slotID, date, "get-book-content");
	}

	public void DCancelBooks(String slotID, String date, String booker) {
		GetMyTaskListenerSB listener;
		listener = null;
		new Connection(listener)
				.execute(
						"http://192.168.42.211:7777/basic/web/index.php/?r=book/d-cancel-book",
						slotID, date, booker, "d-cancel-book");
	}

	public static class Connection extends AsyncTask<String, String, String> {

		public interface BookListener {
			public void onGetContentComplete(String response)
					throws JSONException;
		}

		public interface GetMyTaskListenerSB {
			public void onGetMyTaskCompletSB(String response)
					throws JSONException;

		}

		GetMyTaskListenerSB listenerSB;

		BookListener listener;
		String serviceType;

		public Connection(BookListener listener) {
			this.listener = listener;
		}

		public Connection(GetMyTaskListenerSB listenerSB) {
			this.listenerSB = listenerSB;
		}

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			URL url;
			serviceType = params[params.length - 1];

			String urlParameters = "";
			if (serviceType.equals("get-book-content")) {
				urlParameters = "slotID=" + params[1] + "&date=" + params[2];
			}
			if (serviceType.equals("d-cancel-book")) {
				urlParameters = "slotID=" + params[1] + "&Date=" + params[2]
						+ "&booker=" + params[3];
			} else if (serviceType.equals("show-for-book")) {

				urlParameters = "&studentID=" + params[1] + "&agendaID="
						+ params[2];

				Log.i("Second", "Oks");
			}
			HttpURLConnection connection;
			try {
				url = new URL(params[0]);
				Log.d("connnnn", "l");
				connection = (HttpURLConnection) url.openConnection();
				Log.d("connnnn", "2");
				connection.setDoOutput(true);
				Log.d("connnnn", "3");
				connection.setDoInput(true);
				Log.d("connnnn", "4");
				connection.setInstanceFollowRedirects(false);
				Log.d("connnnn", "5");
				connection.setRequestMethod("POST");
				Log.d("connnnn", "5");
				connection.setConnectTimeout(60000); // 60 Seconds
				connection.setReadTimeout(60000); // 60 Seconds
				Log.d("connnnn", "6");
				connection.setRequestProperty("Content-Type",
						"application/x-www-form-urlencoded;charset=UTF-8");
				OutputStreamWriter writer = new OutputStreamWriter(
						connection.getOutputStream());
				Log.d("connnnn", "8");
				writer.write(urlParameters);
				Log.d("connnnn", "9");
				writer.flush();
				Log.d("connnnn", "l0");
				String line, retJson = "";
				Log.d("connnnn", "l1");
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(connection.getInputStream()));
				Log.d("connnnn", "l2");
				while ((line = reader.readLine()) != null) {
					retJson += line;
					Log.d("connnnn", "l3");
				}
				Log.d("connnnn", "14");
				return retJson;

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;

		}

		@Override
		protected void onPostExecute(String result) {

			// TODO Auto-generated method stub
			super.onPostExecute(result);
			// try {

			if (result != null) {
				if (serviceType.equals("get-book-content")) {
					try {
						listener.onGetContentComplete(result);

					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (serviceType.equals("d-cancel-book")) {
					Log.i("Cancel", "ok");
					for (int i = 0; i < Arr.length; i++) {
						Log.i("Email", Arr[i]);
					}
					new SendEmailAsyncTask().execute();
					Log.i("Cancel", "ba3d");

					// NotificationController c =
					// NotificationController.getInstance();
					AgendaController c2 = AgendaController.getInstance();
					// String owner = c2.GetAgenda().getOwnter();
					c2.showAgenda();
					// c.CncelBookNoti(agendaID, owner, type)
				} else if (serviceType.equals("show-for-book")) {
					Log.i("ohhhh", "lllllo");

					try {
						listenerSB.onGetMyTaskCompletSB(result);

					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}

		class SendEmailAsyncTask extends AsyncTask<Void, Void, Boolean> {
			Mail m = new Mail("aliaaalifci@gmail.com", "aliaalolo20120245");

			public SendEmailAsyncTask() {
				if (BuildConfig.DEBUG)
					Log.v(SendEmailAsyncTask.class.getName(),
							"SendEmailAsyncTask()");
				// String[] toArr = {"engaliaaali16@gmail.com",
				// "hahabeeba_baioumy@fci-cu.edu.eg" ,"amal.khaled3@yahoo.com"};
				for (int i = 0; i < Arr.length; i++) {
					Log.i("Email", Arr[i]);
				}
				m.setTo(Arr);
				m.setFrom("aliaaalifci@gmail.com");
				m.setSubject("Email from Follow Me App");
				Log.i("Body", "Tmaaaam");
				Log.i("Message", "Doctor Ccanncel your book");
				m.setBody("Doctor Ccanncel your book");
			}

			@Override
			protected Boolean doInBackground(Void... params) {
				if (BuildConfig.DEBUG)
					Log.v(SendEmailAsyncTask.class.getName(),
							"doInBackground()");
				try {
					m.send();
					return true;
				} catch (AuthenticationFailedException e) {
					Log.e(SendEmailAsyncTask.class.getName(),
							"Bad account details");
					e.printStackTrace();
					return false;
				} catch (MessagingException e) {
					Log.e(SendEmailAsyncTask.class.getName(), m.getTo()
							+ "failed");
					e.printStackTrace();
					return false;
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			}

		}

	}

}
