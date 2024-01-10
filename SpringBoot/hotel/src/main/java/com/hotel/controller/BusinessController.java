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

    @GetMapping("searchBusinessList/{keyword}")
    public R searchBusinessList(@PathVariable String keyword) {
        System.out.println(keyword);
        List<Business> businessList = businessService.searchBusinessList(keyword);
        return R.success("查找成功",businessList);
    }

}
