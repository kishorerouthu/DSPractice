package com.css.ds.practice.algorithms.dp.problems;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by kishore on 28/4/17.
 */
public class PrimeNumbersAgain {

    private static List<Integer> prematicList = new ArrayList<Integer>();
    private static Map<Integer, Integer> lookup = new HashMap<Integer, Integer>();

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
            out.println(prematicSum(n));
            t--;
        }

        out.flush();
        out.close();
    }

    private static int prematicSum(int n) {
        if (lookup.containsKey(n))
            return lookup.get(n);

        if (!prematicList.contains(n) && isPrimatic(n))
            prematicList.add(n);

        //Base case if it is primatic then it can be fromed by itself
        if (prematicList.contains(n)) {
            lookup.put(n, 1);
            return 1;
        }

        int sum = 0;
        for (int i=sqrt(n); i>=2; i--) {
            if (n % i == 0) {
                sum = prematicSum(i) + prematicSum(n/i);
                break;
            }
        }

        lookup.put(n, sum);
        return sum;
    }

    private static int sqrt(int n) {
        return (int) Math.floor(Math.sqrt(n));
    }

    private static boolean isPrimatic(int n) {
        if(isPrime(n))
            return true;

        for (int i=sqrt(n); i>=2; i--) {
            if (Math.pow(i, i) == n)
                return true;
        }
        return false;
    }

    private static boolean isPrime(int n) {
        for (int i=sqrt(n); i>=2; i--) {
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
