package com.css.ds.practice.algorithms.dp.problems;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

/**
 * Created by kishore on 9/5/17.
 *
 * In a mystical TimeLand, a person's health and wealth is measured in terms of time(seconds) left.
 * Suppose a person there has 24x60x60 = 86400 seconds left, then he would live for another 1 day.
 * A person dies when his time left becomes 0. Some time-amount can be borrowed from other person, or time-banks.
 * Some time-amount can also be lend to another person, or can be used to buy stuffs.
 *
 * Our hero Mr X, is in critical condition, has very less time left.
 *
 * Today's the inaugural day of a new time-bank. So they are giving away free time-amount worth 1000 years.
 *
 * Bank released N slips, A[1], A[2], .... A[N]. Each slip has a time-amount(can be +ve as well as -ve).
 *
 * A person can pick any number of slips(even none, or all of them, or some of them) out of the N slips.
 * But bank introduced a restriction, they announced one more number K.
 * Restriction is that, if a person picks a slip A[i], then the next slip that he can choose to pick will be A[i+K+1].
 * It means there should be a difference of atleast K between the indices of slips picked.
 *
 * Now slip(s) should be picked in such a way that their sum results in maximum positive time-amount sum possible with the given restriction.
 *
 * If you predict the maximum positive sum possible, then you win.
 *
 * Mr X has asked for your help. Help him win the lottery, and make it quick!
 *
 * Input Format:
 * First line of the test file contains single number T, the number of test cases to follow.
 * Each test case consists of two lines.
 * First line contains two numbers N and K , separated by a space.
 * Second line contains the N numbers A[1], A[2] ..... A[N] separated by space.
 *
 * Output Format:
 * For every test case, output in a single line the maximum positive sum possible, that is output for the case.
 *
 * Constraints:
 * T ≤ 250
 * N ≤ 10000
 * -109 ≤ A[i] ≤ 109
 * 0 ≤ K ≤ N-1
 *
 * SAMPLE INPUT
 * 2
 *
 * 10 1
 * 1 2 -3 -5 4 6 -3 2 -1 2
 *
 * 10 2
 * 1 2 -3 -5 4 6 -3 2 -1 2
 *
 * SAMPLE OUTPUT
 * 12
 * 10
 * Explanation
 * 1st Case: We can take slips { A[2]=2, A[6]=6, A[8]=2, A[10]=2 }, slips are atleast 1 indices apart this makes maximum sum, A[2]+A[6]+A[8]+A[10]=12
 *
 * 2nd Case: We can take slips { A[2]=2, A[6]=6, A[10]=2 }, slips are atleast 2 indices apart this makes maximum sum, A[2]+A[6]+A[10]=10
 *
 * ALGORITHM
 * **********
 * function findMaxTime(a[], n, k) {
 *
 * 	m=k+1
 * 	//tabulation cache
 * 	c[n+m]
 *
 * 	for(j from 0 to m)
 * 	c[j] = a[j];
 *
 * 	for(i from m to n+m) {
 * 		c[i] = max (c[i-1], a[i-m] + c[i-m];
 * 	}
 *
 * 	return c[n+k];
 * }
 *
 * Example :
 * 10 1
 * 1 2 -3 -5 4 6 -3 2 -1 2
 *
 * n=10 k=1
 * c[12] = {0,1,0,0,0,0,0,0,0,0,0,0}
 * c[0]=0, c[1]=0
 *
 * i=2 c[2] = max(1, 1+0) = 1
 * i=3 c[3] = max(1, 2+0) = 2
 * i=4 c[4] = max(2, -3+1) = 2
 * i=5 c[5] = max(2, -5+2) = 2
 * i=6 c[6] = max(2, 4+2) = 4
 * i=7 c[7] = max(4, 6+2) = 8
 * i=8 c[8] = max(8, -3+4) = 8
 * i=9 c[9] = max(8, 8+2) = 10
 * i=10 c[10] = max(10, -1+8) = 10
 * i=11 c[11] = max(10, 2+10) = 12
 *
 * c[12] = {0, 1, 1, 2, 2, 2, 4, 8, 8, 10, 10,
 *
 */
public class OnceUponATimeInTimeLand {

    static int numChar;
    static int curChar;
    static byte[] buffer = new byte[1024];
    static InputStream stream;
    static PrintWriter out;

    public static void main(String[] args) throws IOException {

        stream = System.in;
        out = new PrintWriter(System.out);

        int t = readInt();
        while (t>0) {
            int n = readInt();
            int k = readInt();

            long a[] = new long[n];
            for (int i = 0; i < n; i++)
                a[i] = readLong();

            long result = findMaxTime(a, n, k);
            out.println(result);

            t--;
        }

        out.flush();
        out.close();

    }

    private static long findMaxTime(long a[], int n, int k) {

        int m = k + 1;

        //tabulation cache
        long c[] = new long[n+m];
        for (int j = 0; j < m; j++)
            c[j] = 0;

        for (int i = m; i < n+m; i++)
            c[i] = Math.max(c[i-1], a[i-m] + c[i-m]);

        return c[n+k];
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
