package com.hotel.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Checkin)实体类
 *
 * @author makejava
 * @since 2024-01-09 09:51:12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Checkin implements Serializable {
    private static final long serialVersionUID = 666047439778823136L;

    private String businessId;

    private String checkinDates;


    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getCheckinDates() {
        return checkinDates;
    }

    public void setCheckinDates(String checkinDates) {
        this.checkinDates = checkinDates;
    }

}

