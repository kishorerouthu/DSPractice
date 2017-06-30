package com.css.ds.practice.algorithms.sort.merge.problems;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by kishore on 28/1/17.
 *
 * There is a long list of n girls in front of Barney, and he is to calculate the optimal "happiness" he can find by selecting exactly 2 girls. (Why 2? * No one knows!)
 *
 * Ted, as a fan of pros and cons, suggests to make a list, a method for estimating the maximum happiness that Barney can achieve.
 *
 * Each girl is characterized by two parameters:
 *
 * - favour: if this girl is chosen, his happiness increases by this amount.
 * - anger: if this girl is not chosen, his happiness decreases by this amount.
 *
 * Find the maximum "happiness" that Barney can obtain. Note that the answer is allowed to be negative.
 *
 * Input:
 * The first line of input file contains an integer t, denoting the number of test cases to follow.
 *
 * The first line of each test case contains an integer n, as explained in statement. It is followed by n lines, each containing two space-seperated * * integers denoting the favour and anger of the ith girl.
 *
 * Output:
 * The output file should contain t lines, each containing answer for the test case.
 *
 * Constraints:
 * 1 <= t <= 10
 * 2 <= n <= 1e5
 * 0 <= favour[i], anger[i] <= 1e9
 * None of the input files exceed 4MB.
 *
 * SAMPLE INPUT
 * 1
 * 4
 * 2 3
 * 10 2
 * 11 5
 * 4 1
 * SAMPLE OUTPUT
 * 17
 * Explanation
 * Choose girl 2 and 3 happiness = 10 + 11 - 1 - 3 = 17
 */

public class ProAndConList {

    public static void main(String[] args) {
        PrintWriter out = new PrintWriter(System.out);
        InputReader in = new InputReader(System.in);

        long T = in.readLong();
        for (int i = 0; i < T; i++) {
            int N = in.readInt();
            long happy[] = new long[N];
            long angry[] = new long[N];

            long max = 0, max2 = 0;
            int index = 0, index1 = 0;
            long total_angry = 0;
            for (int j = 0; j < N; j++) {
                happy[j] = in.readLong();
                angry[j] = in.readLong();

                long sum = happy[j] + angry[j];
                if(sum >= max) {
                    max2 = max;
                    max = sum;
                    index1 = index;
                    index = j;
                } else if(sum > max2) {
                    max2 = sum;
                    index1 = j;
                }

                total_angry += angry[j];
            }

            long ans = (happy[index] + happy[index1]) - (total_angry - (angry[index] + angry[index1]));
            out.println(ans);
            out.close();
        }
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
