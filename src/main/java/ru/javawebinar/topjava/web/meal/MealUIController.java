package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/profile/meals")
public class MealUIController extends AbstractMealController{

    private final Logger log = LoggerFactory.getLogger(getClass());

    @PostMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void createMeal(@RequestParam LocalDateTime dateTime,
                           @RequestParam String description,
                           @RequestParam Integer calories
    ){
        Meal meal = new Meal(dateTime,description,calories);
        super.create(meal);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(value =  HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        super.delete(id);
    }
}
