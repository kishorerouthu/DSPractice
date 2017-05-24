package com.practice.algorithms.sort.quick.problems;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

/**
 * Created by kishore on 17/5/17
 *
 * Given a set of strings print the set sorted according to their size.
 * If the size of the strings are equal, must maintain the original order of the set.
 *
 * Input :
 * The first line of input has an integer T that indicates the number of sets of strings,
 * each set may contain between 1 and 50 inclusive elements,
 * and each of the strings of the set may contain between 1 and 50 inclusive characters('a' to 'z').
 *
 * Output:
 * The output should contain the set of input strings ordered by the length of strings.
 * A blank space must be printed between two words.
 *
 * SAMPLE INPUT
 * 3
 * ab cd e j asd ljffg df
 * a a b b c c
 * xy yx zxy zx xzy xxx
 *
 * SAMPLE OUTPUT
 * e j ab cd df asd ljffg
 * a a b b c c
 * xy yx zx zxy xzy xxx
 *
 */
public class IThinkItsEasy {

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
            String str = readLine0();
            String s[] = str.split(" ");
            quickSort(s, 0, s.length - 1);

            for (String s1 : s)
                out.print(s1 + " ");
            out.println();

            t--;
        }

        out.flush();
        out.close();
    }


    private static void quickSort(String s[], int l, int r) {
        if (l < r) {
            int p = partition(s, l, r);
            quickSort(s, l, p-1);
            quickSort(s, p+1, r);
        }
    }

    private static int partition(String s[], int p, int r) {
        int i = p-1;
        String x = s[r];
        for (int j = p; j < r; j++) {
            if (s[j].length() < x.length()) {
                i++;
                //swap i with j
                String temp = s[i];
                s[i] = s[j];
                s[j] = temp;
            }
        }

        //swap x with i+1;
        s[r] = s[i+1];
        s[i+1] = x;
        return i + 1;
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
