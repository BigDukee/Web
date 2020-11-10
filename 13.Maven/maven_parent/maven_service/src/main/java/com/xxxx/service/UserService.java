package com.xxxx.service;

import com.xxxx.dao.UserDao;

public class UserService {
    public static void testService(){
        System.out.println("UserService Test");
        //调用maven_dao模块的方法
        UserDao.testDao();

    }
}
