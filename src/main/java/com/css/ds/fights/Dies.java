package com.css.ds.fights;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.InputMismatchException;

/**
 * Created by kishore on 18/5/17.
 */
public class Dies {


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
            int m = readInt();
            if (m == n) {
                out.println("0.00");
                t--;
                continue;
            }

            double result = m;
            if (m == 0)
                m = n;

            while (m > 0) {
                result += (0.5 * 6);
                --m;
            }
            DecimalFormat df = new DecimalFormat("####0.00");
            out.println(df.format(result));

            t--;
        }

        out.flush();
        out.close();
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
