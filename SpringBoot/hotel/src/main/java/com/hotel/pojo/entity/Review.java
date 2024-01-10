package com.hotel.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.io.Serializable;

/**
 * (Review)实体类
 *
 * @author makejava
 * @since 2024-01-09 09:51:38
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review implements Serializable {
    private static final long serialVersionUID = -43471575993134256L;

    private String reviewId;

    private String userId;

    private String businessId;

    private Integer revStars;

    private Integer revUseful;

    private Integer revFunny;

    private Integer revCool;

    private String revText;

    private Date revDate;


    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public Integer getRevStars() {
        return revStars;
    }

    public void setRevStars(Integer revStars) {
        this.revStars = revStars;
    }

    public Integer getRevUseful() {
        return revUseful;
    }

    public void setRevUseful(Integer revUseful) {
        this.revUseful = revUseful;
    }

    public Integer getRevFunny() {
        return revFunny;
    }

    public void setRevFunny(Integer revFunny) {
        this.revFunny = revFunny;
    }

    public Integer getRevCool() {
        return revCool;
    }

    public void setRevCool(Integer revCool) {
        this.revCool = revCool;
    }

    public String getRevText() {
        return revText;
    }

    public void setRevText(String revText) {
        this.revText = revText;
    }

    public Date getRevDate() {
        return revDate;
    }

    public void setRevDate(Date revDate) {
        this.revDate = revDate;
    }

}

