package com.hotel.service;

import com.hotel.pojo.entity.Business;


import java.util.List;

public interface BusinessService {
    List<Business> getHotBusinessList();

    List<Business> searchBusinessList(String keyword);

    List<Business> getBusinessList();
    List<Business> getBusinessListByCategory(String keyword);
}
