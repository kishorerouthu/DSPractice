package com.practice.algorithms.search.binary;

/**
 * Definition :
 * Like binary search, jump search is a searching algorithm on sorted arrays.
 * The basic idea is to check fewer elements(than linear search) by jumping ahead by fixed steps
 * or skipping some elements in place of searching all elements.
 *
 * For example, suppose we have an array arr[] of size n and block(to be jumped) size m.
 * Then we search at the indexes arr[0], arr[m], arr[2m]....arr[km] and so on. Once we found the
 * interval (arr[km]< x < arr[(k+1)m]), we perform linear search operation from the index km to
 * find the element x.
 *
 * Let’s consider the following array: (0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610).
 * Length of the array is 16. Jump search will find the value of 55 with the following steps assuming that the block size to be jumped is 4.
 * STEP 1: Jump from index 0 to index 4;
 * STEP 2: Jump from index 4 to index 8;
 * STEP 3: Jump from index 8 to index 16;
 * STEP 4: Since the element at index 16 is greater than 55 we will jump back a step to come to index 9.
 * STEP 5: Perform linear search from index 9 to get the element 55.
 *
 * What is the optimal block size to be skipped?
 * In the worst case, we have to do n/m jumps and if the last checked value is greater than the element to be searched
 * for, we perform m-1 comparisons more for linear search.
 * Therefore the total number of comparisons in the worst case will be ((n/m) + m-1).
 * The value of the function ((n/m) + m-1) will be minimum when m = √n. Therefore, the best step size is m = √n.
 *
 * ALGORITHM :
 * fun(arr, n, x){
 *  a = 0;
 *  b = ⌊√n⌋
 *  while (arr[min(b,n)-1] < x) {
 *      a = b;
 *      b = b + ⌊√n⌋;
 *      if ( a >= n )
 *          return -1;
 *  }
 *
 *  while(arr[a] < x) {
 *      a++;
 *      if (a == min[b,n])
 *          return -1;
 *  }
 *
 *   if (arr[a] == x)
 *      return a;
 *   else
 *     return -1;
 * }
 *
 * COMPLEXITY:
 * Time complexity :  O(√n)
 *
 * Created by Kishore Routhu on 18/10/16 9:17 AM.
 */
public class JumpSearch {

    public static void main(String[] args) {
        int a[] = new int[]{2, 4, 6, 8, 10, 12, 14, 16, 18, 20};
        System.out.println(search(a, a.length, -12));
    }

    private static int search(int arr[], int n, int x) {
        int a = 0;
        int b = (int)Math.floor(Math.sqrt(n));

        while(arr[Math.min(b, n)-1] < x) {
            a = b;
            b += (int)Math.floor(Math.sqrt(n));

            if (a>=n)
                return -1;
        }

        while (arr[a] < x) {
            a++;
            if (a == Math.min(b, n))
                return -1;
        }

        if (arr[a] == x)
            return a;
        return -1;
    }
}
