package com.ds.practice.miscellaneous.mss;

/**
 * Created by Kishore Routhu on 21/10/16 12:46 PM.
 */
public class MSSKadanes {

    public static void main(String[] args) {
        int a[] = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};  // Answer : 6
        System.out.println(mss(a, a.length));

        // Array of all -ve numbers
        // Answer : -1 as it is largest among all
        int b[] = new int[]{-2, -4, -3, -9, -1, -7, -6, -5, -8};
        System.out.println(mssNegative(b, b.length));
    }

    /**
     * This MSS will return 0 if all array elements are -ve values.
     */
    private static int mss(int[] a, int n) {
        int sum = 0;
        int ans = 0;

        for (int i = 0; i < n; i++) {
            if (sum + a[i] > 0) {
                sum += a[i];
                ans = Math.max(sum, ans);
            } else {
                sum = 0;
            }
        }
        return ans;
    }

    /**
     * This MSS works even if array contains all negative numbers.
     */
    private static int mssNegative(int[] a, int n) {
        int sum = 0;
        int ans = Integer.MIN_VALUE;
        int neg = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            if (sum + a[i] > 0) {
                sum += a[i];
                ans = Math.max(sum, ans);
            } else {
                sum = 0;
            }
            if (a[i] < 0)
                neg = Math.max(a[i], neg);
        }
        return Math.max(neg, ans);
    }
}
