package com.css.ds.practice.algorithms.search.binary.problems;

/**
 * Created by Kishore Routhu on 3/10/16 10:42 PM.
 */
/* IMPORTANT: Multiple classes and nested static classes are supported */

import com.css.ds.io.IOProvider;


public class FindRanks {
        public static void main (String args[])throws Exception {
            IOProvider io = IOProvider.getConsoleInstance();
            String line = io.readLine();
            int N = Integer.parseInt(line);
            long a[] = new long[N];
            String str = io.readLine();
            String arr[] = str.split(" ");
            int j = 0;
            for (int i = 0; i < N; i++, j++) {
                if (j == arr.length) {
                    j = 0;
                    arr = io.readLine().split(" ");
                }
                a[i] = Long.parseLong(arr[j]);
            }

            String line1 = io.readLine();
            int M = Integer.parseInt(line1);
            for (int k = 0; k < M; k++) {
                long x = Integer.parseInt(io.readLine());
                io.println(search(a, 0, a.length - 1, x));
            }
            io.finish();
        }


        private static int search(long a[], int l, int r, long x) {
            if(l <= r) {
                int m = l + ((r - l) >> 1);
                if(x == a[m])
                    return m + 1;
                else if(x < a[m])
                    return search(a, l, m-1, x);
                return search(a, m+1, r, x);
            }
            return -1;
        }


}

