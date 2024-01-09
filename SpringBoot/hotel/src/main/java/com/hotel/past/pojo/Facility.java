package com.hotel.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Facility {
        Integer facilityId;
        String facilityName;
        Integer facilityCapacity;
        Integer facilityNow;
}
