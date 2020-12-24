package com.willing.concurrent.test;

import java.util.ArrayList;
import java.util.List;

public class OOMTest {
    /**
     * -Xms20m -Xmx20m -XX:HeapDumpOnOutOfMemoryError
     */
    public static void main(String[] args) {
        List<Integer[]> list=new ArrayList<>();
        int i=0;
        while (true){
            list.add(new Integer[5*1024]);
            System.out.println(++i);
        }
    }
}
