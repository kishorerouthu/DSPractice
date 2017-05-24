package com.practice.algorithms.dp.problems;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

/**
 * Created by kishore on 8/5/17.
 *
 * Rhezo and his friend Vanya love problem solving. They have a problem set containing N problems,
 * with points assigned to each.
 * Rhezo wants to solve problems in such a way that he gets the maximum number of points.
 * Rhezo has a weird habit of solving only prime number of consecutive problems, that is, if he solves
 * X consecutive problems from the problem set, then X should be prime. V
 * anya has already solved all problems from the problem set and he wonders how much maximum points Rhezo can get.
 * Vanya can answer this, but can you?
 *
 * Input:
 * First line contains a single integer N, denoting the number of problems in the problem set.
 * Next line contains N space separated integers denoting the points assigned to the problems.
 *
 * Output:
 * Print a single integer, the maximum points Rhezo can get.
 *
 * Constraints:
 * 1≤N≤5000
 * 1≤Points of Problems≤10^5
 *
 * SAMPLE INPUT
 * 4
 * 8 1 3 7
 *
 * SAMPLE OUTPUT
 * 12
 *
 * Explanation
 * Rhezo will solve problems
 * 1, 2 and 3 and will get 12 points. Note that, he cannot solve all the problems because then he will solve 4(non-prime) consecutive problems.
 */

public class RhezoAndPrimeProblems {

    static int numChar;
    static int curChar;
    static byte[] buffer = new byte[1024];
    static InputStream stream;
    static PrintWriter out;

    public static void main(String[] args) throws IOException {

        stream = System.in;
        out = new PrintWriter(System.out);

        int n = readInt();
        int a[] = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = readInt();

        long result = maxPoints(a, 0, n);
        out.println(result);

        out.flush();
        out.close();

    }

    private static long maxPoints(int a[], int i, int n) {

        //Boundary Conditions Points will be zero if n is 1 as, 1 is not prime number
        if (n <= 1)
            return 0;

        //Gives the prime number before given n
        int d = getPrimeNumber(n);

        long sum = 0;
        int j = 0;
        while (j < d) {
            sum += a[i + j];
            j++;
        }

        if (n-j == 1)
            sum = Math.max(sum, sum-a[i] + a[i+j]);

        return sum + maxPoints(a, j, n-j);
    }

    private static int getPrimeNumber(int n) {
        while (n > 2) {
            if (isPrime(n))
                return n;
            n--;
        }
        return n;
    }
    private static boolean isPrime(int n) {

        if (n == 1)
            return false;

        if (n>2 && n%2==0)
            return false;

        int s = (int) Math.sqrt(n);
        for (int i = s; i >= 2; i--)
            if (n % i == 0)
                return false;
        return true;
    }

    public static void main2(String[] args) {
        int i = 1;
        while(i<=2000) {
            if (isPrime(i))
                System.out.println(i);
            i++;
        }
    }

    public static void main1(String[] args) {
        System.out.println(isPrime(1425));
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
