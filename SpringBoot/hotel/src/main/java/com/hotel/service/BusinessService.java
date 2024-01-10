package com.hotel.service;

import com.hotel.pojo.entity.Business;

import java.util.List;

public interface BusinessService {
    public List<Business> getHotBusinessList();

    List<Business> searchBusinessList(String keyword);
}
