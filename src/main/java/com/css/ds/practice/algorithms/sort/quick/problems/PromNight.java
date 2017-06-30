package com.css.ds.practice.algorithms.sort.quick.problems;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

/**
 * Created by kishore on 17/2/17.
 */
public class PromNight {

    static int numChar;
    static int curChar;
    static byte[] buffer = new byte[1024];
    static InputStream stream;
    static PrintWriter out;

    public static void main(String[] args) throws IOException {
        stream = System.in;
        out = new PrintWriter(System.out);

        int t = readInt();
        for (int i = 0; i < t; i++) {
            int m = readInt();
            int n = readInt();

            int b[] = new int[m];
            for (int j = 0; j < m; j++) {
                b[j] = readInt();
            }

            int g[] = new int[n];
            for (int k = 0; k < n; k++) {
                g[k] = readInt();
            }

            String result = "";
            if (m > n) {
                result = "NO";
            } else {
                sort(b, 0, m-1);
                sort(g, 0, n-1);

                int k = 0;
                int l = 0;
                int found = 0;
                while (k < m && l < n) {
                    if (b[k] > g[l]) {
                        k++; l++; found++;
                    } else {
                        l++;
                    }
                }

                result = (found == m) ? "YES" : "NO";
            }
            out.println(result);
        }

        out.flush();
        out.close();
    }


    private static void sort(int a[], int l, int r) {
        if (l < r) {
            int p = parition(a, l ,r);
            sort(a, l, p-1);
            sort(a, p+1, r);
        }
    }

    private static int parition(int a[], int l, int r) {

        int i = l-1;
        int j = l;
        int p = a[r];

        while (j < r) {

            if (a[j] < p) {
                i++;
                int temp = a[j];
                a[j] = a[i];
                a[i] = temp;
            }
            j++;
        }

        //Place pivot at right position i+1
        a[r] = a[i+1];
        a[i+1] = p;
        return i+1;

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
