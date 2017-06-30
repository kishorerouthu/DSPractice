package com.css.ds.contest.march;

import java.util.Arrays;

/**
 * Created by kishore on 12/3/17.
 */
public class BallElimination {

    private static int minimumCost(int a[], int n) {

        int cost = 0;

        boolean removed[] = new boolean[Integer.MAX_VALUE]; //mark the ball has been removed
        int index[] = new int[Integer.MAX_VALUE];
        Arrays.fill(index, -1);

        for (int i = 0; i < n; i++) {
            int prevIndex = index[a[i]];
            if (prevIndex == -1) {
                index[a[i]] = i;
            } else {
                if (i - prevIndex == 2) {
                    index[a[i]] = -1;
                    removed[a[i-1]] = true;
                }
            }
        }

        return cost;
    }
}
