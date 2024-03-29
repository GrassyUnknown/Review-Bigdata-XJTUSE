package com.hotel.controller;

import com.hotel.pojo.entity.Business;
import com.hotel.service.BusinessService;
import com.hotel.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080",allowCredentials = "true")
@RequestMapping("/business")
public class BusinessController {
    @Autowired
    private BusinessService businessService;

    @GetMapping("getHotBusinessList")
    public R getHotBusinessList() {
        List<Business> businessList = businessService.getHotBusinessList();
        return R.success("查找成功",businessList);
    }

    @GetMapping("getBusinessList")
    public R getBusinessList() {
        List<Business> businessList = businessService.getBusinessList();
        return R.success("查找成功",businessList);
    }

    @GetMapping("getBusinessList/{country}")
    public R getBusinessListByCategory(@PathVariable String country) {
        List<Business> businessList = businessService.getBusinessListByCategory(country);
        return R.success("查找成功",businessList);
    }

    @GetMapping("searchBusinessList/keyword={keyword}&latitude={latitude}&longitude={longitude}&userid={userid}")
    public R searchBusinessList(@PathVariable String keyword,@PathVariable double latitude,@PathVariable double longitude,@PathVariable String userid) {
        List<Business> businessList = businessService.searchBusinessList(keyword,latitude,longitude,userid);
        return R.success("查找成功",businessList);
    }

}
