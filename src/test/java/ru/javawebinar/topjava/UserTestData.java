package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.BaseEntity;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

/**
 * User: gkislin
 * Date: 26.08.2014
 */
public class UserTestData {
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;

    public static final TestUser USER = new TestUser(BaseEntity.START_SEQ, "User", "user@yandex.ru", "password", true, Role.ROLE_USER);
    public static final User ADMIN = new TestUser(BaseEntity.START_SEQ + 1, "Admin", "admin@gmail.com", "admin", true, Role.ROLE_ADMIN);

    public static class TestUser extends User {

        public TestUser(User u) {
            this(u.getId(), u.getName(), u.getEmail(), u.getPassword(), u.isEnabled(), u.getRoles());
        }

        public TestUser(String name, String email, String password, Role role, Role... roles) {
            this(null, name, email, password, true, EnumSet.of(role, roles));
        }

        public TestUser(Integer id, String name, String email, String password, boolean enabled, Role role, Role... roles) {
            this(id, name, email, password, enabled, EnumSet.of(role, roles));
        }

        public TestUser(Integer id, String name, String email, String password, boolean enabled, Set<Role> roles) {
            super(id, name, email, password, enabled, roles);
        }

        public User asUser() {
            return new User(this);
        }

        @Override
        public String toString() {
            return "User (" +
                    "id=" + id +
                    ", email=" + email +
                    ", name=" + name +
                    ", enabled=" + enabled +
                    ", password=" + password +
                    ", authorities=" + roles +
                    ')';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            TestUser that = (TestUser) o;

            return Objects.equals(this.password, that.password)
                    && Objects.equals(this.id, that.id)
                    && Objects.equals(this.name, that.name)
                    && Objects.equals(this.email, that.email)
                    && Objects.equals(this.enabled, that.enabled);
//                    && Objects.equals(this.roles, that.roles);
        }
    }

    public static final ModelMatcher<User, TestUser> MATCHER = new ModelMatcher<>(
            u -> ((u instanceof TestUser) ? (TestUser) u : new TestUser(u)));

}
