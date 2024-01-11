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
    public R getHotUserList() {
        List<User> userList = userService.getHotUserList();
        return R.success("查找成功",userList);
    }

    @GetMapping("login/username={userid}&password={username}")
    public R login(@PathVariable String userid,@PathVariable String username) {
        User user = userService.login(userid,username);
        if(user!=null)return R.success("Login successful",user);
        return R.error("Login failed");
    }

    @GetMapping("getUser/{userid}")
    public R getUserById(@PathVariable String userid) {
        User user = userService.getUserById(userid);
        return R.success("查找成功",user);
    }

    @GetMapping("getRecommendFriendList/{userid}")
    public R getRecommendFriendList(@PathVariable String userid){
        List<User> userList = userService.getRecommendFriendList(userid);
        return R.success("查找成功", userList);
    }

}
