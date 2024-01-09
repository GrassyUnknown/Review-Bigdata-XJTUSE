package com.hotel.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    Integer userId;
    String userName;
    Integer userBalance;
    Integer userIsvip;
    String userTel;
    String userPwd;
    String userDescription;
}
