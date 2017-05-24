package com.practice.algorithms.dp.problems;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

/**
 * Created by kishore on 19/4/17.
 *
 * Soumika has a string S and its starting index is 1.
 * The string S consists of characters from 1−9.
 * As she is very intelligent, she wants to test his brother Vinay Tendulkar.
 * She asked her brother Vinay Tendulkar to count the number of even numbered characters ( i.e 2,4,6,8 ) for every index (1≤i≤|S|).
 * For an index i, the result should be calculated from i to the end of the string.
 * As Vinay doesn't know about programming, he wants you to help him find the solution.
 *
 * Input:
 * First line contains a string S.
 *
 * Output:
 * Print |S| space-separated integers,the result of every index.
 *
 * Constraints:
 * 1 ≤ |S| ≤ 10^4
 *
 * SAMPLE INPUT
 * 574674546476
 *
 * SAMPLE OUTPUT
 * 7 7 7 6 5 5 4 4 3 2 1 1
 *
 * Explanation
 * Given string S is 574674546476.
 * for index 1 Number of even numbers from 5 to end of the string is 7 so the result of index 1 is 7.
 * for index 2 Number of even numbers from 7 to end of the string is 7 so the result of index 2 is 7.
 * for index 3 Number of even numbers from 4 to end of the string is 7 so the result of index 3 is 7.
 * for index 3 Number of even numbers from 6 to end of the string is 6 so the result of index 4 is 6
 *
 */
public class IntelligentGirl {

    static int numChar;
    static int curChar;
    static byte[] buffer = new byte[1024];
    static InputStream stream;
    static PrintWriter out;

    public static void main(String[] args) throws IOException {

        stream = System.in;
        out = new PrintWriter(System.out);

        String s = readString();
        int n = s.length();
        char[] data = s.toCharArray();

        int counts[] = new int[n];
        int i;
        int count = 0;
        for (i=n-1; i>=0; i--) {
            byte ai = Byte.parseByte(data[i]+"");
            if (ai % 2 == 0)
                count++;
            counts[i] = count;
        }

        i=0;
        while (i<n)
            out.printf("%d ", counts[i++]);

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
