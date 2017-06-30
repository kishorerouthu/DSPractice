package com.css.ds.practice.algorithms.sort.bucket;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.css.ds.utils.ArrayUtils;

/**
 * Created by kishore on 12/3/17.
 *
 * Bucket sort is mainly useful when input is uniformly distributed over a range.
 * For example, consider the following problem.
 * Sort a large set of floating point numbers which are in range from 0.0 to 1.0 and are uniformly distributed across the range.
 * How do we sort the numbers efficiently?
 *
 * A simple way is to apply a comparison based sorting algorithm.
 * The lower bound for Comparison based sorting algorithm (Merge Sort, Heap Sort, Quick-Sort .. etc) is Ω(n Log n), i.e.,
 * they cannot do better than nLogn.
 * Can we sort the array in linear time? Counting sort can not be applied here as we use keys as index in counting sort.
 * Here keys are floating point numbers.
 * The idea is to use bucket sort. Following is bucket algorithm.
 *
 * bucketSort(arr[], n)
 * 1) Create n empty buckets (Or lists).
 * 2) Do following for every array element arr[i].
 * .......a) Insert arr[i] into bucket[n*array[i]]
 * 3) Sort individual buckets using insertion sort.
 * 4) Concatenate all sorted buckets.
 *
 * Time Complexity: If we assume that insertion in a bucket takes O(1) time then steps 1 and 2 of the above algorithm clearly take O(n) time.
 * The O(1) is easily possible if we use a linked list to represent a bucket (In the following code, C++ vector is used for simplicity). Step 4 also  * * takes O(n) time as there will be n items in all buckets.
 * The main step to analyze is step 3.
 * This step also takes O(n) time on average if all numbers are uniformly distributed (please refer CLRS book for more details)
 *
 */
public class BucketSort {

    public static void main(String[] args) {
        int a[] = new int[]{22, 45, 12, 8, 10, 6, 72, 81, 33, 18, 50, 14};
        ArrayUtils.print("ELEMENTS BEFORE SORT :", a);
        sort(a, 12);
        ArrayUtils.print("ELEMENTS AFTER SORT ( ASCENDING ) :", a);
    }

    private static void sort(int a[], int n) {
        int max = findMax(a);
        double div = ((double) (max + 1)) /((double) 10);
        int divider = (int)Math.ceil(div);

        List<Integer> b[] = new List[10];
        int j = 0;
        for (int i = 0; i < n; i++) {
            j = (int) Math.floor(a[i] / divider);
            if (b[j] == null)
                b[j] = new ArrayList<Integer>();
            b[j].add(a[i]);
        }

        int l = 0;
        for (int k = 0; k < b.length; k++) {
            List<Integer> bucket  = b[k];
            if (bucket != null && !bucket.isEmpty()) {
                Collections.sort(bucket);
                for (Integer val : bucket)
                    a[l++] = val;
            }
        }


    }

    private static int findMax(int[] a) {
        int max = Integer.MIN_VALUE;
        for (int val : a)
            if (val > max) max = val;
        return max;
    }
}
