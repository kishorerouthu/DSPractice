package com.css.ds.practice.datastructers.arrays;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

/**
 * Created by Kishore Routhu on 17/6/17 12:45 PM.
 *
 * Arijit is a brilliant boy. He likes memory games.
 * He likes to participate alone but this time he has to have a partner.
 * So he chooses you.
 *
 * In this Game , your team will be shown N numbers for few minutes .
 * You will have to memorize these numbers.
 *
 * Now, the questioner will ask you Q queries, in each query He will give you a number ,
 * and you have to tell him the total number of occurrences of that number in the array
 * of numbers shown to your team . If the number is not present ,
 * then you will have to say “NOT PRESENT” (without quotes).
 *
 * INPUT And OUTPUT
 *
 * The first line of input will contain N, an integer, which is the total number of numbers shown to your team.
 *
 * The second line of input contains N space separated integers .
 *
 * The third line of input contains an integer Q , denoting the total number of integers.
 *
 * The Next Q lines will contain an integer denoting an integer,
 * Bi , for which you have to print the number of occurrences of that number (Bi) in those N numbers on a new line.
 *
 * If the number Bi isn’t present then Print “NOT PRESENT” (without quotes) on a new line.
 *
 * CONSTRAINTS
 * 1≤N≤10^5
 * 0≤Bi≤1000
 * 1≤Q≤10^5
 *
 * SAMPLE INPUT
 * 6
 * 1 1 1 2 2 0
 * 6
 * 1
 * 2
 * 1
 * 0
 * 3
 * 4
 *
 * SAMPLE OUTPUT
 *
 * 3
 * 2
 * 3
 * 1
 * NOT PRESENT
 * NOT PRESENT
 */

public class MemoriseMe {

    static int numChar;
    static int curChar;
    static byte[] buffer = new byte[1024];
    static InputStream stream;
    static PrintWriter out;

    public static void main(String[] args) throws IOException {

        stream = System.in;
        out = new PrintWriter(System.out);

        int n = readInt();
        Map<Integer, Integer> lookup = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            int v = readInt();
            Integer count = lookup.get(v);
            count = count == null ? 0 : count;
            count++;
            lookup.put(v, count);
        }

        int q = readInt();
        while (q > 0) {
            int qi = readInt();
            Integer v = lookup.get(qi);
            out.println(v == null? "NOT PRESENT" : v);
            q--;
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

    private static byte readByte() throws IOException {
        return (byte) readInt();
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
