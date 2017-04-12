package com.ds.practice.dp;

/**
 * Created by kishore on 12/4/17.
 *
 * To print the actual elements of LIS let’s modify algorithm to find the length and element of LIS.
 *
 * To reconstruct the actual LIS we will use a parent[] array of length equal to input array.
 * Where parent[i] be the predecessor of element with index i in the LIS ending at element with index i.
 *
 * Also in the array S, we will not keep the actual integers, but their indices(positions) in the set.
 * We do not keep {1, 2, 4, 5, 8}, but keep{4, 5, 3, 8, 9}.
 * 2 6 3 4 1 2 7 9 5 8
 * So how do we update array parent[] let’s look at the above two points again:
 *
 * Iterate through each element X of an input array arr[]
 * 1) If X > last element in S, then parent[indexOfX] = LastElementOfS.
 * This means the parent of the newest element is the last element of array S and then we will just prepend X to the end of S.
 *
 * 2) Otherwise find the index of the smallest element in S, which is >=than X, and change it to X. Here parent[indexOfX] = S[index – 1].
 */

public class PrintLIS {


    public static void main(String[] args) {
        int a[] = new int[] {10, 22, 9, 33, 21, 50, 41, 60, 80};
        lis(a, a.length);
    }

    private static int b_search(int a[], int s[], int v) {
        int l = 0;
        int h = s.length-1;

        while (l < h) {
            int m = (l + h) >> 1;
            if (a[s[m]] < v)
                l = m + 1;
            else if(a[s[m]] > v)
                h = m;
            else
                return m;
        }
        return l;
    }

    private static void lis(int a[], int n) {
        int s[] = new int[n];
        int p[] = new int[n];
        int size = 0;
        s[size] = 0;
        p[0] = -1;
        for (int i=1; i<n; i++) {
            int x = a[i];
            if (a[s[size]] < x) {
                size++;
                s[size] = i;
                p[i] = s[size-1];
            } else {
                int pos = b_search(a, s, x);
                s[pos] = i;
                if (pos != 0) {
                    p[i] = s[pos-1];
                }
            }
        }

        int pos = s[size];
        while (size >= 0) {
            System.out.printf("%d ", a[pos]);
            pos = p[pos];
            size--;
        }

    }
}


