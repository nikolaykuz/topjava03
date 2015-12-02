package ru.javawebinar.topjava;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import ru.javawebinar.topjava.model.BaseEntity;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.to.UserTo;
import ru.javawebinar.topjava.util.PasswordUtil;
import ru.javawebinar.topjava.util.UserUtil;

import java.io.Serializable;
import java.util.Set;

import static java.util.Objects.requireNonNull;

/**
 * GKislin
 * 06.03.2015.
 * <p>
 * Mock implementation
 */
public class LoggedUser implements UserDetails, Serializable {
    protected final UserTo userTo;
    private final boolean enabled;
    private final Set<Role> roles;
    private String encodedPassword;

    public LoggedUser(User user) {
        this.userTo = UserUtil.asTo(user);
        this.enabled = user.isEnabled();
        this.roles = user.getRoles();
        this.encodedPassword = user.getPassword();
    }

    public static LoggedUser safeGet() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        Object user = auth.getPrincipal();
        return (user instanceof LoggedUser) ? (LoggedUser) user : null;
    }

    public static LoggedUser get() {
        LoggedUser user = safeGet();
        requireNonNull(user, "No authorized user found");
        return user;
    }

    public UserTo getUserTo() {
        return userTo;
    }

    public UserTo update(UserTo updatedTo) {
        userTo.setName(updatedTo.getName());
        userTo.setEmail(updatedTo.getEmail());
        String newPassword = updatedTo.getPassword();
        userTo.setPassword(newPassword);
        encodedPassword = PasswordUtil.encode(newPassword);
        return userTo;
    }

    public static int id() {
//        return get().userTo.getId();
        return BaseEntity.START_SEQ;
    }

    @Override
    public Set<Role> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return encodedPassword;
    }

    @Override
    public String getUsername() {
        return userTo.getEmail();
    }

    public static int getCaloriesPerDay() {
        return 2000;
    }

    @Override
    public boolean isAccountNonExpired() {
        return enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return enabled;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
