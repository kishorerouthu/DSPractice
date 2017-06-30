package com.fights;

/**
 * Created by Kishore Routhu on 8/6/17 8:03 PM.
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.InputMismatchException;

public class MonkGCD {

    static int numChar;
    static int curChar;
    static byte[] buffer = new byte[1024];
    static InputStream stream;
    static PrintWriter out;

    private static final long DIVISOR = 1000000007;

    public static void main(String[] args) throws IOException {

        stream = System.in;
        out = new PrintWriter(System.out);

        int n = readInt();
        int a[] = new int[n];
        long prod = 1l;
        for (int i = 0; i < n; i++) {
            a[i] = readInt();
            prod = (prod * a[i]) % DIVISOR;
        }

        //find gcd of all
        int gcd = getGCD(a, n);
        double res = Math.pow(prod, gcd) % DIVISOR;
        DecimalFormat df = new DecimalFormat("##");
        out.println(df.format(res));

        out.flush();
        out.close();
    }

    private static int getGCD(int a, int b) {
        if(b == 0){
            return a;
        }
        return getGCD(b, a%b);
    }

    private static int getGCD(int[] a, int n) {
        // the GCD of a number with itself is... itself
        int gcd = a[0];

        // compute incrementally
        for( int i=1; i<n; i++ ) {
            gcd = getGCD( gcd, a[i] );
        }

        // return result
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

    private static String readLine0() throws IOException {
        StringBuilder buf = new StringBuilder ();
        int c = read ();
        while (c != '\n' && c != -1){
            if (c != '\r'){
                buf.appendCodePoint (c);
            }
            c = read ();
        }
        return buf.toString ();
    }

    private static boolean isSpaceChar(int c) {
        return c == ' ' || c == '\n' || c == '\t' || c == '\r' || c == -1;
    }
}
