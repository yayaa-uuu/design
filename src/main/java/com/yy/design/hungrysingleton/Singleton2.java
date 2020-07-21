package com.yy.design.hungrysingleton;

/**
 * @author yy
 * @date 2020/7/2 22:31
 */
//内部类单例,内部类可以直接使用外部类的属性
public class Singleton2 {
    //private 不允许外部调用
    private Singleton2() {
    }
    //提供一个全局访问点，不允许被继承，不允许被重写，重载
    public static final Singleton2 getSingleton(){
        return internalClass.SINGLETON;
    }
    static class internalClass{
        private static final Singleton2 SINGLETON=new Singleton2();
    }
}
