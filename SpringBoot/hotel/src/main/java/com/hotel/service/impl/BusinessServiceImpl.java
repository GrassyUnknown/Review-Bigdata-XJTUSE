package com.hotel.service.impl;

import com.hotel.mapper.BusinessMapper;
import com.hotel.mapper.UserMapper;
import com.hotel.pojo.entity.Business;
import com.hotel.pojo.entity.User;
import com.hotel.service.BusinessService;
import com.hotel.utils.PythonCaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.lang.Math.*;

@Service
public class BusinessServiceImpl implements BusinessService {
    @Autowired
    private BusinessMapper businessMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Business> getHotBusinessList() {
        return businessMapper.getHotBusinessList();
    }

    @Override
    public List<Business> searchBusinessList(String keyword,double latitude,double longitude,String userid) {
        List<Business> businesses = businessMapper.searchBusinessList(keyword);
        Map<Business, Double> unsortedMap = new HashMap<>();
        ArrayList<Business> toPredict = new ArrayList<>();
        ArrayList<Integer> toPredictIndex = new ArrayList<>();
        int uindex = 0;
        for(Business b: businesses){
            User u = userMapper.getUserById(userid).get(0);
            if(b.getBusinessIndex() != null && u.getUserIndex() != null) {
                uindex = u.getUserIndex();
                toPredict.add(b);
                toPredictIndex.add(b.getBusinessIndex());
                break;
            }
            double distance = haversineDistance(b.getLatitude(),b.getLongitude(), latitude, longitude);
            b.setLatitude((float)distance);
            distance = (distance/100) - 1;
            double dScore = 1 - 1/(1 + exp(-distance));
            double hot = (double)b.getReviewCount()/7568;
            double rate = b.getStars();
            rate *= 0.2;
            double score = dScore*0.3 + hot*0.2 + rate*0.5;
            unsortedMap.put(b, score);
        }
        if(toPredict.size()>0){
            ArrayList<Double> predictResult = PythonCaller.predict(uindex,toPredictIndex);
            for (int i = 0; i < toPredict.size(); i++) {
                Business b = toPredict.get(i);
                double distance = haversineDistance(b.getLatitude(),b.getLongitude(), latitude, longitude);
                b.setLatitude((float)distance);
                distance = (distance/100) - 1;
                double dScore = 1 - 1/(1 + exp(-distance));
                double hot = (double)b.getReviewCount()/7568;
                double rate = predictResult.get(i);
                rate *= 0.2;
                double score = dScore*0.3 + hot*0.2 + rate*0.5;
                unsortedMap.put(b, score);
            }
        }
        List<Map.Entry<Business, Double>> entryList = new ArrayList<>(unsortedMap.entrySet());
        Collections.sort(entryList, new Comparator<Map.Entry<Business, Double>>() {
            @Override
            public int compare(Map.Entry<Business, Double> entry1, Map.Entry<Business, Double> entry2) {
                return entry2.getValue().compareTo(entry1.getValue());
            }
        });
        ArrayList<Business> result = new ArrayList<>();
        for (Map.Entry<Business, Double> entry : entryList) {
            result.add(entry.getKey());
        }
        return result;
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
        return R * c;
    }
}
