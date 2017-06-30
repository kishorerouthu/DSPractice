package com.css.ds.practice.algorithms.dp;

/**
 * Created by kishore on 12/4/17.
 *
 *  Longest Increasing subsequence in O(nlogn)
 *
 *  Let assume that arr[i] is input array from i = 0 to n
 *  Let S[i] be defined as the smallest integer that ends an increasing sequence of length i.
 *  Now iterate through every integer X of the input set arr[] and do the following:
 *      1) If X > last element in S, then append X to the end of S. This essentially means we have found a new largest LIS.
 *      2) Otherwise find the smallest element in S, which is greater then or equal ( >= ) to X, and change it to X.
 *         Because S is sorted at any time, the element can be found using binary search in log(N).
 *
 *  So, total runtime – N integers and a binary search for each of them – N * log(N) = O(N log N)
 *
 *  Now let’s understand above algorithm using real example:
 *   Set of integers: 2 6 3 4 1 2 7 9 5 8
 *
 * Steps:
 *
 * 0. S = {} - Initialize S to the empty set
 * 1. S = {2} - New largest LIS
 * 2. S = {2, 6} - New largest LIS
 * 3. S = {2, 3} - Changed 6 to 3
 * 4. S = {2, 3, 4} - New largest LIS
 * 5. S = {1, 3, 4} - Changed 2 to 1
 * 6. S = {1, 2, 4} - Changed 3 to 2
 * 7. S = {1, 2, 4, 7} - New Largest LIS
 * 8. S = {1, 2, 4, 7, 9} - New largest LIS
 * 9. S = {1, 2, 4, 5, 9} - Changed 7 to 5
 * 10. S = {1, 2, 4, 5, 8} - Changed 8 to 9
 *
 * So the length of the LIS is 5 (the size of S).
 *
 */
public class LISOptimized {


    public static void main(String[] args) {
        int a[] = new int[] {10, 22, 9, 33, 21, 50, 41, 60, 80};
        System.out.println(lis(a, a.length));
    }

    /*
     * Binary search to find the position of given element such
     * a[pos] >= element
     */
    private static int b_search(int a[], int e) {

        int low = 0;
        int high = a.length - 1;
        while (low < high) {
            int mid = (low + high) >> 1;
            if (a[mid] < e)
                low = mid + 1;
            else if (a[mid] > e)
                high = mid - 1;
            else
                return mid;
        }

        return low;
    }

    private static int lis(int a[], int n) {
        int s[] = new int[n];
        int size = 0;
        s[size++] = a[0];

        for (int i=1; i<n; i++) {
            int x = a[i];
            if (x > s[size - 1])
                s[size++] = x;
            else {
                int p = b_search(s, x);
                s[p] = x;
            }
        }

        return size;
    }
}
