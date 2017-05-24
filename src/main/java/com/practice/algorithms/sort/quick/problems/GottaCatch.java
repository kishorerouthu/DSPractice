package com.practice.algorithms.sort.quick.problems;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by kishore on 24/2/17.
 *
 * Problem Statement :
 * Little Arihant has always wanted to be the best Pokemon trainer in this world.
 * And he thinks he has achieved his goal, so he wants to quickly go and meet Professor Oak and verify this fact.
 * But like all Pokemon trainers, he has a weird habit, too.
 * He catches Pokemons which can go through evolution to become a better one.
 * After roaming in the Pokeworld for days, he has finally managed to catch k such Pokemons.
 *
 * The way he can make a Pokemon go through evolution is NOT by making them fight battles,
 * but by using an evolution stone. Since he has k Pokemons, he naturally needs k evolution stones for every one of them, as well.
 *
 * Now it takes little Arihant one complete day, to use the evolution stone on one Pokemon.
 * And for each Pokemon, he knows how many days will they take to evolute after the evolution stone has been used on them.
 *
 * He will go to meet Professor Oak, the very next day, once all his Pokemons have gone through evolution.
 * He can select the order of applying evolution stones as he likes,
 * so he wants to do it in such a way that he gets to meet Professor Oak as soon as possible!
 *
 * Input Format:
 * The input has two lines. The first line will contain an integer k, which denotes the number of Pokemons.
 * Then, a line with k integers follows, where the i-th integer denotes the number of days it takes for the i-th Pokemon to evolve.
 *
 * Output Format:
 * You need to print the earliest day when little Arihant can go meet Professor Oak.
 *
 * Constraints:
 * The days are numbered 1, 2, 3, 4, 5, 6...
 * 1 ≤ k ≤ 10^5.
 * 1 ≤ Number of days ≤ 10^6.
 * SAMPLE INPUT
 * 2
 * 3 1
 *
 * SAMPLE OUTPUT
 * 5
 *
 * Explanation
 * Here's how it happens:
 * Day 1: He uses the evolution stone on the Pokemon which takes 3 days.
 * Day 2: 3 days Pokemon's first day. He uses the evolution stone on the Pokemon which takes 1 day.
 * Day 3: 3 days Pokemon's second day. 1 day Pokemon's first and final day. It goes through evolution.
 * Day 4: 3 days Pokemon's third day. It goes through evolution.
 * All Pokemons done with, he can go to Professor Oak, now.
 * Day 5: He goes to Professor Oak.
 */
public class GottaCatch {

    public static void main(String[] args) {
        PrintWriter out = new PrintWriter(System.out);
        InputReader in = new InputReader(System.in);

        int k = in.readInt();
        Long a[] = new Long[k];
        for (int i = 0; i < k; i++) {
            a[i] = in.readLong();
        }

        //sort(a, 0, k-1);
        Arrays.sort(a, new Comparator<Long>() {

            public int compare(Long o1, Long o2) {
                return -o1.compareTo(o2);
            }
        });

        long m = 0;
        long l = 0;
        for (int i = 1; i <= k; i++) {
            l = a[i-1] + i;
            if (l > m) m = l;
        }
        out.println(++m);

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
