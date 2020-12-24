package com.willing.concurrent.factory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MobilePhoneFactory {
    private int num=0;
    Object lock;

    public MobilePhoneFactory(Object lock) {
        this.lock = lock;
    }

    public void createCar() {
        synchronized (lock) {
            try {
                while (num == 10) {
                    log.info("当前数量={}，暂停生产", num);
                    lock.wait();
                }
                num++;
                log.info("生产者:{}, 生产了一辆汽车，当前总量:{}", Thread.currentThread().getName(), num);
                lock.notify();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void consumerCar() {
        synchronized (lock) {
            try {
                while (num == 1) {
                    log.info("当前数量={}，暂停销售", num);
                    lock.wait();
                }
                num--;
                log.info("消费者:{}, 购买了了一辆汽车，当前总量:{}", Thread.currentThread().getName(), num);
                lock.notify();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
