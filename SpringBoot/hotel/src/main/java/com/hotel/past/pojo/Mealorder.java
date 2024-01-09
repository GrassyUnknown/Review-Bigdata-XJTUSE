package com.hotel.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mealorder {
    Integer mealorderId;
    String mealorderTiming;
    Integer mealorderNumber;
    Integer mealId;
    Integer mealorderCost;
    Integer userId;
}
