package com.hotel.controller;

import com.hotel.utils.Result;
import com.hotel.Meal;
import com.hotel.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/meal")
public class MealController {
    @Autowired
    private MealService mealService;

    @GetMapping
    public Result list(){
        List<Meal> mealList = mealService.list();
        return Result.success(mealList);
    }

    @DeleteMapping("/{mealId}")
    public Result delete(@PathVariable Integer mealId){
        mealService.delete(mealId);
        return Result.success();
    }

    @PutMapping
    public Result updateMeal(@RequestBody Meal meal){
        mealService.update(meal);
        return Result.success();
    }

    @PostMapping
    public Result insert(@RequestBody Meal meal){
        mealService.insert(meal);
        return Result.success();
    }

    @GetMapping("/{mealId}")
    public Result select(@PathVariable Integer mealId){
        Meal meal = mealService.select(mealId);
        return Result.success(meal);
    }
}