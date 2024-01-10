package com.hotel.mapper;

import com.hotel.pojo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select user_friends from user where user_id = #{userid}")
    String getUserFriends(String userid);

    List<User> getUserFriendList(String[] friends);
}
