package com.css.ds.practice.algorithms.search.binary;

/**
 * Refer : http://www.geeksforgeeks.org/the-ubiquitous-binary-search-set-1/
 *
 * ALGORITHM :
 *  function (arr, l, r, key) {
 *      while(r-l > 1) {
 *          m = r + (r-l)/2;
 *          if(arr[m] <= key)
 *              l = m;
 *          else
 *              r = m;
 *      }
 *
 *      if(arr[l]!=key)
 *          return -1;
 *      else
 *          return l;
 *  }
 *
 * Created by Kishore Routhu on 17/10/16 12:07 PM.
 */
public class UbiquitousBinarySearch {

    public static void main(String[] args) {
        int a[] = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(search(a, 0, a.length-1, -22));
    }


    private static int search(int a[], int l, int r, int key) {
        int m;
        while (r - l > 1) {
            m = l + ((r - l) >> 1);
            if (a[m] <= key)
                l = m;
            else
                r = m;
        }

        if (a[l] == key)
            return l;
        else
            return -1;
    }
}