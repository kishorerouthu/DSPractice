package com.css.ds.practice.algorithms.sort.selection;

import com.css.ds.utils.ArrayUtils;

/**
 * The selection sort algorithm sorts an array by repeatedly finding the minimum element
 * from unsorted portion and putting that at the end of sorted portion.
 *
 * COMPLEXITY :
 * Worst case performance О(n^2)
 * Best case performance О(n^2)
 * Average case performance	О(n^2)
 * Worst case space complexity	О(n) total, O(1) auxiliary
 *
 * Created by Kishore Routhu on 17/9/16 11:40 AM.
 */
public class SelectionSort {

    public static void main(String[] args) {
        int elements[] = new int[]{5, 1, 3, 8, 7, 9, 2, 10, 4, 6};
        ArrayUtils.print("ELEMENTS BEFORE SORT :", elements);       // [ 5 1 3 8 7 9 2 10 4 6 ]
        sort(elements);
        ArrayUtils.print("ELEMENTS AFTER SORT ( ASCENDING ) :", elements); // [ 1 2 3 4 5 6 7 8 9 10 ]
    }

    private static void sort(int elements[]) {
        int n = elements.length;
        for (int i = 0; i < n - 1; i ++) {
            int j = i + 1;
            int min = i;
            while (j < n) {
                if(elements[j] < elements[min])
                    min = j;
                j++;
            }
            if (i != min)
                ArrayUtils.swapIndexes(elements, i, min);
        }
    }


}
