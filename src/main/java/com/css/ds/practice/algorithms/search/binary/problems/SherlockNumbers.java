package com.css.ds.practice.algorithms.search.binary.problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.jws.soap.SOAPBinding;

/**
 * Problem Statement :
 * Watson gives to Sherlock a bag of numbers [1, 2, 3 ... N] and then he removes K numbers A1, A2 ... AK from the bag.
 * He now asks Sherlock to find the P'th smallest number in the bag.
 *
 * Input :
 * First line contains T, the number of test cases.
 * Each test case consists of N, K and P followed by K integers in next line denoting the array A.
 *
 * Output :
 * For each test case, print P'th smallest number in the bag. If no such number exists output -1.
 *
 * Constraints :
 *  1 ≤ T ≤ 10
 *  20% testdata: 1 ≤ N ≤ 10^3
 *  20% testdata: 1 ≤ N ≤ 10^5
 *  60% testdata: 1 ≤ N ≤ 10^9
 *  0 ≤ K ≤ min(N, 10^5)
 *  1 ≤ P ≤ N
 *
 * SAMPLE INPUT
 * 2
 * 4 1 2
 * 1
 * 5 2 4
 * 1 3
 *
 * SAMPLE OUTPUT
 * 3
 * -1
 *
 * Created by Kishore Routhu on 21/10/16 2:35 PM.
 */
public class SherlockNumbers {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        byte T = Byte.parseByte(br.readLine());
        long N, P;
        int K;
        for (int i=0; i<T; i++) {
            String line = br.readLine();
            String input[] = line.split(" ");
            N = Long.parseLong(input[0]);
            K = Integer.parseInt(input[1]);
            P = Long.parseLong(input[2]);

            String[] pLine = br.readLine().split(" ");

            long ans = -1;
            if ((N-K) < P) {
                ans = -1;
            } else {
                long pos, count;
                pos = count = Long.parseLong(pLine[0]) - 1;
                if(P <= count) {
                    ans = P;
                } else {
                    long current = Long.parseLong(pLine[0]), previous;
                    for (int j=1; j<K; j++) {
                        if (count == P) {
                            ans = pos;
                            break;
                        }
                        current = Long.parseLong(pLine[j]);
                        previous = Long.parseLong(pLine[j-1]);
                        long gap = current - (previous + 1);
                        if (count + gap > P) {
                            ans = previous + (P - count);
                            break;
                        } else if(gap > 0) {
                            pos = current - 1;
                            count += gap;
                        }
                    }
                    if (ans == -1 && count < P)
                        ans = current + (P - count);
                }
            }
            System.out.println(ans);
        }
    }
}
