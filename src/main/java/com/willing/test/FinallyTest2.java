package com.willing.test;

/**
 * 在程序没有在执行到finally之前异常退出的情况下，finally是一定执行的，即在finally之前的return语句将在finally执行之后执行。
 * 　　finally总是在控制转移语句（break，continue，return等）执行之前执行。
 */
public class FinallyTest2 {

    public static void main(String[] args) {
        System.out.println("return value of test(): " + test());
    }

    public static int test() {
        int i = 1;

//                  if(i == 1)
//                      return 0;
        System.out.println("the previous statement of try block");
//        i = i / 0;

        try {
            System.out.println("try block");
            return i;
        } finally {
            System.out.println("finally block");
        }
    }
}
