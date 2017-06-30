package com.css.ds.fights;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;

/**
 * Created by kishore on 22/5/17.
 */
public class PrimePythagorian {

    static int numChar;
    static int curChar;
    static byte[] buffer = new byte[1024];
    static InputStream stream;
    static PrintWriter out;
    private static List<String> coPrime = new ArrayList<String>();
    private static HashMap<String, Integer> gcdMap = new HashMap<String, Integer>();

    public static void main(String[] args) throws IOException {

        stream = System.in;
        out = new PrintWriter(System.out);

        int t = readInt();
        while (t > 0) {
            int a = readInt();
            int b = readInt();
            int c = readInt();

            out.println(gcd(a, b, c) == 1? "YES" : "NO");
            t--;
        }

        out.flush();
        out.close();
    }

    private static int gcd(int a, int b, int c) {
        int limit = Math.min(a, b);
        limit = Math.min(limit, c);
        for (int n = limit; n >= 2; n--) {
            if ((a % n == 0) && (b % n == 0) && (c % n == 0)) {
                return n;
            }
        }

        return 1;
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
