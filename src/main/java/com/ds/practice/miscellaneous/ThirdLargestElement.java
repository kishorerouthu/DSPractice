package com.ds.practice.miscellaneous;

/**
 * Created by Kishore Routhu on 18/10/16 2:25 PM.
 */
public class ThirdLargestElement {

    public static void main(String s[]){
        int a[] = new int[]{2,4,1,3,5};
        System.out.println(thirdLargest1(a));

        int b[] = new int[]{10,5,15,5,15,10,1,1};
        System.out.println(thirdLargestWithDuplicate(b));
    }

    /**
     * Method 1:  Simplest way is to solve this question is first iterate through the array and find fist maximum.
     *            Store this first maximum. Now traverse the whole array finding the second max with the changed
     *            condition. Finally traverse the array third time and find the third largest element.
     * @param arr
     * @return third largest value in the give array
     */
    private static int thirdLargest(int arr[]) {
        int first = arr[0];
        int n = arr.length;
        for (int i=1; i<n; i++)
            if (arr[i] > first)
                first = arr[i];

        int second = Integer.MIN_VALUE;
        for (int i=0; i<n; i++)
            if (arr[i] < first && arr[i] > second)
                second = arr[i];

        int third = Integer.MIN_VALUE;
        for (int i=0; i<n; i++)
            if (arr[i] < second && arr[i] < first && arr[i] > third)
                third = arr[i];

        return third;
    }

    /**
     * Method 2: In this method, we need not to iterate array three times. We can find third largest in one traversal only.
     *           1. Initialize first = a[0] and second = -INF, third = -INF
     *           2. Iterate the array and compare each element with first.
     *                  If a[i] is greater than first then update all first, second and third:
     *                      third = second
     *                      second = first
     *                      first = arr[i]
     *                  Else compare arr[i] with second, if its greater than second, then update:
     *                      third = second
     *                      second = arr[i]
     *                 Else compare arr[i] with third, if its greater than third, then update:
     *                      third = arr[i]
     *            3. Return third
     *
     * @param arr
     * @return third largest value in the give array
     */
    private static int thirdLargest1(int arr[]) {

        int n = arr.length;
        int first = arr[0];             //Max element ex : 5
        int second = Integer.MIN_VALUE; //Second Max element ex : 4
        int third = Integer.MIN_VALUE;  //Third Max element ex : 3

        for (int i=1; i<n; i++) {
                int x = arr[i];
                if (x > first) {
                    third = second;
                    second = first;
                    first = x;
                } else if (x > second) {
                    third = second;
                    second = x;
                } else if (x > third) {
                    third = x;
                }
        }
        return third;
    }

    private static int thirdLargestWithDuplicate(int arr[]) {
        int n = arr.length;
        int first = arr[0];
        int second = Integer.MIN_VALUE;
        int third = Integer.MIN_VALUE;

        for (int i=1; i<n; i++) {
            int x = arr[i];
            if (x > first) {
                third = second;
                second = first;
                first = x;
            } else if (x > second && x != first) {
                third = second;
                second = x;
            } else if (x > third && x != second && x != first) {
                third = x;
            }
        }
        return third;
    }
}
