package com.css.ds.practice.algorithms.dp.problems;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

/**
 * Created by kishore on 28/4/17.
 *
 * Xsquare loves to play with arrays a lot. Today, he has two arrays named as A and B.
 * Each array consists of N positive integers.
 *
 * Xsquare has decided to fulfill following types of queries over his array A and array B.
 *
 * 1 L R : Print the value of AL + BL+1 + AL+2 + BL+3 + ... upto Rth term.
 * 2 L R : Print the value of BL + AL+1 + BL+2 + AL+3 + ... upto Rth term.
 * Input
 *
 * First line of input contains two space separated integers N and Q denoting the size of arrays ( A , B ) and number of queries respectively.
 * Next N lines of input contains N space separated integers denoting array A.
 * Next line of input contains N space separated integers denoting array B.
 * Next Q lines of input contains Q queries (one per line). Each query has one of the above mentioned types.
 *
 * Output
 *
 * Output consists of Q lines, each containing answer to the corresponding query.
 *
 * Constraints
 *
 * 1 ≤ N,Q ≤ 105
 * 1 ≤ Ai,Bi ≤ 109
 * 1 ≤ L,R ≤ N
 *
 *
 * SAMPLE INPUT
 * 5 5
 * 1 2 3 4 5
 * 5 4 3 2 1
 * 1 1 5
 * 2 1 5
 * 1 2 4
 * 2 2 4
 * 1 3 5
 *
 * SAMPLE OUTPUT
 * 15
 * 15
 * 9
 * 9
 * 10
 *
 * Explanation
 * Q1 : A[1] + B[2] + A[3] + B[4] + A[5] = 1 + 4 + 3 + 2 + 5 = 15
 * Q2 : B[1] + A[2] + B[3] + A[4] + B[5] = 5 + 2 + 3 + 4 + 1 = 15
 * Q3 : A[2] + B[3] + A[4] = 2 + 3 + 4 = 9
 * Q4 : B[2] + A[3] + B[4] = 4 + 3 + 2 = 9
 * Q5 : A[3] + B[4] + A[5] = 3 + 2 + 5 = 10
 *
 * ALGORITHM::
 * ***********
 * lookup<?,?>
 * function qsum(a[], b[], w, l, r) {
 * 	if (lookup.contains(w+l+r))
 * 		return lookup.contains(w+l+r)
 *
 *     //Base case for last element
 * 	if (r == n-1) {
 * 		sum = (w=='A')? a[r] : b[r]
 * 		lookup.put(w+l+r, sum)
 * 		return sum
 * 	}
 *
 * 	if (w == 'A')
 * 		sum = a[l] + qsum(a, b, 'B', l+1, r)
 * 	else if(w == 'B')
 * 		sum = b[l] + qsum(a, b, 'A', l+1, r)
 *
 * 	lookup.put(w+l+r, sum)
 * 	return sum
 * }
 */
public class XSquareAndTwoArrays {

    static int numChar;
    static int curChar;
    static byte[] buffer = new byte[1024];
    static InputStream stream;
    static PrintWriter out;

    public static void main(String[] args) throws IOException {

        stream = System.in;
        out = new PrintWriter(System.out);

        int n = readInt();
        int q = readInt();

        long a[] = new long[n];
        long b[] = new long[n];

        for (int i = 0; i < n; i++)
            a[i] = readLong();

        for (int i = 0; i < n; i++)
            b[i] = readLong();

        Query queries[] = new Query[q];
        for (int i = 0; i < q; i++) {
            int ab = readInt();
            int l = readInt();
            int r = readInt();
            queries[i] = new Query((ab == 1)? 'A' : 'B', l-1, r-1);
        }

        long startTime = System.currentTimeMillis();
        Map<String, Long> lookup = new HashMap<String, Long>();
        Query temp[] = Arrays.copyOf(queries, q);
        Arrays.sort(temp);
        for (Query query : temp)
             qsum(a, b, query, lookup);

        long endTime = System.currentTimeMillis();

        for (Query query : queries) {
            out.println(lookup.get(getKey(query)));
        }

        out.println("Time-Taken @ :: " + (endTime - startTime));

        out.flush();
        out.close();
    }

    private static long qsum(long a[], long b[], Query q, Map<String, Long> lookup) {
        String key = getKey(q);
        if (lookup.containsKey(key))
            return lookup.get(key);

        long sum = (q.getAb() == 'A'? a[q.getL()] : b[q.getL()]);
        if (q.getL() == q.getR()) {
            lookup.put(key, sum);
            return sum;
        }

        Query q1 = q.copy();
        q1.setAb(q.getAb() == 'A'? 'B' : 'A');
        q1.setL(q.getL() + 1);
        sum += qsum(a, b, q1, lookup);

        lookup.put(key, sum);
        return sum;
    }

    private static String getKey(Query q) {
        return q.getAb() + "$" + q.getL() + "$" + q.getR();
    }

    private static class Query implements Comparable<Query> {

        private char ab;
        private int l;
        private int r;

        public Query(char ab, int l, int r) {
            this.ab = ab;
            this.l = l;
            this.r = r;
        }

        public void setAb(char ab) {
            this.ab = ab;
        }

        public void setL(int l) {
            this.l = l;
        }

        public void setR(int r) {
            this.r = r;
        }

        public char getAb() {
            return ab;
        }

        public int getL() {
            return l;
        }

        public int getR() {
            return r;
        }

        public int compareTo(Query o) {

           if (ab != o.getAb())
               return ab - o.getAb();
          // return o.getL() - l;
            return o.getR() - r;

            /*int diff1 = r - l;
            int diff2 = o.getR() - o.getL();
            return diff2 - diff1;*/
        }

        public Query copy() {
            return new Query(ab, l, r);
        }
    }

    private static int read() throws IOException {
        if (numChar <= curChar) {
            curChar = 0;
            numChar = stream.read(buffer);
            if (numChar <= 0) {
                return -1;
            }
        }
        return buffer[curChar++];
    }

    private static long readLong() throws IOException, InputMismatchException {
        int c = read();
        if (c == -1)
            throw new IOException();
        while (isSpaceChar(c)) {
            c = read();
        }
        boolean negative = false;
        if (c == '-') {
            negative = true;
            c = read();
        }
        long res = 0;
        while (!isSpaceChar(c)) {
            if (c < '0' || c > '9')
                throw new InputMismatchException();
            res *= 10;
            res += (c - '0');
            c = read();
        }
        if (negative)
            return -res;
        return res;
    }

    private static int readInt() throws IOException, InputMismatchException {
        return (int) readLong();
    }

    private static boolean isSpaceChar(int c) {
        return c == ' ' || c == '\n' || c == '\t' || c == '\r' || c == -1;
    }
}
