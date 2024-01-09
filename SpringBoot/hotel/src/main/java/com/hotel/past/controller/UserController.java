package com.hotel.controller;

import com.hotel.utils.Result;
import com.hotel.pojo.User;
import com.hotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public Result list(){
        List<User> userList = userService.list();
        return Result.success(userList);
    }

    @DeleteMapping("/{userId}")
    public Result delete(@PathVariable Integer userId){
        userService.delete(userId);
        return Result.success();
    }

    @PutMapping
    public Result updateUser(@RequestBody User user){
        userService.update(user);
        return Result.success();
    }

    @PostMapping
    public Result insert(@RequestBody User user){
        userService.insert(user);
        return Result.success();
    }

    @GetMapping("/{userId}")
    public Result select(@PathVariable Integer userId){
        User user = userService.select(userId);
        return Result.success(user);
    }
}
