package com.hotel.service;

import com.hotel.pojo.entity.Business;


import java.util.List;

public interface BusinessService {
    List<Business> getHotBusinessList();

    List<Business> searchBusinessList(String keyword,double latitude,double longitude,String userid);

    List<Business> getBusinessList();
    List<Business> getBusinessListByCategory(String keyword);
}
