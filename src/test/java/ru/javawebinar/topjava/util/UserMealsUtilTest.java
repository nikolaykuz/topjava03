package ru.javawebinar.topjava.util;


import org.junit.Test;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.assertEquals;

public class UserMealsUtilTest {
    private final static List<UserMeal> MEAL_LIST = Arrays.asList(
            new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 200),
            new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 300),
            new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 400),
            new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
            new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 600),
            new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 700)
    );

    @Test
    public void testAllExceed() throws Exception {
        List<UserMealWithExceed> filtered = UserMealsUtil.getFilteredMealsWithExceeded(MEAL_LIST, LocalTime.of(10, 0), LocalTime.of(20, 0), 150);
        assertFiltered(filtered, 6);
    }

    @Test
    public void testNoneExceed() throws Exception {
        List<UserMealWithExceed> filtered = UserMealsUtil.getFilteredMealsWithExceeded(MEAL_LIST, LocalTime.of(10, 0), LocalTime.of(20, 0), 700);
        assertFiltered(filtered, 0);
    }

    @Test
    public void testSingleDoesNotExceed() throws Exception {
        List<UserMealWithExceed> filtered = UserMealsUtil.getFilteredMealsWithExceeded(MEAL_LIST, LocalTime.of(10, 0), LocalTime.of(20, 0), 200);
        assertFiltered(filtered, 5);
    }

    @Test
    public void testOutOfStartTime() throws Exception {
        List<UserMealWithExceed> filtered = UserMealsUtil.getFilteredMealsWithExceeded(MEAL_LIST, LocalTime.of(13, 0), LocalTime.of(20, 0), 150);
        assertFiltered(filtered, 4);
    }

    @Test
    public void testOutOfEndTime() throws Exception {
        List<UserMealWithExceed> filtered = UserMealsUtil.getFilteredMealsWithExceeded(MEAL_LIST, LocalTime.of(10, 0), LocalTime.of(19, 59), 150);
        assertFiltered(filtered, 4);
    }

    @Test
    public void testAllAffect() throws Exception {
        List<UserMealWithExceed> filtered = UserMealsUtil.getFilteredMealsWithExceeded(MEAL_LIST, LocalTime.of(11, 0), LocalTime.of(19, 59), 400);
        assertFiltered(filtered, 1);
    }

    @Test
    public void testNoneSelected() throws Exception {
        List<UserMealWithExceed> filtered = UserMealsUtil.getFilteredMealsWithExceeded(MEAL_LIST, LocalTime.of(11, 0), LocalTime.of(19, 59), 800);
        assertFiltered(filtered, 0);
    }


    private static void assertFiltered(List<UserMealWithExceed> filtered, int size) {
        assertEquals("Wrong number of exceeded entities", size, filtered.size());

        //TODO: assert that (but now there are no getters)
        //all flags are true
        //date within range
        //calories exceed limit
    }
}
