package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;

public interface MealRepository {
    // null if not found, when updated
    Meal save(Integer id,Meal meal);

    // false if not found
    boolean delete(Integer idUser, int idMeal);

    // null if not found
    Meal get(Integer idUser,int idMeal);

    Collection<Meal> getAll(Integer idUser);
}
