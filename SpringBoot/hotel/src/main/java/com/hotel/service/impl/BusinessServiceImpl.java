package com.hotel.service.impl;

import com.hotel.mapper.BusinessMapper;
import com.hotel.mapper.UserMapper;
import com.hotel.pojo.entity.Business;
import com.hotel.pojo.entity.User;
import com.hotel.service.BusinessService;
import com.hotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Math.*;

@Service
public class BusinessServiceImpl implements BusinessService {
    @Autowired
    private BusinessMapper businessMapper;

    @Override
    public List<Business> getHotBusinessList() {
        return businessMapper.getHotBusinessList();
    }

    @Override
    public List<Business> searchBusinessList(String keyword,double latitude,double longitude,String userid) {
        List<Business> businesses = businessMapper.searchBusinessList(keyword);
        Map<String, Integer> unsortedMap = new HashMap<>();
        for(Business b: businesses){
            double distance = haversineDistance(b.getLatitude(),b.getLongitude(), latitude, longitude);
            distance = (distance/100) - 1;
            double dScore = 1 - 1/(1 + exp(-distance));
            double hot = 0;
            UserService userService = new UserServiceImpl();
            User u = userService.getUserById(userid);
            hot = b.getReviewCount()/7568;
            double rate = 0;
            if(b.getBusinessIndex() != null && u.getUserIndex() != null) {
                //uid = torch.tensor([b.getBusinessIndex()]);
                //bid = torch.tensor([bi]);
                //with torch.no_grad():rate = model(uid, bid);
            }//else rate = b.stars;
            rate *= 0.2;
            double score = dScore*0.3 + hot*0.2 + rate*0.5;
            //unsortedMap.append(b.business_id, score);
        }
        return null;
    }

    @Override
    public List<Business> getBusinessList() {
        return businessMapper.getBusinessList();
    }

    @Override
    public List<Business> getBusinessListByCategory(String keyword) {
        return businessMapper.getBusinessListByCategory(keyword);
    }

    public double haversineDistance(double lat1, double lon1, double lat2, double lon2) {
        // 将经纬度转换为弧度
        lat1 = toRadians(lat1);
        lon1 = toRadians(lon1);
        lat2 = toRadians(lat2);
        lon2 = toRadians(lon2);

        // Haversine 公式
        double dlat = lat2 - lat1;
        double dlon = lon2 - lon1;
        double a = pow(sin(dlat / 2), 2) + cos(lat1) * cos(lat2) * pow(sin(dlon / 2), 2);
        double c = 2 * atan2(sqrt(a), sqrt(1 - a));

        // 地球半径（单位：km）
        double R = 6371.0;

        // 计算距离
        double distance = R * c;
        return distance;
    }
}
