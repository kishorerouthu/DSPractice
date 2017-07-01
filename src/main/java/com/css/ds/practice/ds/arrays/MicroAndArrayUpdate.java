package com.css.ds.practice.ds.arrays;

import java.io.IOException;

import com.css.ds.io.IOProvider;

/**
 * Created by Kishore Routhu on 17/6/17 1:21 PM.
 *
 * Micro purchased an array A having N integer values. After playing it for a while,
 * he got bored of it and decided to update value of its element.
 *
 * In one second he can increase value of each array element by 1.
 * He wants each array element's value to become greater than or equal to K.
 * Please help Micro to find out the minimum amount of time it will take, for him to do so.
 *
 * Input:
 * First line consists of a single integer, T, denoting the number of test cases.
 * First line of each test case consists of two space separated integers denoting N and K.
 *
 * Second line of each test case consists of N space separated integers denoting the array A.
 *
 * Output:
 * For each test case, print the minimum time in which all array elements will become greater than or equal to K.
 * Print a new line after each test case.
 *
 * Constraints:
 * 1≤T≤5
 * 1≤N≤10^5
 * 1≤A[i],K≤10^6
 *
 * SAMPLE INPUT
 * 2
 * 3 4
 * 1 2 5
 * 3 2
 * 2 5 5
 *
 * SAMPLE OUTPUT
 * 3
 * 0
 *
 */
public class MicroAndArrayUpdate {

    public static void main(String[] args) throws IOException {

        IOProvider io = IOProvider.getConsoleInstance();

        int t = io.readInt();
        while (t > 0) {
            int n = io.readInt();
            int k = io.readInt();
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                int ai = io.readInt();
                min = (ai < min) ? ai : min;
            }

            io.println((k - min) < 0? 0 : (k - min));

            t--;
        }
        io.finish();
    }

}
