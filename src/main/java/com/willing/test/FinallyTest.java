package com.willing.test;


public class FinallyTest {

    public static void main(String[] args) {

        System.out.println(test11());
    }

    public static String test11() {
        try {
            System.out.println("try block");

            return test12();
        } finally {
            System.out.println("finally block");
        }
    }

    public static String test12() {
        System.out.println("return statement");

        return "after return";
    }

}
