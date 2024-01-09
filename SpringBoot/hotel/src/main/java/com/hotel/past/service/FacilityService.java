package com.hotel.service;

import com.hotel.pojo.Facility;

import java.util.List;

public interface FacilityService {
    List<Facility> list();

    void delete(Integer facilityId);

    void update(Facility facility);

    void insert(Facility facility);

    Facility select(Integer facilityId);
}