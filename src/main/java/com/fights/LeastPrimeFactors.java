package com.fights;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

/**
 * Created by kishore on 17/5/17.
 */
public class LeastPrimeFactors {

    static int numChar;
    static int curChar;
    static byte[] buffer = new byte[1024];
    static InputStream stream;
    static PrintWriter out;

    public static void main(String[] args) throws IOException {

        stream = System.in;
        out = new PrintWriter(System.out);

        Map<Integer, Integer> lookup = new HashMap<Integer, Integer>();
        int n = readInt();
        while (n > 0) {
            int num = readInt();
            if (!lookup.containsKey(num))
                lookup.put(num, leastPrimeFactor(num));

            out.println(num - lookup.get(num));

            n--;
        }

        out.flush();
        out.close();
    }

    private static int leastPrimeFactor(int n) {
        if (isPrime(n))
            return n;

        for (int i = 2; i<n; i++) {
            if (isPrime(i) && n % i == 0)
                return i;
        }

        return n;
    }

    private static boolean isPrime(int n) {
        if (n == 2)
            return true;

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0)
                return false;
        }
        return true;
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
