package com.willing.counter;

/**
 * @author yy
 * @date 2020/7/20 9:18
 */
public class test {
    static int x=10;
    static{
        x=+5;
    }


    public static void main(String[] args) {
        System.out.println(x);
    }
    static {
        x/=3;
    }
}
