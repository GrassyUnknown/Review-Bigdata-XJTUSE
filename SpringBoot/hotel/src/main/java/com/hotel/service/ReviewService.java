package com.hotel.service;

import java.util.List;

import com.hotel.pojo.entity.Review;

public interface ReviewService {
    List<Review> getReviewList(String businessId);

    List<Review> getReviewUserList(String userId);
}
