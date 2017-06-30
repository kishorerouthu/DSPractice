package com.css.ds.practice.algorithms.search.binary;

/**
 * The interpolation search is an improvement over Binary Search for instances, where the values
 * in a sorted array are uniformly distribute. Binary search goes to the middle element to check.
 * on the other hand interpolation search may to to different locations according the value of key
 * being searched.
 * For example if the value of key is close to the last element, interpolation search is likely
 * to start search toward the end side.
 *
 * To find the position to be searched, it uses the following formula.
 * pos = l + ((r-l)/(a[r]-a[l])) * (x-a[0]);
 * a -> array where elements need to be searched
 * l ->
 *
 * Created by Kishore Routhu on 17/10/16 6:39 PM.
 */
public class InterpolationSearch {

    public static void main(String[] args) {
        int a[] = new int[]{2, 4, 6, 8, 10, 12, 14, 16, 18, 20};
        System.out.println(search(a, 0, a.length-1, 25));
    }

    private static int search(int a[], int l, int h, int key) {
        while(l<=h) {
            int m = l + (((h-l) /(a[h]-a[l]))*(key - a[l]));
            if(key == a[m])
                return m;
            else if (key < a[m])
                h = m - 1;
            else
                l = m + 1;
        }
        return -1;
    }
}
