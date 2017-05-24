package com.practice.algorithms.search.binary.problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Problem Statement:
 *
 * Square Inc. processes thousands of transactions daily amounting to millions of dollars.
 * They also have a daily target that they must achieve. Given a list of transactions done by Square Inc.
 * and a daily target your task is to determine at which transaction does Square achieves the same.
 *
 * Input:
 * First line contains T, number of transactions done by Square in a day.
 * The following line contains T integers, the worth of each transactions.
 * Next line contains Q, the no of queries.
 * Next Q lines contain an integer each representing the daily target.
 *
 * Output:
 * For each query, print the transaction number where the daily limit is achieved or -1 if the target can't be achieved.
 *
 * Constraints:
 * 1<= T <=100000
 * 1<= Ai <=1000
 * 1<= Target <= 109
 * 1<=Q<=1000000
 *
 * SAMPLE INPUT
 * 5             - No of transactions
 * 1 2 1 3 4     - Transaction amounts
 * 3             - No of queries
 * 4             - Daily limit
 * 2
 * 10
 *
 * SAMPLE OUTPUT
 * 3            - Sum of 4 can be achieved at transaction number 3
 * 2
 * 5
 *
 * Created by Kishore Routhu on 13/10/16 12:06 PM.
 */
public class SquareTransaction {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        long trans[] = new long[T+1];
        trans[0] = 0;
        String[] transactions = br.readLine().split(" ");
        for (int i = 1 ; i <= T; i++) {
            trans[i] = trans[i-1] +  Integer.parseInt(transactions[i-1]);
        }

        int Q = Integer.parseInt(br.readLine());
        for (int j = 0; j < Q; j++) {
            int target = Integer.parseInt(br.readLine());
            System.out.println(search(trans, 1, T, target));
        }
    }

    private static int search(long trans[], int start, int end, long target) {
        if (target > trans[end])
            return -1;
        int trans_no = 0;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (trans[mid] >= target) {
                end = mid - 1;
                trans_no = mid;
            } else
                start = mid + 1;
        }
        return trans_no;
    }
}
