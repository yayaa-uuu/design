package com.willing.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author yy
 * @date 2020/7/2 21:59
 */
public class UserTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
//        //首先告诉JVM让它把User这个类加载到虚拟机中。
//        Class<?> target=Class.forName("com.yy.design.reflection.User");
//        User user= (User) target.newInstance();
//        Method[] method=target.getMethods();
////        for (Method method1:method){
////            System.out.println(method1.getName());
////        }
////        Arrays.stream(method).forEach(System.out::println);
//        Field[] field=target.getDeclaredFields();
//        for (Field field1 : field) {
//            System.out.println(field1.getName());
//        }
//        Field field1=target.getDeclaredField("name");
//        //允许私有属性赋值。
//        field1.setAccessible(true);
//        field1.set(user,"hi");
//        System.out.println(user.getName());
//        Method method1=target.getDeclaredMethod("hello");
//        method1.invoke(user);

    }
}

