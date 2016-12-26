package com.ds.practice.sort.bubble.problems;

import java.io.*;
import java.util.InputMismatchException;

/**
 * Created by Kishore Routhu on 13/11/16 9:41 PM.
 */
public class BennyAndSegments {

    static int numChar;
    static int curChar;
    static byte[] buffer = new byte[1024];
    static InputStream stream;
    static PrintWriter out;

    public static void main(String[] args) throws IOException {
        stream = System.in;
        out = new PrintWriter(new BufferedOutputStream(System.out));
        StringBuilder builder = new StringBuilder();
        int T = readInt();
        for (int i = 0; i < T; i++) {
            long N = readLong();
            long L = readLong();
            long first = 0;
            long last = 0;
            boolean found = false;
            for (int j = 0; j < N; j++) {
                long xl = readLong();
                long xr = readLong();

                if(first == 0 || (last != 0 && last != xl)) {
                    first = xl;
                }
                last = xr;
                if (!found && ((last - first) >= L)) {
                    found = true;
                }
            }
            if (i != 0)
                builder.append("\n");

            if (found)
                builder.append("Yes");
            else
                builder.append("No");
        }

        stream.close();
        out.print(builder);
        out.flush();
        out.close();
    }

    public static int read() throws IOException {
        if (numChar <= curChar) {
            curChar = 0;
            numChar = stream.read(buffer);
            if (numChar <= 0) {
                return -1;
            }
        }
        return buffer[curChar++];
    }

    public static long readLong() throws IOException, InputMismatchException {
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

    public static int readInt() throws IOException, InputMismatchException {
        return (int) readLong();
    }

    public static boolean isSpaceChar(int c) {
        return c == ' ' || c == '\n' || c == '\t' || c == '\r' || c == -1;
    }
}
