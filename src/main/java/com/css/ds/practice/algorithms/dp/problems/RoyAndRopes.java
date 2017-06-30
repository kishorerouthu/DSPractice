package com.css.ds.practice.algorithms.dp.problems;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

/**
 * Created by kishore on 2/5/17.
 *
 * Roy has a rope of length L meters. This rope has several other ropes attached to
 * it at the end of every meter (except for the end of the rope).
 * At each meter there are two ropes attached to this main rope,
 * let's call them upper and lower ropes. See the following example.
 *
 * Roy lit the rope on fire from the left end. This fire burns down the rope by 1 meter/minute.
 * Your task is to find how much time (in minutes) will the fire take to burn down the entire rope.
 *
 * Input:
 * First line contains
 * T - number of test cases.
 * First line of each test case contains
 * L - length of the rope.
 * Second line of each test case contains L−1 integers separated by space denoting lengths of all the upper ropes at each meter.
 * Third line of each test case contains L−1 integers separated by space denoting lengths all the lower ropes at each meter.
 *
 * Output:
 * Output the time (in minutes) required to burn down the entire rope for each test case in a new line.
 *
 * Constraints:
 * 1≤T≤10
 * 2≤L≤1000000
 * 0≤upper[i]≤1000000 where 1≤i≤L−1
 * 0≤lower[i]≤1000000 where 1≤i≤L−1
 *
 * Sample Explanation:
 * Follow the nature of fire. Note that after 1 min. Fire can go in all three directions.
 * Hence after 2 mins fire burnt 1 meter in all 3 directions.
 * Rest is explained in the image below.
 *
 * SAMPLE INPUT
 * 1
 * 5
 * 1 2 1 2
 * 1 0 2 1
 *
 * ALGORITHM:
 *
 * function getMinutes(int u[], int l[], int n) {
 *         long count = 1;
 *         int uv = 0;
 *         int lv = 0;
 *         int max = 0;
 *         for (int i = 0; i < n-1; i ++) {
 *             uv = (u[i] > n-i-1) ? (u[i] - (n-i-1)) : 0;
 *             lv = (l[i] > n-i-1) ? (l[i] - (n-i-1)) : 0;
 *
 *             max = Math.max(Math.max(uv, lv));
 *
 *             if ((i != n-2) || (u[i] != 0 && l[i] != 0))
 *                 count++;
 *         }
 *         return count + max;
 *     }
 *
 * Case1:
 * 3
 * 5 4
 * 7 4
 *
 * i=0 uv=3 lv=5 max=5 count=2
 * i=1 uv=3 lv=3 max=5 count=3
 * retrun count=8
 *
 * Case2:
 * 6
 * 3 0 8 8 4
 * 5 2 4 0 7
 *
 * i=0 uv=0 lv=0 max=0 count=2
 * i=1 uv=0 lv=0 max=0 count=3
 * i=2 uv=5 lv=1 max=5 count=4
 * i=3 uv=6 lv=0 max=6 count=5
 * i=4 uv=3 lv=6 max=6 count=6
 * return 12
 *
 * Case3:
 * 4
 * 4 0 0
 * 6 6 6
 *
 * i=0 uv=1 lu=3 max=3 count=2
 * i=1 uv=0 vu=4 max=4 count=3
 * i=2 uv=0 vu=5 max=5 count=4
 * return 5 + 4
 */
public class RoyAndRopes {


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
            int u[] = new int[n - 1];
            int l[] = new int[n - 1];

            for (int i = 0; i < n - 1; i++)
                u[i] = readInt();

            for (int j = 0; j < n - 1; j++)
                l[j] = readInt();

            out.println(getMinutes(u, l, n));

            t--;
        }

        out.flush();
        out.close();
    }


    private static long getMinutes(int u[], int l[], int n) {
        long count = 1;
        int uv = 0;
        int lv = 0;
        int max = 0;
        for (int i = 0; i < n-1; i ++) {
            uv = (u[i] > n-i-1) ? (u[i] - (n-i-1)) : 0;
            lv = (l[i] > n-i-1) ? (l[i] - (n-i-1)) : 0;

            max = Math.max(max, Math.max(uv, lv));
            count++;
        }
        return count + max;
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
