package com.hotel.mapper;

<<<<<<< HEAD
import com.hotel.pojo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

=======
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.hotel.pojo.entity.User;
>>>>>>> origin/UI_LC
import java.util.List;

@Mapper
public interface UserMapper {
<<<<<<< HEAD
    @Select("select user_friends from user where user_id = #{userid}")
    String getUserFriends(String userid);

    List<User> getUserFriendList(String[] friends);
=======
    @Select("select * from user where user_index is not null limit 10")
    List<User> getHotUserList();

>>>>>>> origin/UI_LC
}
