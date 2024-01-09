package com.hotel.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Facilityorder {
    Integer facilityorderId;
    Integer facilityId;
    Integer userId;
    String reserveTiming;
}
