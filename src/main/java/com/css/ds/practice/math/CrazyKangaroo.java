package com.css.ds.practice.math;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

/**
 * Created by Kishore Routhu on 24/5/17 6:32 PM.
 *
 * Little Jhool is a world renowned kangaroo trainer. He's now living in Australia,
 * and is training kangaroos for his research project on mobile soccer. (We don't know the connection, too.)
 * Anyway, for the project to be completed he observes kangaroos for a lot of time -
 * because he wants to figure out the hop count for various kangaroos he's training.
 *
 * Now, he makes a kangaroo stand at the starting point, and lets him jump to the finishing point -
 * given the hop count of that particular kangaroo,
 * figure out the number of jumps he would take between the starting point to the ending point.
 * Both the starting point and the ending points are inclusive.
 *
 * Note: He will jump only to those positions which are multiples of M or hop count.
 *
 * Input:
 * First line contains number of test cases T. Next T lines contains three integers A, B and M separated by single space.
 * A denoted the starting point, B the finishing point - and M, the hop count - the distance covered by that kangaroo in one jump.
 *
 * Output:
 * For each test case print the number of jumps the kangaroo had to make in the range [A, B] inclusive.
 *
 * Constraints:
 * 1<=T<=100000
 * 1<=A<=B<=1012
 * 1<=M<=1012
 *
 * SAMPLE INPUT
 * 3
 * 1 10 2
 * 5 10 3
 * 7 9 5
 *
 * SAMPLE OUTPUT
 * 5
 * 2
 * 0
 * Explanation
 * Test Case #1:
 * There are 5 multiples of 2 that are {2,4,6,8,10} in range [1,10] .
 *
 * Test Case#2:
 * There are 2 multiples of 3 that are {6,9} in range [5,10] .
 *
 * Test Case#3: There are no any multiple of 5 is there in range [7,9].
 */
public class CrazyKangaroo {

    static int numChar;
    static int curChar;
    static byte[] buffer = new byte[1024];
    static InputStream stream;
    static PrintWriter out;

    public static void main(String[] args) throws IOException {

        stream = System.in;
        out = new PrintWriter(System.out);

        int t = readInt();
        for (int i=0; i<t; i++) {
            long a = readInt();
            long b = readLong();
            long m = readLong();

          /*  if (b%m != 0)
                b = b - (b%m);

            if (a%m != 0)
                a = a + (m - a%m);

            long j = ((b - a) / m ) + 1;*/

            long a1 = a/m;
            long b1 = b/m;
            long j = b1 - a1;

            if (a%m == 0)  j++;
            out.println(j);
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
