package ru.javawebinar.topjava.util;


import org.junit.Test;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static junit.framework.Assert.assertEquals;

public class UserMealsUtilTest {
    private final static List<UserMeal> MEAL_LIST = asList(
            new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 200),
            new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 300),
            new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 400),
            new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 500),
            new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 600),
            new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 700)
    );

    @Test
    public void testAllExceed() throws Exception {
        List<UserMealWithExceed> filtered = UserMealsUtil.getFilteredMealsWithExceeded(MEAL_LIST, LocalTime.of(10, 0), LocalTime.of(20, 0), 150);
        assertFiltered(filtered, 6);
        assertExceedFlags(filtered, asList(true, true, true, true, true, true));
    }

    @Test
    public void testNoneExceed() throws Exception {
        List<UserMealWithExceed> filtered = UserMealsUtil.getFilteredMealsWithExceeded(MEAL_LIST, LocalTime.of(10, 0), LocalTime.of(20, 0), 2000);
        assertFiltered(filtered, 6);
        assertExceedFlags(filtered, asList(false, false, false, false, false, false));
    }

    @Test
    public void testOutOfStartTime() throws Exception {
        List<UserMealWithExceed> filtered = UserMealsUtil.getFilteredMealsWithExceeded(MEAL_LIST, LocalTime.of(13, 0), LocalTime.of(20, 0), 800);
        assertFiltered(filtered, 4);
        assertExceedFlags(filtered, asList(true, true, true, true));
    }

    @Test
    public void testOutOfEndTime() throws Exception {
        List<UserMealWithExceed> filtered = UserMealsUtil.getFilteredMealsWithExceeded(MEAL_LIST, LocalTime.of(10, 0), LocalTime.of(19, 59), 400);
        assertFiltered(filtered, 4);
        assertExceedFlags(filtered, asList(true, true, true, true));
    }

    @Test
    public void testNoneSelected() throws Exception {
        List<UserMealWithExceed> filtered = UserMealsUtil.getFilteredMealsWithExceeded(MEAL_LIST, LocalTime.of(13, 1), LocalTime.of(19, 59), 800);
        assertFiltered(filtered, 0);
    }


    private static void assertFiltered(List<UserMealWithExceed> filtered, int size) {
        assertEquals("Wrong number of exceeded entities", size, filtered.size());
    }

    private static void assertExceedFlags(List<UserMealWithExceed> filtered, List<Boolean> flags) {
        assertEquals("User meals and exceed flags have different size", filtered.size(), flags.size());
        for (int i = 0; i < filtered.size(); i++) {
            assertEquals("Wrong exceed flag at index " + i, flags.get(i).booleanValue(), filtered.get(i).isExceed());
        }
    }
}
