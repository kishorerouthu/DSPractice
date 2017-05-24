package com.practice.algorithms.sort.insertion.problems;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

/**
 * Created by kishore on 26/12/16.
 *
 * Bangalore City, where peace prevails most of the time. Not everyone is a huge fan of peace, though.
 * Certainly not Mr. XYZ, whose identity is not known to us - yet. Mr. XYZ has somehow managed to bring vampires and zombies to Bangalore City to attack and destroy the city.
 * Fatal Eagle, an ordinary citizen of the city is extremely worried on seeing his city being attacked by these weird creatures.
 * But, as of now, he has no power to stop these creatures from their silent attacks. He wants to analyze these creatures firstly.
 * He figured out some things about these creatures, like:
 *
 * Zombies have power in terms of an EVEN number.
 * Vampires have power in terms of an ODD number.
 *
 * If he sees a zombie or a vampire, he marks them in his list with their power.
 * After generating the entire list of power of these creatures, he decides to arrange this data in the following manner:
 *
 * All the zombies arranged in sorted manner of their power, followed by the total power of zombies.
 * All the vampires arranged in sorted manner of their power, followed by the total power of vampires.
 * You've to help him produce the following list to help him save his city.
 *
 * Input constraints:
 * The first line of input will contain an integer — N, denoting the number of creatures.
 * The next line will contain N integers denoting the elements of the list containing the power of zombies and vampires.
 *
 * Output constraints:
 * Print the required list in a single line.
 *
 * Constraints:
 * 1 ≤ N ≤ 103
 * 1 ≤ Ni ≤ 103
 *
 */

public class RiseOfWeird {


    static int numChar;
    static int curChar;
    static byte[] buffer = new byte[1024];
    static InputStream stream;
    static PrintWriter out;


    public static void main(String[] args) throws IOException {
        stream = System.in;
        out = new PrintWriter(new BufferedOutputStream(System.out));
        int N = readInt();

        List<Integer> zombiesList = new ArrayList();
        List<Integer> vampiresList = new ArrayList();

        long zombie_sum = 0;
        long vampire_sum = 0;
        for (int i = 0; i < N; i++) {
            int value = readInt();
            if (value % 2 == 0) {
                zombiesList.add(value);
                zombie_sum += value;
            } else {
                vampiresList.add(value);
                vampire_sum += value;
            }
        }

        Integer zombies[] = new Integer[zombiesList.size()];
        Integer vampires[] = new Integer[vampiresList.size()];

        zombiesList.toArray(zombies);
        vampiresList.toArray(vampires);

        sort(zombies, zombies.length);
        sort(vampires, vampires.length);

        StringBuffer result = new StringBuffer();
        for (int zombie : zombies)
            result.append(zombie + " ");
        result.append(zombie_sum + " ");

        for (int vampire : vampires)
            result.append(vampire + " ");
        result.append(vampire_sum);

        System.out.println(result.toString());;

    }

    private static void sort(Integer arr[], int n) {
        for (int i = 1; i < n; i++) {

            int x = arr[i];
            int j = i;

            while (j > 0 && arr[j-1] > x) {
                arr[j] = arr[j-1];
                j--;
            }

            arr[j] = x;
        }
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

    public static boolean isSpaceChar(int c) {
        return c == ' ' || c == '\n' || c == '\t' || c == '\r' || c == -1;
    }

}
