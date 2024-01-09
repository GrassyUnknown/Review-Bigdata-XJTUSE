package com.hotel.service;

import com.hotel.pojo.User;

import java.util.List;

public interface UserService {
    List<User> list();

    void delete(Integer userId);

    void update(User user);

    void insert(User user);

    User select(Integer userId);

    User login(User user);
}
