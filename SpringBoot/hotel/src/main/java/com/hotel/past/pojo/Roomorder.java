package com.hotel.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Roomorder {
    Integer roomorderId;
    Integer userId;
    Integer roomorderCost;
    String roomorderDescription;
    String roomorderReservedate;
    Integer roomId;
}
