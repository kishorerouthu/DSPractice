package com.ds.practice.search.ternary;

/**
 * Similarly like binary search, ternary search is divide and conquer algorithm and
 * for it array should be sorted in which we are looking for the element.
 * In this search, after each iteration it neglects the 2/3 part of the array
 * and repeats the same operations on the remaining 1/3 part of the array.
 *
 * COMPLEXITY:
 * Worst case performance	O(log n)
 * Best case performance	O(1)
 * Average case performance	O(log n)
 * Worst case space complexity	O(1)
 *
 * Created by Kishore Routhu on 6/10/16 12:18 AM.
 */
public class TernarySearch {

    public static void main(String[] args) {
        int elements[] = new int[]{2, 3, 5, 6, 8, 9, 12, 13, 14};
        int element_to_find = 13;
        int find_index = search(elements, 0, elements.length-1, element_to_find);
        if (find_index == -1)
            System.out.println("ELEMENT " + element_to_find + " NOT FOUND  !!!!");
        else
            System.out.println("ELEMENT " + element_to_find + " FOUND AT INDEX " + find_index + " Hahahahaa.... !!!!");
    }

    private static int search(int arr[], int l, int r, int x) {
        if (l<=r) {
            int mid1 = l + (r - l) / 3;
            int mid2 = r - (r - l) / 3;
            if (x == arr[mid1])
                return mid1;
            if (x == arr[mid2])
                return mid2;
            if (x < arr[mid1])
                return search(arr, l, mid1-1, x);
            if (x > arr[mid2])
                return search(arr, mid2 + 1, r, x);
            else
                return search(arr, mid1 + 1, mid2 - 1, x);
        }
        return -1;
    }

}
