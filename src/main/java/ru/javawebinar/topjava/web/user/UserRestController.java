package ru.javawebinar.topjava.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.LoggerWrapper;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.UserService;

/**
 * GKislin
 * 06.03.2015.
 */
@Controller
public class UserRestController {
    private static final LoggerWrapper LOG = LoggerWrapper.get(UserRestController.class);

    @Autowired
    private UserService service;

    public User get() {
        int id = LoggedUser.id();
        LOG.info("get", id);
        return service.get(id);
    }

    public void delete() {
        int id = LoggedUser.id();
        LOG.info("delete {}", id);
        service.delete(id);
    }

    public void update(User user) {
        int id = LoggedUser.id();
        LOG.info("update");
        service.update(user);
    }
}