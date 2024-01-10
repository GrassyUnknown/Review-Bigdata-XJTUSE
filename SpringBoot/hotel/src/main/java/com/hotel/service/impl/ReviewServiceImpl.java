package com.hotel.service.impl;

import com.hotel.mapper.ReviewMapper;
import com.hotel.pojo.entity.Review;
import com.hotel.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    ReviewMapper reviewMapper;


    @Override
    public List<Review> getReviewList(String businessId) {
        return reviewMapper.getReviewList(businessId);
    }

    @Override
    public List<Review> getReviewUserList(String userId) {
        return reviewMapper.getReviewUserList(userId);
    }
}
