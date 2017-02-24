package com.ds.practice.sort.quick.problems;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

/**
 * Created by kishore on 17/2/17.
 */
public class PromNight {

    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int t = in.readInt();
        for (int i = 0; i < t; i++) {
            int m = in.readInt();
            int n = in.readInt();

            if (m > n) {
                out.println("NO");
                continue;
            }

            int boys[] = new int[m];
            for (int j = 0; j < m; j++) {
                boys[j] = in.readInt();
            }
            sort(boys, 0, m-1);

            int girls[] = new int[n];
            for (int k = 0; k < n; k++) {
                girls[k] = in.readInt();
            }
            sort(girls, 0, n-1);

            String result = "YES";
            for (int l = 0; l < m; l++) {
                if (boys[l] <= girls[l]) {
                    result = "NO";
                    break;
                }
            }

            out.println(result);
        }

        out.flush();
        out.close();
    }


    private static void sort(int a[], int l, int r) {
        if (l < r) {
            int p = parition(a, l ,r);
            sort(a, l, p-1);
            sort(a, p+1, r);
        }
    }

    private static int parition(int a[], int l, int r) {

        int i = l-1;
        int j = l;
        int p = a[r];

        while (j < r) {

            if (a[j] < p) {
                i++;
                int temp = a[j];
                a[j] = a[i];
                a[i] = temp;
            }
            j++;
        }

        //Place pivot at right position i+1
        a[r] = a[i+1];
        a[i+1] = p;
        return i+1;

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
