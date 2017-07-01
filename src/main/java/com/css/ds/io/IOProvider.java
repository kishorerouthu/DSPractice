package com.css.ds.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

/**
 * This class provides a way to read and write i/o for all problem solvers.
 *
 * @author Kishore Routhu on 1/7/17 8:22 PM.
 */
public final class IOProvider {

    private int numChar;
    private int curChar;
    private byte[] buffer = new byte[1024];
    private InputStream in;
    private PrintWriter out;


    public static IOProvider getConsoleInstance() {
        return new IOProvider(System.in, new PrintWriter(System.out));
    }

    public static IOProvider getInstance(InputStream in, OutputStream out) {
        return new IOProvider(in, new PrintWriter(out));
    }

    private IOProvider(InputStream in, PrintWriter out) {
        this.in = in;
        this.out = out ;
    }

    public int read() throws IOException {
        if (numChar <= curChar) {
            curChar = 0;
            numChar = in.read(buffer);
            if (numChar <= 0) {
                return -1;
            }
        }
        return buffer[curChar++];
    }

    public byte readByte() throws IOException {
        return (byte) readInt();
    }

    public int readInt() throws IOException, InputMismatchException {
        return (int) readLong();
    }

    public long readLong() throws IOException, InputMismatchException {
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

    public boolean isSpaceChar(int c) {
        return c == ' ' || c == '\n' || c == '\t' || c == '\r' || c == -1;
    }

    public void println(String s) {
        out.println(s);
    }

    public void println(Object obj) {
        out.println(obj);
    }

    public void printf(String format, Object ... args) {
        out.printf(format, args);
    }



    public void finish() {
        out.close();
        out.flush();
    }

}
