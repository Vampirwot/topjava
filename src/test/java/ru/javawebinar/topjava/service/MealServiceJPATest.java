package ru.javawebinar.topjava.service;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.service.basemealservice.MealServiceTest;

@ActiveProfiles( profiles = {"jpa"})
public class MealServiceJPATest extends MealServiceTest {

}
