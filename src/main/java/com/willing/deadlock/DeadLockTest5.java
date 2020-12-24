package com.willing.deadlock;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * 使用lockInterruptibly()获得锁，如果发生死锁，调用线程interrupt来消除死锁。
 * ReentrantLock.lockInterruptibly允许在等待时由其它线程调用等待线程的Thread.interrupt方法来中断等待线程的等待而直接返回，这时不用获取锁，而会抛出一个InterruptedException。
 * 而ReentrantLock.lock方法不允许Thread.interrupt中断,即使检测到Thread.isInterrupted,一样会继续尝试获取锁，失败则继续休眠。
 * 只是在最后获取锁成功后再把当前线程置为interrupted状态。
 */
public class DeadLockTest5 {

    private static Lock lock1 = new ReentrantLock();
    private static Lock lock2 = new ReentrantLock();

    public static void deathLock() {
        new Thread() {
            @Override
            public void run() {
                try {
                    lock1.lockInterruptibly();
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        lock2.lockInterruptibly();
                        System.out.println("thread 1 ...");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock2.unlock();
                    }

                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                } finally {
                    lock1.unlock();
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                try {
                    lock2.lockInterruptibly();
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        lock1.lockInterruptibly();
                        System.out.println("thread 1 ...");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock1.unlock();
                    }

                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                } finally {
                    lock2.unlock();
                }
            }
        }.start();
    }

    public static void main(String[] args) throws InterruptedException {
        deathLock();

        TimeUnit.SECONDS.sleep(2);
//        checkDeadLock();
    }

    //基于JMX获取线程信息
    public static void checkDeadLock() {
        //获取Thread的MBean
        ThreadMXBean mbean = ManagementFactory.getThreadMXBean();
        //查找发生死锁的线程，返回线程id的数组
        long[] deadLockThreadIds = mbean.findDeadlockedThreads();
        System.out.println("---" + deadLockThreadIds);
        if (deadLockThreadIds != null) {
            //获取发生死锁的线程信息
            ThreadInfo[] threadInfos = mbean.getThreadInfo(deadLockThreadIds);
            //获取JVM中所有的线程信息
            Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces();
            for (Map.Entry<Thread, StackTraceElement[]> entry : map.entrySet()) {
                for (int i = 0; i < threadInfos.length; i++) {
                    Thread t = entry.getKey();
                    if (t.getId() == threadInfos[i].getThreadId()) {
                        //中断发生死锁的线程
                        t.interrupt();
                        //打印堆栈信息
                        // for (StackTraceElement ste : entry.getValue()) {
                        // // System.err.println("t" + ste.toString().trim());
                        // }
                    }

                }
            }
        }
    }
}
