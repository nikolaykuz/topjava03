package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.repository.jdbc.util.CombinedSqlParameterSource;
import ru.javawebinar.topjava.repository.jdbc.util.LocalDateTimeAwareBeanPropertyRowMapper;

import java.sql.Types;
import java.time.LocalDateTime;
import java.util.List;

/**
 * User: gkislin
 * Date: 26.08.2014
 */

@Repository
public class JdbcUserMealRepositoryImpl implements UserMealRepository {
    private static final LocalDateTimeAwareBeanPropertyRowMapper<UserMeal> ROW_MAPPER = new LocalDateTimeAwareBeanPropertyRowMapper(UserMeal.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    @Override
    public UserMeal save(UserMeal userMeal, int userId) {
        CombinedSqlParameterSource map = new CombinedSqlParameterSource(userMeal);
        map.addValue("user_id", userId);
        map.registerSqlType("dateTime", Types.TIMESTAMP);

        if (userMeal.isNew()) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            namedJdbcTemplate.update(
                    "INSERT INTO meals (description, datetime, calories, user_id) VALUES (:description, :dateTime, :calories, :user_id)",
                    map, keyHolder, new String[]{"id"});
            userMeal.setId(keyHolder.getKey().intValue());
        } else {
            namedJdbcTemplate.update(
                    "UPDATE meals SET description=:description, datetime=:dateTime, calories=:calories, " +
                            "user_id=:user_id WHERE id=:id", map);
        }
        return userMeal;
    }

    @Override
    public boolean delete(int id, int userId) {
        return jdbcTemplate.update("DELETE FROM meals WHERE id = ? AND user_id = ?", id, userId) != 0;
    }

    @Override
    public UserMeal get(int id, int userId) {
        return jdbcTemplate.queryForObject(
                "SELECT id, description, datetime, calories, user_id FROM meals WHERE id = ? AND user_id = ?",
                ROW_MAPPER,
                id, userId
        );
    }

    @Override
    public List<UserMeal> getAll(int userId) {
        return jdbcTemplate.query(
                "SELECT id, description, datetime, calories, user_id FROM meals WHERE user_id = ?",
                ROW_MAPPER,
                userId
        );
    }

    @Override
    public void deleteAll(int userId) {
        jdbcTemplate.update("DELETE FROM meals WHERE user_id = ?", userId);
    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        CombinedSqlParameterSource map = new CombinedSqlParameterSource(new Object());
        map.addValue("user_id", userId);
        map.addValue("start_date", startDate);
        map.addValue("end_date", endDate);
        map.registerSqlType("start_date", Types.TIMESTAMP);
        map.registerSqlType("end_date", Types.TIMESTAMP);

        return namedJdbcTemplate.query(
                "SELECT id, description, datetime, calories, user_id FROM meals WHERE datetime BETWEEN :start_date AND :end_date AND user_id = :user_id",
                map, ROW_MAPPER);
    }
}
