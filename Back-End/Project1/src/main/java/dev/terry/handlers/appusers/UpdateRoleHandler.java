package dev.terry.handlers.appusers;

import com.google.gson.Gson;
import dev.terry.app.App;
import dev.terry.entities.AppUser;
import dev.terry.entities.enums.Role;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class UpdateRoleHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        String username = ctx.pathParam("username");
        String role = ctx.pathParam("role");
        Gson gson = new Gson();

        AppUser appUser = App.appUserService.updateRole(App.appUserService.getUserByUsername(username), Role.valueOf(role));

        String json = gson.toJson(appUser);

        ctx.result(json);
        //Press the button
        //Send the request either APPROVE or DENY
        //Get the username and role from the path

    }
}
