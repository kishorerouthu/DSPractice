package com.ds.practice.sort.quick.problems;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by kishore on 19/2/17.
 */
public class OneSizedGame {

    public static void main(String[] args) {
        PrintWriter out = new PrintWriter(System.out);
        InputReader in = new InputReader(System.in);

        int t = in.readInt();
        for (int i = 0; i < t; i++) {
            int n = in.readInt();
            List<Long> list = new ArrayList<Long>(n);
            for (int j = 0; j < n; j++) {
                list.add(in.readLong());
            }

            Collections.sort(list);
            Long a[] = new Long[n];
            list.toArray(a);

            while(true) {
                for (int l = 0; l < a.length; l++) {
                    if (a[l] < 1)
                        list.remove(a[l]);
                    else
                        list.set(l, a[l]- 1);
                }

                if(list.size() == 1 || list.size() == 0)
                    break;

                a = new Long[list.size()];
                list.toArray(a);
            }
        }
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

        a[r] = a[i+1];
        a[i+1] = p;

        return i+1;
    }

    static final class InputReader {

        private final InputStream stream;
        private final byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        private int read() throws IOException {
            if (curChar >= numChars) {
                curChar = 0;
                numChars = stream.read(buf);
                if (numChars <= 0) {
                    return -1;
                }
            }
            return buf[curChar++];
        }

        public final int readInt() {
            return (int) readLong();
        }

        public final long readLong() {
            int c = 0;
            try {
                c = read();
            } catch (IOException ex) {
            }
            while (isSpaceChar(c)) {
                try {
                    c = read();
                } catch (IOException ex) {
                }
            }
            boolean negative = false;
            if (c == '-') {
                negative = true;
                try {
                    c = read();
                } catch (IOException ex) {
                }
            }
            long res = 0;
            do {
                if (c < '0' || c > '9');
                res *= 10;
                res += (c - '0');
                try {
                    c = read();
                } catch (IOException ex) {
                }
            } while (!isSpaceChar(c));
            return negative ? (-res) : (res);
        }

        public final String readString() {
            int c = 0;
            try {
                c = read();
            } catch (IOException ex) {
            }
            while (isSpaceChar(c)) {
                try {
                    c = read();
                } catch (IOException ex) {
                }
            }
            StringBuilder res = new StringBuilder();
            do {
                res.append((char) c);
                try {
                    c = read();
                } catch (IOException ex) {
                }
            } while (!isSpaceChar(c));
            return res.toString();
        }

        private boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }
    }
}
