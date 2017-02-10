package com.ds.practice.sort.merge.problems;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

/**
 * Created by kishore on 30/1/17.
 *
 * Andrew is very fond of Maths.He has N boxes with him,in each box there is some value which represents the Strength of the Box.The ith box has
 * strength A[i]. He wants to calculate the Overall Power of the all N Boxes.
 *
 * Overall Power here means Sum of Absolute Difference of the strengths of the boxes(between each pair of boxes)
 * multiplied by the Maximum strength among N boxes.
 * Since the Overall Power could be a very large number,output the number modulus 10^9+7(1000000007).
 *
 * Input
 * First line of the input contains the number of test cases T. It is followed by T test cases. Each test case has 2 lines. First line contains the
 * number of boxes N. It is followed by a line containing N elements where ith element is the strength of Andrew's ith box.
 *
 * Output
 * For each test case, output a single number, which is the Overall Power for that testcase.
 *
 * Constraints
 *
 * 1<=T<= 10
 *
 * 2<=N<=10^5
 *
 * 0<=A[i]<=10^9
 *
 * SAMPLE INPUT
 * 2
 * 2
 * 1 2
 * 5
 * 4 5 3 1 2
 * SAMPLE OUTPUT
 * 2
 * 100
 *
 * Explanation
 * For 1st test case sum of absolute difference between strength is 1.So Overall Power would be 2.
 * For 2nd test case sum of absolute difference between each pair of boxes is 20.So Overall Power is 100.
 *
 */
public class GameOfStrengths {

    public static void main(String[] args) {

        InputReader in = new InputReader(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int t = in.readInt();
        for (int i = 0; i < t; i++) {
            int n = in.readInt();
            long a[] = new long[n];
            for (int j = 0; j < n; j++) {
                a[j] = in.readLong();
            }
            sort(a, 0, n-1);
            long sum = 0;
            for (int k=0; k<n; k++) {
                sum = sum + (k*a[k]) - (n-1-k) * a[k];
            }
            sum=(sum%1000000007)*(a[n-1]%1000000007);
            out.println(sum%1000000007);
        }

        out.flush();
        out.close();
    }

    private static void sort(long[] a, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            sort(a, l, m);
            sort(a, m+1, r);
            merge(a, l, m, r);
        }
    }

    private static void merge(long[] a, int l, int m, int r) {

        int n1 = m - l + 1;
        int n2 = r - m;

        long left[] = new long[n1];
        for (int i=0; i<n1; i++)
            left[i] = a[l+i];

        long right[] = new long[n2];
        for (int j=0; j<n2; j++)
            right[j] = a[m+j+1];

        int i = 0;
        int j = 0;
        int k = l;
        while (i < n1 && j < n2) {

            if (left[i] > right[j]) {
                a[k] = right[j];
                j++;
                k++;
            } else {
                a[k] = left[i];
                i++;
                k++;
            }
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
