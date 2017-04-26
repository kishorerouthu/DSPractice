package com.ds.practice.dp.problems;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

/**
 * Created by kishore on 18/4/17.
 *
 * Harry was contesting to be the most stylist person in his college.
 * He had to collect maximum points from the judges to be able to win.
 * However there was a problem.
 * The judges were sitting in a line and each pair of adjacent judges had ego issues with each other.
 * So if one judge gave X points to Harry then the next judge won’t give him any points.
 * Harry had a friend in the organizing team and through him he had found out the exact points
 * he would get from each judge if he chose their score to be considered.
 *
 * Help him find out the maximum points he can score.
 *
 * INPUT
 * The first line of input contains the number of test cases, T.
 * 0 < T < = 10
 *
 * Each test case starts with a number N, the number of judges.
 * 0 <= N < = 10^4.
 *
 * The next line will have N numbers, number of points each judge gave Harry
 * 0 < = X(i) < = 10^9.
 *
 * The order of the judges does not change.
 *
 * OUTPUT
 * For each test case print “Case T: A” without quotes in a single line.
 * T is the case number, starting with 1.
 * A is the maximum number of points Harry can collect.
 *
 * SAMPLE INPUT
 * 2
 * 5
 * 1 2 3 4 5
 * 1
 * 10
 *
 * SAMPLE OUTPUT
 * Case 1: 9
 * Case 2: 10
 *
 * Explanation
 * Case 1 : Harry chooses judges 1,3 and 5 with points 1 ,3 and 5 respectively to add up to 9 points in total.
 * Case 2: There is only one judge so Harry chooses his points alone.
 *
 *
 * ALGORITHM ::
 * 	function getMaxScore(a[], n) {
 * 		sum = a[0]
 * 		p_index = 0
 *
 * 		for (i from 1 to n-1) {
 * 			if (i-1 == p_index) {
 * 				if (sum-a[p_index] + a[j] > sum) {
 * 					sum = sum -a[p_index] + a[j]
 * 					p_index = i
 * 				}
 * 			} else {
 * 					sum = sum + a[j]
 * 					p_index = i
 * 			}
 * 		}
 * 		return sum;
 * 	}
 *
 *
 * Algorithm Evaluation::
 * array = 9961 491 2995 11942 4827 5436 32391
 *
 * i=0 :
 * p_index = 0, sum = 9961
 *
 * i=1 :
 * p_index = 0, sum = 9961
 *
 * i=2
 * p_index=2, sum = 12956
 *
 * i=3
 * p_index=3, sum = 21903
 *
 * i=4
 * p_index=3, sum = 21903
 *
 * i=5
 * p_index=5, sum=27339
 *
 * i=6
 * p_index=6, sum=54294
 *
 */
public class ChoosingTheJudges {

    static int numChar;
    static int curChar;
    static byte[] buffer = new byte[1024];
    static InputStream stream;
    static PrintWriter out;

    public static void main(String[] args) throws IOException {

        stream = System.in;
        out = new PrintWriter(System.out);

        int t = readInt();
        for (int i=0; i<t; i++) {
            int n = readInt();

            long a[] = new long[n];
            for (int j=0; j<n; j++) {
                a[j] = readLong();
            }
            long result = getMaxScore(a, n);
            System.out.printf("Case %d: %d", i+1, result);
            System.out.println();
        }
    }

    private static long getMaxScore(long a[], int n) {
        long sum = a[0];
        int pivot_index = 0;
        int previous_index = 0;

        if (n > 3) {
            long sum02 = a[0] + a[2];
            long sum03 = a[0] + a[3];
            long sum13 = a[1] + a[3];

            if (sum02 > sum13 || sum03 > sum13) {
                sum = a[0];
                pivot_index = 0;
            } else {
                sum = a[1];
                pivot_index = 1;
            }

            if (n > 4) {
                long sum14 = a[1] + a[4];
                if ((sum02 > sum13 && sum02 > sum14) || (sum03 > sum13 && sum03 > sum14)) {
                    sum = a[0];
                    pivot_index = 0;
                } else {
                    sum = a[1];
                    pivot_index = 1;
                }
            }

        }

        for (int i=pivot_index+2; i<n; i++) {
            if ((i-1) == pivot_index) {
                if (sum - a[pivot_index] + a[i] > sum) {
                    sum = sum - a[pivot_index] + a[i];
                    pivot_index = i;

                    if (pivot_index - previous_index > 3) {
                        sum += a[previous_index + 2];
                        previous_index +=  2;
                    }

                }
            } else {
                sum = sum + a[i];
                pivot_index = i;
            }
        }
        return sum;
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

    private static String readString() throws IOException {
        int c = read();
        if (c == -1)
            throw new IOException();
        while (isSpaceChar(c)) {
            c = read();
        }
        StringBuilder builder = new StringBuilder();
        while (!isSpaceChar(c)) {
            builder.append((char) c);
            c = read();
        }
        return builder.toString();
    }

    private static boolean isSpaceChar(int c) {
        return c == ' ' || c == '\n' || c == '\t' || c == '\r' || c == -1;
    }

}
