package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.UserMealService;
import ru.javawebinar.topjava.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Controller
public class UserMealController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserMealService service;

    @RequestMapping(value = "/meals", method = RequestMethod.GET)
    public String mealList(Model model) {
        List<UserMeal> allMeals = userService.getAll().stream().
                flatMap(user -> service.getAll(user.getId()).stream()).
                collect(toList());

        model.addAttribute("mealList", allMeals);
        return "mealList";
    }
}
