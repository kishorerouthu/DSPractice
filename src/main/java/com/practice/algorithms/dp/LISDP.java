package com.practice.algorithms.dp;

import java.util.Arrays;

/**
 * Created by kishore on 12/4/17.
 *
 * Problem Statement : The Longest Increasing Subsequence (LIS) problem is to find the length of the longest subsequence of
 *                     a given sequence such that all elements of the subsequence are sorted in increasing order.
 *  Example 1 : Given array {10, 22, 9, 33, 21, 50, 41, 60, 80}
 *            LIS = 6  {10, 22, 33, 50, 60, 80}
 *
 *  Example 2 :  arr[] = {3, 10, 2, 1, 20}
 *               LIS = 3  {3, 10, 20}
 *
 *  Example 3 : arr[] = {50, 3, 10, 7, 40, 80}
 *               LIS = 4  {3, 7, 40, 80}
 *
 */
public class LISDP {

    public static void main(String[] args) {
        int a[] = new int[] {10, 22, 9, 33, 21, 50, 41, 60, 80};
        MaxLis maxLis = new MaxLis(1);
        _lis(a, a.length, maxLis);
        System.out.println(maxLis.getMaxLis());

        System.out.println(lis(a, a.length));
    }

    private static int _lis(int a[], int n, MaxLis maxLis) {

        //base case
        if (n == 1)
            return 1;

        int current_lis_length = 1;
        for (int i=0; i<n; i++) {

            //Recursively calculate length of LIS ending at arr[i]
            int subproblem_lis_length = _lis(a, i, maxLis);

            if (a[i] < a[n-1] && current_lis_length < (1 + subproblem_lis_length))
                current_lis_length = 1 + subproblem_lis_length;
        }

        if (maxLis.getMaxLis() < current_lis_length)
            maxLis.setMaxLis(current_lis_length);

        return current_lis_length;
    }

    private static class MaxLis {

        int maxLis;

        public MaxLis(int maxLis) {
            this.maxLis = maxLis;
        }

        public int getMaxLis() {
            return maxLis;
        }

        public void setMaxLis(int maxLis) {
            this.maxLis = maxLis;
        }
    }


    /**
     * Using Dynamic Programming Tabulation method
     */

    private static int lis(int a[], int n) {

        int lis[] = new int[n];
        Arrays.fill(lis, 1);

        for (int i=1; i<n; i++)
            for (int j=0; j<i; j++)
                if (a[i] > a[j] && lis[i] < lis[j] + 1)
                    lis[i] =lis[j] + 1;


        int max = 1;
        for (int val : lis)
            max = val > max ? val : max;

        return max;
    }


}


