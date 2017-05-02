package com.ds.practice.dp.problems;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

import sun.misc.resources.Messages_pt_BR;

/**
 * Created by kishore on 25/4/17.
 * <p>
 * Limelight is a technique that is used when all four users take place in the cardinal directions.
 * They will then join their strength in the form of four connecting streams above the target area.
 * It will then create a massive ball of lightning powerful enough to incinerate everything within the area of the four users.
 * <p>
 * The Leaf village is build in the shape of Spiral of integers. Spiral of integers, of an integer N, is an interesting
 * N∗N spiral matrix which starts with 1 at the center. For example, for N=4, the spiral of integers is
 * <p>
 * 16  15  14  13
 * <p>
 * 5   4   3   12
 * <p>
 * 6   1   2   11
 * <p>
 * 7   8   9   10
 * <p>
 * Kitane, Nauma, Tōu and Seito are planning to destroy the whole Leaf village.
 * The limelight spot will be the 4 corners of the village.
 * Strength of the attack is equal to the sum of all the elements in the connecting streams as shown in the figure
 * ( sum of diagonal elements of the spiral of integers of N ) .
 * <p>
 * Given the value of N, you need to compute the strength of the attack (mod 10^9+9).
 * <p>
 * Input:
 * First line contains an integer T, denoting the number of testcases.
 * Each test case consists of a single integer N.
 * <p>
 * Output:
 * For each test case output a single integer denoting the strength of the attack (mod 10^9+9).
 * <p>
 * Constraints:
 * 1≤T≤10^5
 * 1≤N≤10^7
 * <p>
 * SAMPLE INPUT
 * 2
 * 4
 * 10000000
 * <p>
 * SAMPLE OUTPUT
 * 56
 * 679604006
 */
public class LeafAndLimelightAttack {

    static int numChar;
    static int curChar;
    static byte[] buffer = new byte[1024];
    static InputStream stream;
    static PrintWriter out;

    private static final long MODULE = 1000000009;


    public static void main(String[] args) throws IOException {

        stream = System.in;
        out = new PrintWriter(System.out);

        int t = readInt();
        long a[] = new long[t];
        long even[] = new long[t];
        long odd[] = new long[t];
        int ec = 0;
        int oc = 0;
        for (int i = 0; i < t; i++) {
            a[i] = readLong();
            if (a[i] % 2 == 0)
                even[ec++] = a[i];
            else
                odd[oc++] = a[i];
        }

        final Map<Long, Long> lookup = new HashMap<Long, Long>();
        Arrays.sort(even);
        for (long n : even) {
            if (n == 0)
                continue;
            getSum(n, lookup);
        }

        Arrays.sort(odd);
        for (long n : odd) {
            if (n == 0)
                continue;
            getSum(n, lookup);
        }

        for (long n : a) {
            out.println(lookup.get(n));
        }

        out.flush();
        out.close();
    }

   private static long getSum(long n, Map<Long, Long> lookup) {

        //Base case for (1 X 1) and (0 X 0) matrix
        if (n == 1 || n == 0) {
            lookup.put(n, n);
            return n;
        }

        if (lookup.get(n) != null)
            return lookup.get(n);

        /*
          sum = 4*n*n - 6*n + 6  sum of 4 corners of the matrix (n X n)

          Explanation :  TopLeft Corner n^2
                          TopRight Corner n^2 - (n-1)
                          TopBottom Corner n^2 - 2(n-1)
                          TopRight Corner n^2 - 3(n-1)

                    sum = n^2 + n^2 - (n-1) + n^2 - 2(n-1) + n^2 - 3(n-1)
                        = n^2 + n^2 - n + 1 +  n^2 - 2n + 2 + n^2 - 3n + 3
                        = 4n^2 - 6n + 6
         */
        long sum = 4*n*n - 6*n + 6;
        sum = sum + getSum(n-2, lookup);
        lookup.put(n, sum % MODULE);
        return sum;
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
