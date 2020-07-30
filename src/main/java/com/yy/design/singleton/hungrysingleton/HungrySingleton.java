package com.yy.design.singleton.hungrysingleton;

public class HungrySingleton {
    //static修饰成员变量，是为了保证类初始化就为其变量分配内存（分配内存区域方法区），fianl只允许此变量被赋值一次。
    private static final HungrySingleton HUNGRY_SINGLETON=new HungrySingleton();
    //private修饰构造器是为了不能再外部创建该类的对象。仅在该类中使用
    private HungrySingleton(){

    }
    //提供一个全局方法，获取该类的实例。static修饰的方法不能重写。可以重载，重载条件：一大、两小、两同。
    public static HungrySingleton getInstance(){
        return HUNGRY_SINGLETON;
    }

    public static void main(String[] args) {
        Thread thread1= new Thread(() -> {
            HungrySingleton singleton=HungrySingleton.HUNGRY_SINGLETON;
            System.out.println(Thread.currentThread().getName()+""+HUNGRY_SINGLETON);
        });
        Thread thread2= new Thread(() -> {
            HungrySingleton singleton=HungrySingleton.HUNGRY_SINGLETON;
            System.out.println(Thread.currentThread().getName()+""+HUNGRY_SINGLETON);
        });
        thread1.start();
        thread2.start();
        System.out.println("执行完成");
    }
}
