package com.css.ds.practice.algorithms.dp;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

/**
 * Created by kishore on 12/4/17.
 *
 * Given an integer N, print the factorial of the N(mod 10^9+7).
 *
 * Input:
 * First line contains one integer, T, number of test cases.
 * Each test case contains one integer, N.
 *
 * Output:
 * For each test case you need to print the factorial of N (mod 10^9+7).
 *
 * Constraints:
 * 1≤T≤10^5
 * 0≤N≤10^5
 *
 * SAMPLE INPUT
 * 5
 * 1
 * 2
 * 3
 * 4
 * 5
 *
 * SAMPLE OUTPUT
 * 1
 * 2
 * 6
 * 24
 * 120
 *
 */
public class Factorial {

    static int numChar;
    static int curChar;
    static byte[] buffer = new byte[1024];
    static InputStream stream;
    static PrintWriter out;

    public static void main(String[] args) throws IOException {
        stream = System.in;
        out = new PrintWriter(System.out);

        int t = readInt();
        Map<Long, Long> lookup = new HashMap<Long, Long>();
        for (int i=0; i<t; i++) {
            long n = readLong();
            out.println(fact(n, lookup));
        }

        out.flush();
        out.close();
    }


    public static long fact(long n, Map<Long, Long> lookup) {

        if (!lookup.containsKey(n)) {
            if (n == 0 || n == 1)
                lookup.put(n, 1l);
            else
                lookup.put(n, (n * fact(n - 1, lookup)) % 1000000007);
        }

        return (lookup.get(n)%1000000007);
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
