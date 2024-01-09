package com.hotel.controller;

import com.hotel.utils.Result;
import com.hotel.pojo.Mealorder;
import com.hotel.service.MealorderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/mealorder")
public class MealorderController {
    @Autowired
    private MealorderService mealorderService;

    @GetMapping
    public Result list(){
        List<Mealorder> mealorderList = mealorderService.list();
        return Result.success(mealorderList);
    }

    @DeleteMapping("/{mealorderId}")
    public Result delete(@PathVariable Integer mealorderId){
        mealorderService.delete(mealorderId);
        return Result.success();
    }

    @PostMapping
    public Result insert(@RequestBody Mealorder mealorder){
        mealorderService.insert(mealorder);
        return Result.success();
    }

    @GetMapping("/{mealorderId}")
    public Result select(@PathVariable Integer mealorderId){
        Mealorder mealorder = mealorderService.select(mealorderId);
        return Result.success(mealorder);
    }
}