package com.willing.concurrent.producer;

import java.util.Random;
import java.util.Vector;

public class Consumer implements Runnable {
    //公共资源
    private final Vector shareQueue;

    public Consumer(Vector shareQueue) {
        this.shareQueue = shareQueue;
    }



        @Override
        public void run () {
            Random random = new Random();
            System.out.println("start consumer id=" + Thread.currentThread().getId());
            try {
                while (true) {
                    //模拟延迟
                    Thread.sleep(1000);
                    //队列为空等待
                    while (shareQueue.isEmpty())
                        synchronized (shareQueue) {
                            System.out.println("Queue is empty, consumer " + Thread.currentThread().getId()
                                    + "is waiting,size:" + shareQueue.size());
                            shareQueue.wait();
                        }
                    synchronized (shareQueue) {
                        System.out.println("消费者消费数据 data：" + shareQueue.remove(0) + ",size:" + shareQueue.size());
                        shareQueue.notifyAll();
                    }

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }
