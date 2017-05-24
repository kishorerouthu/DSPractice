package com.practice.algorithms.sort.quick.problems;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

/**
 * Created by kishore on 17/2/17.
 */
public class KingsRace {

    public static void main(String[] args) {
        PrintWriter out = new PrintWriter(System.out);
        InputReader in = new InputReader(System.in);

        int t = in.readInt();
        long s[];
        long h[];
        for (int i = 0; i < t; i++) {
            int n = in.readInt(); //strengths
            int k = in.readInt(); //hurdles

            s = new long[n];
            for (int j = 0; j < n; j++)
                s[j] = in.readLong();

            h = new long[k];
            for (int l = 0; l < k; l++)
                h[l] = in.readLong();

            sort(s, 0, n - 1);
            sort(h, 0, k - 1);

            int max = 0;
            int winner = 0;
            int m = n-1;
            int l = k-1;
            while (m > -1 && l > -1) {
                if(h[l] <= s[m]) {
                    if (max < l || max == l) {
                        max = l;
                        winner = m;
                    } else if(max > l) {
                        break;
                    }
                    m--;
                } else {
                    l--;
                }
            }

            out.println(winner);

        }

        out.flush();
        out.close();
    }

        private static void sort(long a[], int l, int r) {
            if (l < r) {
                int p = partition(a, l, r);
                sort(a, l, p-1);
                sort(a, p+1, r);
            }
        }

        private static int partition(long a[], int l, int r) {

            int i = l-1;
            int j = l;
            long p = a[r];

            while (j < r) {

                if (a[j] < p) {
                    i++;

                    long temp = a[j];
                    a[j] = a[i];
                    a[i] = temp;
                }

                j++;
            }

            long temp = a[r];
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
        public final long readLong() {
            int c = 0;
            c = read();

            while (isSpaceChar(c)) {
                c = read();
            }
            boolean negative = false;
            if (c == '-') {
                negative = true;
                c = read();
            }
            long res = 0;
            do {
                if (c < '0' || c > '9');
                res *= 10;
                res += (c - '0');
                c = read();
            } while (!isSpaceChar(c));
            return negative ? (-res) : (res);
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
