package com.hotel.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.hotel.pojo.entity.Business;
import java.util.List;

@Mapper
public interface BusinessMapper {
    @Select("select * from business where business_index is not null limit 10")
    List<Business> getHotBusinessList();

    @Select("select * from business where business_name like '%${keyword}%'")
    List<Business> searchBusinessList(String keyword);

    @Select("select * from business limit 1000")
    List<Business> getBusinessList();

    @Select("select * from business where categories like '%${keyword}%' limit 200")
    List<Business> getBusinessListByCategory(String keyword);
}
