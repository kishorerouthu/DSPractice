package com.css.ds.practice.algorithms.dp.problems;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

/**
 * Created by kishore on 23/4/17.
 *
 * Dhoni has n days of vacations! So he decided to go either driving or do his cricket practice.
 * Dhoni knows the following information about each of these n days:
 * whether he can drive his car or not (due to odd-even rule) and whether the cricket academy is open for practice on that day.
 * For the i-th day there are four options:
 *
 * 1.He can not drive his car and the academy is closed.
 * 2.He can not drive his car and the academy is open.
 * 3.He can drive his car and the academy is closed.
 * 4.He can drive his car and the academy is open.
 *
 * On each of these days Dhoni can either have rest or drive his car (if it is possible on this day),
 * or go to academy (if the academy is open on this day).
 *
 * Find the minimum number of days on which Dhoni will have rest (it means, he will not go driving and academy at the same time).
 * The only limitation that Dhoni has — he does not want to do the same activity on two consecutive days:
 * it means, he will not go for driving on two consecutive days, and academy for practice on two consecutive days.
 *
 * Input Format
 * The first line contains a positive integer n (1 ≤ n ≤ 100) — the number of days of Dhoni's holidays.
 * The second line contains the sequence of integers a1, a2, ..., an (0 ≤ ai ≤ 3) separated by space, where:
 * ai equals 0, if on the i-th day of holiday he can not drive his car and the academy is closed.
 * ai equals 1, if on the i-th day of holiday he can not drive his car and the academy is open.
 * ai equals 2, if on the i-th day of holiday he can drive his car and the academy is closed.
 * ai equals 3, if on the i-th day of holiday he can drive his car and the academy is open.
 *
 * Output format
 * Print the minimum possible number of days on which Dhoni will have rest.
 *
 * SAMPLE INPUT
 * 4
 * 1 3 2 0
 *
 * SAMPLE OUTPUT
 * 2
 *
 * Explanation
 * In the first test case Dhoni can go to academy on the day number 1 and go for driving on the day number 3.
 * Thus, he will have a rest for only 2 days
 *
 */
public class DhoniAndHolidays {

    static int numChar;
    static int curChar;
    static byte[] buffer = new byte[1024];
    static InputStream stream;
    static PrintWriter out;
    static int restCount = 0;

    public static void main(String[] args) throws IOException {
        stream = System.in;
        out = new PrintWriter(System.out);

        int n = readInt();
        int a[] = new int[n];
        for (int i=0; i<n; i++) {
            a[i] = readInt();
        }

        fmr(0, a, n);
        out.println(restCount);

        out.flush();
        out.close();
    }

    private static int fmr(int i, int a[], int n) {

        //Base Case
        if (i == n-1) {
            if (a[i] == 0)
                restCount++;
            return a[i];
        }

        int prev = fmr(i+1, a, n);
        if ((a[i] == 0) || (prev != 3 && prev == a[i])) {
            restCount++;
            return 0;
        } else if (prev != 3 && prev != 0 && a[i] == 3) {
            return (prev == 1) ? 2 : 1;
        }
        return a[i];
    }

    //Method2 : Without recursive
    private static int fmr(int arr[], int n) {
        int count = 0;
        for (int i = 0; i < n - 1; i++) {
            if (arr[i] == 0) {
                count++;
            } else if (arr[i] == 1) {
                if (arr[i + 1] == 1) {
                    arr[i + 1] = 0;
                } else if (arr[i + 1] == 3) {
                    arr[i + 1] = 2;
                }
            } else if (arr[i] == 2) {
                if (arr[i + 1] == 2) {
                    arr[i + 1] = 0;
                } else if (arr[i + 1] == 3) {
                    arr[i + 1] = 1;
                }
            }
        }
        if (arr[n - 1] == 0) {
            count++;
        }
        return count;
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
