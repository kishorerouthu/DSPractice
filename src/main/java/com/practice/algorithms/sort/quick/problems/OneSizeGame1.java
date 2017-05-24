package com.practice.algorithms.sort.quick.problems;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

/**
 * Created by kishore on 27/2/17.
 */
public class OneSizeGame1 {

    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();

        PrintWriter out = new PrintWriter(System.out);
        InputReader in = new InputReader(System.in);

        int t = in.readInt();
        for (int i = 0; i < t; i++) {
            int n = in.readInt();
            int a[] = new int[n];

            int min_value = Integer.MAX_VALUE;
            int min_index = 0;
            int totalNeg = 0;
            for (int j = 0; j < n; j++) {
                a[j] = in.readInt();
                if (a[j] > -1) {
                    if (min_value > a[j]) {
                        min_value = a[j];
                        min_index = j + 1;
                    }
                } else
                    totalNeg++;
            }

            int indexCount = min_index;
            boolean removed[] = new boolean[n];
            do {
                min_value = Integer.MAX_VALUE;
                for (int k = 0; k < n; k++) {
                    int value = a[k] - indexCount;
                    if (removed[k])
                        continue;
                    if (value < 0) {
                        removed[k] = true;
                        totalNeg++;
                    } else if (value < min_value) {
                        min_index = k - totalNeg + 1;
                        min_value = a[k] - indexCount;
                    }
                }
                indexCount += min_index;
            } while (totalNeg != n-1 && totalNeg != n);

            out.println(totalNeg == n-1 ? "Ladia" : "Kushagra");
        }

        out.flush();
        out.close();

        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
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
