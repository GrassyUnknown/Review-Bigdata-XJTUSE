package com.hotel.mapper;

import com.hotel.pojo.Room;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RoomMapper {
    @Select("select * from room")
    List<Room> list();

    @Delete("delete from room where room_id = #{roomId}")
    void delete(Integer roomId);

    @Update("update room set room_cost = #{roomCost}, room_name = #{roomName}, room_img = #{roomImg}," +
            "room_description = #{roomDescription} where room_id = #{roomId}")
    void update(Room room);

    @Insert("insert into room(room_id, room_cost, room_name, room_Img, room_description)" +
            " values(#{roomId}, #{roomCost}, #{roomName}, #{roomImg}, #{roomDescription})")
    void insert(Room room);

    @Select("select * from room where room_id = #{roomId}")
    Room select(Integer roomId);
}