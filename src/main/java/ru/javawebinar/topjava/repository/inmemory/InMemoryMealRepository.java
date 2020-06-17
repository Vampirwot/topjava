package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(meal -> {
            this.save(1, meal);
        });
        MealsUtil.MEALS3.forEach(meal -> {
            this.save(3, meal);
        });
    }

    @Override
    public Meal save(Integer id, Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            meal.setUserId(id);
            repository.put(meal.getId(), meal);
            return meal;
        }
        // handle case: update, but not present in storage
        Meal meal1 = repository.get(meal.getId());
        if (meal1 != null && meal1.getUserId().equals(id)) {
            meal.setUserId(id);
            meal1 = repository.put(meal.getId(), meal);
        }
        return meal1;
    }

    @Override
    public boolean delete(Integer idUser, int idMeal) {
        Meal meal = repository.get(idMeal);
        if (meal == null || !meal.getUserId().equals(idUser)) {
            return false;
        }
        return repository.remove(idMeal) != null;
    }

    @Override
    public Meal get(Integer idUser, int idMeal) {
        Meal meal = repository.get(idMeal);
        return idUser.equals(meal.getUserId()) ? meal : null;
    }

    @Override
    public Collection<Meal> getAll(Integer idUser) {
        return repository
                .values()
                .stream()
                .filter(meal -> meal != null && meal.getUserId().equals(idUser))
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
    }
}

