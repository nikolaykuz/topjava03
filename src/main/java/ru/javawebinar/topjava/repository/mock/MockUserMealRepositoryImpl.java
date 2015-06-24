package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.LoggerWrapper;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;

import java.util.List;

/**
 * GKislin
 * 15.06.2015.
 */
@Repository
public class MockUserMealRepositoryImpl implements UserMealRepository {
    private static final LoggerWrapper LOG = LoggerWrapper.get(MockUserMealRepositoryImpl.class);

    @Override
    public List<UserMeal> getAll(int userId) {
        return null;
    }

    @Override
    public UserMeal getById(int mealId) {
        return null;
    }

    @Override
    public boolean delete(int mealId, int userId) {
        return false;
    }

    @Override
    public UserMeal save(UserMeal userMeal, int userId) {
        return userMeal;
    }

    @Override
    public int deleteAll(int userId) {
        return 0;
    }
}
