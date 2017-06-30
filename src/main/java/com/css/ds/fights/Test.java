package com.css.ds.fights;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

/**
 * Created by kishore on 31/1/17.
 */
public class Test {

    public static void main(String[] args) {
        int a[] = new int[] {1, 2, 4};
        int pos = binarySearchPosition(a, a.length, 5);
        System.out.println(pos);

    }

    private static int binarySearchPosition(int a[], int n, int x) {
        int l = 0;
        int r = n-1;

        while (l < r) {
            int m = (r + l) >> 1;
            if (a[m] <= x)
                l = m + 1;
            else
                r = m;
        }

        return a[l] > x ? l-1 : l;
    }
}
