package dev.terry.handlers.appusers;

import com.google.gson.Gson;
import dev.terry.app.App;
import dev.terry.entities.AppUser;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class CreateAccountHandler implements Handler {

    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        String json = ctx.body();
        Gson gson = new Gson();

        AppUser appUser = gson.fromJson(json, AppUser.class);

        AppUser newUser = App.appUserService.createAccount(appUser);

        String appUserJson = gson.toJson(newUser);
        ctx.status(201);
        ctx.result(appUserJson);
    }
}
