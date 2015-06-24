package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.exception.ExceptionUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
@Service
public class UserMealServiceImpl implements UserMealService {
    @Autowired
    private UserMealRepository repository;

    @Override
    public List<UserMeal> getAll(User user) {
        return repository.getAll(user.getId());
    }

    @Override
    public UserMeal get(int id, User user) throws NotFoundException {
        UserMeal userMeal = ExceptionUtil.check(repository.getById(id), id);
        if (userMeal.getOwner().getId().equals(user.getId()))
           throw new NotFoundException("Wrong owner of meal with id " + id);
        return userMeal;
    }

    @Override
    public void delete(int id, User user) throws NotFoundException {
        ExceptionUtil.check(repository.delete(id, user.getId()), id);
    }

    @Override
    public UserMeal save(UserMeal userMeal, User user) {
       return repository.save(userMeal, user.getId());
    }

    @Override
    public void update(UserMeal userMeal, User user) throws NotFoundException {
        ExceptionUtil.check(repository.save(userMeal, user.getId()), userMeal.getId());
    }

    @Override
    public void deleteAll(User user) {
        repository.deleteAll(user.getId());
    }
}
