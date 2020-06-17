package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.ValidationUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.util.Collection;

import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;

@Controller
public class MealRestController {

    private MealService service;

    @Autowired
    public MealRestController(MealService service) {
        this.service = service;
    }

    public Meal create(Meal meal) {
        return service.create(authUserId(), meal);
    }

    public void delete(int idMeal) {
        service.delete(authUserId(), idMeal);
    }

    public Meal get(int idMeal) {
        return service.get(authUserId(), idMeal);
    }

    public Collection<Meal> getAll() {
        return service.getAll(authUserId());
    }

    public void update(Meal meal) {
        service.update(authUserId(), meal);
    }
}