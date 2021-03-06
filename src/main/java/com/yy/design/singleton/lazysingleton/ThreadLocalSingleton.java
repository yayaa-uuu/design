package com.yy.design.singleton.lazysingleton;

public class ThreadLocalSingleton {
    private static final ThreadLocal<ThreadLocalSingleton> THREAD_LOCAL_SINGLETON_THREAD_LOCAL=
            ThreadLocal.withInitial(ThreadLocalSingleton::new);
    private ThreadLocalSingleton(){

    }
    public static ThreadLocalSingleton getInstance(){
        return THREAD_LOCAL_SINGLETON_THREAD_LOCAL.get();
    }

    public static void main(String[] args) {
        System.out.println(ThreadLocalSingleton.getInstance());
        System.out.println(ThreadLocalSingleton.getInstance());
        System.out.println(ThreadLocalSingleton.getInstance());
        System.out.println(ThreadLocalSingleton.getInstance());
        Thread t1=new Thread();
        Thread t2=new Thread();
        t1.start();
        t2.start();
        System.out.println("End");
    }
}
