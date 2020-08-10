package com.sixkery.suanfa;

import java.util.Arrays;

/**
 * @author sixkery
 * @date 2020/8/10
 */
public class Practice {

    public static void main(String[] args) {
        Integer[] in1 = {18, 89, 5, 84, 3, 45, 12, 33, 77, 98, 456, 878, 654, 213, 897};
        insertArray(in1);
        bubblingArray(in1);
        chooseArray(in1);

    }

    // 插入排序
    private static void insertArray(Integer[] ins) {
        for (int i = 1; i < ins.length; i++) {
            while (ins[i] < ins[i - 1]) {
                int temp = ins[i - 1];
                ins[i - 1] = ins[i];
                ins[i] = temp;
                if (i > 1) {
                    i--;
                }
            }
        }
        System.out.println(Arrays.toString(ins));
    }

    // 冒泡排序
    private static void bubblingArray(Integer[] ins) {
        // 冒泡排序的原理：相邻元素两两比较，大的往后放，第一次完毕，最大值出现在了最大索引处
        // 外层循环次数是数组长度减一,从索引0开始
        for (int i = 0; i < ins.length - 1; i++) {
            for (int j = 0; j < ins.length - 1 - i; j++) {
                // 里层循环次数是数组长度减第几轮循环
                if (ins[j] > ins[j + 1]) {
                    int temp = ins[j - 1];
                    ins[j - 1] = ins[j];
                    ins[j] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(ins));
    }

    private static void chooseArray(Integer[] ins) {
        //外层循环次数是数组长度减一,从索引0开始
        for (int i = 0; i < ins.length - 1; i++) {
            for (int j = i + 1; j < ins.length; j++) {
                //里层循环次数是数组长度减第几轮循环
                if (ins[i] > ins[j]) {
                    int temp = ins[j];
                    ins[j] = ins[i];
                    ins[i] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(ins));
    }
}
