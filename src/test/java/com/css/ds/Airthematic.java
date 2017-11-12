package com.css.ds;

/**
 * @author Kishore Routhu on 21/8/17 8:10 PM.
 */
public class Airthematic {

    public static void main(String[] args) {
        int a[] = new int[]{-1, 1, 3, 3, 3, 2, 1, 0};
        System.out.println(solution(a));
    }

    public static int solution(int[] A) {
        int prev_diff = A[1] - A[0];
        int prev_index = 0;
        int curr_index = 1;
        int count = 0;
        for (int i = 2; i < A.length; i++) {
            int curr_diff = A[i] - A[i - 1];
            if (curr_diff == prev_diff) {
                curr_index = i;
                if (i == A.length - 1)
                    count = changeCount(curr_index, prev_index, count);
            } else {
                count = changeCount(curr_index, prev_index, count);
                prev_index = i - 1;
                curr_index = i;
                prev_diff = curr_diff;
            }
        }

        return count > 1000000000 ? -1 : count;
    }

    private static int changeCount(int curr_index, int prev_index, int count) {
        int diff = curr_index - prev_index + 1;
        if (diff >= 3)
            count = count + getCount(diff);
        return count;
    }

    private static int getCount(int n) {
        return ((n-1) * (n-2)) / 2;
    }
}