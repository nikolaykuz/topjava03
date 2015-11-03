package ru.javawebinar.topjava.util;

import org.springframework.util.StringUtils;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.to.UserTo;

/**
 * GKislin
 */
public class UserUtil {
    public static User createFromTo(UserTo newUser) {
        return new User(null, newUser.getName(), newUser.getEmail(), newUser.getPassword(), Role.ROLE_USER);
    }

    public static UserTo asTo(User user) {
        return new UserTo(user.getId(), user.getName(), user.getEmail(), user.getPassword());
    }

    public static User updateFromTo(User user, UserTo userTo) {
        String password = userTo.getPassword();
        if (!StringUtils.isEmpty(password)) {
            user.setPassword(PasswordUtil.encode(password));
        }
        user.setName(userTo.getName());
        user.setEmail(userTo.getEmail().toLowerCase());
        return user;
    }

    public static User normalize(User user) {
        user.setPassword(PasswordUtil.encode(user.getPassword()));
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }
}
