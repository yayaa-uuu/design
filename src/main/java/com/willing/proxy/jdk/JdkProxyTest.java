package com.willing.proxy.jdk;

public class JdkProxyTest {
    public static void main(String[] args) {
        UserDaoImpl userDao=new UserDaoImpl();
        JdkProxy jdkProxy=new JdkProxy();
        UserDao proxy= (UserDao) jdkProxy.creatProxy(userDao);
        proxy.say();
    }
}
