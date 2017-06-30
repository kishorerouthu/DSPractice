package com.css.ds.practice.miscellaneous.mss;

/**
 * De
 *
 * Created by Kishore Routhu on 18/10/16 4:34 PM.
 */
public class MssBruteForce {

    public static void main(String[] args) {
        int a[] = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};  // Answer : 6
        System.out.println(mss(a, a.length));
        System.out.println(optimumMss(a, a.length));
    }

    /**
     * Complexity : O(n^3)
     * @param arr
     * @param n
     * @return
     */
    private static int mss(int arr[], int n) {
        int max_sum = Integer.MIN_VALUE;
        for (int sub_array_size = 1; sub_array_size <= n; sub_array_size++) {
            for (int start_index=0; start_index<n; start_index++) {
                if (start_index + sub_array_size > n) {
                    break;
                }
                int sum = 0;
                for (int j = start_index; j < start_index + sub_array_size; j++)
                    sum += arr[j];
                max_sum = Math.max(max_sum, sum);
            }
        }
        return max_sum;
    }

    /**
     * Complexity : O(n^2)
     * @param arr
     * @param n
     * @return
     */
    private static int optimumMss(int arr[], int n) {
        int max_sum = Integer.MIN_VALUE;
        for (int start_index = 0; start_index < n; start_index++) {
            int sum = 0;
            for (int sub_array_size = 1; sub_array_size <= n; sub_array_size++) {
                if (start_index + sub_array_size > n)
                    break;
                sum += arr[start_index + sub_array_size-1];
                max_sum = Math.max(max_sum, sum);
            }
        }
        return max_sum;
     }
}
