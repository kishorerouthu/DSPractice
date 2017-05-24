package com.practice.algorithms.search.linear.problems;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

/**
 * Created by kishore on 15/5/17.
 *
 * Manna is extremely disappointed to find out that no one in his college knows his first name.
 * Even his classmates call him only by his last name.
 * Frustrated, he decides to make his fellow college students know his first name by forcing them to solve this question.
 *
 * You are given a long string as input in each testcase, containing any ASCII character.
 * Your task is to find out the number of times SUVO and SUVOJIT appears in it.
 *
 * Note: This problem must be solved in C language only.
 *
 * Input Format
 * The first line contains the number of testcases, T. Next, T lines follow each containing a long string S.
 *
 * Output Format
 * For each long string S, display the no. of times SUVO and SUVOJIT appears in it.
 *
 * Constraints
 * 1 <= T <= 100
 * 1 <= Length of each string <= 150
 *
 * SAMPLE INPUT
 * 3
 * SUVOJITSU
 * 651SUVOMN
 * $$$$$SUVOSUVOJIT$$$$$
 *
 * SAMPLE OUTPUT
 * SUVO = 0, SUVOJIT = 1
 * SUVO = 1, SUVOJIT = 0
 * SUVO = 1, SUVOJIT = 1
 *
 */
public class MannasFirstName {

    static int numChar;
    static int curChar;
    static byte[] buffer = new byte[1024];
    static InputStream stream;
    static PrintWriter out;

    public static void main(String[] args) throws IOException {

        stream = System.in;
        out = new PrintWriter(System.out);

        int t = readInt();
        while (t>0) {
            String s = readLine0();
            int svCount = 0;
            int svJtCount = 0;
            int sv_index = s.indexOf("SUVO");
            int svjt_index = s.indexOf("SUVOJIT");

            while (sv_index != -1 || svjt_index != -1) {

                if (sv_index != -1 && svjt_index != -1 && sv_index == svjt_index)
                    svJtCount++;
                else if (svjt_index != -1)
                    svJtCount++;
                else if (sv_index != -1)
                    svCount++;

                if (svjt_index != -1 && svjt_index + 7 < s.length()) {
                    sv_index = s.indexOf("SUVO", svjt_index+7);
                    svjt_index = s.indexOf("SUVOJIT", svjt_index+7);
                } else if (sv_index != -1 && sv_index + 4 < s.length()) {
                    sv_index = s.indexOf("SUVO", sv_index+4);
                    svjt_index = s.indexOf("SUVOJIT", sv_index+4);
                }
            }

            out.println("SUVO = "+ svCount +", SUVOJIT = "+svJtCount);

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
