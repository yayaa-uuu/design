package com.willing.test;

import java.util.ArrayList;
import java.util.List;

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
//    public static void main(String[] args) {
//        List<Integer> list=new ArrayList<>();
//        List<Integer> list2=new ArrayList<>();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        list.add(4);
//        list2.add(1);
//        list.removeAll(list2);
//        System.out.println(list);
//        System.out.println(test());
//
//
//    }
//    public static String test2(){
//        int i=0;
//        try {
//            if (i>=0){
//                return "1";
//            }
//        }finally {
//            System.out.println("finally");
//        }
//        return "0";
//    }
}
