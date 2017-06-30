package com.css.ds.practice.algorithms.sort.heap;

/**
 * Created by kishore on 5/3/17.
 */
public class HeapSort {

    public static void main(String[] args) {
        int a[] = new int[]{4, 3, 7, 1, 8, 5};
        heapSort(a, a.length);
        for (int v : a)
            System.out.printf("%d ", v);
    }

    private static void heapSort(int a[], int n) {
        buildMaxHeap(a, n);

        //swap a[1] which is max element with a[n]
        for (int i = n-1; i>=0; i--) {
            int temp = a[0];
            a[0] = a[i];
            a[i] = temp;
            n--;

            maxHeapify(a, 0, n);
        }
    }

    private static void buildMaxHeap(int a[], int n) {
        for (int i = n/2 - 1; i >= 0; i--) {
            maxHeapify(a, i, n);
        }
    }

    private static void maxHeapify(int a[], int i, int size) {
        int lc =  (i << 1) + 1; // (i+1
        int rc = (i + 1) <<  1;

        int largest;
        if(lc < size && a[i] < a[lc])
            largest = lc;
        else
            largest = i;

        if(rc < size && a[largest] < a[rc])
            largest = rc;

        if (largest != i) {
            //swap largest with i
            int temp = a[i];
            a[i] = a[largest];
            a[largest] = temp;

            maxHeapify(a, largest, size);
        }
    }
}
