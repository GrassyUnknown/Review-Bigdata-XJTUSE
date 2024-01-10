package com.hotel.controller;

import com.hotel.service.ReviewService;
import com.hotel.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.hotel.pojo.entity.Review;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080", allowCredentials = "true")
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    @GetMapping("/list/{businessId}")
    public R getReviewList(@PathVariable String businessId) {
        List<Review> reciewList = reviewService.getReviewList(businessId);
        return R.success("成功", reciewList);
    }
}
