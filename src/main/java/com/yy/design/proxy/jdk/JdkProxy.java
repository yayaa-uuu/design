package com.yy.design.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxy implements InvocationHandler {
    private UserDao userDao;
    public Object creatProxy(UserDao userDao){
        ClassLoader classLoader=userDao.getClass().getClassLoader();
        Class<?>[] interfaces=userDao.getClass().getInterfaces();
        this.userDao=userDao;
        return Proxy.newProxyInstance(classLoader,interfaces,this);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object o=method.invoke(userDao,args);
        System.out.println("object:"+o);
        return o;
    }
}
