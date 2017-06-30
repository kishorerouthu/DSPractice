package com.fights;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

/**
 * Created by Kishore Routhu on 7/6/17 7:59 PM.
 */
public class Dinosaurs {

    static int numChar;
    static int curChar;
    static byte[] buffer = new byte[1024];
    static InputStream stream;
    static PrintWriter out;

    public static void main(String[] args) throws IOException {

        stream = System.in;
        out = new PrintWriter(System.out);

        int n = readInt();
        int k = readInt();
        int l = readInt();
        int q = readInt();

        Map<String, String> dinosaurs = new HashMap<String, String>();
        for (int i = 0; i < n; i++) {
            String line[] = readLine0().split(" ");
            int a[] =  new int[3];
            a[0] = Integer.parseInt(line[1]);
            a[1] = Integer.parseInt(line[2]);
            a[2] = Integer.parseInt(line[3]);
            Arrays.sort(a);
            String key = a[0] + "$" + a[1] + "$" + a[2];
            dinosaurs.put(key, line[0]);
        }

        for (int i = 0; i < q; i++) {
            String line[] = readLine0().split(" ");
            int a[] =  new int[3];
            a[0] = Integer.parseInt(line[0]);
            a[1] = Integer.parseInt(line[1]);
            a[2] = Integer.parseInt(line[2]);
            Arrays.sort(a);
            String key = a[0] + "$" + a[1] + "$" + a[2];
            if (dinosaurs.containsKey(key))
                out.println(dinosaurs.get(key));
            else
                out.println("You cant fool me :P");
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
