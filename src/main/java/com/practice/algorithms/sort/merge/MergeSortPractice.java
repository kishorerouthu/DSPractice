package com.practice.algorithms.sort.merge;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

/**
 * Created by kishore on 8/3/17.
 *
 * Given an array A on size N, you need to find the number of ordered pairs
 * (i,j) such that i<j and A[i]>A[j].
 *
 * Input:
 * First line contains one integer N, size of array.
 * Second line contains N space separated integers denoting the elements of the array A.
 *
 * Output:
 * Print the number of ordered pairs (i,j) such that i<j and A[i]>A[j].
 *
 * Constraints: 1≤N≤106
 * 	     1≤A[i]≤106
 *
 * SAMPLE INPUT
 * 5
 *
 * 1 4 3 2 5
 * SAMPLE OUTPUT
 * 3
 */
public class MergeSortPractice {


    static int numChar;
    static int curChar;
    static byte[] buffer = new byte[1024];
    static InputStream stream;
    static PrintWriter out;

    static long count = 0;

    public static void main(String args[] ) throws Exception {

        stream = System.in;
        out = new PrintWriter(System.out);

        int n = readInt();

        int a[] = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = readInt();
        }

        mergeSort(a, 0, a.length - 1);

        out.println(count);
        out.flush();
    }

    private static void mergeSort(int a[], int l, int r) {
        int count = 0;
        if (l < r) {
            int m = (l + r) >> 1;
            mergeSort(a, l, m);
            mergeSort(a, m + 1, r);
            merge(a, l, m, r);
        }
    }

    private static void merge(int a[], int l, int m, int r) {

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
        int k = l;
        while (i < n1 && j < n2) {
            if (left[i]  > right[j]) {
                a[k] = right[j];
                j++;
                count +=  (m - (i + l) + 1);
            } else {
                a[k] = left[i];
                i++;
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
    }

    public static int read() throws IOException {
        if (numChar <= curChar) {
            curChar = 0;
            numChar = stream.read(buffer);
            if (numChar <= 0) {
                return -1;
            }
        }
        return buffer[curChar++];
    }

    public static long readLong() throws IOException, InputMismatchException {
        int c = read();
        if (c == -1)
            throw new IOException();
        while (isSpaceChar(c)) {
            c = read();
        }
        boolean negative = false;
        if (c == '-') {
            negative = true;
            c = read();
        }
        long res = 0;
        while (!isSpaceChar(c)) {
            if (c < '0' || c > '9')
                throw new InputMismatchException();
            res *= 10;
            res += (c - '0');
            c = read();
        }
        if (negative)
            return -res;
        return res;
    }



    public static int readInt() throws IOException, InputMismatchException {
        return (int) readLong();
    }

    public static String readString() throws IOException {
        int c = read();
        if (c == -1)
            throw new IOException();
        while (isSpaceChar(c)) {
            c = read();
        }
        StringBuilder builder = new StringBuilder();
        while (!isSpaceChar(c)) {
            builder.append((char) c);
            c = read();
        }
        return builder.toString();
    }

    public static boolean isSpaceChar(int c) {
        return c == ' ' || c == '\n' || c == '\t' || c == '\r' || c == -1;
    }
}
