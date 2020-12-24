package com.willing.atomicity.vola;

/**
 * @author yy
 * @date 2020/7/21 22:08
 */
public class VolatileFeaturesExample {
    volatile long v1=0L;

    public long getV1() {
        return v1;
    }

    public void getAndIncrement(){
        v1++;
    }

}
