package com.willing.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author wxli
 * @date 2020/12/30 17:47
 */
public class T1046 {

    public static int lastStoneWeight(int[] stones) {
        //从数组中取最大数，取两次，记录索引位置
        int max = 0;
        for (int i = 1; i < stones.length; i++) {
            if (max < stones[i]) {
                max = stones[i];
            }
        }
        return max;
    }

    public static int[] sort(int[] stones) {

        return stones;
    }


    public static void main(String[] args) {

        int[] ints = new int[]{2, 7, 4, 1, 8, 1};


        ArrayList<Integer> lists = new ArrayList(Arrays.asList());

        Collections.sort(lists);


        System.out.println(lists);
//        System.out.println(lastStoneWeight(ints));



    }


}
