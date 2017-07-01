package com.css.ds.practice.algorithms.search.binary.problems;

import java.io.IOException;
import java.util.Arrays;

import com.css.ds.io.IOProvider;

/**
 * Created by Kishore Routhu on 15/10/16 3:25 PM.
 */
public class DiscoverTheMonk {

    public static void main(String[] args) throws IOException {

        IOProvider io = IOProvider.getConsoleInstance();

        String line[] = io.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int q = Integer.parseInt(line[1]);

        String arr[] = io.readLine().split(" ");
        long a[] = new long[n];
        for (int i=0; i<n; i++)
            a[i] = Long.parseLong(arr[i]);

        Arrays.sort(a);

        for (int j=0; j<q; j++) {
            long query = Long.parseLong(io.readLine());
            if(search(a, 0, n-1, query))
                System.out.println("YES");
            else
                System.out.println("NO");
        }

        io.finish();
    }

    private static boolean search(long a[], int start, int end, long x) {
        if (start <= end) {
            int mid = start + ((end - start) >> 1);
            if (x < a[mid])
                return search(a, start, mid - 1, x);
            else if (x > a[mid])
                return search(a, mid + 1, end, x);
            else
                return true;
        }
        return false;
    }
}
