package com.css.ds.practice.algorithms.sort.quick.problems;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

/**
 * Created by kishore on 16/2/17.
 */
public class GrovyleString {

    public static void main(String[] args) {

        PrintWriter out = new PrintWriter(System.out);
        InputReader in = new InputReader(System.in);

        int t = in.readInt();
        for (int i = 0; i < t; i++) {
            String s = in.readString();
            char[] a = s.toCharArray();
            int n = a.length;
            sort(a, 0, n/2-1, false);
            sort(a, n/2, n-1, true);
            out.println(new String(a));
        }

        out.flush();
        out.close();
    }

    private static void sort(char a[], int l, int r, boolean reverse) {
        if (l < r) {
            int p = partition(a, l, r, reverse);
            sort(a, l, p-1, reverse);
            sort(a, p+1, r, reverse);
        }
    }

    private static int partition(char a[], int l, int r, boolean reverse) {

        int p = a[r];
        int i = l-1;
        int j = l;
        while (j < r) {

            if(reverse) {
                if (a[j] > p) {
                    i++;
                    char temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            } else if (a[j] < p) {
                i++;
                char temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
            j++;
        }

        char temp = a[r];
        a[r] = a[i+1];
        a[i+1] = temp;

        return i + 1;
    }

    static class InputReader {

        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;
        private InputReader.SpaceCharFilter filter;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        public int read() {
            if (numChars == -1)
                throw new InputMismatchException();
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0)
                    return -1;
            }
            return buf[curChar++];
        }

        public int readInt() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public String readString() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (!isSpaceChar(c));
            return res.toString();
        }

        public boolean isSpaceChar(int c) {
            if (filter != null)
                return filter.isSpaceChar(c);
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        public String next() {
            return readString();
        }

        public interface SpaceCharFilter {
            public boolean isSpaceChar(int ch);
        }
    }
}
