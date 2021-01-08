package com.willing.juc;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InterruptedTest {
    public static void main(String[] args) {

        Thread sleepThread = new Thread(() -> {
            try {
                Thread.sleep(100000000);

            } catch (InterruptedException e) {
                e.printStackTrace();
                log.info("sleepTread:已被唤醒");
            }
        });

        Thread wakeThread = new Thread(()->{
            try {
                Thread.sleep(10000);

                log.info("10s已经到了唤醒正在睡眠的线程");
                sleepThread.interrupt();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        sleepThread.start();
        wakeThread.start();
    }
}
