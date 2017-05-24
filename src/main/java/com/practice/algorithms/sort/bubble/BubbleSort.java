package com.practice.algorithms.sort.bubble;

import com.practice.utils.ArrayUtils;

/**
 * BubbleSort is the simplest sorting algorithm that works by repeatedly swapping
 * the adjacent data if they are in wrong order, sometimes referred to as sinking sort.
 *
 * The pass through the list is repeated until no swaps are needed, which indicates that
 * the list is sorted
 *
 * COMPLEXITY :
 * Worst case performance O(n^2)
 * Best case performance O(n)
 * Average case performance O(n^2)
 * Worst case space complexity O(1) auxiliary
 * Worst case space complexity	O(1)
 * Created by Kishore Routhu on 17/9/16 1:23 PM.
 */
public class BubbleSort {

    public static void main(String[] args) {
        int a[] = new int[]{5, 1, 3, 8, 7, 9, 2, 10, 4, 6};
        //int data[] = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        ArrayUtils.print("ELEMENTS BEFORE SORT :", a); // [ 5 1 3 8 7 9 2 10 4 6 ]
        sortOfKLargestElements(a, 3);
        ArrayUtils.print("ELEMENTS AFTER SORT ( ASCENDING ) :", a); // [ 1 2 3 4 5 6 7 8 9 10 ]
    }

    private static void sort(int a[]) {
        int n = a.length;
        boolean sorted = false;
        int i = n - 1;
        while (!sorted) {
            sorted = true;
            for (int j = 0; j < i; j++) {
                if (a[j + 1] < a[j]) {
                    ArrayUtils.swapIndexes(a, j, j + 1);
                    sorted = false;
                }
            }
            i--;
        }
        System.out.println("\n NUMBER OF PASSES ::: " + (n - i - 1));
    }

    private static void sortOfKLargestElements(int a[], int k) {
        int n = a.length;
        int count = 0;
        for (int i = n-1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (a[j+1] > a[j])
                    ArrayUtils.swapIndexes(a, j, j + 1);
            }
            count++;
            if (count == k)
                break;
        }
    }
}
