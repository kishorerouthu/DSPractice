package com.css.ds.fights; /**
 * Created by kishore on 16/2/17.
 */
/* IMPORTANT: Multiple classes and nested static classes are supported */

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


class TestClass {

    public static void main(String args[] ) throws Exception {

        InputReader in = new InputReader(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int n = in.readInt();
        int a[] = new int[n];
        int count[] = new int[2*n];
        int count1[] = new int[2*n];
        for(int i = 0; i < n; i++) {
            a[i] = in.readInt();
            ++count[a[i]];
            ++count1[a[i]];
        }

        for(int j = 1; j < count.length; j++)
            count[j] += count[j-1];

        int output[] = new int[n];
        for(int k = 0; k < n; k++) {
            output[count[a[k]] - 1] = a[k];
            --count[a[k]];
        }

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int l = 0; l < n; l++) {
            map.put(output[l], count1[output[l]]);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet())
            out.println(entry.getKey() + " " + entry.getValue());

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
