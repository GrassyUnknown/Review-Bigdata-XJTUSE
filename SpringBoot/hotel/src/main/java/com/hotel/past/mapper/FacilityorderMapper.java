package com.hotel.mapper;

import com.hotel.pojo.Facility;
import com.hotel.pojo.Facilityorder;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FacilityorderMapper {
    @Select("select * from facilityorder")
    List<Facilityorder> list();

    @Delete("delete from facilityorder where facilityorder_id = #{facilityorderId}")
    void delete(Integer facilityorderId);

    @Insert("insert into facilityorder(facilityorder_id, facility_id, user_id, reserve_timing)" +
            " values(#{facilityorderId}, #{facilityId}, #{userId}, #{reserveTiming})")
    void insert(Facilityorder facilityorder);

    @Select("select * from facilityorder where facilityorder_id = #{facilityorderId}")
    Facilityorder select(Integer facilityorderId);

    @Select("select * from facilityorder where facilityorder_id = #{facilityorderId}")
    Facility check(Facilityorder facilityorder);
}