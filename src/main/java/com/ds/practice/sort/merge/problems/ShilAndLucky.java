package com.ds.practice.sort.merge.problems;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

/**
 * Created by kishore on 9/2/17.
 *
 * Shil is your new boss and he likes lucky strings very much. A string is called lucky if and only if each character from first half of the string  * can be paired to each character from second half of the string. AND:
 * In each pair, a character from the first half is strictly greater than a character from the second half OR
 * In each pair, a character from the first half is strictly lesser than a character from the second half OR
 * In each pair, a character from the first half is equal to character from the second half.
 * Each character should be used exactly once in pairing.
 * Your are given a string S. You want to change the minimum number of characters to make this string lucky so that you can gift it to your boss.
 * Note that each character in lucky string should be in lower case alphabet ( 'a' - 'z' ).
 *
 * Input format:
 * The first line consists of an integer N. The second line consists of N lower case characters.
 *
 * Output format:
 * Print minimum number of changes required to make given string lucky.
 *
 * Constraints:
 * 1 < N ≤ 105
 * N will be an even integer.
 * Sample Input(Plaintext Link)
 * 6
 * aabbbb
 * Sample Output(Plaintext Link)
 * 1
 * Explanation
 * To satisfy case 1, number of changes required is 3.
 * To satisfy case 2, number of changes requires is 1.
 * To satisfy case 3,number of changes required is 2.
 *
 *
 *
 * Note that the problem is equivalent to 3 subproblems:
 *
 * finding the optimal pairing of characters such that the number of x such that a[x] = b[x] is true
 * finding the optimal pairing of characters such that the number of x such that a[x] < b[x] is true
 * finding the optimal pairing of characters such that the number of x such that a[x] > b[x] is true
 *
 * For example, if strings are deada and patel, then since a[1] < b[1], a[3] < b[3], and a[5] < b[5], there are 3 such x in the 2nd case.
 * So we can change only the 2nd and 4th character of the first string to make it less than the second string.
 * (Or if that character is 'a', change the last character of the corresponding second half)
 *
 * Consider the cases.
 * Case 1 :
 * Count number of each character (a to z) in first half and last half, and for each a to z,
 * add min(ct[a], ct2[z]) to sum. O(N + 26O(1)) = O(N).
 * Example: say string is weedshwe. Then, ct[w] = 1, ct[e] = 2, ct[d] = 1 for the first half, 'weed', and ct2[s] = ct2[h] = ct2[w] = ct2[e] = 1.
 * And everything else 0. So, answer would be min(ct[a], ct2[a]) + ... + min(ct[z], ct2[z]) = 2.
 * So we have 2 that are in correct position, so 4 - 2 changes to be made.
 *
 * Case 3 :
 * Now for the other two cases. FIrst we take a[x] > b[x].
 * We first sort the two halves and take characters in a fashion similar to that of merge in mergesort.
 * For example, say string is deadtark.
 * Call the remaining strings S1, S2.
 * First half is dead, second half is tark. Sort the strings by character.
 * At start, S1 = arkt, S2 = adde.
 * We compare top character of s1 with top character of s2. We discard first character of S1 if both are equal.
 * So since a = a, S1 = rkt, S2 = adde.
 * So now, first characters satisfy property, r > a.
 * So we increment count by 1 and remove both characters: S1 = kt, S2 = dde.
 * Same goes for last 2 characters, so ct = 3.
 * And answer would be (length of half - ct) = 4-3 = 1.
 * Complexity O(N).
 *
 * We take minimum of these 3 cases, it's our answer.
 *
 */
public class ShilAndLucky {

    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int n = in.readInt();
        String s = in.readString();
        char data[] = s.toCharArray();
        char ff[] = new char[n/2];
        char ss[] = new char[n/2];
        int hash1[] = new int[26];
        int hash2[] = new int[26];

        int i = 0;
        while (i < n/2) {
            ff[i] = data[i];
            hash1[ff[i]-'a']++;
            i++;
        }

        int j = 0;
        while (i < n) {
            ss[j] = data[i];
            hash2[ss[j]-'a']++;
            i++;
            j++;
        }

        mergesort(ff, 0, (n/2)-1);
        mergesort(ss, 0, (n/2)-1);

        int ans1=0, ans2=0, ans3=0;
        for (int k = 0; k < 26; k++) {
            //ans1 += Math.abs(hash1[k] - hash2[k]);
            ans1 += Math.min(hash1[k], hash2[k]);
        }

        ans1 = n/2 - ans1;

        int l=0, m=0;
        while (l!=n/2 && m!=n/2) {
            if(ff[l] < ss[m]) {
                l++; m++;
            } else {
                m++;
            }
        }
        ans2 = Math.abs(m-l);

        l=0; m=0;
        while (l!=n/2 && m!=n/2) {
            if(ss[l] < ff[m]) {
                l++; m++;
            } else {
                m++;
            }
        }
        ans3 = Math.abs(m-l);
       // out.println(Math.min(Math.min(ans1/2, ans2), ans3));
        out.println(Math.min(Math.min(ans1, ans2), ans3));
        out.flush();
        out.close();
    }

    private static void mergesort(char elements[], int l, int r) {
        if ( l < r) {
            int m = (l + r) >> 1;
            mergesort(elements, l, m);
            mergesort(elements, m + 1, r);
            merge(elements, l, m, r);
        }
    }

    private static void merge(char elements[], int l, int m, int r) {

        int n1 = m - l + 1;
        int n2 = r - m;

        char left[] = new char[n1];
        char right[] = new char[n2];

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
