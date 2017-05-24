package com.practice.algorithms.search.binary.problems;
/**
 * Problem Statement: Given an sorted array of N distinct integers,
 * find floor value of input ‘key’.
 * Say, A = {-1, 2, 3, 5, 6, 8, 9, 10} and key = 7, we should return 6 as outcome.
 *
 * We keep moving the left pointer to right most as long as the invariant holds.
 * Eventually left pointer points an element less than or equal to key (by definition floor value).
 * The following are possible corner cases,
 * —> If all elements in the array are smaller than key, left pointer moves till last element.
 * —> If all elements in the array are greater than key, it is an error condition.
 *  —> If all elements in the array equal and <= key, it is worst case input to our implementation.
 *
 * Created by Kishore Routhu on 17/10/16 3:14 PM.
 */
public class FloorValueOfKey {

    public static void main(String[] args) {
        int a[] = new int[]{ 1, 2, 3, 4, 5, 6, 8, 9, 10};
        System.out.println(floor(a, 7));
    }

    private static int floor(int a[], int key) {
        int l = a.length - 1;
        if(key < a[0])
            return -1;
        else if(key > a[l])
            return a[l];
        return search(a, 0, a.length - 1, key);
    }

    private static int search(int a[], int l, int r, int key) {
        int m;
        while (r - l > 1) {
            m = l + ((r-l) >> 1);
            if (a[m] <= key)
                l = m;
            else
                r = m;
        }
        return a[l];
    }

}



