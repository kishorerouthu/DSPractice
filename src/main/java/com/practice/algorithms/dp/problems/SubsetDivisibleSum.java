package com.practice.algorithms.dp.problems;

/**
 * Created by kishore on 18/4/17.
 *
 * Definition :
 * Given a set of non-negative distinct integers, and a value m,
 * determine if there is a subset of the given set with sum divisible by m.
 *
 * Input Constraints
 * Size of set i.e., n <= 1000000, m <= 1000
 *
 * Examples:
 * Input : arr[] = {3, 1, 7, 5};
 *         m = 6;
 * Output : YES
 *
 * Input : arr[] = {1, 6};
 *         m = 5;
 * Output : NO
 */
public class SubsetDivisibleSum {

    public static void main(String[] args) {
        int a[] = new int[]{3, 1, 7, 5};
        System.out.println(isSubsetDivisibleByM(a, a.length, 6));

        int a1[] = new int[]{1, 6};
        System.out.println(isSubsetDivisibleByM(a1, a1.length, 5));
    }

    private static boolean isSubsetDivisibleByM(int a[], int n, int m) {

        int sum = 0;
        for (int val : a)
            sum += val;

        //subSet[i][j] is true, if there is a subSet of a[0...j-1] has the sum i
        boolean subSet[][] = new boolean[sum+1][n+1];

        for (int i=0; i<=n; i++)
            subSet[0][i] = true;

        for (int i=1; i<=sum; i++) {
            for (int j=1; j<=n; j++) {
                subSet[i][j] = subSet[i][j-1];

                if (i >= a[j-1])
                    subSet[i][j] = subSet[i][j] || subSet[i-a[j-1]][j-1];
            }
        }

        int i = 2;
        while (m <= sum) {
            int j = n;
            while (j>=0)
            if (subSet[m][j--])
                return true;

            m = m * i++;
        }

        return false;
    }
}
