package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;

/**
 * GKislin
 * 11.01.2015.
 */
public class UserMeal extends BaseEntity {
    protected final LocalDateTime dateTime;

    protected final String description;

    protected final int calories;

    protected final User owner;

    public UserMeal(LocalDateTime dateTime, String description, int calories, User owner) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.owner = owner;
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

    public User getOwner() {
        return owner;
    }
}
