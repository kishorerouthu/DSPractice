package com.practice.datastructers.arrays;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

/**
 * Created by Kishore Routhu on 17/6/17 1:34 PM.
 *
 * Having a good previous year, Monk is back to teach algorithms and data structures.
 * This year he welcomes the learners with a problem which he calls "Welcome Problem".
 * The problem gives you two arrays A and B (each array of size N) and
 * asks to print new array C such that: C[i]=A[i]+B[i] ;
 * 1≤i≤N
 * Now, Monk will proceed further when you solve this one. So, go on and solve it :)
 *
 * Input:
 * First line consists of an integer N,
 * denoting the size of A and B.
 * Next line consists of N space separated integers denoting the array A.
 * Next line consists of N space separated integers denoting the array B.
 *
 * Output:
 * Print N space separated integers denoting the array C.
 *
 * Input Constraints:
 * 1≤N≤100000
 * 1≤A[i]≤100000;
 * 1≤i≤N
 * 1≤B[i]≤100000;
 * 1≤i≤N
 *
 * SAMPLE INPUT
 * 5
 * 1 2 3 4 5
 * 4 5 3 2 10
 *
 * SAMPLE OUTPUT
 * 5 7 6 6 15
 *
 */
public class MonkWelcomeProblem {

    static int numChar;
    static int curChar;
    static byte[] buffer = new byte[1024];
    static InputStream stream;
    static PrintWriter out;

    public static void main(String[] args) throws IOException {

        stream = System.in;
        out = new PrintWriter(System.out);

        int n = readInt();
        int a[] = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = readInt();

        int b[] = new int[n];
        for (int i = 0; i < n; i++)
            b[i] = readInt();

        for (int i = 0; i < n; i++)
            out.printf("%d ", a[i] + b[i]);

        out.flush();
        out.close();

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

    private static byte readByte() throws IOException {
        return (byte) readInt();
    }

    private static int readInt() throws IOException, InputMismatchException {
        return (int) readLong();
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

    private static boolean isSpaceChar(int c) {
        return c == ' ' || c == '\n' || c == '\t' || c == '\r' || c == -1;
    }
}
