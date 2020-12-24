//package com.willing.concurrent.test;
//
//import com.willing.concurrent.factory.MobilePhoneFactory;
//import com.willing.concurrent.producer.WaitConsumer;
//import com.willing.concurrent.consumer.WaitProducer;
//
///**
// * @author yy
// * @date 2020/8/13 16:40
// */
//public class WaitTest {
//    public static void main(String[] args) {
//
//        MobilePhoneFactory factory = new MobilePhoneFactory(new Object());
//        WaitProducer producer = new WaitProducer(factory);
//        WaitConsumer consumer = new WaitConsumer(factory);
//        new Thread(producer, "生产者A").start();
//        new Thread(consumer, "消费者A").start();
//
//
//    }
//}
