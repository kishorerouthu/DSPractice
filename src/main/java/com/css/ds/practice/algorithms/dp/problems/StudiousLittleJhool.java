package com.css.ds.practice.algorithms.dp.problems;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

/**
 * Created by kishore on 4/5/17.
 *
 * Yes, you read it right - Little Jhool is back, but no, he's not over his break up, still.
 * And he's sad, broken and depressed; thus, he decided to visit a psychologist.
 * She tells him to think about his pleasant memories of childhood, and stay busy so as to not miss his ex-girlfriend.
 *
 * She asks him about his favorite memories from childhood, and
 * being the genius Mathematician Little Jhool is,
 * he remembered that solving Mathematics problem given to him by his teacher was his favorite memory.
 *
 * He had two types of notebooks, when he was a kid.
 * 10 problems could be solved in one page, in the first notebook.
 * 12 problems could be solved in one page, in the second notebook.
 *
 * Little Jhool remembered how in order to maintain symmetry, if he was given with
 * n problems in total to solve, he tore out pages from both notebooks, so no space was wasted. EVER!
 *
 * But, now he's unable to solve his own problem because of his depression, and for the exercise of the week,
 * he has to answer the queries asked by his psychologist.
 *
 * Given n number of questions, print the minimum number of pages he needs to tear out
 * from the combination of both the notebooks, so that no space is wasted.
 *
 * Input Format:
 * The first line will contain
 * t - number of test cases.
 *
 * The second will contain an integer
 * n - number of questions.
 *
 * Output Format:
 * Corresponding to the input, print the minimum number of pages Little Jhool needs to tear out from the combination of both the notebooks.
 * If it is NOT possible, print "−1".
 *
 * Constraints:
 * 1≤t≤100
 * 1≤n≤113
 *
 * SAMPLE INPUT
 * 2
 * 23
 * 32
 *
 * SAMPLE OUTPUT
 * -1
 * 3
 *
 * For
 * 32
 * 32: 2 pages from the notebook, where 10 can be solved;
 * 1 page from the notebook, where 12 can be solved.
 *
 */
public class StudiousLittleJhool {


    static int numChar;
    static int curChar;
    static byte[] buffer = new byte[1024];
    static InputStream stream;
    static PrintWriter out;

    public static void main(String[] args) throws IOException {

        stream = System.in;
        out = new PrintWriter(System.out);

        int t = readInt();
        while (t > 0) {
            int n = readInt();
            int result = findPages(n);

            out.println(result);
            t--;
        }

        out.flush();
        out.close();
    }

    private static int findPages(int n) {
        int p = -1;
        for (int i = 0; i <= n; i=i+10) {
            int a = n-i;
            if (a%12 == 0) {
                p = (i/10) + (a/12); break;
            }
        }
        return p;
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
