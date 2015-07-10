package ru.javawebinar.topjava.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

/**
 * User: gkislin
 * Date: 26.08.2014
 */

@Repository
@Transactional(readOnly = true)
public class JpaUserMealRepositoryImpl implements UserMealRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public UserMeal save(UserMeal userMeal, int userId) {
        User ref = em.getReference(User.class, userId);
        if (ref == null)
            return null; //no such user
        if (userMeal.isNew()) {
            userMeal.setUser(ref);
            em.persist(userMeal);
            return userMeal;
        } else {
            User user = userMeal.getUser();
            if (user == null || ref.equals(user)) {
                userMeal.setUser(ref);
                return em.merge(userMeal);
            }
        }
        return null;
    }

    @Transactional
    @Override
    public boolean delete(int id, int userId) {
        return em.createNamedQuery(UserMeal.DELETE).
                setParameter("id", id).
                setParameter("user_id", userId).
                executeUpdate() > 0;

    }

    @Override
    public UserMeal get(int id, int userId) {
        try {
            return em.createNamedQuery(UserMeal.GET, UserMeal.class).
                    setParameter("id", id).
                    setParameter("user_id", userId).
                    getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<UserMeal> getAll(int userId) {
        return em.createNamedQuery(UserMeal.ALL_SORTED, UserMeal.class).setParameter("user_id", userId).getResultList();
    }

    @Transactional
    @Override
    public void deleteAll(int userId) {
        em.createNamedQuery(UserMeal.DELETE_ALL).setParameter("user_id", userId).executeUpdate();
    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return em.createNamedQuery(UserMeal.GET_BETWEEN, UserMeal.class).
                setParameter("user_id", userId).
                setParameter("start_time", startDate).
                setParameter("end_time", endDate).
                getResultList();
    }
}