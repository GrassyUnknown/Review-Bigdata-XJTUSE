package com.hotel.service.impl;

import com.hotel.pojo.entity.User;
import com.hotel.mapper.UserMapper;
import com.hotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getUserFriendList(String userid) {
        String[] friends = userMapper.getUserFriends(userid).split(",");

        return userMapper.getUserFriendList(friends);
    }
}
