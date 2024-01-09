package com.hotel;

import com.hotel.utils.R;
import com.hotel.utils.Result;
import com.hotel.pojo.entity.Business;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/test")
public class ControllerTest {
    @Autowired
    private MapperTest mapperTest;

    @GetMapping
    public R list() {
        List<Business> businessList = mapperTest.list();
        return R.success("测试成功",businessList);
    }
}
