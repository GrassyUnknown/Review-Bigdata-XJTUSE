package com.hotel.service;

import com.hotel.Facility;
import com.hotel.Facilityorder;

import java.util.List;

public interface FacilityorderService {
    List<Facilityorder> list();

    void delete(Integer facilityorderId);

    void insert(Facilityorder facilityorder);

    Facilityorder select(Integer facilityorderId);

    Facility check(Facilityorder facilityorder);
}
