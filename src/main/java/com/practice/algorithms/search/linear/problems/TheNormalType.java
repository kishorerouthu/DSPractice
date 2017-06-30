package com.practice.algorithms.search.linear.problems;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by Kishore Routhu on 29/5/17 7:19 PM.
 */
public class TheNormalType {

    static int numChar;
    static int curChar;
    static byte[] buffer = new byte[1024];
    static InputStream stream;
    static PrintWriter out;

    private static final long MODULE = 1000000009;


    public static void main(String[] args) throws IOException {

        stream = System.in;
        out = new PrintWriter(System.out);

        int n = readInt();
        long a[] = new long[n];
        Set<Long> set = new HashSet<Long>();
        for (int i = 0; i < n; i++) {
            a[i] = readLong();
            set.add(a[i]);
        }

        int dis_count = 0;
        for (int i = 0; i < n - set.size(); i++) {
            Set<Long> cache = new HashSet<Long>();
            for (int j = i; j < n; j++) {
                cache.add(a[j]);

                if (cache.size() > set.size()) break;

                if (cache.size() == set.size())
                    dis_count++;
            }
        }

        out.println(dis_count);

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
