package com.hotel.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    Integer roomId;
    Integer roomCost;
    String roomName;
    String roomDescription;
    String roomImg;
}
