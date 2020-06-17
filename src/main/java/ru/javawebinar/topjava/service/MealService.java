package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.ValidationUtil;

import java.util.Collection;

@Service
public class MealService {

    private MealRepository repository;

    @Autowired
    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public Meal create(int userId, Meal meal) {
        return repository.save(userId, meal);
    }

    public void delete(int userId, int idMeal) {
        ValidationUtil.checkNotFoundWithId(repository.delete(userId, idMeal), idMeal);
    }

    public Meal get(int idUser, int idMeal) {
        return ValidationUtil.checkNotFoundWithId(repository.get(idUser, idMeal), idMeal);
    }

    public Collection<Meal> getAll(int idUser) {
        return repository.getAll(idUser);
    }

    public void update(int idUser, Meal meal) {
        ValidationUtil.checkNotFoundWithId(repository.save(idUser, meal), meal.getId());
    }
}