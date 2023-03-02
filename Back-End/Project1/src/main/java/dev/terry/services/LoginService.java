package dev.terry.services;

import dev.terry.entities.AppUser;

public interface LoginService {
    public AppUser validateUser(String username, String password);
}
