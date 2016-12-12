package com.ds.practice.search.binary.problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

/**
 * Xsquare loves to play with numbers a lot. Today, he has a multi set S consisting of N integer elements.
 * At first, he has listed all the subsets of his multi set S and later replaced all the subsets with the
 * maximum element present in the respective subset.
 *
 * For example :
 * Consider the following multi set consisting of 3 elements S = {1,2,3}.
 *
 * List of all subsets :
 * {}, {1}, {2}, {3}, {1,2}, {2,3}, {1,3}, {1,2,3}
 *
 * Final List :
 * {0}, {1}, {2}, {3}, {2}, {3}, {3}, {3}
 *
 * Now, Xsquare wonders that given an integer K how many elements in the final list are greater than ( > ) ,
 * less than ( < ) or equals to ( == ) K.
 * To make this problem a bit more interesting, Xsquare decided to add Q queries to this problem.
 * Each of the queries has one of the following type.
 * > K : Count the number of elements X in the final list such that X > K.
 * < K : Count the number of elements X in the final list such that X < K.
 * = K : Count the number of elements X in the final list such that X == K.
 *
 * Note:
 * Answer to a particular query can be very large. Therefore, Print the required answer modulo 109+7.
 * An empty subset is replaced by an integer 0.
 *
 * Input
 * First line of input contains two space separated integers N and Q denoting the size of multiset S and number of
 * queries respectively.
 * Next line of input contains N space separated integers denoting elements of multi set S.
 * Next Q lines of input contains Q queries each having one of the mentioned types.
 *
 * Output
 * For each query, print the required answer modulo 109+7.
 *
 * Constraints:
 * 1 ≤ N,Q ≤ 5*105
 * 1 ≤ K,Si ≤ 109
 * query_type = { < , > , = }
 *
 * Warning :
 * Prefer to use printf / scanf instead of cin / cout.
 *
 * SAMPLE INPUT
 * 3 5
 * 1 2 3
 * < 1
 * > 1
 * = 3
 * = 2
 * > 3
 *
 * SAMPLE OUTPUT
 * 1
 * 6
 * 4
 * 2
 * 0
 *
 * Created by Kishore Routhu on 26/10/16 4:23 PM.
 */
public class XSquare {

    public static void main1(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input[] = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int Q = Integer.parseInt(input[1]);

        String data[] = br.readLine().split(" ");

        long sum = 0;
        long sa[] = new long[N];
        long a[] = new long[N];
        for (int i = N-1; i >= 0; i--) {
            sum += Math.pow(2, i);
            sa[i] = sum;
            a[i] = Long.parseLong(data[i]);
        }

        long ans;
        int pos;
        for (int j = 0; j < Q; j++) {
            String condition[] = br.readLine().split(" ");
            long key = Long.parseLong(condition[1]);
            ans = 0;
            if (key > a[N-1])
                pos = N-1;
            else
                pos = search(a, key);

            if(key < a[0] && ">".equals(condition[0])) {
                ans = sum + 1;
            } else if ("=".equals(condition[0]) && key == a[pos]) {
                ans = sa[pos];
                if (pos < N-1)
                    ans -= sa[pos + 1];
            } else if ("<".equals(condition[0]) && !(key < a[0])) {
                ans = sa[0];
                if (a[pos] == key)
                    ans -= sa[pos];
            } else if (">".equals(condition[0]) && (pos < N-1)) {
                 if (a[pos] != key)
                    ans = sa[pos];
                 else
                    ans = sa[pos + 1];
            }
            System.out.println(ans);
        }

    }

    private static int search(long a[], long key) {
        int m;
        int l = 0;
        int r = a.length - 1;
        while (r - l > 1) {
            m = l + ((r - l) >> 1);
            if (a[m] >= key)
                r = m;
            else
                l = m;
        }
        return r;
    }

    public static void main(String[] args) {
        long a[] = new long[]{1, 3, 5};
        System.out.println(search(a, 1));
    }
}
