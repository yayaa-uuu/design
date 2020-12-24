package com.willing.singleton.hungry;

public class Singleton {

    private Singleton() {
    }
    public static final Singleton getSingleton() {

        return innerClass.SINGLETON;
    }
    static class innerClass{
        private static final Singleton SINGLETON=new Singleton();
    }
}
