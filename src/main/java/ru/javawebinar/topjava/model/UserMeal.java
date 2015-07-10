package ru.javawebinar.topjava.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

/**
 * GKislin
 * 11.01.2015.
 */
@Entity
@Table(name = "MEALS")
@NamedQueries({
        @NamedQuery(name = UserMeal.DELETE_ALL, query = "DELETE FROM UserMeal m WHERE m.user.id=:user_id"),
        @NamedQuery(name = UserMeal.DELETE, query = "DELETE FROM UserMeal m WHERE m.id=:id AND m.user.id=:user_id"),
        @NamedQuery(name = UserMeal.GET, query = "SELECT m FROM UserMeal m WHERE m.id=:id AND m.user.id=:user_id"),
        @NamedQuery(name = UserMeal.GET_BETWEEN, query = "SELECT m FROM UserMeal m WHERE m.user.id=:user_id AND dateTime BETWEEN :start_time AND :end_time"),
        @NamedQuery(name = UserMeal.ALL_SORTED, query = "SELECT m FROM UserMeal m WHERE m.user.id=:user_id ORDER BY m.id DESC"),
})
public class UserMeal extends BaseEntity {
    public static final String ALL_SORTED = "UserMeal.getAllSorted";
    public static final String GET = "UserMeal.get";
    public static final String GET_BETWEEN = "UserMeal.getBetween";
    public static final String DELETE = "UserMeal.delete";
    public static final String DELETE_ALL = "UserMeal.deleteAll";

    @Column(name = "dateTime", nullable = false)
    //@Convert(converter = LocalDateTimeConverter.class)
    protected LocalDateTime dateTime;

    @Column(name = "description")
    protected String description;

    @Column(name = "calories", nullable = false)
    @Min(value = 0)
    protected int calories;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public UserMeal() {
    }

    public UserMeal(Integer id, LocalDateTime dateTime, String description, int calories) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Meal(" + id + ", " + dateTime + ", '" + description + "', calories:" + calories + ')';
    }
}
