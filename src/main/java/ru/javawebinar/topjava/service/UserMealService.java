package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * GKislin
 * 15.06.2015.
 */
public interface UserMealService {
    UserMeal get(int id, int userId);

    void delete(int id, int userId);

    default List<UserMeal> getBetweenDates(LocalDate startDate, LocalDate endDate, int userId) {
        return getBetweenDateTimes(LocalDateTime.of(startDate, LocalTime.MIN), LocalDateTime.of(endDate, LocalTime.MAX), userId);
    }

    List<UserMeal> getBetweenDateTimes(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId);

    List<UserMeal> getAll(int userId);

    void deleteAll(int userId);

    UserMeal update(UserMeal meal, int userId);

    UserMeal save(UserMeal meal, int userId);
}
