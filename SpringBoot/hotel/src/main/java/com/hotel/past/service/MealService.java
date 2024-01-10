package com.hotel.service;

import com.hotel.Meal;

import java.util.List;

public interface MealService {
    List<Meal> list();

    void delete(Integer mealId);

    void update(Meal meal);

    void insert(Meal meal);

    Meal select(Integer mealId);
}