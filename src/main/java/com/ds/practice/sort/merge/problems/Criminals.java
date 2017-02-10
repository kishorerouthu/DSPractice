package com.ds.practice.sort.merge.problems;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kishore on 3/2/17.
 *
 * Little Deepu and Little Kuldeep are world renowned criminals. But, they are not bad people at heart. (Oh, they are...) Anyway, their occupation is
 * to smuggle drugs from one place to another. And both of them are partners in this occupation of theirs. But, now Little Deepu is an amateur drug *seller, while Little Kuldeep is a professional at that.
 *
 * So, every drug box Little Deepu packs has a value X, that is to say, if there are 5 packets, every packet has some high quantity of a given number. A packet can fit inside another packet easily, iff Xi < Xj - and one packet can contain only ONE packet inside it.
 *
 * So, when Little Kuldeep receives all the packets from Deepu, he decides to reduce the number of total packets for easier smuggling; can you help
 * Little Kuldeep extend his business, by letting him know the minimum number of packets required for him to successfully smuggle the drugs?
 *
 * Input:
 * The first line contains the number of test cases T. Every test case contains a number N, denoting the number of total drug packets. This is followed * by N lines, containing the highness value of each packet.
 *
 * Output:
 * You've to output the minimum number of packets, optimally putting one inside each other.
 *
 * Constraints:
 * 1 <= T <= 1000
 * 1 <= N <= 100000
 * 1 <= X <= 1000000000

 * SAMPLE INPUT
 * 3
 * 3
 * 1
 * 2
 * 3
 * 4
 * 2
 * 2
 * 2
 * 2
 * 3
 * 11
 * 111
 * 1111
 *
 * SAMPLE OUTPUT
 * 1
 * 4
 * 1
 *
 * Explanation
 * Test Case # 1:
 *
 * There are three boxes of size 1 , 2 and 3
 * box of size 1 can fit inside box of size 2 and now box of size 2 which contains box of size 1 can fit inside box of size 3 . So finally there will
 * be only one box.
 *
 * Test Case # 2:
 *
 * There are four boxes of same size 2 so we can't put boxes inside of any other box of same size. So the answer will be 4.
 *
 * Test Case # 3:
 *
 * There are three boxes of size 11,111,1111 . Box of size 11 can fit inside box of size 111 and box of size 111 which contains box of size 11 can fit
 * inside box 1111 .At final there will be only one box so answer is 1.
 *
 */

public class Criminals {

    public static void main(String[] args) {
        PrintWriter out = new PrintWriter(System.out);
        InputReader in = new InputReader(System.in);

        int n,packets;
        boolean[] used;
        int usedindex;
        int t = in.readInt();
        for (int i = 0; i < t; i++) {
            n = in.readInt();
            packets=0;
            usedindex=0;
            used=new boolean[n];
            long a[] = new long[n];
            for (int j = 0; j < n; j++) {
                a[j] = in.readLong();
            }
            sort(a, 0, n-1);

            for (int j = 0; j < n; j++) {
                while (usedindex < n && !(a[j] < a[usedindex]) && !used[usedindex])
                    usedindex++;
                if (usedindex < n) {
                    used[usedindex] = true;
                    // System.out.println(usedindex+" value="+arr[usedindex]);
                    usedindex++;
                } else if (usedindex >= n) {
                    packets++;
                    //    System.out.println(packets+" coz of "+i+" value="+arr[i]);
                }
            }
            out.println(packets);
        }

        out.flush();
        out.close();
    }

    private static void sort(long a[], int l, int r) {
        if (l < r) {
            int m = (l + r) >> 1;
            sort(a, l, m);
            sort(a, m+1, r);
            merge(a, l, m, r);
        }
    }

    private static void merge(long a[], int l, int m, int r) {

        int n1 = m - l + 1;
        int n2 = r - m;

        long left[] =  new long[n1];
        for (int i = 0; i < n1; i++) {
            left[i] = a[l+i];
        }

        long right[] = new long[n2];
        for (int j = 0; j < +n2; j++) {
            right[j] = a[m+1+j];
        }

        int i = 0;
        int j = 0;
        int k = l;

        while (i < n1 && j < n2) {

            if (left[i] <= right[j]) {
                a[k] = left[i];
                i++;
            } else {
                a[k] =  right[j];
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

        private boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }
    }
}
