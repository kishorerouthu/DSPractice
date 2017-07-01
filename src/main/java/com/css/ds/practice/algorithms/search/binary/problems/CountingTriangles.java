package com.css.ds.practice.algorithms.search.binary.problems;

import java.io.IOException;

import com.css.ds.io.IOProvider;

/**
 * Created by kishore on 16/5/17.
 */
public class CountingTriangles {

    public static void main(String[] args) throws IOException {

        IOProvider io = IOProvider.getConsoleInstance();

        int n = io.readInt();
        String t[] = new String[n];
        for (int i = 0; i < n; i++) {
            t[i] = getSortedKey(new long[]{io.readLong(), io.readLong(), io.readLong()});
        }

        int uniqueCount = 0;
        for (int j = 0; j < n; j++)
            if (findTriangle(t, j+1, t[j]) != -1)
                uniqueCount++;

        io.println(uniqueCount);
        io.finish();
    }

    private static long findTriangle(String t[], int i, String key) {
        return -1l;
    }

    private static String getSortedKey(long[] a) {

        return a + "$" + a[0] + "$" + a[1];
    }

    private static int[] getCounts(int a[], int n) {
        int handShakes = 0;
        int firstBumps = 0;
        int result[] = new int[2];

        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                if (a[i] != a[j])
                    handShakes++;
                else
                    firstBumps++;
            }
        }

        result[0] = handShakes;
        result[1] = firstBumps;
        return result;
    }
}
