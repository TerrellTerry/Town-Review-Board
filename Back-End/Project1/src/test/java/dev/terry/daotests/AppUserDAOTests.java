package dev.terry.daotests;

import dev.terry.data.AppUserDAO;
import dev.terry.data.AppUserDAOPostgres;
import dev.terry.data.ComplaintDAO;
import dev.terry.data.ComplaintDAOPostgres;
import dev.terry.entities.AppUser;
import dev.terry.entities.enums.Role;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AppUserDAOTests {

    static AppUserDAO appUserDAO = new AppUserDAOPostgres();

    @Test
    @Order(1)
    void create_new_user(){
        AppUser appUser = new AppUser(0, "Kirito", "TheBestPlayerEver123", "Kazuto", "Kirigaya", false, Role.UNREGISTERED);
        AppUser savedAppUser = appUserDAO.createAccount(appUser);
        Assertions.assertNotNull(savedAppUser);
    }

    @Test
    @Order(2)
    void update_user_role(){
        AppUser appUser = appUserDAO.getAppUserByUsername("Kirito");
        AppUser savedUser = appUserDAO.updateRole(appUser, Role.CONSTITUENT);
        Assertions.assertEquals(savedUser.getRole(), Role.CONSTITUENT);
    }

    @Test
    @Order(3)
    void get_all_app_users()
    {
        Assertions.assertNotEquals(0, appUserDAO.getAllAppUsers().size());
    }
}
