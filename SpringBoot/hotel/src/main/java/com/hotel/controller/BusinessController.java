package com.hotel.controller;

import com.hotel.service.BusinessService;
import com.hotel.service.FacilityService;
import com.hotel.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080",allowCredentials = "true")
@RequestMapping("/business")
public class BusinessController {
    @Autowired
    private BusinessService businessService;

    @GetMapping("getHotBusinessList")
    public R getHotBusinessList() {
        List<com.hotel.pojo.entity.Business> businessList = businessService.getHotBusinessList();
        return R.success("查找成功",businessList);
    }

}
