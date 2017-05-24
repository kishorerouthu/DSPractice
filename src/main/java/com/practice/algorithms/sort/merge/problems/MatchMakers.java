package com.practice.algorithms.sort.merge.problems;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import com.practice.utils.ArrayUtils;

/**
 * Created by kishore on 27/1/17.
 *
 *Little Mojo owns a match making company, which even to her surprise is an extreme hit. She says that her success rate cannot be matched (Yeah, *wordplay!) in the entire match-making industry. She follows an extremely simple algorithm to determine if two people are matches for each other. Her *algorithm is not at all complex, and makes no sense - not even to her. But she uses it anyway.
 *
 *Let's say say that on a given day she decides to select n people - that is, n boys and n girls. She gets the list of n boys and n girls in a random *order initially. Then, she arranges the list of girls in ascending order on the basis of their height and boys in descending order of their heights. *A girl Ai can be matched to a boy on the same index only, that is, Bi and no one else. Likewise, a girl standing on Ak can be only matched to a boy *on the same index Bk and no one else.
 *
 *Now to determine if the pair would make an ideal pair, she checks if the modulo of their heights is 0, i.e., Ai % Bi == 0 or Bi % Ai == 0. Given the *number of boys and girls, and their respective heights in non-sorted order, determine the number of ideal pairs Mojo can find.
 *
 *Input format:
 * The first line contains number of test cases. Then, the next line contains an integer, n, saying the number of boys and girls. The next line contains
 * the height of girls, followed by the height of boys.
 *
 *Output format:
 *Print the number of ideal pairs corresponding to every test case.
 *
 * Constraints:
 * 1 <= Test Cases <= 102
 * 1 <= N <= 104
 * 1 <= A i , B i <= 105
 * SAMPLE INPUT
 * 2
 * 4
 * 1 6 9 12
 * 4 12 3 9
 * 4
 * 2 2 2 2
 * 2 2 2 2
 * SAMPLE OUTPUT
 * 2
 * 4
 * Time Limit:	2.0 sec(s) for each input file.
 * Memory Limit:	256 MB
 * Source Limit:	1024 KB
 *
 */

public class MatchMakers {

    public static void main(String[] args) {
        PrintWriter out = new PrintWriter(System.out);
        InputReader in = new InputReader(System.in);

        int T = in.readInt();
        for (int i = 0; i < T; i++) {
            int N = in.readInt();
            int G[] = new int[N];
            int B[] = new int[N];
            for (int j = 0; j < N; j++) {
                G[j] = in.readInt();
            }

            for (int k = 0; k < N; k++) {
                    B[k] = in.readInt();
            }

            sort(G, 0, N-1, false);
            sort(B, 0, N-1, true);

            int ip = 0;
            for (int m = 0; m < N; m++)
                if ((G[m] % B[m] == 0) || (B[m] % G[m] == 0))
                    ip++;
            System.out.println(ip);
        }
    }

    private static void sort(int a[], int l, int r, boolean desc) {
        if (l < r) {
            int m = (l + r) >> 1;
            sort(a, l, m, desc);
            sort(a, m+1, r, desc);
            merge(a, l, m, r, desc);
        }
    }

    private static void merge(int a[], int l, int m, int r, boolean desc) {
        int n1 = m - l + 1;
        int n2 = r - m;

        int left[] = new int[n1];
        int right[] = new int[n2];

        for (int i = 0; i < n1; i++)
            left[i] = a[l + i];

        for (int j = 0; j < n2; j++)
            right[j] = a[m + 1 + j];

        int i = 0;
        int j = 0;
        int k = l;

        while (i<n1 && j<n2) {
            if (desc)
                 if(left[i] >= right[j])
                    a[k] = left[i++];
                 else
                     a[k] = right[j++];
            else if(left[i] <= right[j])
                    a[k] = left[i++];
            else
                a[k] = right[j++];
            k++;
        }

        while (i<n1) {
            a[k] = left[i++];
            k++;
        }

        while (j<n2) {
            a[k] = right[j++];
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
