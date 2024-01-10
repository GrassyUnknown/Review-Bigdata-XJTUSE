package com.hotel.service;

import com.hotel.pojo.entity.User;

import java.util.List;

public interface UserService {
    List<User> getUserFriendList(String userid);

    List<User> getHotUserList();

    User getUserById(String userid);

    User login(String userid,String username);
}
