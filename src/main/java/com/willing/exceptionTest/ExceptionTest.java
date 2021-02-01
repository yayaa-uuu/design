package com.willing.exceptionTest;

import org.junit.Test;

public class ExceptionTest {

    @Test
    public static void test1() throws Exception {
        //主动捕获异常，程序会中断，需要调用者处理
        if (true) {
            throw new Exception("2");
        }
    }

    @Test
    public static void test2() {
        try {
            if (true) {
                throw new Exception("2");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("异常被主动抛出执行catch代码块");
        }
    }
}
