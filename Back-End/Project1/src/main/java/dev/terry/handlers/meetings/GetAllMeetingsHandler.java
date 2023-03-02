package dev.terry.handlers.meetings;

import com.google.gson.Gson;
import dev.terry.app.App;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class GetAllMeetingsHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        Gson gson = new Gson();

        String json = gson.toJson(App.meetingService.getAllMeetings());

        ctx.result(json);
    }
}
