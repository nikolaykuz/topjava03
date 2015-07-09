package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.LoggerWrapper;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * GKislin
 * 15.06.2015.
 */
@Repository
public class MockUserMealRepositoryImpl implements UserMealRepository {
    private static final LoggerWrapper LOG = LoggerWrapper.get(MockUserMealRepositoryImpl.class);

    @Override
    public boolean delete(int id, int userId) {
        LOG.info("delete {} for User {}", id, userId);
        return true;
    }

    @Override
    public UserMeal save(UserMeal meal, int userId) {
        LOG.info("save {} for User {}", meal, userId);
        return meal;
    }

    @Override
    public UserMeal get(int id, int userId) {
        LOG.info("get {} for User {}", id, userId);
        return null;
    }

    @Override
    public List<UserMeal> getAll(int userId) {
        LOG.info("getAll for User {}", userId);
        return MealTestData.USER_MEALS;
    }

    @Override
    public void deleteAll(int userId) {
        LOG.info("deleteAll for User {}", userId);
    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        LOG.info("getBetween {} - {} for User {}", startDate, endDate, userId);
        return MealTestData.USER_MEALS;
    }
}
