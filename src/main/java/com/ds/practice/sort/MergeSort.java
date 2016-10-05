package com.ds.practice.sort;

import com.ds.practice.utils.ArrayUtils;

/**
 * MergeSort is a Divide and Conquer algorithm. It divides the input array in two halves,
 * call itself for two halves and then merges the two sorted halves.
 * The merge function is used for merging two halves.
 *
 * Most implementations produce stable sort, which means that the implementation preserves
 * the input order of equal data in the sorted output.
 *
 *
 * COMPLEXITY :
 * Worst case performance O(n log n)
 * Best case performance O(n log n) typical, O(n) natural variant
 * Average case performance	O(n log n)
 * Worst case space complexity	О(n) total, O(n) auxiliary
 *
 * Created by Kishore Routhu on 23/9/16 5:33 PM.
 */
public class MergeSort {

    public static void main(String[] args) {
        int elements[] = new int[]{5, 1, 3, 8, 7, 9, 2, 10, 4, 6};
        ArrayUtils.print("ELEMENTS BEFORE SORT :", elements);  // [ 5 1 3 8 7 9 2 10 4 6 ]
        mergesort(elements, 0, elements.length - 1);
        ArrayUtils.print("ELEMENTS AFTER SORT ( ASCENDING ) :", elements); // [ 1 2 3 4 5 6 7 8 9 10 ]
    }

    private static void mergesort(int elements[], int l, int r) {
        if ( l < r) {
            int m = (l + r) >> 1;
            mergesort(elements, l, m);
            mergesort(elements, m + 1, r);
            merge(elements, l, m, r);
        }
    }

    private static void merge(int elements[], int l, int m, int r) {

        int n1 = m - l + 1;
        int n2 = r - m;

        int left[] = new int[n1];
        int right[] = new int[n2];

        for (int i = 0; i < n1; i++) {
            left[i] = elements[l + i];
        }

        for (int j = 0; j < n2; j++) {
            right[j] = elements[m + 1 + j];
        }

        int i = 0;
        int j = 0;
        int k = l;

        while (i < n1 && j < n2) {
            if (left[i] <= right[j]) {
                elements[k] = left[i];
                i++;
            } else {
                elements[k] = right[j];
                j++;
            }

            k++;
        }

        while (i < n1) {
            elements[k] = left[i];
            i++;
            k++;
        }

        while (j < n2) {
            elements[k] = right[j];
            j++;
            k++;
        }
    }

}
