package com.css.ds.practice.algorithms.sort.merge.problems;

import java.util.*;
import java.io.*;

/**
 * Created by kishore on 10/2/17.
 */
public class ScoringInExam1 {

    public static void main(String[] args) {
        InputReader sc = new InputReader(System.in);
        int nQuestions = sc.readInt();
        int nQueries = sc.readInt();
        Question[] questions = new Question[nQuestions];
        for(int i=0;i<nQuestions;i++) {
            int timeTaken = sc.readInt();
            questions[i] = new Question(timeTaken);
        }
        MergeSort.sort(questions, new Question[nQuestions], nQuestions);
        sc.readInt();
        for(int i=1;i<nQuestions;i++) {
            questions[i].score = sc.readInt();
            questions[i].timeTaken += questions[i - 1].timeTaken;
        }
        StringBuffer output = new StringBuffer();
         System.out.println(output);
    }

    static class MergeSort {

        public static void sort(Question[] A, Question[] B, int N) {
            B = Arrays.copyOf(A, N);
            splitMerge(B, 0, N, A);
        }

        private static void splitMerge(Question[] B, int low, int high, Question[] A) {
            if(high - low < 2) {
                return;
            }
            int mid = (low + high) / 2;
            splitMerge(A, low, mid, B);
            splitMerge(A, mid, high, B);
            merge(B, low, mid, high, A);
        }

        private static void merge(Question[] A, int low, int mid, int high, Question[] B) {
            int i = low, j = mid;
            for(int k = low; k < high; k++) {
                if(i < mid && (j >= high || A[i].timeTaken > A[j].timeTaken)) {
                    B[k] = A[i];
                    i++;
                }else{
                    B[k] = A[j];
                    j++;
                }
            }
        }
    }


    static class Question implements Comparable<Question>{

        int score;
        int timeTaken;

        public Question(int timeTaken) {
            this.timeTaken = timeTaken;
        }

        public Question(int score, int timeTaken) {
            this.score = score;
            this.timeTaken = timeTaken;
        }

        public int compareTo(Question that) {
            return that.timeTaken - this.timeTaken;
        }

    }

    static class InputReader {

        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;
        private SpaceCharFilter filter;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        public int read() {
            if (numChars == -1)
                throw new InputMismatchException();
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0)
                    return -1;
            }
            return buf[curChar++];
        }

        public int readInt() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public String readString() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (!isSpaceChar(c));
            return res.toString();
        }

        public boolean isSpaceChar(int c) {
            if (filter != null)
                return filter.isSpaceChar(c);
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        public String next() {
            return readString();
        }

        public interface SpaceCharFilter {
            public boolean isSpaceChar(int ch);
        }
    }
}
