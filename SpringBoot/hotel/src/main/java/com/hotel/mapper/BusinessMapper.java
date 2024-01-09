package com.hotel.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.hotel.pojo.entity.Business;
import java.util.List;

@Mapper
public interface BusinessMapper {
    @Select("select * from business limit 10")
    List<Business> getHotBusinessList();
}