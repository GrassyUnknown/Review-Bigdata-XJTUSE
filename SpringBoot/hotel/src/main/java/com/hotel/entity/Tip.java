package com.hotel.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.io.Serializable;

/**
 * (Tip)实体类
 *
 * @author makejava
 * @since 2024-01-09 09:51:56
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tip implements Serializable {
    private static final long serialVersionUID = 306631013100673759L;

    private Integer tipId;

    private String userId;

    private String businessId;

    private String tipText;

    private Date tipDate;

    private Integer tipComplimentCount;


    public Integer getTipId() {
        return tipId;
    }

    public void setTipId(Integer tipId) {
        this.tipId = tipId;
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

    public String getTipText() {
        return tipText;
    }

    public void setTipText(String tipText) {
        this.tipText = tipText;
    }

    public Date getTipDate() {
        return tipDate;
    }

    public void setTipDate(Date tipDate) {
        this.tipDate = tipDate;
    }

    public Integer getTipComplimentCount() {
        return tipComplimentCount;
    }

    public void setTipComplimentCount(Integer tipComplimentCount) {
        this.tipComplimentCount = tipComplimentCount;
    }

}

