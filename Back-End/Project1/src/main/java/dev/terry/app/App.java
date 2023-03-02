package dev.terry.app;

import com.google.gson.Gson;
import dev.terry.data.AppUserDAOPostgres;
import dev.terry.data.ComplaintDAOPostgres;
import dev.terry.data.MeetingDAOPostgres;
import dev.terry.dtos.LoginCredentials;
import dev.terry.entities.AppUser;
import dev.terry.handlers.appusers.CreateAccountHandler;
import dev.terry.handlers.appusers.GetAllAppUsersHandler;
import dev.terry.handlers.appusers.UpdateRoleHandler;
import dev.terry.handlers.complaints.*;
import dev.terry.handlers.meetings.CreateMeetingHandler;
import dev.terry.handlers.meetings.GetAllMeetingsHandler;
import dev.terry.services.*;
import io.javalin.Javalin;

public class App {
    public final static ComplaintService complaintService = new ComplaintServiceImpl(new ComplaintDAOPostgres());
    public final static MeetingService meetingService = new MeetingServiceImpl(new MeetingDAOPostgres());

    public final static AppUserService appUserService = new AppUserServiceImpl(new AppUserDAOPostgres());

    public final static LoginService loginService = new LoginServiceImpl(new AppUserDAOPostgres());

    public static void main(String[] args) {
        Javalin app = Javalin.create(config ->{
            config.enableDevLogging();
            config.enableCorsForAllOrigins();
        });

        //Complaint Handlers
        CreateComplaintHandler createComplaintHandler = new CreateComplaintHandler();
        GetAllComplaintsHandler getAllComplaintsHandler = new GetAllComplaintsHandler();
        UpdateComplaintMeetingHandler updateComplaintMeetingHandler = new UpdateComplaintMeetingHandler();
        UpdatePriorityHandler updatePriorityHandler = new UpdatePriorityHandler();
        CreateComplaintWithMeetingHandler createComplaintWithMeetingHandler = new CreateComplaintWithMeetingHandler();

        app.post("/complaints", createComplaintHandler); //Creates a new complaint
        app.get("/complaints", getAllComplaintsHandler); //gets all complaints
        app.put("/complaints/{id}/{priority}", updatePriorityHandler); //Update based on id
        app.put("/complaints/{id}/meetings/{mId}", updateComplaintMeetingHandler); //Attach a complaint to a meeting
        app.post("/complaints/{mId}", createComplaintWithMeetingHandler); //create complaint with meeting id

        //Meeting Handlers

        CreateMeetingHandler createMeetingHandler = new CreateMeetingHandler();
        GetAllMeetingsHandler getAllMeetingsHandler = new GetAllMeetingsHandler();

        app.post("/meetings", createMeetingHandler); //create a new meeting
        app.get("/meetings", getAllMeetingsHandler); //gets all meetings
        //app.patch("/meetings/{id}/{status}", null); //Update meeting status

        //AppUser Handlers

        CreateAccountHandler createAccountHandler = new CreateAccountHandler();
        UpdateRoleHandler updateRoleHandler = new UpdateRoleHandler();
        GetAllAppUsersHandler getAllAppUsersHandler = new GetAllAppUsersHandler();

        app.post("/login", ctx -> {
            String jsonBody = ctx.body();
            Gson gson = new Gson();
            LoginCredentials loginCredentials = gson.fromJson(jsonBody, LoginCredentials.class);

            AppUser appUser = loginService.validateUser(loginCredentials.getUsername(), loginCredentials.getPassword());

            String json = gson.toJson(appUser);
            ctx.result(json);
        });

        app.post("/appusers", createAccountHandler);//Create a new user
        app.patch("/appusers/{username}/{role}", updateRoleHandler);//Updates user role
        app.get("/appusers", getAllAppUsersHandler); //Get all appusers

        app.start();
    }
}
