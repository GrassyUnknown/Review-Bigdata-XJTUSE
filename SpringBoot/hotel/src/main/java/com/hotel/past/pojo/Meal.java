package com.hotel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Meal {
    Integer mealId;
    String mealName;
    Integer mealCost;

    String mealDescription;
}