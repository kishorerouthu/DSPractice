package com.css.ds.practice.algorithms.sort.insertion;

import com.css.ds.utils.ArrayUtils;

/**
 * Insertion sort takes the element from unsorted portion and find the
 * right position in sorted portion and insert in that position.
 *
 * Insertion has 2 portions of array OR list.
 * 1. Sorted Portion.
 * 2. UnSorted Portion.
 *
 * Consider the first element has Sorted Portion in first pass.
 * And then moving from left to right in unsorted portion and find position
 * of each element in the sorted portion.
 *
 * Note : Move from LEFT to RIGHT in UnsortedPortion
 *        Move from RIGHT to LEFT in SortedPortion
 *
 * Example : 3 7 4 9 5 2 6 1
 *
 *  Steps : Notions  U - UnSorted , S - Sorted
 *          3 | 7 4 9 5 2 6 1
 *          S   U
 *
 *          3 7 | 4 9 5 2 6 1
 *          S     U
 *
 *         3 4 7 | 9 5 2 6 1
 *         S        U
 *
 *         3 4 7 9 | 5 2 6 1
 *         S         U
 *
 *         3 4 5 7 9 | 2 6 1
 *         S            U
 *
 *         2 3 4 5 7 9 | 6 1
 *         S             U
 *
 *         2 3 4 5 6 7 9 | 1
 *         S                U
 *
 *         1 2 3 4 5 6 7 9
 *         S
 *
 *    COMPLEXITY :
 *      Worst case performance	О(n^2) comparisons, swaps
 *      Best case performance	O(n) comparisons, O(1) swaps
 *      Average case performance	О(n^2) comparisons, swaps
 *      Worst case space complexity	О(n) total, O(1) auxiliary
 *
 * Created by Kishore Routhu on 18/9/16 11:41 AM.
 */
public class InsertionSort {


    public static void main(String[] args) {
        int elements[] = new int[]{5, 1, 3, 8, 7, 9, 2, 10, 4, 6};
        //int data[] = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        ArrayUtils.print("ELEMENTS BEFORE SORT :", elements);  // [ 5 1 3 8 7 9 2 10 4 6 ]
        sort(elements);
        ArrayUtils.print("ELEMENTS AFTER SORT ( ASCENDING ) :", elements); // [ 1 2 3 4 5 6 7 8 9 10 ]
    }

    private static void sort(int elements[]) {
        int n = elements.length;
        for (int i = 1; i < n; i++) {
            int x = elements[i];
            int j = i;
            while (j > 0 && elements[j-1] > x) {
                elements[j] = elements[j-1];
                j--;
            }
            elements[j] = x;
        }
    }
}
