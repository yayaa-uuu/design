package com.yy.design.singleton.lazysingleton;
//在需要时才实例化
public class LazySimpleSingleton {
    private LazySimpleSingleton(){

    }
    //静态块，公共内存区域
    private static LazySimpleSingleton lazySimpleSingleton=null;
    //一次只允许一个线程进入
    public synchronized static LazySimpleSingleton getInstance(){
//    public static LazySimpleSingleton getInstance(){
        if (lazySimpleSingleton==null){
            lazySimpleSingleton=new LazySimpleSingleton();
        }
        return lazySimpleSingleton;
    }

}
