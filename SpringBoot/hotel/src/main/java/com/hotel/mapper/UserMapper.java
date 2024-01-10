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

    @Select("select * from user where user_index is not null limit 10")
    List<User> getHotUserList();

    @Select("select * from user where user_id = #{userid} and user_name = #{username}")
    List<User> login(String userid, String username);
}
