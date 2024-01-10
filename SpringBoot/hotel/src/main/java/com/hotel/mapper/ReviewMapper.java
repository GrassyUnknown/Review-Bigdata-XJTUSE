package com.hotel.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.hotel.pojo.entity.Review;
import java.util.List;

@Mapper
public interface ReviewMapper {
    @Select("select * from review where business_id=#{businessId}")
    List<Review> getReviewList(String businessId);

    @Select("select * from review where user_id=#{userId}")
    List<Review> getReviewUserList(String userId);


}
