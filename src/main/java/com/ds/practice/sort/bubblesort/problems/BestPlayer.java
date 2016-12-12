package com.ds.practice.sort.bubblesort.problems;

/**
 * Created by Kishore Routhu on 8/11/16 6:04 PM.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BestPlayer {
    public static void main(String args[] ) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line[] = br.readLine().split(" ");
        int N = Integer.parseInt(line[0]);
        int T = Integer.parseInt(line[1]);

        String fname[] = new String[N];
        long fquot[] = new long[N];
        for (int i = 0; i < N; i++) {
            String fan[] = br.readLine().split(" ");
            fname[i] = fan[0];
            fquot[i] = Long.parseLong(fan[1]);
        }

        sort(fname, fquot, N);
        for (int j = 0; j < T; j++) {
            System.out.println(fname[j]);
        }
    }

    private static void sort(String fname[], long fquot[], int n) {
        boolean sorted = false;
        int i = n-1;
        while(!sorted) {
           sorted = true;
            for(int j=0; j<i; j++) {
                if ((fquot[j+1] > fquot[j]) || ((fquot[j+1] == fquot[j]) && (fname[j+1].compareTo(fname[j]) < 0))) {
                    swap(fname, fquot, j, j+1);
                    sorted = false;
                }
            }
            i--;
        }
    }

    private static void swap(String fname[], long fquot[], int i, int j) {
        long temp = fquot[i];
        fquot[i] = fquot[j];
        fquot[j] = temp;

        String tname = fname[i];
        fname[i] = fname[j];
        fname[j] = tname;
    }
}
