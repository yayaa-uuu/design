package com.willing.juc;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class Test {
    private static Map<String, Object> map = new ConcurrentHashMap<>();
    private static String hello;
    private Test() {
    }

    public void getLock(String parkCode) {
        if (map.get(parkCode)==null){
            synchronized (Test.class) {
                if (map.get(parkCode) == null) {
                    ReentrantLock lock = new ReentrantLock(true);
                    map.put(parkCode, lock);
                }
            }
        }
    }

    public static void main(String[] args) {
        Optional<String> s=Optional.of("sss");


    }
}
