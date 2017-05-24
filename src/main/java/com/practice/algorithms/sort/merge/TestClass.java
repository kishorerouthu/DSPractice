package com.practice.algorithms.sort.merge;

/**
 * Created by kishore on 8/3/17.
 */
/* IMPORTANT: Multiple classes and nested static classes are supported */

import java.util.Scanner;

class TestClass {
    static long counter=0;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int N= s.nextInt();

        int a[]= new int[N];
        for(int i=0;i<N;i++)
        {
            a[i]=s.nextInt();
        }


        merge_sort(a,0,N-1);

        System.out.println(counter);


    }

    public static void merge_sort(int A[], int start, int end)
    {

        if(start < end)
        {
            int mid = (start+end)/2;

            merge_sort(A, start, mid);
            merge_sort(A, mid+1, end);

            merge(A,start, mid,end);


        }

    }
    public static long merge(int[] a, int start, int mid, int end) {
        int p=start, q=mid+1;
        int n=(end-start)+1;
        int a2[]= new int[n];
        int k=0;

        for(int i=start;i<=end;i++)
        {
            if(p> mid){
                a2[k++]=a[q++];

            }

            else if(q> end)
                a2[k++]=a[p++];

            else if(a[p]>a[q]){
                a2[k++]=a[q++];
                counter+=(long)(mid-p+1);

            }

            else{
                a2[k++]=a[p++];

            }
        }
        for(int m=0;m<k;m++)
        {
            a[start++]=a2[m];
        }


        return counter;
    }
}