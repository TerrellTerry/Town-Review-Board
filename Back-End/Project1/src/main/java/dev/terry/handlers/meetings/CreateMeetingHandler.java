package dev.terry.handlers.meetings;

import com.google.gson.Gson;
import dev.terry.app.App;
import dev.terry.entities.Meeting;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class CreateMeetingHandler implements Handler {

    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        String json = ctx.body();
        Gson gson = new Gson();

        Meeting meeting = gson.fromJson(json, Meeting.class);

        App.meetingService.createMeeting(meeting);

        ctx.status(201);
        ctx.result("Successfully created meeting");
    }
}
