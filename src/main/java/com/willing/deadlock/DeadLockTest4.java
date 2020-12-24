package com.willing.deadlock;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * 使用tryLock(long timeout, TimeUnit unit) 方法来防止多线程死锁。
 * tryLock(long time, TimeUnit unit)方法和tryLock()方法是类似的，只不过区别在于这个方法在拿不到锁时会等待一定的时间，在时间期限之内如果还拿不到锁，就返回false。
 * 如果一开始拿到锁或者在等待期间内拿到了锁，则返回true。

 */
public class DeadLockTest4 {

    private static ReentrantLock lock1 = new ReentrantLock();
    private static ReentrantLock lock2 = new ReentrantLock();

    public static void deathLock() {
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        if (lock1.tryLock(10, TimeUnit.MILLISECONDS)) {
                            try {
                                //如果获取成功则执行业务逻辑，如果获取失败，则释放lock1的锁，自旋重新尝试获得锁
                                if (lock2.tryLock(10, TimeUnit.MILLISECONDS)) {
                                    System.out.println("Thread1：已成功获取 lock1 and lock2 ...");
                                    break;
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } finally {
                                if(lock2.isHeldByCurrentThread()){
                                    lock2.unlock();
                                }
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        if(lock1.isHeldByCurrentThread()){
                            lock1.unlock();
                        }
                    }
                    System.out.println("Thread1：获取锁失败，重新获取---");
                    try {
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
                    try {
                        if (lock2.tryLock(10, TimeUnit.MILLISECONDS)) {
                            try {
                                //如果获取成功则执行业务逻辑，如果获取失败，则释放lock1的锁，自旋重新尝试获得锁
                                if (lock1.tryLock(10, TimeUnit.MILLISECONDS)) {
                                    System.out.println("Thread2：已成功获取 lock2 and lock1 ...");
                                    break;
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } finally {
                                if(lock1.isHeldByCurrentThread()){
                                    lock1.unlock();
                                }
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        if(lock2.isHeldByCurrentThread()){
                            lock2.unlock();
                        }
                    }
                    System.out.println("Thread2：获取锁失败，重新获取---");
                    try {
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
