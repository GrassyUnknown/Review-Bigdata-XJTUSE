package com.hotel.controller;

import com.hotel.service.UserService;
import com.hotel.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import com.hotel.pojo.entity.User;
@RestController
@CrossOrigin(origins = "http://localhost:8080", allowCredentials = "true")
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("getHotUserList")
    public R getHotBusinessList() {
        List<User> businessList = userService.getHotUserList();
        return R.success("查找成功",businessList);
    }
}
