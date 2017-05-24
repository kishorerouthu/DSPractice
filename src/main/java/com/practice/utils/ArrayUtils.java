package com.practice.utils;

/**
 * Created by Kishore Routhu on 23/9/16 6:02 PM.
 */
public abstract class ArrayUtils {

    private ArrayUtils() {
    }

    public static int[] print(String header, int arr[]) {
        System.out.println(header);
        print(arr);
        return arr;
    }

    private static int[] print(int arr[]) {
        StringBuffer sb = new StringBuffer("[ ");
        for (int element : arr)
            sb.append(element + " ");
        sb.append("]");
        System.out.print(sb.toString());
        return arr;
    }

    public static int[] swapIndexes(int elements[], int i, int j) {
        int a = elements[i];
        int b = elements[j];

        a = a ^ b;
        b = a ^ b;
        a = a ^ b;

        elements[i] = a;
        elements[j] = b;

        return elements;
    }

    public static <E> E[] print(E arr[]) {
        StringBuffer sb = new StringBuffer("[ ");
        for (E element : arr)
            sb.append(element + " ");
        sb.append("]");
        System.out.print(sb.toString());
        return arr;
    }

}
