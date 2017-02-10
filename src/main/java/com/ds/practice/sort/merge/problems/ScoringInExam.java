package com.ds.practice.sort.merge.problems;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

/**
 * Created by kishore on 10/2/17.
 *
 * Milly is at the examination hall where she is reading a question paper. She checked the question paper and discovered that there are N questions  * in that paper. Each question has some score value. Ideally it's like questions requiring more time have more score value and strangely no two     * questions on the paper require same time to be solved.
 *
 * She is very excited by looking these questions. She decided to solve K questions while maximizing their score value. Could you please help Milly  * to determine the exact time she needs to solve the questions.
 *
 * Input
 * First line of input contains two space separated integers N and Q, where N is the number of questions available and Q is number of queries
 * Next line contains N space separated integers denoting the time Ti of N questions
 * Next line contains N space separated integers denoting the scores Si of N questions
 * Next Q lines contains a number K each, the number of questions she wants to solve
 *
 * Output
 * Print the time required for each query in a separate line.
 *
 * Constraints
 * 1 <= N <= 105
 * 1 <= Q <= 105
 * 1 <= K <= N
 * 1 <= Ti, Si <= 109
 *
 * SAMPLE INPUT
 * 5 2
 * 2 3 9 4 5
 * 3 5 11 6 7
 * 5
 * 3
 *
 * SAMPLE OUTPUT
 * 23
 * 18
 * Explanation
 * For second query k = 3, Three most scoring questions are those with values 11, 7 and 6 and time required are 9, 5 and 4 respectively so the total * total time required = 18.
 */
public class ScoringInExam {

    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int n = in.readInt();
        int q = in.readInt();

        long t[] = new long[n];
        for (int i = 0; i < n; i++) {
            t[i] = in.readLong();
        }

        for (int i = 0; i < n; i++)
            in.readLong();

        mergesort(t, 0, n-1);

        for (int k = 1; k < n; k++) {
            t[k] += t[k-1];
        }

        for (int j = 0; j < q; j++) {
            int query = in.readInt();
            out.println(t[query-1]);
        }

        out.flush();
        out.close();
    }

    private static void mergesort(long a[], int l, int r) {
        if ( l < r) {
            int m = (l + r) >> 1;
            mergesort(a, l, m);
            mergesort(a, m + 1, r);
            merge(a, l, m, r);
        }
    }

    private static void merge(long a[], int l, int m, int r) {

        int n1 = m - l + 1;
        int n2 = r - m;

        long left[] = new long[n1];
        long right[] = new long[n2];

        for (int i = 0; i < n1; i++) {
            left[i] = a[l + i];
        }

        for (int j = 0; j < n2; j++) {
            right[j] = a[m + 1 + j];
        }

        int i = 0;
        int j = 0;
        int k = l;

        while (i < n1 && j < n2) {
            if (left[i] >= right[j]) {
                a[k] = left[i];
                i++;
            } else {
                a[k] = right[j];
                j++;
            }

            k++;
        }

        while (i < n1) {
            a[k] = left[i];
            i++;
            k++;
        }

        while (j < n2) {
            a[k] = right[j];
            j++;
            k++;
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
