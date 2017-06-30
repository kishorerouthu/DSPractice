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
public class SpecialNumber {


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
            int n = readInt();
            out.println(specialNumber(n));

            t--;
        }

        out.flush();
        out.close();
    }

    private static int foo(int n) {
        int ret = 0;
        for (int i = 1; i <= n; i++)
            if (isCoPrime(n, i))
                ret++;

        return ret;
    }

    private static boolean isCoPrime(int a, int b) {
        if (coPrime.contains(a + "$" + b) || coPrime.contains(b + "$" + a))
            return true;
        if (gcd(a, b) == 1) {
            coPrime.add(a + "$" + b);
            coPrime.add(b + "$" + a);
            return true;
        }
        return false;
    }

    private static int specialNumber(int n) {
        int ret = 0;
        for (int i = 1; i <= n; i++)
            if (n % i == 0)
                ret += foo(i);

        return ret;
    }


    public static int gcd(int a, int b) {
        String key1 = a + "$" + b;
        String key2 = b + "$" + a;

        if (gcdMap.containsKey(key1))
            return gcdMap.get(key1);

        if (gcdMap.containsKey(key2))
            return gcdMap.get(key2);

        if(b == 0){
            return a;
        }
        int gcd = gcd(b, a%b);
        gcdMap.put(key1, gcd);
        gcdMap.put(key2, gcd);
        return gcd;
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
