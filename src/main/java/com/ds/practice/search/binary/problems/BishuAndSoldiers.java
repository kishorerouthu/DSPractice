package com.ds.practice.search.binary.problems;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

/**
 * Created by kishore on 9/5/17.
 *
 * Bishu went to fight for Coding Club. There were N soldiers with various powers.
 * There will be Q rounds to fight and in each round Bishu's power will be varied.
 * With power M, Bishu can kill all the soldiers whose power is less than or equal to M(<=M).
 * After each round, All the soldiers who are dead in previous round will reborn.
 * Such that in each round there will be N soldiers to fight. As Bishu is weak in mathematics,
 * help him to count the number of soldiers that he can kill in each round and total sum of their powers.
 *
 * 1<=N<=10000
 *
 * 1<=power of each soldier<=100
 *
 * 1<=Q<=10000
 *
 * 1<=power of bishu<=100
 *
 * SAMPLE INPUT
 * 7
 * 1 2 3 4 5 6 7
 * 3
 * 3
 * 10
 * 2
 *
 * SAMPLE OUTPUT
 * 3 6
 * 7 28
 * 2 3
 *
 * Explanation
 * In first round bhishu power is 3
 *
 * So there are 3 soldiers whose power is <=3 and the sum of their power is 1+2+3=6
 *
 * therefore ans= 3 6
 *
 * same for the next round
 *
 * ALGORITHM
 * **********
 *
 * function searchShoulder(a[], n, q) {
 * 	l = 0;
 * 	r = n-1;
 * 	while(l<=r) {
 * 		m = (l+r)/2;
 * 		if (a[m]<=q)
 * 			l = m+1;
 * 		else
 * 			r = m-1;
 * 	}
 * 	return l;
 * }
 *
 */
public class BishuAndSoldiers {

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

        a[0] = readInt();

        for (int i = 1; i < n; i++) {
            a[i] = readInt();
        }

        Arrays.sort(a);

        long sum[] = new long[n];
        sum[0] = a[0];
        for (int j = 1; j < n; j++)
            sum[j] = sum[j-1] + a[j];

        int q = readInt();
        for (int j = 0; j < q; j++) {
            int k = readInt();
            int index = bSearch(a, n, k);
            out.println(index + " "+ sum[index-1]);
        }

        out.flush();
        out.close();

    }

    private static int bSearch(int a[], int n, int k) {
        int l = 0;
        int r = n-1;
        while (l<=r) {
            int m = (l + r) >> 1;
            if (a[m] <= k)
                l = m+1;
            else
                r = m-1;
        }

        return l;
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
