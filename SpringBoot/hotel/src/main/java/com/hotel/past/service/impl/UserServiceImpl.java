package com.hotel.service.impl;

import com.hotel.mapper.UserMapper;
import com.hotel.pojo.User;
import com.hotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<User> list() {
        return userMapper.list();
    }

    @Override
    public void delete(Integer userId) {
        userMapper.delete(userId);
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public void insert(User user) {
        userMapper.insert(user);
    }

    @Override
    public User select(Integer userId) {
        return userMapper.select(userId);
    }

    @Override
    public User login(User user) {
        User u = userMapper.check(user);
        return u;
    }
}
