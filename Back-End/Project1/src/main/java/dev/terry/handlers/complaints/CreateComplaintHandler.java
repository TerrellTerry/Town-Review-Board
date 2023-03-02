package dev.terry.handlers.complaints;

import com.google.gson.Gson;
import dev.terry.app.App;
import dev.terry.entities.Complaint;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class CreateComplaintHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        String json = ctx.body();
        Gson gson = new Gson();
        Complaint complaint = gson.fromJson(json, Complaint.class);

        App.complaintService.createComplaint(complaint);

        ctx.status(201);
        ctx.result("Successfully created a new complaint.");
    }
}
