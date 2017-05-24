package com.practice.algorithms.search.binary.problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Our monk, while taking a stroll in the park, stumped upon a polynomial ( A X2 + B X +C ) lying on the ground.
 * The polynomial was dying! Being considerate, our monk tried to talk and revive the polynomial.
 * The polynomial said:
 * I have served my purpose, and shall not live anymore. Please fulfill my dying wish.
 * Find me the least non-negative integer Xo, that shall make my value atleast K i.e., A Xo2 + B Xo + C >= K .
 * Help our Monk fulfill the polynomial's dying wish!
 *
 * Input:
 * The first line contains an integer T. T test cases follow.
 * Each test case consists of four space-separated integers A, B, C and K.
 *
 * Output:
 * For each test case, output the answer in a new line.

 * Constraints:
 * 1 ≤ T ≤ 105
 * 1 ≤ A,B,C ≤ 105
 * 1 ≤ K ≤ 1010
 *
 * Created by Kishore Routhu on 15/10/16 10:33 PM.
 */
public class MonkPolynomial {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        for (int i=0; i<n; i++) {
            String line = br.readLine();
            String input[] = line.split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);
            int c = Integer.parseInt(input[2]);
            int k = Integer.parseInt(input[3]);
            System.out.println(findX0(a, b, c, k));
        }
    }

    private static int findX0(int a, int b, int c, long k) {
        int l = 0;
        int h = 1;
        int x0 = 0;
        while (polynomial(a, b, c, h) < k) {
            l = h;
            h *= 2;
        }
        while(l <= h) {
            int mid = l + ((h - l) >> 1);
            if (polynomial(a, b, c, mid) < k) {
                l = mid + 1;
            }
            else {
                h = mid - 1;
                x0 = mid;
            }
        }
        return x0;
    }


    private static long polynomial(int a, int b, int c, long x) {
        long sqr = x * x;
        return a * sqr + b * x + c;
    }
}
