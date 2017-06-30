package com.css.ds.practice.miscellaneous;

/**
 * Created by Kishore Routhu on 29/9/16 6:25 PM.
 * https://www.hackerearth.com/submission/5415298/
 *
 *  In an array, which consists of N elements, A1, A2, ..., AN, if a sub-array has the total
 *  number of distinct elements as that of the original array, that determines the presence of Team Rocket.
 *
 *  You've to help the normal type Poke'mons in figuring out the total number of sub-arrays having total number of
 *  distinct elements same as that of the original array.
 *
 * Input format:
 *  The first line of the input will consist of a single integer N.
 *  The next line will consist of N integers A1, A2, ... , AN.
 *
 *  Output format: Output the answer to the problem.
 *
 *  SAMPLE INPUT
 *  5
 *  1 2 2 1 1
 *
 *  SAMPLE OUTPUT
 *  8
 *
 *  Explanation :
 *  All the possible sub-arrays are [1,2] , [1,3] , [1,4] , [1,5] , [2,4] , [2,5] , [3,4] , [3,5]
 *  Note : [1,2] means 1 to 2 i.e indexes 0,1 in array. [1,3] it should be continuous indexes.
 *  [1,2]  -  {1, 2}
 *  [1,3]  -  {1, 2, 2}
 *  [1,4]  -  {1, 2, 2, 1}
 *  [1,5]  -  {1, 2, 2, 1, 1}
 *  [2,4]  -  {2, 2, 1}
 *  [2,5]  -  {2, 2, 1, 1}
 *  [3,4]  -  {2, 1}
 *  [3,5]  -  {2, 1, 1}
 *
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Pokemon {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long total=0;
        String line = br.readLine();
        int size=Integer.parseInt(line);
        String str[]=br.readLine().split(" ");
        int arr[]=new int[str.length];
        HashSet<Integer> hsor=new HashSet<Integer>();
        HashSet<Integer> hs;
        for(int i=0; i<arr.length;i++)
        {
            arr[i]=Integer.parseInt(str[i]);
            hsor.add(arr[i]);
        }
        int count=hsor.size();
        hs=new HashSet<Integer>();
        int temp=0;

        for(int i=0;i<size-count+1;i++)
        {
            hs.add(arr[i]);
            for(int j=i;j<size;j++)
            {

                hs.add(arr[j]);
                if(hs.size()==count)
                {

                    if(j>temp) temp=j;
                    total=total+(size-temp);
                    break;
                }
            }
            hs.remove(arr[i]);
        }

        System.out.println(total);

    }

}
