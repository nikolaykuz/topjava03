package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.javawebinar.topjava.model.UserMeal;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

public interface ProxyUserMealRepository extends JpaRepository<UserMeal, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM UserMeal m WHERE m.id=:id AND m.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Modifying
    @Transactional
    @Query("DELETE FROM UserMeal m WHERE m.user.id=:userId")
    void deleteAll(@Param("userId") int userId);

    @Query("SELECT m FROM UserMeal m WHERE m.id=:id AND m.user.id=:userId")
    UserMeal get(@Param("id") int id, @Param("userId") int userId);

    @Query("SELECT m FROM UserMeal m WHERE m.user.id=:userId ORDER BY m.dateTime DESC")
    List<UserMeal> getAll(@Param("userId") int userId);

    @Override
    UserMeal save(UserMeal userMeal);

    @Query("SELECT m FROM UserMeal m WHERE m.user.id=:userId AND m.dateTime BETWEEN :after AND :before ORDER BY m.dateTime DESC")
    List<UserMeal> getBetween(@Param("after") LocalDateTime startDate, @Param("before") LocalDateTime endDate, @Param("userId") int userId);
}
