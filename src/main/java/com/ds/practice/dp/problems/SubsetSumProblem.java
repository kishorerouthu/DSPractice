package com.ds.practice.dp.problems;

/**
 * Created by kishore on 17/4/17.
 *
 * Given a set of non-negative integers, and a value sum,
 * determine if there is a subset of the given set with sum equal to given sum.
 *
 * Examples: set[] = {3, 34, 4, 12, 5, 2}, sum = 9
 * Output:  True  //There is a subset (4, 5) with sum 9.
 *
 */
public class SubsetSumProblem {

    public static void main(String[] args) {
        int a[] = new int[]{3, 34, 4, 12, 5, 2};
        int n = a.length;
        int sum = 9;

        long startTime1 = System.currentTimeMillis();
        boolean isSumPresent = isSubsetSum(a, n, sum);
        long endTime1 = System.currentTimeMillis();
        System.out.println("Method1 :\n***********************");
        System.out.println(isSumPresent);
        System.out.println(endTime1 - startTime1);


        long startTime2 = System.currentTimeMillis();
        boolean isSumPresentDp = isSubsetSumDp(a, n, sum);
        long endTime2 = System.currentTimeMillis();
        System.out.println("Method2 (DP) :\n***********************");
        System.out.println(isSumPresentDp);
        System.out.println(endTime2 - startTime2);



    }

    /*
     *  Method1 : Naive approach
     * Let isSubSetSum(int set[], int n, int sum) be the function to find whether there is a subset of set[] with sum equal to sum.
     * n is the number of elements in set[].
     *
     * The isSubsetSum problem can be divided into two subproblems
     *
     * a) Include the last element, recur for n = n-1, sum = sum – set[n-1]
     * b) Exclude the last element, recur for n = n-1.
     * If any of the above the above subproblems return true, then return true.
     *
     * Following is the recursive formula for isSubsetSum() problem.
     *
     * isSubsetSum(set, n, sum) = isSubsetSum(set, n-1, sum) ||
     *                            isSubsetSum(set, n-1, sum-set[n-1])
     * Base Cases:
     * isSubsetSum(set, n, sum) = false, if sum > 0 and n == 0
     * isSubsetSum(set, n, sum) = true, if sum == 0
     *
     * Complexity :
     * The above solution may try all subsets of given set in worst case.
     * Therefore time complexity of the above solution is exponential.
     * The problem is in-fact NP-Complete (There is no known polynomial time solution for this problem).
     */

    private static boolean isSubsetSum(int a[], int n, int sum) {

        //Base Cases
        if (sum == 0)
            return true;

        if (n == 0 && sum > 0)
            return false;


        //If last element is greater than sum, then ignore it
        if (a[n-1] > sum)
            return isSubsetSum(a, n-1, sum);

        /*  else check if the sum can be obtained by any of the following
            (a) including the element
            (b) excluding the last element
         */
        return isSubsetSum(a, n-1, sum) || isSubsetSum(a, n-1, sum - a[n-1]);
    }


    /*
    * Method2 : Using Dynamic Programming
    * We can solve the problem in Pseudo-polynomial time using Dynamic programming.
    * We create a boolean 2D table subset[][] and fill it in bottom up manner.
    * The value of subset[i][j] will be true if there is a subset of set[0..j-1] with sum equal to i., otherwise false.
    * Finally, we return subset[sum][n]
    *
    * Example : {3, 34, 4, 12, 5, 2} sum = 9
    *
    *          0	    1	    2	     3	      4	       5	    6
    *
    *     0  true     true     true     true     true     true      true
    *     1  false    false    false    false    false    false     false
    *     2  false    false    false    false    false    false     true      subset[2][6] true indicates that there sum = 2 in subset of set[0...5] i.e at set[5]
    *     3  false    true     true     true     true     true      true
    *     4  false    false    false    true     true     true      true
    *     5  false    false    false    false    false    true      true
    *     6  false    false    false    false    false    false     true
    *     7  false    false    false    true     true     true      true
    *     8  false    false    false    false    false    true      true
    *     9  false    false    false    false    false    true      true
    *
    *
    */
    private static boolean isSubsetSumDp(int a[], int n, int sum) {

        //The value of subset[i][j] is true, if there is subset of set[0...j-1] having the sum i
        boolean subset[][] = new boolean[sum+1][n+1];

        //If the sum is 0, then answer is true
        for (int i=0; i<=n; i++)
            subset[0][i] = true;

        //Fill the subset table in the bottom up manner
        for (int i=1; i<=sum; i++) {
            for (int j=1; j<=n; j++) {
                subset[i][j] = subset[i][j-1];

                if (i >= a[j-1])
                    subset[i][j] = subset[i][j] || subset[i-a[j-1]][j-1];
            }
        }

        for (int i = 0; i <= sum; i++) {
            for (int j = 0; j <= n; j++)
                System.out.printf("    " + subset[i][j]);
            System.out.printf("\n");
        }

        return subset[sum][n];
    }
}

