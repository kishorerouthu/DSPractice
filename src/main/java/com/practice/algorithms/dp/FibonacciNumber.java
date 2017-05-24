package com.practice.algorithms.dp;

import java.util.Arrays;

/**
 * Created by kishore on 12/4/17.
 */
public class FibonacciNumber {

    public static void main(String[] args) {
        System.out.println(fibonacci(10));

        long startTime = System.currentTimeMillis();

        int[] mem = new int[31];
        Arrays.fill(mem, -1);
        int f = fibonacciRecursive(30, mem);
        long endTime = System.currentTimeMillis();
        System.out.println("Recursive Time-Taken ::  " + (endTime - startTime));
        System.out.println("Recursive result :: " + f);

        System.out.println(fibonacciDp(10));

        System.out.println(fibonacciMatrix(10));

        System.out.println(fibonacciMatrixRec(10));
    }

    // Method1: Finding n'th Fibonacci number with recursion
    private static int fibonacciRecursive(int n, int memorized[]) {
        if (memorized[n] == -1) {
            if (n <= 1)
                memorized[n] = n;
            else
                memorized[n] = fibonacciRecursive(n - 1, memorized) + fibonacciRecursive(n - 2, memorized);
        }
        return memorized[n];
    }

    /**
     * Method2 : Using Dynamic Programming
     * We can avoid repeated work done by storing the Fibonacci numbers calculated so far.
     */
    private static int fibonacciDp(int n) {

        int f[] = new int[n+1];

        f[0] = 0;
        f[1] = 1;

        for (int i = 2; i <=n; i++) {
            f[i] = f[i-1] + f[i-2];
        }

        return f[n];
    }

    /**
     * Method3: Space optimization method
     * We can optimize the space used in method 2 by storing the previous two numbers only because that is
     * all we need to get the next Fibonacci number in the series
     */
    private static int fibonacci(int n) {

        int f1 = 0;
        int f2 = 1;
        int f3 = 0;

        for (int i = 2; i < n; i++) {
            f3 = f1 + f2;
            f1 = f2;
            f2 = f3;
        }

        return f3;
    }

    /**
     * Method4: Using power of matrix {{1,1},{1,0}}
     * This is another O(n) which relies on that if we n times multiply the matrix M = {{1,1}, {1,0}} to itself
     * (in other words calculate power(M, n)), then we get the (n+1)th Fibonacci number as the element at row and column(0, 0)
     * in the resultant matrix
     *  power({{1,1}, {1,0}}, n) = {{fn+1, fn}, {fn, fn-1}}
     */
    private static int fibonacciMatrix(int n) {
        int[][] f = new int[][]{{1,1},{1,0}};

        if (n == 0)
            return 0;
        power(f, n-1);

        return f[0][1];
    }

    private static void power(int[][] f, int n) {

        int[][] m = new int[][]{{1,1},{1,0}};
        for (int i = 2; i <= n; i++)
            multiply(f, m);

    }

    private static void multiply(int[][] f, int[][] m) {

        int x = f[0][0] * m[0][0] + f[0][1] * m[1][0];
        int y = f[0][0] * m[0][1] + f[0][1] * m[1][1];
        int z = f[1][0] * m[0][0] + f[1][1] * m[1][0];
        int w = f[1][0] * m[0][1] + f[1][1] * m[1][1];

        f[0][0] = x;
        f[0][1] = y;
        f[1][0] = z;
        f[1][1] = w;
     }


    /**
     * Method5: Optimized method4
     * The method4 can be optimized to work with O(Log n) time complexity. We can do recursive multiplication to get
     * power(M,n) in teh previous method
     */
     private static void powerRec(int[][] f, int n) {
         if (n == 0 || n == 1)
             return;

         int m[][] = new int[][]{{1, 1}, {1, 0}};

         powerRec(f, n/2);
         multiply(f, f);
         if (n%2 != 0)
             multiply(f, m);
     }

    private static int fibonacciMatrixRec(int n) {
        int[][] f = new int[][]{{1,1},{1,0}};

        if (n == 0)
            return 0;
        powerRec(f, n-1);

        return f[0][0];
    }

}
