package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.LoggerWrapper;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

/**
 * GKislin
 * 15.06.2015.
 */
@Repository
public class MockUserMealRepositoryImpl implements UserMealRepository {
    private static final LoggerWrapper LOG = LoggerWrapper.get(MockUserRepositoryImpl.class);
    private static final List<UserMeal> mealList = Arrays.asList(
            new UserMeal(1, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
            new UserMeal(2, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
            new UserMeal(3, LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
            new UserMeal(4, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 500),
            new UserMeal(5, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 1000),
            new UserMeal(6, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
    );

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
        return mealList;
    }

    @Override
    public void deleteAll(int userId) {
        LOG.info("deleteAll for User {}", userId);
    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        LOG.info("getBetween {} - {} for User {}", startDate, endDate, userId);
        return mealList;
    }
}
