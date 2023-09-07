package com.common.user;


import lombok.Data;

@Data
public class UserInfo {
    private int userId;
    private String userName;
    private String openId;
    private String nickname;
    private String photo;
    private int sex;
    private int tel;
    private String email;
    private String dept;
    private String status;
    private String roles;

    private String errorCode;
    private String errorMsg;
    private String token;
    private String refreshToken;
}
