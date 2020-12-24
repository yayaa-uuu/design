package com.willing.concurrent.consumer;

import java.util.Random;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer implements Runnable{
    //生产者一直执行，false停掉生产者
    private volatile boolean isRunning=true;

    //公共资源
    private final Vector sharedQueue;
    //公共资源大小
    private final int SIZE;
    //生产数据
    private static AtomicInteger count=new AtomicInteger();

    public Producer(Vector sharedQueue, int SIZE) {
        this.sharedQueue = sharedQueue;
        this.SIZE = SIZE;
    }

    @Override
    public void run() {
        int data;
        Random random=new Random();
        System.out.println("start producer id="+Thread.currentThread().getName());
        while (isRunning){
            //模拟延迟
            try {
                Thread.sleep(random.nextInt(1000));
                synchronized (sharedQueue){
                    System.out.println("队列已满, 生产者id:"+Thread.currentThread().getId()+",等待,队列中还有数据size:"+sharedQueue.size());
                }
                //队列不满时持续创建新元素
                synchronized (sharedQueue){
                    //生产数据
                    data=count.incrementAndGet();
                    sharedQueue.add(data);
                    System.out.println("生产者创建数据data:"+data+",大小size"+sharedQueue.size());
                    sharedQueue.notifyAll();

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }
    public void stop(){
        isRunning=false;
    }

}
