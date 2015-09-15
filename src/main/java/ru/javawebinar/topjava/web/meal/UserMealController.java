package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.service.UserMealService;

/**
 * User: gkislin
 * Date: 22.08.2014
 */
@Controller
public class UserMealController {
    @Autowired
    private UserMealService service;

    @RequestMapping(value = "/meals", method = RequestMethod.GET)
    public String mealList(Model model) {
        model.addAttribute("mealList", service.getAll(LoggedUser.id()));
        return "mealList";
    }
}
