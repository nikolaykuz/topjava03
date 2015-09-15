package ru.javawebinar.topjava.web.mock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.util.exception.NotFoundException;
import ru.javawebinar.topjava.web.user.AdminUserRestController;

/**
 * GKislin
 * 13.03.2015.
 */
@ContextConfiguration({"classpath:spring/spring-mvc.xml", "classpath:spring/spring-app.xml", "classpath:spring/mock.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserAdminSpringMockTest {

    @Autowired
    private AdminUserRestController controller;

    @Test
    public void testCreate() throws Exception {
        controller.create(new User(null, "Name", "email@ya.ru", "password", Role.ROLE_USER));
    }

    @Test
    public void testDelete() throws Exception {
        controller.delete(7);
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteNotFound() throws Exception {
        controller.delete(0);
    }
}
