Maximum sum sub-array(MSS) problem is the task of finding the contiguous sub-array within a one-dimensional array of
n numbers which has largest sum.

EXAMPLE :
    For sequence of values -2, 1, -3, 4, -1, 2, 1, -5, 4 ;  the contiguous sub-array with largest sum is
    4, -1, 2, 1 with sum 6;


METHOD 1:  Brute Force
           1. Sizes of all possible sub-arrays are from 1...n;
           2. For each size Find the max size of sum all possible sub-arrays of that size
           3. Find the maximum of all sizes of sub-arrays

           ALGORITHM :
               fun(arr[], n) {
                    max_sum = -INF
                    for (sub_array_size -> 1 to n) {
                        for (startIndex -> 0 to n-1) {
                            if(startIndex + sub_array_size > n)
                                break;
                            sum = 0;
                            for (i -> startIndex to startIndex + sub_array_size)
                                sum = sum + arr[i];
                            max_sum = max(max_sum, sum);
                        }
                    }
                    return max_sum
               }

               Time complexity : O(n^3)


           OPTIMISED ALGORITHM :
                In this algorithm we take the already calculated sum previous (less size) sub-array to calculate next
                sub-array at each index.

                Example :

                For index 0 all possible sub-arrays
                Size                                              Sum
                1        -2                              =              -2
                2        -2, 1                           =  -2 + 1      -1
                3        -2, 1, -3                       =  -1 + -3     -4
                4        -2, 1, -3, 4                    =  -4 + 4       0
                5        -2, 1, -3, 4, -1                =  0 + -1      -1
                6        -2, 1, -3, 4, -1, 2             =  -1 + 2       1
                7        -2, 1, -3, 4, -1, 2, 1          =  1 + 1        2
                8        -2, 1, -3, 4, -1, 2, 1, -5      =  2 + -5      -3
                9        -2, 1, -3, 4, -1, 2, 1, -5, 4   =  -3 + 4       1


                For index 1 all possible sub-arrays
                Size                                           Sum
                1        1                           =              1
                2        1, -3                       =  1 + -3     -2
                3        1, -3, 4                    =  -2 + 4      2
                4        1, -3, 4, -1                =  2 + -1      1
                5        1, -3, 4, -1, 2             =  1 + 2       3
                6        1, -3, 4, -1, 2, 1          =  3 + 1       4
                7        1, -3, 4, -1, 2, 1, -5      =  4 + -5     -1
                8        1, -3, 4, -1, 2, 1, -5, 4   =  -1 + 4      3


                For index 2 all possible sub-arrays
                Size                                           Sum
                1        -3                       =             -3
                2        -3, 4                    =  -3 + 4      1
                3        -3, 4, -1                =  1 + -1      0
                4        -3, 4, -1, 2             =  0 + 2       2
                5        -3, 4, -1, 2, 1          =  2 + 1       3
                6        -3, 4, -1, 2, 1, -5      =  3 + -5     -2
                7        -3, 4, -1, 2, 1, -5, 4   =  -2 + 4      2

                For index 3 all possible sub-arrays
                Size                                        Sum
                1        4                    =              4
                2        4, -1                =  4 + -1      3
                3        4, -1, 2             =  3 + 2       5
                4        4, -1, 2, 1          =  5 + 1       6
                5        4, -1, 2, 1, -5      =  6 + -5      1
                6        4, -1, 2, 1, -5, 4   =  1 + 4       5

                etc....
                we found max 6 at 4, -1, 2, 1

                fun(arr[], n) {
                        max_sum = -INF
                        for (startIndex -> 0 to n-1) {
                            sum = 0;
                            for(sub_array_size -> 1 to n) {
                                if(startIndex + sub_array_size > n)
                                    break;
                                sum = sum + arr[startIndex + sub_array_size-1];
                                max_sum = max(sum, max_sum);
                            }
                        }
                        return max_sum;
                }

                Time complexity : O(n^2)

METHOD 2 : Divide and Conquer


METHOD 3 : Kadane's Algorithm