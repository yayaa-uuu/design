package com.willing.concurrent.consumer;

import com.willing.concurrent.factory.MobilePhoneFactory;

public class WaitProducer implements Runnable {

    private MobilePhoneFactory factory;

    public WaitProducer(MobilePhoneFactory factory) {
        this.factory = factory;
    }

    @Override
    public void run() {
        while (true) {
            factory.createCar();
        }
    }
}
