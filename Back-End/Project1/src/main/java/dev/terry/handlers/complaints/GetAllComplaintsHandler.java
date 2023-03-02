package dev.terry.handlers.complaints;

import com.google.gson.Gson;
import dev.terry.app.App;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class GetAllComplaintsHandler implements Handler {

    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        Gson gson = new Gson();

        String json = gson.toJson(App.complaintService.getAllComplaints());

        ctx.result(json);
    }
}
