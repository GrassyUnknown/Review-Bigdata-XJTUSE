package com.hotel.controller;

import com.hotel.pojo.entity.User;
import com.hotel.service.UserService;
import com.hotel.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080",allowCredentials = "true")
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("getUserFriendList/{userid}")
    public R getUserFriendList(@PathVariable String userid) {
        List<User> userList = userService.getUserFriendList(userid);
        return R.success("查找成功", userList);
    }

    @GetMapping("getHotUserList")
    public R getHotBusinessList() {
        List<User> businessList = userService.getHotUserList();
        return R.success("查找成功",businessList);
    }

    @GetMapping("login/username={userid}&password={username}")
    public R login(@PathVariable String userid,@PathVariable String username) {
        int login = userService.login(userid,username);
        if(login==1)return R.success("Login successful",null);
        return R.error("Login failed");
    }

}
