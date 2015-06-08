package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;
import static ru.javawebinar.topjava.util.TimeUtil.isBetween;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 500)
        );
        getFilteredMealsWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed> getFilteredMealsWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int maxCaloriesPerDay) {
        List<UserMeal> mealsFilteredByDate = mealList.stream()
                .filter(um -> isBetween(um.getDateTime().toLocalTime(), startTime, endTime))
                .collect(toList());

        Map<LocalDate, Integer> dateToCalories = mealsFilteredByDate.stream()
                .collect(groupingBy(um -> um.getDateTime().toLocalDate(),
                        reducing(0,
                                UserMeal::getCalories,
                                Integer::sum
                        )
                ));

        List<UserMealWithExceed> result = mealsFilteredByDate.stream()
                .map(um -> new UserMealWithExceed(um.getDateTime(),
                        um.getDescription(),
                        um.getCalories(),
                        dateToCalories.get(um.getDateTime().toLocalDate()) > maxCaloriesPerDay))
                .collect(toList());

        return result;
    }
}
