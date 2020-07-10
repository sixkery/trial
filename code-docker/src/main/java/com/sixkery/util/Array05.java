package com.sixkery.util;

/**
 * 数组的增删改查
 *
 * @author liugang
 * @date 2020/7/10
 */
public class Array05 {

    private int[] data;
    private int n;
    private int count;


    public static void main(String[] args) {
        Array05 array05 = new Array05(4);
    }

    public Array05(int capacity) {
        data = new int[capacity];
        n = capacity;
        count = 0;
    }

    /**
     * 头部插入元素
     *
     * @param value 要插入的元素
     * @return 是否插入成功
     */
    private Boolean insertHead(int value) {
        data[count] = value;
        count++;
        return true;
    }

    /**
     * 数组中插入元素
     *
     * @param index 数组下标索引
     * @param value 要插入的值
     * @return 是否插入成功
     */
    private Boolean insert(int index, int value) {
        // 边界值判断
        if (index < 0 || index > count) {
            return false;
        }
        // 如果一直删除元素， 直到把元素删完，数组长度为 0 ,无法进行插入元素
        if (count == n) {
            return false;
        }
        //数组中数据从最后一依次向后移动，直到将用户指定索引元素空出空间
        System.arraycopy(data, index, data, index + 1, count - index);
        //将元素插入到数组中
        data[index] = value;
        //数组长度+1
        ++count;
        return true;

    }

}
