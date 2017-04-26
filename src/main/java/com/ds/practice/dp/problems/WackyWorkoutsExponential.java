package com.ds.practice.dp.problems;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

/**
 * Created by kishore on 13/4/17.
 */
public class WackyWorkoutsExponential {
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
            long n = readLong();
            long f = fibonacci(n+2);
            out.println(f);
        }

        out.flush();
        out.close();
    }

    private static long fibonacci(long n) {
        long[][] f = new long[][]{{1, 1}, {1, 0}};

        if (n == 0)
            return n;

        power(f, n);
        return f[0][0] % 1000000007;
    }

    private static void power(long[][] f, long n) {

        if (n == 0 || n == 1)
            return;

        long[][] m = new long[][]{{1, 1}, {1, 0}};

        power(f, n/2);
        multiply(f, f);

        if (n%2 == 1)
            multiply(f, m);
    }

    private static void multiply(long[][] f, long[][] m) {

        long x = (f[0][0] * m[0][0] + f[0][1] * m[1][0]) % 1000000007;
        long y = (f[0][0] * m[0][1] + f[0][1] * m[1][1]) % 1000000007;
        long z = (f[1][0] * m[0][0] + f[1][1] * m[1][0]) % 1000000007;
        long w = (f[1][0] * m[0][1] + f[1][1] * m[1][1]) % 1000000007;

        f[0][0] = x;
        f[0][1] = y;
        f[1][0] = z;
        f[1][1] = w;
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

    private static String readString() throws IOException {
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

    private static boolean isSpaceChar(int c) {
        return c == ' ' || c == '\n' || c == '\t' || c == '\r' || c == -1;
    }
}
