package com.contest.march;

/**
 * @author Kishore Routhu on 28/6/17 6:45 PM.
 */
public class Painting {

    public static void main(String[] args) {

       // int a[] = new int[]{1, 3, 2, 1, 2, 1, 5, 3, 3, 4, 2};
      //  int a[] = new int[]{1, 1, 1, 1};
        //int a[] = new int[]{5, 8};
         int a[] = new int[]{3, 3, 3, 3, 4, 5, 1, 4, 2, 6, 1, 3};
        int n = a.length;
        System.out.println(findMinPaint(a, n));
    }

    private static int findMinPaint(int a[], int n) {
        int min_count = a[0];
        for (int i = 0; i < n-1; i++) {
            if (a[i] < a[i+1])
                min_count = min_count + (a[i+1] - a[i]);
        }

        return min_count;
    }
}
