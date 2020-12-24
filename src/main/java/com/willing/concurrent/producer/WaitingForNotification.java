package com.willing.concurrent.producer;

import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * @author yy
 * @date 2020/8/12 10:17
 */
public class WaitingForNotification {

    public static void main(String[] args) {
        Object lock=new Object();
        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                    System.out.println(Thread.currentThread().getName() + "\t我进入等待");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(() -> {
            System.out.println("");
            synchronized (lock) {
                lock.notify();
                System.out.println(Thread.currentThread().getName() + "\t唤醒其他线程");
            }
        });
        t1.start();
        t2.start();
    }
}
