package com.ds.practice.sort.bubble;

import com.ds.practice.utils.ArrayUtils;

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
        int elements[] = new int[]{5, 1, 3, 8, 7, 9, 2, 10, 4, 6};
        //int data[] = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        ArrayUtils.print("ELEMENTS BEFORE SORT :", elements); // [ 5 1 3 8 7 9 2 10 4 6 ]
        sort(elements);
        ArrayUtils.print("ELEMENTS AFTER SORT ( ASCENDING ) :", elements); // [ 1 2 3 4 5 6 7 8 9 10 ]
    }

    private static void sort(int elements[]) {
        int n = elements.length;
        boolean sorted = false;
        int i = n - 1;
        while (!sorted) {
            sorted = true;
            for (int j = 0; j < i; j++) {
                if (elements[j + 1] < elements[j]) {
                    ArrayUtils.swapIndexes(elements, j, j + 1);
                    sorted = false;
                }
            }
            i--;
        }
        System.out.println("\n NUMBER OF PASSES ::: " + (n - i - 1));
    }
}
