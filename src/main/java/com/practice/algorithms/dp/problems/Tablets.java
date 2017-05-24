package com.practice.algorithms.dp.problems;

import java.util.Arrays;

/**
 * Created by kishore on 2/5/17.
 *
 * Therasa is a Nurse. She wants to give some tablets to the patients in her practice.
 * All the patients sit in a line and each of them has a rating score according to his or her health score.
 * Therasa wants to give at least 1 tablet for each patient.
 * Patients get jealous of their immediate neighbors,
 * so if two patients sit next to each other then the one with the higher rating must get more tablets.
 * Therasa wants to save money, so she wants to minimize the total number of tablets.
 *
 * Input
 * The first line of the input is an integer N, the number of patients in Therasa’s practice.
 * Each of the following N lines contains an integer indicates the health score of each patient.
 *
 * Output
 * Output a single line containing the minimum number of tablets Therasa must give.
 *
 * Constraints
 * 1 <= N <= 100000
 * 1 <= health score <= 100000
 *
 * SAMPLE INPUT
 * 3
 * 1
 * 2
 * 2
 *
 * SAMPLE OUTPUT
 * 4
 *
 * Explanation
 * Here 1, 2, 2 is the health score. Note that when two patients have equal health score they are allowed to have different number of tablets.
 * Hence optimal distribution will be 1, 2, 1.
 *
 */
public class Tablets {

    public static void main(String[] args) {
        int a[] = new int[]{10, 9, 8, 7, 6};
        int n = a.length;
        int minTabs = minTabCount(a, n);
        System.out.println(minTabs);
    }

    private static int minTabCount(int a[], int n) {

        int dp[] = new int[n];
        Arrays.fill(dp, 1);

        for (int i = 1; i < n; i++) {
            if (a[i] > a[i-1] && dp[i-1] < dp[i])
                dp[i] = dp[i-1] + 1;
            else if (a[i] == a[i-1]) {
                if (dp[i-1] > 1)
                    dp[i] = dp[i-1] - 1;
                else {
                    dp[i] = dp[i] + 1;
                }
            }
        }

        int sum = 0;
        for (int j = 0; j < n; j++)
            sum += dp[j];
        return sum;
    }



}
