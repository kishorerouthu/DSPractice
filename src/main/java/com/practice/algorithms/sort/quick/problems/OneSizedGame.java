package com.practice.algorithms.sort.quick.problems;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kishore on 19/2/17.
 */
public class OneSizedGame {

    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();


        PrintWriter out = new PrintWriter(System.out);
        InputReader in = new InputReader(System.in);

        int t = in.readInt();
        for (int i = 0; i < t; i++) {
            int n = in.readInt();
            int min_value = Integer.MAX_VALUE;
            int min_index = 0;
            List<Integer> a = new ArrayList<Integer>(n);
            for (int j = 0; j < n; j++) {
                int value = in.readInt();
                if (value > -1 && min_value > value) {
                    min_value = value;
                    min_index = j+1;
                }
                a.add(value);
            }

            do {
                 reduceList(a, a.get(min_index-1));
                 min_index = sinkArray(a, min_index);
            } while ( a.size() != 0 && a.size() != 1);

            out.println(a.size() == 0? "Kushagra" : "Ladia");
        }

        out.flush();
        out.close();

        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
    }

    private static void reduceList(List<Integer> a, int min_value) {
        for (int i = 0; i < a.size(); i++) {
            a.set(i, a.get(i) - min_value);
        }

    }

    private static int sinkArray(List<Integer> a, int min_index) {
        Integer arr[] = new Integer[a.size()];
        a.toArray(arr);
        a.clear();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] - min_index;
            if (arr[i] > -1) {
                a.add(arr[i]);
            }
        }
        return getMinIndex(a);
    }

    private static int getMinIndex(List<Integer> a) {
        int min = 0;
        int min_value = Integer.MAX_VALUE;
        for (int i = 0; i < a.size(); i++) {
            int value = a.get(i);
            if (value < min_value) {
                min_value = value;
                min = i + 1;
            }
        }
        return min;
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
