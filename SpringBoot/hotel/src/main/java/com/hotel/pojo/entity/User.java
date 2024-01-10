package com.hotel.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2024-01-09 09:52:12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = -23148181119847206L;

    private String userId;

    private String userName;

    private Integer userReviewCount;

    private Date userYelpingSince;

    private String userFriends;

    private Integer userUseful;

    private Integer userCool;

    private Integer userFans;

    private String userElite;

    private Float userAverageStars;

    private Integer userComplimentHot;

    private Integer userComplimentMore;

    private Integer userComplimentProfile;

    private Integer userComplimentCute;

    private Integer userComplimentList;

    private Integer userComplimentNote;

    private Integer userComplimentPlain;

    private Integer userComplimentCool;

    private Integer userComplimentFunny;

    private Integer userComplimentWriter;

    private Integer userComplimentPhotos;

    private Integer userFunny;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserReviewCount() {
        return userReviewCount;
    }

    public void setUserReviewCount(Integer userReviewCount) {
        this.userReviewCount = userReviewCount;
    }

    public Date getUserYelpingSince() {
        return userYelpingSince;
    }

    public void setUserYelpingSince(Date userYelpingSince) {
        this.userYelpingSince = userYelpingSince;
    }

    public String getUserFriends() {
        return userFriends;
    }

    public void setUserFriends(String userFriends) {
        this.userFriends = userFriends;
    }

    public Integer getUserUseful() {
        return userUseful;
    }

    public void setUserUseful(Integer userUseful) {
        this.userUseful = userUseful;
    }

    public Integer getUserCool() {
        return userCool;
    }

    public void setUserCool(Integer userCool) {
        this.userCool = userCool;
    }

    public Integer getUserFans() {
        return userFans;
    }

    public void setUserFans(Integer userFans) {
        this.userFans = userFans;
    }

    public String getUserElite() {
        return userElite;
    }

    public void setUserElite(String userElite) {
        this.userElite = userElite;
    }

    public Float getUserAverageStars() {
        return userAverageStars;
    }

    public void setUserAverageStars(Float userAverageStars) {
        this.userAverageStars = userAverageStars;
    }

    public Integer getUserComplimentHot() {
        return userComplimentHot;
    }

    public void setUserComplimentHot(Integer userComplimentHot) {
        this.userComplimentHot = userComplimentHot;
    }

    public Integer getUserComplimentMore() {
        return userComplimentMore;
    }

    public void setUserComplimentMore(Integer userComplimentMore) {
        this.userComplimentMore = userComplimentMore;
    }

    public Integer getUserComplimentProfile() {
        return userComplimentProfile;
    }

    public void setUserComplimentProfile(Integer userComplimentProfile) {
        this.userComplimentProfile = userComplimentProfile;
    }

    public Integer getUserComplimentCute() {
        return userComplimentCute;
    }

    public void setUserComplimentCute(Integer userComplimentCute) {
        this.userComplimentCute = userComplimentCute;
    }

    public Integer getUserComplimentList() {
        return userComplimentList;
    }

    public void setUserComplimentList(Integer userComplimentList) {
        this.userComplimentList = userComplimentList;
    }

    public Integer getUserComplimentNote() {
        return userComplimentNote;
    }

    public void setUserComplimentNote(Integer userComplimentNote) {
        this.userComplimentNote = userComplimentNote;
    }

    public Integer getUserComplimentPlain() {
        return userComplimentPlain;
    }

    public void setUserComplimentPlain(Integer userComplimentPlain) {
        this.userComplimentPlain = userComplimentPlain;
    }

    public Integer getUserComplimentCool() {
        return userComplimentCool;
    }

    public void setUserComplimentCool(Integer userComplimentCool) {
        this.userComplimentCool = userComplimentCool;
    }

    public Integer getUserComplimentFunny() {
        return userComplimentFunny;
    }

    public void setUserComplimentFunny(Integer userComplimentFunny) {
        this.userComplimentFunny = userComplimentFunny;
    }

    public Integer getUserComplimentWriter() {
        return userComplimentWriter;
    }

    public void setUserComplimentWriter(Integer userComplimentWriter) {
        this.userComplimentWriter = userComplimentWriter;
    }

    public Integer getUserComplimentPhotos() {
        return userComplimentPhotos;
    }

    public void setUserComplimentPhotos(Integer userComplimentPhotos) {
        this.userComplimentPhotos = userComplimentPhotos;
    }

    public Integer getUserFunny() {
        return userFunny;
    }

    public void setUserFunny(Integer userFunny) {
        this.userFunny = userFunny;
    }

}

