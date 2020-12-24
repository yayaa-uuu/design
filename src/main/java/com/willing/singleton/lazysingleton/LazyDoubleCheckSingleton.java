package com.willing.singleton.lazysingleton;


public class LazyDoubleCheckSingleton {
    private volatile static LazyDoubleCheckSingleton lazyDoubleCheckSingleton=null;
    private LazyDoubleCheckSingleton(){

    }
    public static LazyDoubleCheckSingleton getInstance(){
        if (lazyDoubleCheckSingleton==null){
            synchronized (LazyDoubleCheckSingleton.class){
                if (lazyDoubleCheckSingleton==null){
                    lazyDoubleCheckSingleton=new LazyDoubleCheckSingleton();
                    //分配内存给这个对象
                    //初始化对象
                    //设置lazy指向刚分配的内存地址
                }
            }
        }
        return lazyDoubleCheckSingleton;
    }
}
