package com.yy.design.concurrent.producer;

import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yy
 * @date 2020/7/4 18:27
 */
public class MainTest {
    public static void main(String[] args) throws InterruptedException {
        //构建内存缓冲区
        Vector shareQueue=new Vector();
        int size=4;
        //建立线程池和线程
        ExecutorService service= Executors.newCachedThreadPool();
        Producer producer1=new Producer(shareQueue,size);
        Producer producer2=new Producer(shareQueue,size);
        Producer producer3=new Producer(shareQueue,size);
        Consumer consumer1=new Consumer(shareQueue);
        Consumer consumer2=new Consumer(shareQueue);
        Consumer consumer3=new Consumer(shareQueue);
        service.execute(producer1);
        service.execute(producer2);
        service.execute(producer3);
        service.execute(consumer1);
        service.execute(consumer2);
        service.execute(consumer3);
        Thread.sleep(10*1000);
        producer1.stop();
        producer2.stop();
        producer3.stop();
        //再睡一会儿关闭线程池
        Thread.sleep(3000);
        service.shutdown();
    }
}
