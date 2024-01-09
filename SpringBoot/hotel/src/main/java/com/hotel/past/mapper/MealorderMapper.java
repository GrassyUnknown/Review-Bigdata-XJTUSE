package com.hotel.mapper;

import com.hotel.pojo.Mealorder;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MealorderMapper {
    @Select("select * from mealorder")
    List<Mealorder> list();

    @Delete("delete from mealorder where mealorder_id = #{mealorderId}")
    void delete(Integer mealorderId);

    @Insert("insert into mealorder(mealorder_id, mealorder_timing, mealorder_number, meal_id, mealorder_cost, user_id)" +
            " values(#{mealorderId}, #{mealorderTiming}, #{mealorderNumber}, #{mealId}, #{mealorderCost}, #{userId})")
    void insert(Mealorder mealorder);

    @Select("select * from mealorder where mealorder_id = #{mealorderId}")
    Mealorder select(Integer mealorderId);
}
