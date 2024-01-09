package com.hotel.service.impl;

import com.hotel.mapper.FacilityMapper;
import com.hotel.pojo.Facility;
import com.hotel.service.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacilityServiceImpl implements FacilityService {
    @Autowired
    private FacilityMapper facilityMapper;
    @Override
    public List<Facility> list() {
        return facilityMapper.list();
    }

    @Override
    public void delete(Integer facilityId) {
        facilityMapper.delete(facilityId);
    }

    @Override
    public void update(Facility facility) {
        facilityMapper.update(facility);
    }

    @Override
    public void insert(Facility facility) {
        facilityMapper.insert(facility);
    }

    @Override
    public Facility select(Integer facilityId) {
        return facilityMapper.select(facilityId);
    }
}
