package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.BaseEntity;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;

/**
 * GKislin
 * 13.03.2015.
 */
public class MealTestData {
    public static final LocalDateTime BREAKFAST_DATE_TIME = LocalDateTime.parse("2015-01-14T10:00:00");
    public static final LocalDateTime LUNCH_DATE_TIME = LocalDateTime.parse("2015-01-14T13:30:00");
    public static final LocalDateTime DINNER_DATE_TIME = LocalDateTime.parse("2015-01-14T18:00:00");

    public static UserMeal BREAKFAST = new UserMeal(BaseEntity.START_SEQ + 2, BREAKFAST_DATE_TIME, "Breakfast", 500);
    public static UserMeal LUNCH = new UserMeal(BaseEntity.START_SEQ + 3, LUNCH_DATE_TIME, "Lunch", 1000);
    public static UserMeal DINNER = new UserMeal(BaseEntity.START_SEQ + 4, DINNER_DATE_TIME, "Dinner", 700);

    public static final ModelMatcher<UserMeal, String> MATCHER = new ModelMatcher<>(UserMeal::toString);

}
