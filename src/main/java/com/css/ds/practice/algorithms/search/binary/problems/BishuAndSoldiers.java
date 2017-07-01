package com.css.ds.practice.algorithms.search.binary.problems;

import java.io.IOException;
import java.util.Arrays;

import com.css.ds.io.IOProvider;

/**
 * Created by kishore on 9/5/17.
 *
 * Bishu went to fight for Coding Club. There were N soldiers with various powers.
 * There will be Q rounds to fight and in each round Bishu's power will be varied.
 * With power M, Bishu can kill all the soldiers whose power is less than or equal to M(<=M).
 * After each round, All the soldiers who are dead in previous round will reborn.
 * Such that in each round there will be N soldiers to fight. As Bishu is weak in mathematics,
 * help him to count the number of soldiers that he can kill in each round and total sum of their powers.
 *
 * 1<=N<=10000
 *
 * 1<=power of each soldier<=100
 *
 * 1<=Q<=10000
 *
 * 1<=power of bishu<=100
 *
 * SAMPLE INPUT
 * 7
 * 1 2 3 4 5 6 7
 * 3
 * 3
 * 10
 * 2
 *
 * SAMPLE OUTPUT
 * 3 6
 * 7 28
 * 2 3
 *
 * Explanation
 * In first round bhishu power is 3
 *
 * So there are 3 soldiers whose power is <=3 and the sum of their power is 1+2+3=6
 *
 * therefore ans= 3 6
 *
 * same for the next round
 *
 * ALGORITHM
 * **********
 *
 * function searchShoulder(a[], n, q) {
 * 	l = 0;
 * 	r = n-1;
 * 	while(l<=r) {
 * 		m = (l+r)/2;
 * 		if (a[m]<=q)
 * 			l = m+1;
 * 		else
 * 			r = m-1;
 * 	}
 * 	return l;
 * }
 *
 */
public class BishuAndSoldiers {


    public static void main(String[] args) throws IOException {

        IOProvider io = IOProvider.getConsoleInstance();

        int n = io.readInt();
        int a[] = new int[n];

        a[0] = io.readInt();

        for (int i = 1; i < n; i++) {
            a[i] = io.readInt();
        }

        Arrays.sort(a);

        long sum[] = new long[n];
        sum[0] = a[0];
        for (int j = 1; j < n; j++)
            sum[j] = sum[j-1] + a[j];

        int q = io.readInt();
        for (int j = 0; j < q; j++) {
            int k = io.readInt();
            int index = bSearch(a, n, k);
            io.println(index + " "+ sum[index-1]);
        }

        io.finish();

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
