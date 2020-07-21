package com.yy.design.proxy.cglib;

public class CglibProxyTest {
    public static void main(String[] args) {
        CglibProxy cglibProxy=new CglibProxy();
        UserDaoImpl userDao=new UserDaoImpl();
        UserDaoImpl target= (UserDaoImpl) cglibProxy.createProxy(userDao);
        target.say();
    }
}
