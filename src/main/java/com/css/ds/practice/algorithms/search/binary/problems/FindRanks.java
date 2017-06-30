package com.css.ds.practice.algorithms.search.binary.problems;

/**
 * Created by Kishore Routhu on 3/10/16 10:42 PM.
 */
/* IMPORTANT: Multiple classes and nested static classes are supported */

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class FindRanks {
        public static void main (String args[])throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String line = br.readLine();
            int N = Integer.parseInt(line);
            long a[] = new long[N];
            String str = br.readLine();
            String arr[] = str.split(" ");
            int j = 0;
            for (int i = 0; i < N; i++, j++) {
                if (j == arr.length) {
                    j = 0;
                    arr = br.readLine().split(" ");
                }
                a[i] = Long.parseLong(arr[j]);
            }

            String line1 = br.readLine();
            int M = Integer.parseInt(line1);
            for (int k = 0; k < M; k++) {
                long x = Integer.parseInt(br.readLine());
                System.out.println(search(a, 0, a.length - 1, x));
            }
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

