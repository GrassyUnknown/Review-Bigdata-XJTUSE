package com.hotel.service.impl;

import com.hotel.pojo.entity.User;
import com.hotel.mapper.UserMapper;
import com.hotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Override
    public List<User> getHotUserList () {
        return userMapper.getHotUserList();
    }

    @Override
    public User getUserById(String userid) {
        return userMapper.getUserById(userid).get(0);
    }

    @Override
    public User login(String userid, String username) {
        List<User> user=userMapper.login(userid,username);
        if(user.isEmpty())return null;
        else return user.get(0);
    }

    @Override
    public List<User> getRecommendFriendList(String userid) {
        List<User> recommendFriends = new ArrayList<>();
        for(User friend : getUserFriendList(userid)){
            recommendFriends.addAll(getUserFriendList(friend.getUserId()));
        }
        User user=getUserById(userid);
        recommendFriends.addAll(userMapper.getSimilarNameList(user.getUserName()));
        return recommendFriends;
    }
}
