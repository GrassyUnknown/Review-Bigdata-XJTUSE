package com.hotel.mapper;

import com.hotel.pojo.Meal;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MealMapper {
    @Select("select * from meal")
    List<Meal> list();

    @Delete("delete from meal where meal_id = #{mealId}")
    void delete(Integer mealId);

    @Update("update meal set meal_name = #{mealName},meal_cost = #{mealCost},meal_description = #{mealDescription} where meal_id = #{mealId}")
    void update(Meal meal);

    @Insert("insert into meal(meal_id, meal_name, meal_cost, meal_description)" +
            " values(#{mealId}, #{mealName}, #{mealCost}, #{mealDescription})")
    void insert(Meal meal);

    @Select("select * from meal where meal_id = #{mealId}")
    Meal select(Integer mealId);
}
