package com.ds.practice.miscellaneous;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by Kishore Routhu on 30/9/16 12:40 PM.
 */
public class StressTime {

    public static void main(String[] args) throws Exception {
        new StressTime().findTime();
    }

    public void findTime() throws Exception {
        InputStream fis = new FileInputStream("/home/kishorer/Projects/DS_Practice/src/main/java/com/ds/practice/miscellaneous/StressTimeTestCase.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long startTime = System.currentTimeMillis();
        String line = br.readLine();
        int N = Integer.parseInt(line);
        for (int i = 0; i < N; i++) {
            String input[] = br.readLine().split(" ");
            long a = Long.parseLong(input[0]);
            long b = Long.parseLong(input[1]);
            long c = Long.parseLong(input[2]);
            long d = Long.parseLong(input[3]);
            long k = Long.parseLong(input[4]);

            long cbrt = (long)Math.cbrt(k);
            long stress = search(1, cbrt, a, b, c, d, k, 0);
            System.out.println(stress);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Total time : " + (endTime - startTime));
    }

    private long search(long l, long h, long a, long b, long c, long d, long k, long ans) {
        if (h >= l) {
            long m = l + ((h - l) >> 1);
            long fk = evalFunction(a, b, c, d, m);
            if (fk <= k) {
                ans = Math.max(ans, m);
                return search(m + 1, h, a, b, c, d, k, ans);
            } else {
                return search(l, m - 1, a, b, c, d, k, ans);
            }
        }
        return ans;
    }

    private long evalFunction(long a, long b, long c, long d, long t) {
        long tsq = t * t;
        long tcb = tsq * t;
        return a * tcb + b * tsq + c * t + d;
    }


}
