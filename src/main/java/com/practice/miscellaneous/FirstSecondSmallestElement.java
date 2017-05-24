package com.practice.miscellaneous;

/**
 * Definition : Find smallest and second smallest element in an array.
 * Created by Kishore Routhu on 18/10/16 3:21 PM.
 */
public class FirstSecondSmallestElement {

    public static void main(String[] args) {
        int a[] = new int[]{12, 13, 1, 10, 34, 1};
        secondSmallest(a);
    }

    private static void secondSmallest(int arr[]) {
        int first = arr[0];
        int second = Integer.MAX_VALUE;
        int n = arr.length;

        for (int i=1; i<n; i++) {
           int x = arr[i];
            if (x < first) {
                second = first;
                first = x;
            } else if (x < second && x != first) {
                second = x;
            }
        }
        System.out.println(first + " : " + second);
    }
}
