package com.ds.practice.sort.bubblesort.problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Kishore Routhu on 13/11/16 9:41 PM.
 */
public class BennyAndSegments {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            String line[] = br.readLine().split(" ");
            long N = Long.parseLong(line[0]);
            long L = Long.parseLong(line[1]);
            long first = 0;
            long last = 0;
            boolean found = false;
            for (int j = 0; j < N; j++) {
                String road[] = br.readLine().split(" ");
                long xl = Long.parseLong(road[0]);
                long xr = Long.parseLong(road[1]);
                if(first == 0 || (last != 0 && last != xl)) {
                    first = xl;
                }
                last = xr;
                if (last - first == L) {
                    found = true; break;
                }
            }
            if (found)
                System.out.println("Yes");
            else
                System.out.println("No");
        }
    }
}
