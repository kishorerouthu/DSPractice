package com.ds.practice.sort.radix;

/**
 * Created by kishore on 2/3/17.
 */
public class RadixSort {

    public static void main(String[] args) {
        int a[] = new int[]{170, 45, 75, 90, 802, 24, 2, 66};
        int n = a.length;
        radixSort(a, n);
        for (int v : a)
            System.out.printf("%d ", v);
    }

    private static void radixSort(int a[], int n) {
        int max = getMax(a, n);
        for (int exp = 1; max/exp > 0; exp*=10)
            countingSort(a, n, exp);
    }

    private static int getMax(int a[], int n) {
        int max = Integer.MIN_VALUE;
        for (int val : a)
            max = (val > max)? val : max;
        return max;
    }

    private static void countingSort(int a[], int n, int exp) {

        int count[] = new int[10];
        // Store count of occurrences in count[]
        for (int i = 0; i < n; i++)
            count[(a[i] / exp)%10]++;

        // Change count[i] so that count[i] now contains actual
        //  position of this digit in output[]
        for (int j = 1; j < 10; j++)
            count[j] += count[j-1];


        // Build the output array
        int output[] = new int[n];
        for (int k = n-1; k >= 0; k--) {
            output[count[ (a[k]/exp)%10 ] -1] = a[k];
            count[ (a[k]/exp) % 10]--;
        }

        // Copy the output array to arr[], so that arr[] now
        // contains sorted numbers according to current digit
        for (int l = 0; l < n; l++)
            a[l] = output[l];
    }
}
