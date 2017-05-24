package com.practice.algorithms.search.binary.problems;

/**
 * Problem Statement:
 * Given a sorted array with possible duplicate elements. Find number of occurrences of input ‘key’ in log N time.
 * The idea here is finding left and right most occurrences of key in the array using binary search.
 *
 ****************************
 * GetLeftMost occurrence :
 * **************************
 * function(arr, l, r, key) {
 *     while(r-l > 1){
 *         m = r + (r-l)/2;
 *         if(arr[m] >= key)
 *              r = m;
 *         else
 *              l = m;
 *     }
 *     return r;
 * }
 *
 * **************************
 * GetRightMost occurrence :
 * **************************
 * function(arr, l, r, key){
 *     while(r-l > 1){
 *         m = l + (r-l)/2;
 *         if(arr[m] <= key)
 *          l = m;
 *         else
 *          r = m;
 *     }
 *     return l;
 * }
 *
 * Created by Kishore Routhu on 17/10/16 3:33 PM.
 */

public class FineOccurrencesOfKey {

    public static void main(String[] args) {
        int a[] = new int[]{0, 1, 2, 3, 5, 5, 5, 5, 8, 9, 10};
        int key = 5;
        int left = getLeftMost(a, 0, a.length - 1, key);
        int right = getRightMost(a, 0, a.length - 1, key);
        int n = (a[left] == key && a[right] == key) ? (right - left + 1) : 0;
        System.out.println("Occurrences of " + key + " : " + n);

    }


    private static int getLeftMost(int a[], int l, int r, int key) {
        int m;
        while (r - l > 1) {
            m = l + ((r - l) >> 1);
            if (a[m] >= key)
                r = m;
            else
                l = m;
        }
        return r;
    }

    private static int getRightMost(int a[], int l, int r, int key) {
        int m;
        while(r-l > 1) {
            m = l + ((r-l) >> 1);
            if (a[m] <= key)
                l = m;
            else
                r = m;
        }
        return l;
    }

}