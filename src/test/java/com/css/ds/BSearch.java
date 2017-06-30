package com.css.ds;

/**
 * Created by kishore on 9/5/17.
 */
public class BSearch {

    public static void main(String[] args) {
        int a[] = new int[]{1, 2, 3, 5, 6, 7, 8};
        int n = 7;
        int k = 5;
        System.out.println(bSearch(a, n, k));
    }

    private static int bSearch(int a[], int n, int k) {
        int l = 0;
        int r = n-1;
        while (l<=r) {
            int m = (l + r) >> 1;
            if (a[m] <= k)
                l = m+1;
            else
                r = m-1;
        }

        return l;
    }
}
