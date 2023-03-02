package dev.terry.handlers.complaints;

import dev.terry.app.App;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class UpdateComplaintMeetingHandler implements Handler {

    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int id = Integer.parseInt(ctx.pathParam("id"));
        int meetingId = Integer.parseInt(ctx.pathParam("mId"));

        //if(App.complaintService.getComplaintWithId(id) != null){
            if(App.meetingService.getMeetingWithId(meetingId) != null){
                //Execute the update
                App.complaintService.updateMeeting(App.complaintService.getComplaintWithId(id), meetingId);
                ctx.result("Successfully updated the meeting on the complaint");
            }
            else {
                //Could not find the ID
                ctx.status(404);
                ctx.result("Could not find the meeting with that ID");
            }
        //}
        //else {
            //ctx.status(404);
            //ctx.result("Could not find the complaint with that ID");
        //}
    }
}
