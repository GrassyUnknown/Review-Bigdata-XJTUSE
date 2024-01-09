package com.hotel.service.impl;

import com.hotel.mapper.MealorderMapper;
import com.hotel.pojo.Mealorder;
import com.hotel.service.MealorderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealorderServiceImpl implements MealorderService {
    @Autowired
    private MealorderMapper mealorderMapper;
    @Override
    public List<Mealorder> list() {
        return mealorderMapper.list();
    }

    @Override
    public void delete(Integer mealorderId) {
        mealorderMapper.delete(mealorderId);
    }

    @Override
    public void insert(Mealorder mealorder) {
        mealorderMapper.insert(mealorder);
    }

    @Override
    public Mealorder select(Integer mealorderId) {
        return mealorderMapper.select(mealorderId);
    }
}