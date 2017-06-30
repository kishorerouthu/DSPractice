package com.css.ds.practice.algorithms.sort.merge.problems;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

/**
 * Created by kishore on 6/2/17.
 */
public class InnocentSwaps {

    public static void main(String[] args) {
        PrintWriter out = new PrintWriter(System.out);
        Partitioning.InputReader in = new Partitioning.InputReader(System.in);

        int t = in.readInt();
        for (int i = 0; i < t; i++) {
            long n = in.readLong();
            long k = in.readLong();

            long ncr = ncr(n, k);
            out.println((ncr * (k*2))%1000000007);
        }
        out.flush();
        out.close();
    }

    private static long ncr(long n, long k) {
        return fact(n) / (fact(k) * fact(n-k));
    }

    private static long fact(long n) {
        long mul = 1;
        while (n > 0) {
            mul *= n--;
        }
        return mul;
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

        private boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }
    }
}
