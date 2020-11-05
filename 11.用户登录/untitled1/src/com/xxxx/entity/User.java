package com.xxxx.entity;

/**
 * 用户实体类
 * @author BigDuke
 */
public class User {
    private Integer userId; //用户编号
    private String userName; //用户姓名
    private String userPwd; //用户密码
    private Integer userAge; //用户年龄

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }
}
