package com.ds.practice.sort.counting.problems;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kishore on 28/2/17.
 *
 *Problem Statement
 *
 *Given an array A of N numbers, find the number of distinct pairs (i, j) such that j >=i and A[i] = A[j].
 *
 * First line of the input contains number of test cases T.
 * Each test case has two lines, first line is the number N, followed by a line consisting of N integers which are the elements of array A.
 *
 * For each test case print the number of distinct pairs.
 *
 * Constraints
 * 1 <= T <= 10
 * 1 <= N <= 10^6
 * -10^6 <= A[i] <= 10^6 for 0 <= i < N
 *
 * SAMPLE INPUT
 * 3
 * 4
 * 1 2 3 4
 * 3
 * 1 2 1
 * 5
 * 1 1 1 1 1
 *
 * SAMPLE OUTPUT
 * 4
 * 4
 * 15
 *
 *
 */
public class FindingPairs {

    public static void main(String[] args) {

        InputReader in = new InputReader(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int t = in.readInt();
        for (int i = 0; i < t; i++) {
            int n = in.readInt();
            Map<Integer, Integer> map = new HashMap<Integer, Integer>();
            for (int j = 0; j < n; j++) {
                int val = in.readInt();
                Integer count = map.get(val);
                if (count == null)
                    count = 0;
                map.put(val, ++count);
            }

            int total_count = 0;
            for(Integer count : map.values())
                total_count += possibleCounts(count);

            out.println(total_count);
        }

        out.flush();
        out.close();
    }

    private static int possibleCounts(int count) {
        int result = count;
        count--;
        result += ((count * (count+1)) / 2);
        return result;
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
