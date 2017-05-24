package com.practice.algorithms.search.ternary.problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Kishore Routhu on 12/10/16 1:33 PM.
 */
public class TernaryUniModalFunction {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int N = Integer.parseInt(line);
        for (int i = 0; i < N; i++) {
            String[] limits = br.readLine().split(" ");
            int l = Integer.parseInt(limits[0]);
            int r = Integer.parseInt(limits[1]);
            int result = search(l, r);
            if (result == -1)
                System.out.println();
            else
                System.out.println(result);
        }
    }

    private static int search(int l, int r) {
        boolean matched = false;
        for(int i = 0; i < 200; i++) {
            int mid1 = (l*2 + r) / 3;
            int mid2 = (l + 2*r) / 3;

            if (mid1 == mid2) {
                if (matched)
                    return -1;
                mid2 = r;
                matched = true;
            }

            if (funX(mid1) > funX(mid2))
                l = mid1 + 1;
            else
                r = mid2;
        }
        System.out.println(l);
        return funX(l);
    }

    private static int funX(int x) {
        int sqr = x * x;
        return (2 * sqr - 12 * x + 7);
    }

    private static void print(int l, int r) {
        for (int j = l; j <= r; j++)
            System.out.println(j + "   " + funX(j));
    }
}
