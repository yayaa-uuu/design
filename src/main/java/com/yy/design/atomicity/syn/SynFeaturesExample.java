package com.yy.design.atomicity.syn;

/**
 * @author yy
 * @date 2020/7/21 22:08
 */
public class SynFeaturesExample {
    private static long v1 = 0L;

    public static long getV1() {
        return v1;
    }

    public static void getAndIncrement() {
        synchronized (SynFeaturesExample.class) {
            long temp = getV1();
            temp += 1L;
            setV1(temp);
        }
    }

    public static void setV1(final long i) {
        v1 = i;
    }

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                getAndIncrement();
                System.out.println(Thread.currentThread().getName() + "  " + getV1());
            }).start();
        }
    }
}
