package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Set;

/**
 * GKislin
 * 06.03.2015.
 */
public class LoggedUser {

    public static int id() {
        return 1;
    }

    public  static User getUser() {
        return new User(0, "", "", "", Role.ROLE_USER);
    }
}
