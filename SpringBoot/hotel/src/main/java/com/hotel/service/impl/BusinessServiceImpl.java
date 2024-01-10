package com.hotel.service.impl;

import com.hotel.mapper.BusinessMapper;
import com.hotel.pojo.entity.Business;
import com.hotel.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessServiceImpl implements BusinessService {
    @Autowired
    private BusinessMapper businessMapper;

    @Override
    public List<Business> getHotBusinessList() {
        return businessMapper.getHotBusinessList();
    }

    @Override
    public List<Business> searchBusinessList(String keyword) {
        return businessMapper.searchBusinessList(keyword);
    }
}
