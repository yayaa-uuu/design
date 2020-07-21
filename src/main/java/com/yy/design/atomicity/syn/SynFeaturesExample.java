package com.yy.design.atomicity.syn;

/**
 * @author yy
 * @date 2020/7/21 22:08
 */
public class SynFeaturesExample {
    long v1=0L;

    public synchronized long getV1() {
        return v1;
    }

    public void getAndIncrement(){

        long temp=getV1();
        temp+=1L;
        setV1(temp);
    }
    public synchronized void setV1(long i) {
        v1=1;
    }
}
