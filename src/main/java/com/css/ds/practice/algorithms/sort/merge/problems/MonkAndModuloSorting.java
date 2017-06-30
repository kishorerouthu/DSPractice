package com.css.ds.practice.algorithms.sort.merge.problems;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

/**
 * Created by kishore on 9/3/17.
 */
public class MonkAndModuloSorting {

    static int numChar;
    static int curChar;
    static byte[] buffer = new byte[1024];
    static InputStream stream;
    static PrintWriter out;
    static long key;

    public static void main(String[] args) throws IOException {

        stream = System.in;
        out = new PrintWriter(System.out);

        int n = readInt();
        key = readLong();

        long a[] = new long[n];
        for (int i = 0; i < n; i++)
              a[i] = readLong();

        mergeSort(a, 0, n-1);

        for (long val : a)
             out.printf("%d ", val);

        out.flush();
        out.close();
    }

    private static void mergeSort(long a[], int l, int r) {
        if ( l < r) {
            int m = (l + r) >> 1;
            mergeSort(a, l, m);
            mergeSort(a, m + 1, r);
            merge(a, l, m, r);
        }
    }

    private static void merge(long a[], int l, int m, int r) {

        int n1 = m - l + 1;
        int n2 = r - m;

        long left[] = new long[n1];
        long right[] = new long[n2];

        for (int i = 0; i < n1; i++)
            left[i] = a[l + i];


        for (int j = 0; j < n2; j++)
            right[j] = a[m + 1 + j];


        int i = 0;
        int j = 0;
        int k = l;

        while (i < n1 && j < n2) {

            long rem1 = left[i] % key;
            long rem2 = right[j] % key;

            if (rem1 <= rem2)
                a[k++] = left[i++];
            else
                a[k++] = right[j++];
        }

        while (i < n1)
            a[k++] = left[i++];

        while (j < n2)
            a[k++] = right[j++];


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
