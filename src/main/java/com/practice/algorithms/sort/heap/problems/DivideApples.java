package com.practice.algorithms.sort.heap.problems;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

/**
 * Created by kishore on 7/3/17.
 *
 * N boys are sitting in a circle. Each of them have some apples in their hand. You find that the total number of the apples can be divided by N.      * So you want to divide the apples equally among all the boys. But they are so lazy that each one of them only wants to give one apple to one of      * the neighbors at one step. Calculate the minimal number of steps to make each boy have the same number of apples.
 *
 * Input
 *
 * The first line of input is an integer N. 2 <= N <= 10000 The second line is N integers indicates the number of apples of the ith boy.
 * Each integer is positive and no more than 10^9.
 *
 * Output
 *
 * A single line contains the minimal number of steps to make each boy have the same number of apples.
 *
 * SAMPLE INPUT
 * 4
 * 1 3 9 7
 *
 * SAMPLE OUTPUT
 * 8
 *
 * Explanation
 * Here are the 8 steps starting from (1,3,9,7):
 *
 * (2,3,9,6)
 * (3,3,9,5)
 * (3,4,8,5)
 * (3,5,7,5)
 * (3,6,6,5)
 * (3,7,5,5)
 * (4,6,5,5)
 * (5,5,5,5)
 *
 */
public class DivideApples {

    public static void main(String[] args) {

        PrintWriter out = new PrintWriter(System.out);
        InputReader in = new InputReader(System.in);

        int n = in.readInt();
        long a[] = new long[n];
        long sum = 0, avg = 0;
        for (int i = 0; i < n; i++) {
            a[i] = in.readLong();
            sum += a[i];
        }

        avg = sum / n;

        long ans = findMinSteps(a, n, avg);
        out.println(ans);

        out.flush();
        out.close();
    }

    private static long findMinSteps(long a[], int n, long avg) {
        long m, ans = 0;

        long b[] = new long[n];
        for (int i = 1; i < n; i++)
            b[i] = b[i-1] + a[i-1] - avg;

        mergesort(b, 0, n-1);

        m = -b[n/2];

        for (int i = 0; i < n; i++)
            ans += Math.abs(b[i] + m);

        return ans;
    }

    private static void heapSort(long a[], int n) {
        buildMaxHeap(a, n);

        for (int i = n-1; i>=0; i--) {
            //swap i with root 0
            long max = a[0];
            a[0] = a[i];
            a[i] = max;
            n--;

            maxHeapify(a, 0, n);
        }
    }

    private static void buildMaxHeap(long a[], int n) {
        for (int i = n/2 - 1; i >=0; i--)
            buildMaxHeap(a, n);
    }

    private static void maxHeapify(long a[], int i, int n) {

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

            long temp = a[i];
            a[i] = a[largest];
            a[largest] = temp;

            maxHeapify(a, largest, n);
        }
    }

    private static void mergesort(long elements[], int l, int r) {
        if ( l < r) {
            int m = (l + r) >> 1;
            mergesort(elements, l, m);
            mergesort(elements, m + 1, r);
            merge(elements, l, m, r);
        }
    }

    private static void merge(long elements[], int l, int m, int r) {

        int n1 = m - l + 1;
        int n2 = r - m;

        long left[] = new long[n1];
        long right[] = new long[n2];

        for (int i = 0; i < n1; i++) {
            left[i] = elements[l + i];
        }

        for (int j = 0; j < n2; j++) {
            right[j] = elements[m + 1 + j];
        }

        int i = 0;
        int j = 0;
        int k = l;

        while (i < n1 && j < n2) {
            if (left[i] <= right[j]) {
                elements[k] = left[i];
                i++;
            } else {
                elements[k] = right[j];
                j++;
            }

            k++;
        }

        while (i < n1) {
            elements[k] = left[i];
            i++;
            k++;
        }

        while (j < n2) {
            elements[k] = right[j];
            j++;
            k++;
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
