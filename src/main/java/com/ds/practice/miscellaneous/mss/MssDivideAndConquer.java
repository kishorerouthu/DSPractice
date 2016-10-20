package com.ds.practice.miscellaneous.mss;

import java.util.Arrays;

/**
 * Created by Kishore Routhu on 20/10/16 4:24 PM.
 */
public class MssDivideAndConquer {

    public static void main(String[] args) {
        int a[] = new int[]{-2, -3, 4, -1, -2, 1, 5, -3};
        System.out.println(mss(a, a.length));
    }

    private static int mss(int a[], int n) {
        if (n == 1)
            return a[0];

        int m = n >> 1;

        int LeftMSS = mss(a, m);
        int RightMSS = mss(Arrays.copyOfRange(a, m, n), n - m);

        int leftSum = Integer.MIN_VALUE;
        int rightSum = Integer.MIN_VALUE;

        int sum = 0;
        for (int i = m-1; i >= 0; i--) {
            sum += a[i];
            leftSum = Math.max(leftSum, sum);
        }

        sum = 0;
        for (int i = m; i < n; i++) {
            sum += a[i];
            rightSum = Math.max(rightSum, sum);
        }

        int ans = Math.max(LeftMSS, RightMSS);
        return Math.max(ans, leftSum + rightSum);
    }
}
