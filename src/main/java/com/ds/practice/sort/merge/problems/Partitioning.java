package com.ds.practice.sort.merge.problems;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

/**
 * Created by kishore on 6/2/17.
 */
public class Partitioning {


    public static void main(String[] args) {
        PrintWriter out = new PrintWriter(System.out);
        InputReader in = new InputReader(System.in);

        int n = in.readInt();
        int a[] = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            a[i] = in.readInt();
            sum += a[i];
        }

        double leftSum = 0;
        double rightSum = 0;
        for (int i = n-1; i >= 0; i--) {
            rightSum += a[i];
            leftSum = sum - rightSum;
            if (rightSum >= leftSum)
                break;
        }
        System.out.println("Left-Sum : " + leftSum);
        System.out.println("Right-Sum : " + rightSum);
        double div = leftSum / rightSum;
        out.print(Math.abs(1.0d - div));
        out.flush();
        out.close();
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
