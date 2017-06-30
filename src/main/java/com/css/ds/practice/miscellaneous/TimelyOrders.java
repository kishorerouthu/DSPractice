package com.css.ds.practice.miscellaneous;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Kishore Routhu on 1/10/16 2:05 PM.
 */
public class TimelyOrders {
    public static void main(String args[] ) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int N = Integer.parseInt(line);

        //testing time complexity
        long startTime = System.currentTimeMillis();

        List<Long> weights = new ArrayList();
        List<Integer> times = new ArrayList();

        for (int i = 0; i < N; i++) {
            String line1 = br.readLine();
            String data[] = line1.split(" ");
            int f = Integer.parseInt(data[0]);
            if (f == 1) {
                weights.add(Long.parseLong(data[1]));
                times.add(Integer.parseInt(data[2]));
            } else {
                int end = Integer.parseInt(data[2]);
                int start = end - Integer.parseInt(data[1]);

                Long w_arr[] = new Long[weights.size()];
                w_arr = weights.toArray(w_arr);

                int start_index = 0;
                int end_index = 0;

                long totalWeight = 0;
                for (int j = start_index; j <= end_index; j++) {
                    totalWeight += w_arr[j];
                }
                System.out.println(totalWeight);
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Total-time :: " + (endTime - startTime));
    }

   /* private int findStartIndex(Long w_arr[], int start) {

    }*/
}
