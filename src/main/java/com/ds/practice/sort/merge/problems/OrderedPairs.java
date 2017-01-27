package com.ds.practice.sort.merge.problems;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.ds.practice.utils.ArrayUtils;

/**
 * Created by kishore on 28/12/16.
 * <p>
 * Given an array A on size N, you need to find the number of ordered pairs(i,j)
 * such that i<j and A[i]>A[j].
 * <p>
 * Input:
 * First line contains one integer N, size of array.
 * Second line contains N space separated integers denoting the elements of the array A.
 * <p>
 * Output:
 * Print the number of ordered pairs(i,j) such that
 * i<j and A[i]>A[j].
 * <p>
 * Constraints:
 * 1≤N≤10^6
 * 1≤A[i]≤10^6
 */
public class OrderedPairs {

    public static void main(String args[] ) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int N = Integer.parseInt(line);

        int a[] = new int[N];
        String ar[] = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            a[i] = Integer.parseInt(ar[i]);
        }


        StringBuffer sb = new StringBuffer();
        mergeSort(a, 0, a.length - 1, sb);
        System.out.println(sb.toString());
    }

    private static void mergeSort(int a[], int l, int r, StringBuffer sb) {
        int count = 0;
        if (l < r) {
            int m = (l + r) >> 1;
            mergeSort(a, l, m, sb);
            mergeSort(a, m + 1, r, sb);
            count += merge(a, l, m, r);
        }
        if (sb.toString().length() != 0)
            count = Integer.parseInt(sb.toString()) + count;
        sb.delete(0, sb.length()).append(count);
    }

    private static int merge(int a[], int l, int m, int r) {

        int n1 = m - l + 1;
        int n2 = r - m;

        int left[] = new int[n1];
        int right[] = new int[n2];

        for (int i = 0; i < n1; i++)
            left[i] = a[l + i];

        for (int j = 0; j < n2; j++)
            right[j] = a[m + 1 + j];


        int i = 0;
        int j = 0;
        int count = 0;
        int k = l;
        while (i < n1 && j < n2) {
            if (left[i]  >= right[j]) {
                a[k] = left[i];
                i++;
                count++;
            } else {
                a[k] = right[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            a[k] = left[i];
            i++;
            k++;
        }

        while (j < n2) {
            a[k] = right[j];
            j++;
            k++;
        }


        return count;
    }
}

