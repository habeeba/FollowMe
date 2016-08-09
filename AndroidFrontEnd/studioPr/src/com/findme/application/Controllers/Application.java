package com.findme.application.Controllers;



import android.content.Context;

public class Application extends android.app.Application {

	private static Context context;
	private static StaffController staffController;
	private static StudentControllers studentController;
	private static AgendaController agendaController;
	private static BookController bookController;
	private static FollowController followController;

	

    public void onCreate(){
        super.onCreate();
        Application.context = getApplicationContext();
        Application.staffController = StaffController.getInstance();
        Application.agendaController = AgendaController.getInstance();
        Application.studentController = StudentControllers.getInstance();
        Application.followController = FollowController.getInstance();


        
    }

    public static Context getAppContext() {
        return Application.context;
    }
    
   
    public static StudentControllers getStudentController(){
    	return Application.studentController;
    }
	
    public static StaffController getStaffController(){
    	return Application.staffController;
    }
	
	
	public static AgendaController getAgendaController() {
		return Application.agendaController;
	}

	public static BookController getBookController() {
		return Application.bookController;
	}
	public static FollowController getFollowController() {
		return Application.followController;
	}

    
}
