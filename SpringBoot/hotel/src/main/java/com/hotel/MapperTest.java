package com.hotel;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.hotel.pojo.entity.Business;
import java.util.List;

@Mapper
public interface MapperTest {
    @Select("select * from business limit 10 ")
    List<Business> list();
}
