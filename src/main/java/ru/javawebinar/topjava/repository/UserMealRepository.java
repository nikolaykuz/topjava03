package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.UserMeal;

import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
public interface UserMealRepository {

    List<UserMeal> getAll(int userId);

    // null if not found
    UserMeal getById(int mealId);

    // false if not found
    boolean delete(int mealId, int userId);

    UserMeal save(UserMeal userMeal, int userId);

    // number of deleted
    int deleteAll(int userId);

    //TODO: filtering
}
