package com.css.ds.practice.miscellaneous;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

/**
 * Created by kishore on 1/3/17.
 */
public class RaghuVsSayan {

    public static void main1(String[] args) {
        InputReader in = new InputReader(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int t = in.readInt();
        for (int i = 0; i < t; i++) {
            long a = in.readLong();
            long b = in.readLong();

            int n = in.readInt(); //number of dishes
            long d[] = new long[n]; // calories of dishes

            for (int j = 0; j < n; j++)
                d[j] = in.readInt();

            sort(d, 0, n-1);

            for (int j = 1; j < n; j++)
                d[j] += d[j-1];

            int rd = search(d, n, a);
            int sd = search(d, n, b);
            out.println(rd > sd ? "Raghu Won" : rd < sd ? "Sayan Won" : "Tie");
        }

        out.flush();
        out.close();
    }

    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int t = in.readInt();
        for (int i = 0; i < t; i++) {
            long a = in.readLong();
            long b = in.readLong();

            int n = in.readInt(); //number of dishes
            long d[] = new long[n]; // calories of dishes

            for (int j = 0; j < n; j++)
                d[j] = in.readInt();

            sort(d, 0, n-1);

            int ans = 0;
            int sum = 0;
            for (int j = 0; j < n; j++) {
                sum += d[j];

                if (sum > a && sum < b) {
                    ans = 1; break;
                }

                if (sum < a && sum > b) {
                    ans = 2; break;
                }
            }

            out.println(ans == 2 ? "Raghu Won" : ans == 1 ? "Sayan Won" : "Tie");
        }

        out.flush();
        out.close();
    }

    private static void sort(long a[], int l, int r) {
        if (l < r) {
            int m = (r + l) >> 1;
            sort(a, l, m);
            sort(a, m+1, r);
            merge(a, l, m, r);
        }
    }

    private static void merge(long a[], int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        long left[] = new long[n1];
        long right[] = new long[n2];

        for (int i = 0; i < n1; i++)
            left[i] = a[l + i];

        for (int j = 0; j < n2; j++)
            right[j] = a[m + j + 1];

        int i = 0;
        int j = 0;
        int k = l;

        while (i < n1 && j < n2)
            if (left[i] <= right[j])
                a[k++] = left[i++];
            else
                a[k++] = right[j++];


        while (i < n1)
            a[k++] = left[i++];


        while (j < n2)
            a[k++] = right[j++];
    }

    private static int search(long a[], int n, long x) {

        int l = 0;
        int r = n-1;
        int m = 0;

        while (l < r) {
            m = (l + r) >> 1;

            if (a[m] <= x)
                l = m+1;
            else
                r = m;
        }

        return a[l] > x ? l-1 : l;
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
