package dev.terry.data;

import dev.terry.entities.AppUser;
import dev.terry.entities.enums.Role;

import java.util.List;

public interface AppUserDAO {
    public AppUser createAccount(AppUser appUser);

    public AppUser getAppUserByUsername(String username);

    public AppUser updateRole(AppUser appUser, Role role);

    public List<AppUser> getAllAppUsers();

}
