package com.yy.design.proxy.cglib;

import com.yy.design.proxy.jdk.UserDao;

public class UserDaoImpl implements UserDao {
    @Override
    public void say() {
        System.out.println("say");
    }
}
