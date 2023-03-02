package dev.terry.services;

import dev.terry.data.AppUserDAO;
import dev.terry.entities.AppUser;
import dev.terry.entities.enums.Role;

import java.util.List;

public class AppUserServiceImpl implements AppUserService{

    private AppUserDAO appUserDAO;

    public AppUserServiceImpl(AppUserDAO appUserDAO){
        this.appUserDAO = appUserDAO;
    }
    @Override
    public AppUser createAccount(AppUser appUser) {
        if(appUser.getUsername().length() == 0 || appUser.getPassword().length() == 0)
        {
            throw new RuntimeException("Username/Password cannot be empty");
        }
        if(appUser.getFname().length() == 0 || appUser.getLname().length() == 0) {
            throw new RuntimeException("Name fields cannot be empty");
        }

        return this.appUserDAO.createAccount(appUser);
    }

    public AppUser getUserByUsername(String username){
        return this.appUserDAO.getAppUserByUsername(username);
    }

    @Override
    public AppUser updateRole(AppUser appUser, Role role) {
        return this.appUserDAO.updateRole(appUser, role);
    }

    @Override
    public List<AppUser> getAllAppUsers() {
        return this.appUserDAO.getAllAppUsers();
    }
}
