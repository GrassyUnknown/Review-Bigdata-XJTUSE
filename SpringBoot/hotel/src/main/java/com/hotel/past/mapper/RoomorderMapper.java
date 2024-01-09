package com.hotel.mapper;

import com.hotel.pojo.Roomorder;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RoomorderMapper {
    @Select("select * from roomorder")
    List<Roomorder> list();

    @Delete("delete from roomorder where roomorder_id = #{roomorderId}")
    void delete(Integer roomorderId);

    @Insert("insert into roomorder(roomorder_id,user_id,roomorder_cost,roomorder_reservedate,room_id,roomorder_description)" +
            " values(#{roomorderId}, #{userId}, #{roomorderCost}, #{roomorderReservedate}, #{roomId}, #{roomorderDescription})")
    void insert(Roomorder roomorder);

    @Select("select * from roomorder where roomorder_id = #{roomorderId}")
    Roomorder select(Integer roomorderId);

    @Select("select * from roomorder where room_id = #{roomId} and roomorder_reservedate = #{roomorderReservedate}")
    Roomorder check(Roomorder roomorder);

    @Update("update roomorder set user_id = #{userId}, roomorder_cost = #{roomorderCost}, roomorder_reservedate = #{roomorderReservedate}, room_id = #{roomId}, roomorder_description = #{roomorderDescription} where roomorder_id = #{roomorderId}")
    void update(Roomorder roomorder);
}
