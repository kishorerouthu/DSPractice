package com.ds.practice.sort.bubblesort.problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Kishore Routhu on 8/11/16 7:08 PM.
 */
public class SavePatients {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String number = br.readLine();
        int n = Integer.parseInt(number);

        long vaccin[] = new long[n];
        String vaccinStr[] = br.readLine().split(" ");

        long patient[] = new long[n];
        String patientStr[] = br.readLine().split(" ");

        for (int i = 0; i < n; i++) {
            vaccin[i] = Long.parseLong(vaccinStr[i]);
            patient[i] = Long.parseLong(patientStr[i]);
        }

        sort(vaccin, n);
        sort(patient, n);

        String result = "Yes";
        for (int j=0; j<n; j++) {
            if (vaccin[j] <= patient[j]) {
                result = "No";
                break;
            }
        }
        System.out.println(result);

    }

    private static void sort(long arr[], int n) {
        boolean sorted = false;
        int i = n-1;
        while(!sorted) {
            sorted = true;
            for (int j=0; j<i; j++){
                if(arr[j+1] < arr[j]) {
                    sorted = false;
                    long temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
            i--;
        }
    }
}
