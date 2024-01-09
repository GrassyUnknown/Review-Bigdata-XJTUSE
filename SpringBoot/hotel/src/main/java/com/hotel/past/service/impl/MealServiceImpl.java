package com.hotel.service.impl;

import com.hotel.mapper.MealMapper;
import com.hotel.pojo.Meal;
import com.hotel.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealServiceImpl implements MealService {
    @Autowired
    private MealMapper mealMapper;
    @Override
    public List<Meal> list() {
        return mealMapper.list();
    }

    @Override
    public void delete(Integer mealId) {
        mealMapper.delete(mealId);
    }

    @Override
    public void update(Meal meal) {
        mealMapper.update(meal);
    }

    @Override
    public void insert(Meal meal) {
        mealMapper.insert(meal);
    }

    @Override
    public Meal select(Integer mealId) {
        return mealMapper.select(mealId);
    }
}
