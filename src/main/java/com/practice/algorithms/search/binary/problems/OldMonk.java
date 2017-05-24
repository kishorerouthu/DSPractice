package com.practice.algorithms.search.binary.problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Problem Statement:
 *
 * Big Chandan is a dire lover of Biryani, especially Old Monk's Biryani. Today, he went over to have some of it.
 * To his surprise, the waiter turns out be to be a coding geek and refuses to serves him unless Chandu solves his two- arrays problem,
 * stated as:
 * Given two non-increasing array of integers A,B i.e A[i] >= A[i+1] and B[i] >= B[i+1] and for all i, 0 ≤ i < n-1.
 * The monkiness of two numbers is given by: M (A[i],B[j]) = j - i , if j >=i and B[j] >= A[i], or 0 otherwise.
 *
 *  Find the monkiness of the two arrays, that is given by: M (A,B)= max (M(A[i],B[j])) for 0≤ i, j< n-1.
 *
 *  Input Format:
 *  The first line contains an integer, tc, denoting the number of test cases.
 *  The next line contains an integer, n, denoting the size of the two arrays.
 *  The size of both the arrays will be equal.
 *  After that line, the next line contains n integers denoting the numbers in the array A,
 *  and in the next line, there will be n numbers denoting the numbers in the array B.
 *
 * Output format:
 * Print the monkiness of the two arrays.
 *
 * Constraints:
 * 1 <= Test Cases <= 50
 * 1 <= N <= 105
 * 1 <= Ai, Bi <= 1012
 *
 * SAMPLE INPUT
 * 2
 * 9
 * 7 7 3 3 3 2 2 2 1
 * 8 8 7 7 5 5 4 3 2
 * 6
 * 6 5 4 4 4 4
 * 2 2 2 2 2 2
 *
 * SAMPLE OUTPUT
 * 5
 * 0
 * Created by Kishore Routhu on 15/10/16 1:20 PM.
 */
public class OldMonk {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        byte tc = Byte.parseByte(br.readLine());
        for (int i=0; i<tc; i++) {

            int n = Integer.parseInt(br.readLine());

            String aLine = br.readLine();
            long a[] = fillArray(n, aLine);

            String bLine = br.readLine();
            long b[] = fillArray(n, bLine);

            System.out.println(monk(a, b));

        }
    }

    private static long[] fillArray(int m, String line) {
        long arr[] = new long[m];
        m=0;
        for (String s : line.split(" ")) {
            arr[m++] = Long.parseLong(s);
        }
        return arr;
    }

    private static int monk(long a[], long b[]) {
        int max_monk = 0;
        for (int j=1; j<b.length; j++) {
            int i = search(a, 0, j-1, b[j]);
            if (i != -1) {
                max_monk = Math.max(max_monk, (j-i));
            }
        }
        return max_monk;
    }

    private static int search(long a[], int start, int end, long target) {
        if (target < a[end])
                return -1;
        int index = 0;
        while(start <= end) {
            int mid = start + ((end - start) >> 1);
            if (a[mid] <= target) {
                end = mid - 1;
                index = mid;
            } else {
                start = mid + 1;
            }
        }
        return index;
    }
}
