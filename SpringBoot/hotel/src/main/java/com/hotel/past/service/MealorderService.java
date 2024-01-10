package com.hotel.service;

import com.hotel.Mealorder;

import java.util.List;

public interface MealorderService {
    List<Mealorder> list();

    void delete(Integer mealorderId);

    void insert(Mealorder mealorder);

    Mealorder select(Integer mealorderId);
}