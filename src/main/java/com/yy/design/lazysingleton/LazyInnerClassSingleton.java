package com.yy.design.lazysingleton;
//饿汉单例会有内存浪费的问题，synchronized会照成性能问题
public class LazyInnerClassSingleton {
    //使用LazyInnerClassSingleton时会默认先初始化内部类
    //如果没有使用，则内部类不加载
    private LazyInnerClassSingleton(){

    }
    //static是为了使单例的空间共享，final保证这个方法不会被重写、重载，提供一个全局访问点。
    public static final LazyInnerClassSingleton getInstance(){
        //在返回结果前会先加载内部类
        return InnerClassSingleton.SINGLETON;
    }
    //默认不加载
    private static class InnerClassSingleton{
        private static final LazyInnerClassSingleton SINGLETON=new LazyInnerClassSingleton();
    }
}
