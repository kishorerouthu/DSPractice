package com.css.ds.practice.algorithms.search.binary.problems;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

/**
 * Created by kishore on 16/5/17.
 */
public class CountingTriangles {

    static int numChar;
    static int curChar;
    static byte[] buffer = new byte[1024];
    static InputStream stream;
    static PrintWriter out;

    public static void main(String[] args) throws IOException {

        stream = System.in;
        out = new PrintWriter(System.out);

        int n = readInt();
        String t[] = new String[n];
        for (int i = 0; i < n; i++) {
            t[i] = getSortedKey(new long[]{readLong(), readLong(), readLong()});
        }

        int uniqueCount = 0;
        for (int j = 0; j < n; j++)
            if (findTriangle(t, j+1, t[j]) != -1)
                uniqueCount++;

        out.println(uniqueCount);

        out.flush();
        out.close();
    }

    private static long findTriangle(String t[], int i, String key) {
        return -1l;
    }

    private static String getSortedKey(long[] a) {

        return a + "$" + a[0] + "$" + a[1];
    }

    private static int[] getCounts(int a[], int n) {
        int handShakes = 0;
        int firstBumps = 0;
        int result[] = new int[2];

        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                if (a[i] != a[j])
                    handShakes++;
                else
                    firstBumps++;
            }
        }

        result[0] = handShakes;
        result[1] = firstBumps;
        return result;
    }

    private static int read() throws IOException {
        if (numChar <= curChar) {
            curChar = 0;
            numChar = stream.read(buffer);
            if (numChar <= 0) {
                return -1;
            }
        }
        return buffer[curChar++];
    }

    private static long readLong() throws IOException, InputMismatchException {
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

    private static int readInt() throws IOException, InputMismatchException {
        return (int) readLong();
    }

    private static boolean isSpaceChar(int c) {
        return c == ' ' || c == '\n' || c == '\t' || c == '\r' || c == -1;
    }
}
