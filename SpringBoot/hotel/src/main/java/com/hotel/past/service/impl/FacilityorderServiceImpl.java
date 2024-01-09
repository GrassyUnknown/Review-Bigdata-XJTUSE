package com.hotel.service.impl;

import com.hotel.mapper.FacilityorderMapper;
import com.hotel.pojo.Facility;
import com.hotel.pojo.Facilityorder;
import com.hotel.service.FacilityorderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacilityorderServiceImpl implements FacilityorderService {
    @Autowired
    private FacilityorderMapper facilityorderMapper;
    @Override
    public List<Facilityorder> list() {
        return facilityorderMapper.list();
    }

    @Override
    public void delete(Integer facilityorderId) {
        facilityorderMapper.delete(facilityorderId);
    }

    @Override
    public void insert(Facilityorder facilityorder) {
        facilityorderMapper.insert(facilityorder);
    }

    @Override
    public Facilityorder select(Integer facilityorderId) {
        return facilityorderMapper.select(facilityorderId);
    }

    @Override
    public Facility check(Facilityorder facilityorder) {
        return facilityorderMapper.check(facilityorder);
    }
}