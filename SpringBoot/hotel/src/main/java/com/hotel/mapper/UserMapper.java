package com.hotel.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.hotel.pojo.entity.User;
import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from user where user_index is not null limit 10")
    List<User> getHotUserList();

}
