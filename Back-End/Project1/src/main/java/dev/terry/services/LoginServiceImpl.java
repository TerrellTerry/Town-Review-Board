package dev.terry.services;

import dev.terry.data.AppUserDAO;
import dev.terry.entities.AppUser;

public class LoginServiceImpl implements LoginService{

    private AppUserDAO appUserDAO;

    public LoginServiceImpl(AppUserDAO appUserDAO){
        this.appUserDAO = appUserDAO;
    }

    @Override
    public AppUser validateUser(String username, String password) {
        AppUser appUser = this.appUserDAO.getAppUserByUsername(username);
        if(appUser == null)
        {
            throw new RuntimeException("User with that username does not exist");
        }
        if(!appUser.getPassword().equals(password))
        {
            throw new RuntimeException("Password does not match");
        }

        return appUser;
    }
}
