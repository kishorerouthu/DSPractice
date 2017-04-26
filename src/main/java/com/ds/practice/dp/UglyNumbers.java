package com.ds.practice.dp;

/**
 * Created by kishore on 17/4/17.
 *
 * Definition : Ugly numbers are the numbers whose only prime factors 2, 3 or 5.
 *              The sequence 1,2,3,4,5,6,8,9,10,12,15 shows the first 11 ugly numbers.
 *              By convention, 1 is included.
 *
 *    Given a number, task is to find n'th ugly number.
 *
 *    Input : n = 7
 *    Output : 8
 *
 *    Input : n = 10
 *    Output : 12
 *
 *    Input : n = 15
 *    Output : 24
 *
 *    Input : n = 150
 *    Output : 5832
 *
 */
public class UglyNumbers {

    public static void main(String[] args) {
        int n = 1200;

        long startTime = System.currentTimeMillis();
        int uglyNumber = getNthUglyNumber(n);
        long endTime = System.currentTimeMillis();
        System.out.println("Method1 :");
        System.out.println("Time taken:: " + ((endTime - startTime)));
        System.out.println(uglyNumber);
        System.out.println("===============================");


        long startTime2 = System.currentTimeMillis();
        int uglyNumber2 = findTheNthUglyNumber(n);
        long endTime2 = System.currentTimeMillis();
        System.out.println("Method2 :");
        System.out.println("Time taken:: " + ((endTime2 - startTime2)));
        System.out.println(uglyNumber2);

    }

    /**
     * This method divides a by greatest divisible power of b
     */
    private static int maxDivide(int a, int b) {

        while (a % b == 0)
            a /= b;

        return a;
    }

    /**
     * This method checks whethere the given number is ugly or not.
     */
    private static boolean isUgly(int n) {
        n = maxDivide(n, 2);
        n = maxDivide(n, 3);
        n = maxDivide(n, 5);

        return n == 1;
    }

    /*
     * This method will find the given n'th ugly number.
     */
    private static int getNthUglyNumber(int n) {
        int uglyCount = 1;
        int i = 2;
        while (true) {
            if (isUgly(i))
                uglyCount++;

            if (uglyCount == n)
                break;

            i++;
        }
        return i;
    }


    /**
     * Method 2 :
     *
     * Ugly numbers are numbers whose only prime factors are 22, 33 or 55. We use dynamic programming to find the nthnth ugly number.
     *
     * Declare an array for ugly numbers.
     * Initialize the first ugly number to be 1 and
     * 3 array index variables to point to the first element of the ugly number array.
     *
     * Fill up the ugly number array : The next ugly number will be the multiplication of the existing ugly numbers with 2, 3 or 5.
     * The existing ugly numbers are multiplied by 2, 3 and 5.
     * In each cases choose the product of the multiplication that is the next greater integer number of the
     * already found biggest ugly number.
     * The next ugly number will be the minimum of these three numbers.
     * Repeat the above until the desired indexed ugly number is found.
     *
     * Time complexity = O(n), space complexity = O(n).
     *
     *  Reference : http://www.algoqueue.com/algoqueue/default/view/9175040/find-nth-ugly-number-
     */

    public static int findTheNthUglyNumber(int n) {

        if(n <= 0)
            return 0;

        int ugly[] = new int[n];
        ugly[0] = 1;

        int nextUglyIndex = 1;
        int index2 = 0;
        int index3 = 0;
        int index5 = 0;

        while (nextUglyIndex < n) {

            int minimum = Math.min(Math.min(ugly[index2] * 2, ugly[index3] * 3), ugly[index5] * 5);
            ugly[nextUglyIndex] = minimum;

            while (ugly[index2] * 2 <= ugly[nextUglyIndex])
                index2++;

            while (ugly[index3] * 3 <= ugly[nextUglyIndex])
                index3++;

            while (ugly[index5] * 5 <= ugly[nextUglyIndex])
                index5++;

            nextUglyIndex++;
        }

        return ugly[nextUglyIndex-1];
    }


}
