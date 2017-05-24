package com.practice.algorithms.dp.problems;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by kishore on 13/4/17.
 *
 * The government of virtualBit believes that the health of its citizens is the highest priority.
 * It has devised an N-day health plan. During these N days, the citizens are encouraged to go to the gym for workouts.
 *
 * A person may or may not go to the gym on any day. The people of virtualBit are a lazy lot,
 * and they don't want to go to the gym for more than one consecutive day. Given the number of days N,
 * print the number of ways the N-day plan can be completed.
 *
 * Note that it is possible to not go to the gym on any day at all and still complete the plan.
 *
 * Input
 *
 * The first line contains T, the number of test cases.
 *
 * Each test case contains a number N denoting the number of days.
 *
 * Output
 *
 * Each test case contains a single number denoting the number of ways by which the N-day plan may be completed.
 *
 * Output your answer modulo 109+7.
 *
 * Constraints
 * 1 <= T <= 60000
 * 1 <= N <= 10^18
 *
 * SAMPLE INPUT
 * 2
 * 2
 * 3
 *
 * SAMPLE OUTPUT
 * 3
 * 5
 *
 * Explanation
 * In the first test case the plan can be completed by going only on the first day, only on the second day or on no day at all.
 *
 * In the second test case, the plan can be completed by any of the following combinations.
 *
 * {}, {1}, {2}, {3}, {1,3}
 */

public class WackyWorkouts {
    static int numChar;
    static int curChar;
    static byte[] buffer = new byte[1024];
    static InputStream stream;
    static PrintWriter out;

    public static void main(String[] args) throws IOException {
        stream = System.in;
        out = new PrintWriter(System.out);

        int t = readInt();

        TreeMap<Long, Long> lookup = new TreeMap<Long, Long>();
        for (int i = 0; i < t; i++) {
            long n = readLong();
            long f = fibTabulation(n+2, lookup) % 1000000007;
            out.println(f);
        }

        out.flush();
        out.close();
    }

    private static long fibTabulation(long n, TreeMap<Long, Long> lookup) {
        if (lookup.containsKey(n))
            return lookup.get(n);

        long a = 1;
        long b = 1;
        int index = 3;
        int size = lookup.size();
        if (size != 0) {
            Set<Long> lookupKeys = lookup.keySet();
            Long keys[] = new Long[lookupKeys.size()];
            lookupKeys.toArray(keys);

            a = lookup.get(keys[size - 2]) % 1000000007;
            b = lookup.get(keys[size - 1]) % 1000000007;
            index = size + 1;
        }

        lookup.put(1l, 1l);
        lookup.put(2l, 1l);

        long c = 0;
        for (long i=index; i<=n; i++) {
            c = (a + b) % 1000000007;
            lookup.put(i, c%1000000007);

            a = b;
            b = c;
        }

        return lookup.get(n)%1000000007;
    }

    private static long fibonacci(long n, Map<Long, Long> lookup) {
            if (!lookup.containsKey(n)) {
                if (n <= 1)
                    lookup.put(n, n);
                else
                    lookup.put(n, (fibonacci(n-1, lookup) + fibonacci(n-2, lookup)) % 1000000007);
            }

            return (lookup.get(n) % 1000000007);
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
