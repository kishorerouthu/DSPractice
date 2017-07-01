package com.css.ds.practice.algorithms.search.binary.problems;

/**
 * Created by Kishore Routhu on 24/10/16 6:52 PM.
 */

import java.io.IOException;
import java.util.InputMismatchException;

import com.css.ds.io.IOProvider;

public class NonDecreasingSequence {


    public static void main(String[] args) throws InputMismatchException, IOException {

        IOProvider io = IOProvider.getConsoleInstance();

        StringBuilder builder = new StringBuilder();
        int t = io.readInt();
        while (t-- != 0) {
            int n = io.readInt();
            long[] arr = new long[n];
            for (int i = 0; i < n; i++) {
                arr[i] = io.readLong();
            }
            int l = io.readInt();
            long[] min = new long[n];
            int len = 1;
            min[0] = arr[0];
            for (int i = 1; i < n; i++) {
                if (arr[i] < min[0])
                    min[0] = arr[i];
                else if (arr[i] > min[len - 1]) {
                    min[len] = arr[i];
                    len++;
                } else {
                    int start = 0, end = len - 1, ans = 0;
                    while (start <= end) {
                        int mid = (start + end) >> 1;
                        if (min[mid] < arr[i]) {
                            start = mid + 1;
                        } else {
                            ans = mid;
                            end = mid - 1;
                        }
                    }
                    min[ans] = arr[i];
                }
            }
            if (len < l) {
                builder.append("-1\n");
            } else {
                builder.append(min[l - 1] + "\n");
            }
        }
        io.println(builder.toString());
        io.finish();
    }
}