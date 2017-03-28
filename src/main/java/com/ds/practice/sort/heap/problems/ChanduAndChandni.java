package com.ds.practice.sort.heap.problems;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

/**
 * Created by kishore on 8/3/17.
 *
 * Chandu and chandni Talk on phone for a long time daily.
 * Being afraid that someone will hear their private conversation chandu suggested chandni an idea.
 * He suggested that he will talk only with encrypted strings with her and only she would know,
 * how to decrypt the string. So that even if someone hears, He/She would not be able to anticipate their conversation.
 *
 * Rules of encryption are as follows:
 * 1. String on length N is assumed to be cyclic consisting of lower case English alphabets.
 *
 * 2. In each iteration, we pick the last character and put it in starting of the string.
 *    For example: april performing iterations and collecting each string formed in a set until we get the original string.
 *    Ex: {april,lapri, ilapr, rilap, prila}
 *
 * 3. sort the set of string in lexicographically reverse order. Ex: {rilap, prila,lapri, ilapr, april }
 * 4. Taking the last character of each string in the set is the encrypted string. Ex: pairl
 *
 * Chandu also sends the position(K) of first letter in encrypted string from original string
 * i.e 2 (p is on position 2 in original string and is the first character of encrypted string)
 *
 * Now, Chandni is ofcourse not that brilliant to decrypt the strings in real time and understand what chandu is saying.
 * So, chandu decided to write a program for the same.
 *
 * Help chandu write this program.
 *
 * Input:
 * First line contains an integer t, which is the number of test cases.
 * Next t lines contain a encrypted string and K as decribed above.
 *
 * Output:
 * Print the decrypted string for each test case.
 *
 * Constraints:
 * 1<=t<=1000
 * 1<=length of string<=10000
 * 1<=k<=length of string
 *
 * SAMPLE INPUT
 * 2
 * d 1
 * pairl 2
 *
 * SAMPLE OUTPUT
 * d
 * april
 *
 */
public class ChanduAndChandni {

    static int numChar;
    static int curChar;
    static byte[] buffer = new byte[1024];
    static InputStream stream;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {

            stream = System.in;
            out = new PrintWriter(System.out);

            int t = readInt();
            while (t-- != 0) {
                char input[] = readString().toCharArray();
                int n = readInt() - 1;

                Swift sw[] = new Swift[input.length];
                for (int i = 0; i < input.length; i++) {
                    Swift swift = new Swift();
                    swift.ch = input[i];
                    swift.index = i;

                    sw[i] = swift;
                }

                Arrays.sort(sw);
                char output[] = new char[input.length];
                output[0] = input[n];
                if (input.length > 1) {
                    output[1] = sw[n].ch;
                    int i = 2;
                    int j = sw[n].index;
                    while (i < input.length) {
                        output[i++] = sw[j].ch;
                        j = sw[j].index;
                    }
                }

                out.println(new String(output));

            }
        out.flush();
        out.close();
    }

    private static class Swift implements Comparable<Swift> {

        char ch;
        int index;

        public int compareTo(Swift o) {
            if (this.ch > o.ch) {
                return -1;
            } else if(this.ch < o.ch) {
                return 1;
            } else {
                if (o.index > this.index) {
                    return -1;
                } else {
                    return 1;
                }
            }
        }
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

    public static String readString() throws IOException {
        int c = read();
        if (c == -1)
            throw new IOException();
        while (isSpaceChar(c)) {
            c = read();
        }
        StringBuilder builder = new StringBuilder();
        while (!isSpaceChar(c)) {
            builder.append((char) c);
            c = read();
        }
        return builder.toString();
    }

    public static boolean isSpaceChar(int c) {
        return c == ' ' || c == '\n' || c == '\t' || c == '\r' || c == -1;
    }
}
