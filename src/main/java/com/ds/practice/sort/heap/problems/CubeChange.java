package com.ds.practice.sort.heap.problems;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

/**
 * Created by kishore on 8/3/17.
 *
 * Chandan gave his son a cube with side N. The N X N X N cube is made up of small 1 X 1 X 1 cubes.
 *
 * Chandan's son is extremely notorious just like him. So he dropped the cube inside a tank filled with Coke.
 * The cube got totally immersed in that tank.
 * His son was somehow able to take out the cube from the tank.
 * But sooner his son realized that the cube had gone all dirty because of the coke.
 * Since Chandan did not like dirty stuffs so his son decided to scrap off all the smaller cubes that got dirty in the process.
 * A cube that had coke on any one of its six faces was considered to be dirty and scrapped off.
 * After completing this cumbersome part his son decided to calculate volume of the scrapped off material.
 * Since Chandan's son is weak in maths he is unable to do it alone.
 *
 * Help him in calculating the required volume.
 *
 * Input:
 *
 * The first line contains T denoting the number of test cases.
 * Then T lines follow each line contains N that is the side of cube.
 *
 * Output:
 *
 * For each case output the required volume.
 *
 * Constraints:
 *
 * 1 ≤ T ≤ 100
 * 1 ≤ N ≤ 109
 * Note:
 *
 * There is no hole or space between 2 smaller cubes.
 *
 * SAMPLE INPUT
 * 2
 * 1
 * 3
 * SAMPLE OUTPUT
 * 1
 * 26
 *
 * Explanation
 * For the first test case : There is only 1 small cube in a 1 x 1 x 1 cube.
 * This cube gets coke on all of its 6 faces so it needs to be scrapped off.
 * Volume of material that gets scrapped is 1 x 1 x 1 = 1.
 *
 * Concept : All the outer side of the cube got dirty. So we need to take of all outer cubes of all sides.
 * That means we retain the internal cube.
 * So the taken out volume = volume of full cube - volume of internal cube = N^3 - (N-2)^3 for N > 1.
 *
 */

public class CubeChange {

    static int numChar;
    static int curChar;
    static byte[] buffer = new byte[1024];
    static InputStream stream;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {

        stream = System.in;
        out = new PrintWriter(System.out);

        int t = readInt();
        long n = 0;
        long ans  = 0;
        while (t-- != 0) {
            n = readLong();

            // 8 corners + 12 (n-2) edges + 6 (n-2)(n-2) faces
            //ans = 8 + 12 * (n-2) + 6 * (n-2) * (n-2);
            if (n > 1)
                ans = (n * n * n) - ((n-2) * (n-2) * (n-2));
            else
                ans = n;
            out.println(ans);
        }

        out.flush();
        out.close();
    }


    public static int read() throws IOException {
        if (numChar <= curChar) {
            curChar = 0;
            numChar = stream.read(buffer);
            if (numChar <= 0) {
                return -1;
            }
        }
        return buffer[curChar++];
    }

    public static long readLong() throws IOException, InputMismatchException {
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



    public static int readInt() throws IOException, InputMismatchException {
        return (int) readLong();
    }

    public static String readString() throws IOException {
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

    public static boolean isSpaceChar(int c) {
        return c == ' ' || c == '\n' || c == '\t' || c == '\r' || c == -1;
    }
}
