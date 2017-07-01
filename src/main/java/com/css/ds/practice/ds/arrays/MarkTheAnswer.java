package com.css.ds.practice.ds.arrays;

import java.io.IOException;

import com.css.ds.io.IOProvider;

/**
 * Created by Kishore Routhu on 17/6/17 1:52 PM.
 *
 * Our friend Monk has an exam that has quite weird rules.
 * Each question has a difficulty level in the form of an Integer.
 * Now, Monk can only solve the problems that have difficulty level less than X .
 *
 * Now the rules are-
 * 	Score of the student is equal to the maximum number of answers he/she has attempted without skipping a question.
 * 	Student is allowed to skip just "one" question that will not be counted in the continuity of the questions.
 *
 * Note- Assume the student knows the solution to the problem he/she attempts and always starts the paper from first question.
 *
 * Given the number of Questions, N ,the maximum difficulty level of the problem Monk can solve , X ,
 * and the difficulty level of each question , Ai can you help him determine his maximum score?
 *
 * Input Format
 * First Line contains Integer N ,
 * the number of questions and the maximum difficulty X Monk can solve.
 *
 * Next line contains N integers,
 * Ai denoting the difficulty level of each question.
 *
 * Output Format
 * Maximum score Monk can achieve in the exam.
 *
 * Constraints
 * 1≤N≤10^5
 * 1≤X≤10^9
 * 1≤Ai≤1^09
 *
 * SAMPLE INPUT
 * 7 6
 * 4 3 7 6 7 2 2
 *
 * SAMPLE OUTPUT
 * 3
 *
 * Explanation
 * In this example, maximum difficulty = 6, Monk solves question 0 and 1,
 * but skips the question 2 as A[2]>6. Monk then solves the question 3 ,
 * but stops at 4 because A[4]>6 and question 2 was already skipped.
 * As 3 questions (0,1 and 3) were solved and 2 questions (2 and 4) have been skipped, therefore we print "3".
 *
 */
public class MarkTheAnswer {

    public static void main(String[] args) throws IOException {

        IOProvider io = IOProvider.getConsoleInstance();

        int n = io.readInt();
        int x = io.readInt();
        int solveCount = 0;
        int skipCount = 0;
        for (int i = 0; i < n; i++) {
            if (io.readInt() <= x && skipCount <= 1)
                solveCount++;
            else
                skipCount++;
        }
        io.println(solveCount);
        io.finish();
    }
}
