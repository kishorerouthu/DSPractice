package com.ds.practice.sort.merge.problems;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

/**
 * Created by kishore on 9/3/17.
 *
 * In the previous problem Chandu bought some unsorted arrays and sorted them (in non-increasing order).
 * Now, he has many sorted arrays to give to his girlfriend.
 * But, the number of sorted arrays are very large so Chandu decided to merge two sorted arrays into one sorted array.
 * But he is too lazy to do that.
 * So, he asked your help to merge the two sorted arrays into one sorted array (in non-increasing order).
 *
 * Input:
 * First line contains an integer T, denoting the number of test cases.
 * First line of each test case contains two space separated integers N and M, denoting the size of the two sorted arrays.
 * Second line of each test case contains N space separated integers, denoting the first sorted array A.
 * Third line of each test case contains M space separated integers, denoting the second array B.
 *
 * Output:
 * For each test case, print (N + M) space separated integer representing the merged array.
 *
 * Constraints:
 * 1 <= T <= 100
 * 1 <= N, M <= 5*104
 * 0 <= Ai, Bi <= 109
 *
 * SAMPLE INPUT
 * 1
 * 4 5
 * 9 7 5 3
 * 8 6 4 2 0
 *
 * SAMPLE OUTPUT
 * 9 8 7 6 5 4 3 2 0
 *
 */
public class ChanduAndGirlfriendReturns {

    static int numChar;
    static int curChar;
    static byte[] buffer = new byte[1024];
    static InputStream stream;
    static PrintWriter out;

    public static void main(String[] args) throws IOException {

        stream = System.in;
        out = new PrintWriter(System.out);

        int t = readInt();
        while (t-- != 0) {
            int n = readInt();
            int m = readInt();

            long a[] = new long[n];
            for (int i = 0; i < n; i++)
                a[i] = readLong();

            long b[] = new long[m];
            for (int i = 0; i < m; i++)
                b[i] = readLong();

            long c[] = new long[n + m];
            merge(a, b, c, n, m);

            for (long val : c)
                out.printf("%d ", val);

            out.println();
        }

        out.flush();
        out.close();
    }

    private static void merge(long a[], long b[], long c[], int n, int m) {

        int k = 0;
        int i = 0;
        int j = 0;
        while (i < n && j < m) {
            if (a[i] >= b[j]) {
                c[k] = a[i];
                i++;
            } else {
                c[k] = b[j];
                j++;
            }
            k++;
        }

        while (i < n)
            c[k++] = a[i++];

        while (j < m)
            c[k++] = b[j++];

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
