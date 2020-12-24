package com.willing.concurrent.producer;

import com.willing.concurrent.factory.MobilePhoneFactory;

/**
 * @author yy
 * @date 2020/8/12 23:34
 */
public class WaitConsumer implements Runnable {

    public WaitConsumer(MobilePhoneFactory factory) {
        this.factory = factory;
    }

    private MobilePhoneFactory factory;

    @Override
    public void run() {
        while (true) {
            factory.consumerCar();
        }
    }

}
