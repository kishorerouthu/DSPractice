package com.practice.algorithms.sort.heap;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

/**
 * Created by kishore on 6/3/17.
 *
 * You are given a list of amount of money of T people, one by one. After each element in the list print the top 3 richest people's amount of money.
 *
 * Input: line contains an integer,T, number of queries.
 * Next T lines contains an integer each, X.
 * ith query contains amount of money ith person have.
 *
 * Output:
 * For each query, print the top 3 richest people's amount of money in the town and if there are less than 3 people in town then print -1.
 *
 * Constraints:
 * 1≤T≤105
 * 1≤X≤106
 *
 * SAMPLE INPUT
 * 5
 * 1
 * 2
 * 3
 * 4
 * 5
 * SAMPLE OUTPUT
 * -1
 * -1
 * 3 2 1
 * 4 3 2
 * 5 4 3
 */
public class HeapSortPractice {

    public static void main(String[] args) {
        PrintWriter out = new PrintWriter(System.out);
        InputReader in = new InputReader(System.in);

        int t = in.readInt();
        int a[] = new int[t];
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;
        for (int i = 0; i < t; i++) {
            a[i] = in.readInt();
            if (i < 2) {
                out.println(-1);
                continue;
            } else if (max1 == Integer.MIN_VALUE) {
                heapSort(a, i + 1);

                max1 = a[i];
                max2 = a[i-1];
                max3 = a[i-2];
            } else {

                if (a[i] > max1) {
                    max3 = max2; max2 = max1; max1 = a[i];
                } else if (a[i] > max2) {
                    max3 = max2; max2 = a[i];
                } else if (a[i] > max3) {
                    max3 = a[i];
                }
            }

            out.printf("%d %d %d", max1, max2, max3);
            out.println();
        }
        out.flush();
        out.close();
    }

    private static void heapSort(int a[], int n) {
        buildMaxHeap(a, n);

        for (int i = n-1; i >=0; i--) {
            int max = a[0];
            a[0] = a[i];
            a[i] = max;

            n--;
            maxHeapify(a, 0, n);
        }

    }

    private static void buildMaxHeap(int a[], int n) {
        for (int i = n/2 -1; i>=0; i--)
            maxHeapify(a, i, n);
    }

    private static void maxHeapify(int a[], int i, int n) {
        int l = (i << 1) + 1;
        int r = (i + 1) << 1;

        int largest;

        if (l < n && a[l] > a[i])
            largest = l;
        else
            largest = i;

        if (r < n && a[r] > a[largest])
            largest = r;

        if (largest != i) {
            //swap i with largest
            int temp = a[i];
            a[i] = a[largest];
            a[largest] = temp;
            maxHeapify(a, largest, n);
        }
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
