package com.willing.deadlock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class DeadlockTest2 {
    public static void main(String[] args) {
        final ReentrantLock lockA = new ReentrantLock();
        final ReentrantLock lockB = new ReentrantLock();

        Thread threadA = new Thread(() -> {
            try {
                lockA.lock();
                Thread.sleep(100);
                try {
                    lockB.lock();
                } finally {
                    lockB.unlock();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lockA.unlock();
            }
        }
        );
        Thread threadB = new Thread(() -> {
            try {
                lockB.lock();
                //用户态到内核态的一次转换
                Thread.sleep(100);
                try {
                    lockA.lock();
                } finally {
                    lockA.unlock();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lockB.unlock();
            }
        }
        );
        threadA.start();
        threadB.start();
    }

}
