package com.willing.proxy.cglib;

import com.willing.proxy.jdk.UserDao;

public class UserDaoImpl implements UserDao {
    @Override
    public void say() {
        System.out.println("say");
    }
}
