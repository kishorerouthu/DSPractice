package com.css.ds.practice.algorithms.dp.problems;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

/**
 * Created by kishore on 28/4/17.
 * <p>
 * Xsquare loves to play with the coins very much. Today , he has N stacks of coins .
 * Each stack of coins has some non zero height Hi equal to the number of coins on that stack
 * ( considering all the coins are identical and each coin has a height of 1 unit ) .
 * <p>
 * In one move, Xsquare can select any number of consecutive stacks of coins such that the height of each selected stack of coins Hi ≤ K .
 * Once such a sequence of stacks is chosen , Xsquare can collect any number of coins from the chosen sequence of stacks .
 * <p>
 * Xsquare wonders what is the maximum number of coins that he can collect this way ?
 * <p>
 * INPUT
 * <p>
 * First line of input contains a single integer T denoting the number of test cases .
 * First line of each test case contains two space separated integers N and K where N being the number of stacks of coins.
 * Second line of each test case contains N space separated integers denoting the number of coins Hi on each stack .
 * <p>
 * OUTPUT
 * For each test case , Print the maximum number of coins Xsquare can collect following the above gaming strategy.
 * <p>
 * CONSTRAINTS
 * 1 ≤ T ≤ 105
 * 1 ≤ N ≤ 105
 * 1 ≤ K ≤ 109
 * 1 ≤ Hi ≤ 109
 * <p>
 * Note :
 * <p>
 * sum of N over all the test case will not exceed 106.
 * <p>
 * SAMPLE INPUT
 * 2
 * 8 1
 * 3 2 2 3 1 1 1 3
 * <p>
 * 8 2
 * 3 2 2 3 1 1 1 3
 * <p>
 * SAMPLE OUTPUT
 * 3
 * 4
 * <p>
 * Explanation
 * Test 1 : N = 8 , K = 1 3 2 2 3 1 1 1 3 We can collect coins from stacks numbered 5 , 6 and 7 .
 * Test 2 : N = 8 , K = 2 3 2 2 3 1 1 1 3 We can collect coins from stacks numbered 2 and 3 .
 * Algorithm :
 * <p>
 * findMaxCoins(a[], n, k) {
 * <p>
 * sum1 = 0
 * sum2 = 0
 * for (i from 0 to n) {
 * if (a[i] <= k)
 * sum2 += a[i]
 * else {
 * sum1 = max (sum1, sum2)
 * sum2 = 0
 * }
 * }
 * <p>
 * return max(sum1, sum2);
 * }
 * <p>
 * Example :
 * 8 2
 * 3 2 2 3 1 1 1 3
 * <p>
 * n=8
 * k=2
 * sum1=0
 * sum2=0
 * <p>
 * i=0 sum2=0 sum1=0
 * i=1 sum2=2 sum1=0
 * i=2 sum2=4 sum1=0
 * i=3 sum2=0 sum1=4
 * i=4 sum2=1 sum1=4
 * i=5 sum2=2 sum1=4
 * i=6 sum2=3 sum1=4
 * i=7 sum2=0 sum1=4
 */
public class XsquareCoinCollection {


    static int numChar;
    static int curChar;
    static byte[] buffer = new byte[1024];
    static InputStream stream;
    static PrintWriter out;


    public static void main(String[] args) throws IOException {

        stream = System.in;
        out = new PrintWriter(System.out);

        int t = readInt();
        while (t > 0) {
            int n = readInt();
            long k = readLong();

            long sum1 = 0l;
            long sum2 = 0l;
            for (int i = 0; i < n; i++) {
                long a = readLong();
                if (a <= k) {
                    sum2 += a;
                } else {
                    sum1 = Math.max(sum1, sum2);
                    sum2 = 0l;
                }
            }
            out.println(Math.max(sum1, sum2));

            t--;
        }

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
