package com.practice.algorithms.sort.quick;

import com.practice.utils.ArrayUtils;

/**
 *
 * Note : MergeSort and QuickSort follow the Divide and Conquer rule
 * QuickSort is most feasible than the MergeSort for the smaller inputs.
 *
 * ALGORITHM :
 *        partition(A, p, r){
 *          x = A[r];
 *          i = p - 1;
 *          for ( j = p to r-1) {
 *              if (A[j] <= x) {
 *                  i++;
 *                  exchange A[i] with A[j];
 *              }
 *          }
 *          exchange A[i+1] with A[r];
 *          return i+1;
 *        }
 *
 * COMPLEXITY :
 * Worst case performance	O(n2)
 * Best case performance	O(n log n) (simple partition) or O(n) (three-way partition and equal keys)
 * Average case performance	O(n log n)
 * Worst case space complexity	O(n) auxiliary (naive) O(log n) auxiliary
 *
 * Created by Kishore Routhu on 23/9/16 5:22 PM.
 *
 *
 */
public class QuickSort {

    public static void main(String[] args) {
        int data[] = new int[]{5, 1, 3, 8, 7, 9, 2, 10, 4, 6};
        ArrayUtils.print("ELEMENTS BEFORE SORT :", data);  // [ 5 1 3 8 7 9 2 10 4 6 ]
        quickSort(data, 0, data.length - 1);
        System.out.println();
        ArrayUtils.print("ELEMENTS AFTER SORT ( ASCENDING ) :", data); // [ 1 2 3 4 5 6 7 8 9 10 ]
    }

    private static void quickSort(int data[], int l, int r) {
        if (l < r) {
            int p = partition(data, l, r);
            quickSort(data, l, p-1);
            quickSort(data, p+1, r);
        }
    }

    private static int partition(int data[], int p, int r) {
        int x = data[r]; //Pivot
        int i = p - 1;
        for (int j=p; j<r; j++) {
            if (data[j] <= x) {
                i++;
                ArrayUtils.swapIndexes(data, i, j);
            }
        }
        ArrayUtils.swapIndexes(data, i+1, r);
        return i+1;
    }

}

