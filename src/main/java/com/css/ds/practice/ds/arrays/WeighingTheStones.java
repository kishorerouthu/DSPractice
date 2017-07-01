package com.css.ds.practice.ds.arrays;

import java.io.IOException;

import com.css.ds.io.IOProvider;

/**
 * @author Kishore Routhu on 30/6/17 8:21 PM.
 *
 * Problem Statement :
 *
 * Rupam and Ankit are both fond of collecting stones.
 * They both follow this hobby diligently and go out finding stones together.
 * So, one fine day they decided to play a small game.
 * They decided to weigh each of the stones and note them down in a notepad,
 * and then at the end of the day both of them will compare their notes.
 *
 * They will check how many stones of a particular weight are there.
 * Then they will compare the weight of the stone occurring maximum number of times
 * (you can consider stones of similar weight to be similar).
 * In case that all different stones occur exactly same number of times,
 * the stone with maximum weight will be considered for comparison.
 * The person with higher weighing stone wins, else there will be a tie.
 * You need to determine the result.
 *
 * INPUT:
 * The first line of input consists of a single integer T denoting number of test cases.
 * Each test case consists of three lines.
 * First line consists of a single integer N denoting the number of stones.
 * The second line consists of N space separated integers denoting the weights of stones collected by Rupam.
 * The third line consists of N space separated integers denoting the weights of stones collected by Ankit.
 *
 * OUTPUT:
 * For each test case, print the result in a new line.
 * If Rupam wins, print "Rupam" (without the quotes).
 * If Ankit wins, print "Ankit" (without the quotes).
 * In case of a Tie, print "Tie" (without the quotes).
 *
 * CONSTRAINTS:
 *
 * 1<=T<=1000
 * 1<=N<=100
 * 0<=Weight of stones<=100
 *
 * SAMPLE INPUT
 * 2
 * 6
 * 5 3 1 4 3 2
 * 4 3 1 1 1 5
 * 5
 * 1 2 3 4 5
 * 5 4 3 2 1
 *
 * SAMPLE OUTPUT
 * Rupam
 * Tie
 */
public class WeighingTheStones {

    public static void main(String[] args) throws IOException {

        IOProvider io = IOProvider.getConsoleInstance();

        int t = io.readInt();
        while (t > 0) {
            int n = io.readInt();
            int a[] = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = io.readInt();
            }

            int b[] = new int[n];
            for (int i = 0; i < n; i++) {
                b[i] = io.readInt();
            }

            String winner = findWinner(a, b, n);
            io.println(winner);

            t--;
        }
        io.finish();
    }


    private static String findWinner(int a[], int b[], int n) throws IOException {

        int lookup1[] = new int[101];
        int lookup2[] = new int[101];

        int v = 0;
        for (int i = 0; i < n; i++) {
            v = a[i];
            lookup1[v]++;
        }

        for (int i = 0; i < n; i++) {
            v = b[i];
            lookup2[v]++;
        }

        int rhwc = lookup1[0];
        int rhwi = 0;

        int ahwc = lookup2[0];
        int ahwi = 0;
        for (int i = 1; i < 100; i++) {
            if (lookup1[i] >= rhwc) {
                rhwc = lookup1[i];
                rhwi = i;
            }

            if (lookup2[i] >= ahwc) {
                ahwc = lookup2[i];
                ahwi = i;
            }
        }
        return (rhwi > ahwi? "Rupam" : ahwi > rhwi? "Ankit" : "Tie");
    }
}
