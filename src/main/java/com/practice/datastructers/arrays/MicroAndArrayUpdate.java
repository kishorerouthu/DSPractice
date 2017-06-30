package com.practice.datastructers.arrays;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

/**
 * Created by Kishore Routhu on 17/6/17 1:21 PM.
 *
 * Micro purchased an array A having N integer values. After playing it for a while,
 * he got bored of it and decided to update value of its element.
 *
 * In one second he can increase value of each array element by 1.
 * He wants each array element's value to become greater than or equal to K.
 * Please help Micro to find out the minimum amount of time it will take, for him to do so.
 *
 * Input:
 * First line consists of a single integer, T, denoting the number of test cases.
 * First line of each test case consists of two space separated integers denoting N and K.
 *
 * Second line of each test case consists of N space separated integers denoting the array A.
 *
 * Output:
 * For each test case, print the minimum time in which all array elements will become greater than or equal to K.
 * Print a new line after each test case.
 *
 * Constraints:
 * 1≤T≤5
 * 1≤N≤10^5
 * 1≤A[i],K≤10^6
 *
 * SAMPLE INPUT
 * 2
 * 3 4
 * 1 2 5
 * 3 2
 * 2 5 5
 *
 * SAMPLE OUTPUT
 * 3
 * 0
 *
 */
public class MicroAndArrayUpdate {

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
            int k = readInt();
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                int ai = readInt();
                min = (ai < min) ? ai : min;
            }

            out.println((k - min) < 0? 0 : (k - min));

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

    private static byte readByte() throws IOException {
        return (byte) readInt();
    }

    private static int readInt() throws IOException, InputMismatchException {
        return (int) readLong();
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

    private static boolean isSpaceChar(int c) {
        return c == ' ' || c == '\n' || c == '\t' || c == '\r' || c == -1;
    }
}
