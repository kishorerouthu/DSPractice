package com.ds.practice.search;

/**
 * Binary search is an efficient algorithm for finding an item from ordered list OR array
 * of items. It works by repeatedly dividing in half portion of the list that could contain the item,
 * until you have narrowed down the possible locations to just one.
 *
 * We basically ignore half of the elements just after one comparison.
 * Lets take x be the element to find in array.
 * 1. Compare x with the middle element
 * 2. If x matches with middle element, then we return them middle index.
 * 3. Else if x greater than the middle element, then x can only lie in right sub-array after the middle element.
 * So we recure of the right half.
 * 4. Else ( x is smaller ) then recure the left half.
 *
 * Created by Kishore Routhu on 11/9/16 1:44 PM.
 */
public class BinarySearch {

    public static void main(String[] args) {
        int elements[] = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int element_to_find = 6;
        int find_index = search(elements, 0, elements.length-1, element_to_find);
        if (find_index == -1)
            System.out.println("ELEMENT " + element_to_find + " NOT FOUND  !!!!");
        else
            System.out.println("ELEMENT " + element_to_find + " FOUND AT INDEX " + find_index + " Hahahahaa.... !!!!");
    }

    private static int search(int elements[], int l, int r, int x) {
        if (r >= l) {
            int mid = l + ((r - l) >> 1); //() because >> takes precedence over +
            int mid_element = elements[mid];
            if (x == mid_element)
                return mid;
            if (x < mid_element)
                return search(elements, l, mid - 1 , x);
            return search(elements, mid + 1, r, x);
        }
        return -1;
    }
}
