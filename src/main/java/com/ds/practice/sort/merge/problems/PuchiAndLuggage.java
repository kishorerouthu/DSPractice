package com.ds.practice.sort.merge.problems;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kishore on 30/1/17.
 *
 * Puchi hates to carry luggage, but unfortunately he got a job to carry the luggage of his N friends in office.
 * Each day, one of his N friends, gives him the luggage of a particular weight to carry.
 * You will be given the weight of luggage of each friend in the array Weight, where Weighti is the
 * weight of luggage of ith friend carried by Puchi on ith day.
 * It is given that all the luggages carried by Puchi are distinct in their weights.
 * As Prateek assigned this job to Puchi, so for each day,
 * he wants to know the number of days in future when Puchi will have to carry the luggage ,
 * having weight less than the weight of luggage of current day.
 *
 * Please help Prateek for the same.
 *
 * Input:
 * The first line contains a single integer T, denoting the number of test cases.
 * In each test case, the following input will be present:
 * First line contains an integer N, where N represents the number of friends.
 * Next N line contains N integers, where ith line contains ith integer, which represents Weighti.
 *
 * Output:
 * Output exactly T lines. Each line contains N integer separated by a space, where ith integer represents
 * the number of luggage of future, which are less than the weight of luggage of the current day.
 * Constraints:
 *
 * Subtask 1:
 * 1 <= T <= 30
 * 1<= N <= 104
 * 1<= Weighti <= 106
 * Subtask 2:
 * 1 <= T <= 10
 * 1<= N <= 105
 * 1<= Weighti <= 106

 * SAMPLE INPUT
 * 1
 * 4
 * 2
 * 1
 * 4
 * 3
 * SAMPLE OUTPUT
 * 1 0 1 0
 *
 * Explanation
 * In sample input, given T =1 in first line and N = 4 in next line.
 * In next 4 lines, Weighti i.e weight of luggage of each friend on ith day is given.
 *
 * For weight on first day i.e 2 ,future weights smaller than 2 are {1} . Therefore output for this weight will be 1.
 * For weight on second day i.e 1, no future weights are smaller than 1. So output for this weight will be 0.
 * For weight on third day i.e 4, future weights smaller than 4 are {3}. So output for this weight will be 1.
 * For weight on fourth day i.e 3, no future weights are smaller than 3. So output for this weight will be 0.
 * So the final output is {1, 0, 1, 0}.
 *
 */
public class PuchiAndLuggage {

    private static int TEST_CASE_COUNT = 0;

    public static void main1(String[] args) {
        PrintWriter out = new PrintWriter(System.out);
        InputReader in = new InputReader(System.in);

        long startTime = System.currentTimeMillis();
        int T = in.readInt();
        for (int i = 0; i < T; i++) {
            int N = in.readInt();
            int a[] = new int[N];
            for (int j = 0; j < N; j++) {
                a[j] = in.readInt();
            }
            out.println(findWeightsCount(a, N));
        }
        long endtime = System.currentTimeMillis();
        out.println("TOTAL-TIME " + (endtime - startTime));
        out.flush();
        out.close();

    }


    //Simple approach is to pass through array from 1 to n-1  2 times
    private static String findWeightsCount(int a[], int n) {
        long startTime = System.currentTimeMillis();
        StringBuffer sb = new StringBuffer();
        for (int i = 0 ; i < n; i++) {
            int count = 0;
            for (int j = i+1; j < n; j++) {
                if (a[j] < a[i])
                    count++;
            }
            if (sb.length() != 0)
                sb.append(" ");
            sb.append(count);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("TEST_CASE [" + TEST_CASE_COUNT +"] : " + (endTime - startTime));
        TEST_CASE_COUNT++;
        return sb.toString();

    }


    //Effective solution
      public static void merge(int st, int mid, int end, Integer[]arr, Map<Integer, Integer> freq) {

        int p = st;
        int q = mid+1;
        int count=0;
        Integer[] newArr = new Integer[end-st+1];
        while(p<=mid && q <=end) {
            if(arr[p] <= arr[q]) { //actually for this question equal condition will never happen
                Integer val = freq.get(arr[p]);
                freq.put(arr[p], val + (q-mid-1) );
                newArr[count++] = arr[p++];

            } else {
                newArr[count++] = arr[q++];
            }
        }
        while(p<=mid) {
            int val = freq.get(arr[p]);
            freq.put(arr[p], val + (q-mid-1) );
            newArr[count++] = arr[p++];
        }
        while(q<=end) {
            newArr[count++] = arr[q++];
        }
        count=0;
//       now copy the values to the original array
        for(int i=st;i<=end;i++) {
            arr[i] = newArr[count++];
        }
    }

    public static void mergeSort(int st, int end, Integer[]arr, Map<Integer, Integer> freq) {

        if(st<end) {
            int mid = (st+end)/2;
            mergeSort(st, mid, arr, freq);
            mergeSort(mid+1, end, arr, freq);
            merge(st, mid, end, arr, freq);
        }
    }

    public static void main(String []args) throws Exception{
        PrintWriter out = new PrintWriter(System.out);
        InputReader in = new InputReader(System.in);

        int t = in.readInt();
        for(int i=0;i<t;i++) {
            int n = in.readInt();
            Integer []arr = new Integer[n];
            Integer []copied = new Integer[n];

            Map<Integer, Integer> freq = new HashMap<Integer, Integer>();
            for (int j=0;j<n;j++) {
                arr[j] = in.readInt();
                copied[j] = arr[j];
                freq.put(arr[j], 0);
            }

            mergeSort(0, n-1, arr, freq);

            for (int j=0;j<n;j++) {
                out.print(freq.get(copied[j]) + " ");
            }
            out.println();
        }
        out.flush();
        out.close();
    }

    static final class InputReader {

        private final InputStream stream;
        private final byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        private int read() throws IOException {
            if (curChar >= numChars) {
                curChar = 0;
                numChars = stream.read(buf);
                if (numChars <= 0) {
                    return -1;
                }
            }
            return buf[curChar++];
        }

        public final int readInt() {
            return (int) readLong();
        }

        public final long readLong() {
            int c = 0;
            try {
                c = read();
            } catch (IOException ex) {
            }
            while (isSpaceChar(c)) {
                try {
                    c = read();
                } catch (IOException ex) {
                }
            }
            boolean negative = false;
            if (c == '-') {
                negative = true;
                try {
                    c = read();
                } catch (IOException ex) {
                }
            }
            long res = 0;
            do {
                if (c < '0' || c > '9');
                res *= 10;
                res += (c - '0');
                try {
                    c = read();
                } catch (IOException ex) {
                }
            } while (!isSpaceChar(c));
            return negative ? (-res) : (res);
        }

        public final String readString() {
            int c = 0;
            try {
                c = read();
            } catch (IOException ex) {
            }
            while (isSpaceChar(c)) {
                try {
                    c = read();
                } catch (IOException ex) {
                }
            }
            StringBuilder res = new StringBuilder();
            do {
                res.append((char) c);
                try {
                    c = read();
                } catch (IOException ex) {
                }
            } while (!isSpaceChar(c));
            return res.toString();
        }

        private boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }
    }

    //TODO Need to visualize the SOLUTION once
}
