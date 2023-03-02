package dev.terry.handlers.complaints;

import dev.terry.app.App;
import dev.terry.entities.enums.Priority;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class UpdatePriorityHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int id = Integer.parseInt(ctx.pathParam("id"));
        String priority = ctx.pathParam("priority");

        //if(App.complaintService.getComplaintWithId(id) != null){
            //Execute the update
            switch(priority){
                case "HIGH": //assign to high
                    updatePriority(id, "HIGH");
                    ctx.result("Successfully updated the priority on the complaint");
                    break;
                case "LOW": //assign to low
                    updatePriority(id, "LOW");
                    ctx.result("Successfully updated the priority on the complaint");
                    break;
                case "IGNORED": //assign to ignored
                    updatePriority(id, "IGNORED");
                    ctx.result("Successfully updated the priority on the complaint");
                    break;
                case "ADDRESSED": //assign to addressed
                    updatePriority(id, "ADDRESSED");
                    ctx.result("Successfully updated the priority on the complaint");
                case "UNASSIGNED": //assign to unassigned
                    updatePriority(id, "UNASSIGNED");
                    ctx.result("Successfully updated the priority on the complaint");
                    break;
                default:
                    ctx.status(422);
                    ctx.result("Unable to update the priority on complaint.");
                    break;
            }
        //}
        //else {
            //ctx.status(404);
            //ctx.result("Could not find the complaint with that ID");
        //}
    }

    public void updatePriority(int id, String priority){
        App.complaintService.updatePriority(App.complaintService.getComplaintWithId(id), Priority.valueOf(priority));
    }
}
