package com.ds.practice.dp.problems;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

/**
 * Created by kishore on 4/5/17.
 *
 * February Easy Challenge 2015 is underway. Hackerearth welcomes you all and hope that all you awesome coders have a great time.
 * So without wasting much of the time let's begin the contest.
 *
 * Prime Numbers have always been one of the favourite topics for problem setters.
 * For more information on them you can use see this link http://en.wikipedia.org/wiki/Prime_number .
 *
 * Aishwarya is a mathematics student at the Department of Mathematics and Computing, California.
 * Her teacher recently gave her an intriguing assignment with only a single question.
 * The question was to find out the minimum number of single digit prime numbers which when summed equals a given number X.
 *
 * Input:
 * The first line contains T denoting the number of test cases.
 * Each of the next T lines contains a single integer X.
 *
 * Output:
 * Print the minimum number required. If it is not possible to obtain X using single digit prime numbers, output -1.
 *
 * Constraints:
 * 1<=T<=100
 * 1<=X<=10^6
 *
 * SAMPLE INPUT
 * 4
 * 7
 * 10
 * 14
 * 11
 *
 * SAMPLE OUTPUT
 * 1
 * 2
 * 2
 * 3
 *
 * Explanation
 * 10 can be represented as 7 + 3.
 * 14 can be represented as 7+7
 *
 * 11 can be represented as 5+3+3.
 *
 */
public class LetsBegin {


    static int numChar;
    static int curChar;
    static byte[] buffer = new byte[1024];
    static InputStream stream;
    static PrintWriter out;

    static final int s[] = new int[]{2, 3, 5, 7};

    public static void main(String[] args) throws IOException {

        stream = System.in;
        out = new PrintWriter(System.out);

        int t = readInt();
        while (t>0) {
            int x = readInt();
            int m = 4;
            int coins = minCoinsDP(s, m, x);
            out.println((coins != Integer.MAX_VALUE)? coins : -1);
            t--;
        }

        out.flush();
        out.close();

    }

    private static int minCoins(int s[], int m, int n) {

        //Boundary conditions
        //There will no minimum coins required if n<=0
        if (n == 0)
            return 0;

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            if (s[i] <= n) {
              int sub_res =  minCoins(s, m, n - s[i]);

              if (sub_res != Integer.MAX_VALUE && sub_res + 1 < res)
                        res = sub_res + 1;
            }
        }

        return res;
    }

    private static int minCoinsDP(int s[], int m, int n) {

        int tab[][] = new int[n+1][m];

        //For sum 0 there are no minimum coins required
        for (int i = 0; i < m; i++)
            tab[0][i] = 0;

        //Fill the rest of cells in the table
        for (int i = 1; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j=0; j<m; j++) {
                if (s[j] <= i)
                    if (tab[i-s[j]][j] != Integer.MAX_VALUE && tab[i-s[j]][j] + 1< min)
                        min = tab[i-s[j]][j] + 1;

                tab[i][j] = min;
            }
        }

        return tab[n][m-1];
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
