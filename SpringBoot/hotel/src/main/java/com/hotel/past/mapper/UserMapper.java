package com.hotel.mapper;

import com.hotel.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from user")
    List<User> list();

    @Delete("delete from user where user_id = #{userId}")
    void delete(Integer userId);

    @Update("update user set user_name = #{userName}, user_balance = #{userBalance}, user_tel = #{userTel}," +
            "user_pwd = #{userPwd}, user_description = #{userDescription}, user_isvip = #{userIsvip} where user_id = #{userId}")
    void update(User user);

    @Insert("insert into user(user_id, user_name, user_balance, user_tel, user_pwd, user_description, user_isvip)" +
            " values(#{userId}, #{userName}, #{userBalance}, #{userTel}, #{userPwd}, #{userDescription}, #{userIsvip})")
    void insert(User user);

    @Select("select * from user where user_id = #{userId}")
    User select(Integer userId);

    @Select("select * from user where user_id = #{userId} and user_pwd = #{userPwd}")
    User check(User user);
}
