package com.willing.deadlock;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * https://cloud.tencent.com/developer/article/1129619
 * <p>
 * 使用tryLock()方法来防止多线程死锁。
 * <p>
 * tryLock()方法：尝试获取一把锁，如果获取成功返回true，如果还拿不到锁，就返回false。
 * <p>
 * 在程序没有在执行到finally之前异常退出的情况下，finally是一定执行的，即在finally之前的return语句将在finally执行之后执行。
 * finally总是在控制转移语句（break，continue，return等）执行之前执行。
 */
@Slf4j
public class DeadlockTest3 {
    private static final Lock lock1 = new ReentrantLock();
    private static final Lock lock2 = new ReentrantLock();

    public static void deathLock() {
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    if (lock1.tryLock()) {
                        try {
                            //如果获取成功则执行业务逻辑，如果获取失败，则释放lock1的锁，自旋重新尝试获得锁
                            if (lock2.tryLock()) {
                                try {
                                    System.out.println("Thread1：已成功获取 lock1 and lock2 ...");
                                    break;
                                } finally {
                                    lock2.unlock();
                                }
                            }
                        } finally {
                            lock1.unlock();
                        }
                    }
                    System.out.println("Thread1：获取锁失败，重新获取---");
                    try {
                        //防止发生活锁
                        TimeUnit.NANOSECONDS.sleep(new Random().nextInt(100));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    if (lock2.tryLock()) {
                        try {
                            //如果获取成功则执行业务逻辑，如果获取失败，则释放lock2的锁，自旋重新尝试获得锁
                            if (lock1.tryLock()) {
                                try {
                                    System.out.println("Thread2：已成功获取 lock2 and lock1 ...");
                                    break;
                                } finally {
                                    lock1.unlock();
                                }
                            }
                        } finally {
                            lock2.unlock();
                        }
                    }
                    System.out.println("Thread2：获取锁失败，重新获取---");
                    try {
                        //防止发生活锁
                        TimeUnit.NANOSECONDS.sleep(new Random().nextInt(100));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            deathLock();
        }
    }

}
