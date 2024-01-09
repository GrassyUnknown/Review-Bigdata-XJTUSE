package com.hotel.mapper;

import com.hotel.pojo.Facility;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FacilityMapper {
    @Select("select * from facility")
    List<Facility> list();

    @Delete("delete from facility where facility_id = #{facilityId}")
    void delete(Integer facilityId);

    @Update("update facility set facility_name = #{facilityName},facility_capacity = #{facilityCapacity}," +
            "facility_now = #{facilityNow} where facility_id = #{facilityId}")
    void update(Facility facility);

    @Insert("insert into facility(facility_id, facility_name, facility_capacity, facility_now)" +
            " values(#{facilityId}, #{facilityName}, #{facilityCapacity}, #{facilityNow})")
    void insert(Facility facility);

    @Select("select * from facility where facility_id = #{facilityId}")
    Facility select(Integer facilityId);
}