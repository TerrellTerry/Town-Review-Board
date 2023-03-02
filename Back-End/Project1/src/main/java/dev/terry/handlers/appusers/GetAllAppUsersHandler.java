package dev.terry.handlers.appusers;

import com.google.gson.Gson;
import dev.terry.app.App;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class GetAllAppUsersHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        Gson gson = new Gson();

        String json = gson.toJson(App.appUserService.getAllAppUsers());

        ctx.result(json);
    }
}
