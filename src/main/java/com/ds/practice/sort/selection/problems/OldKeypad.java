package com.ds.practice.sort.selection.problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by kishore on 16/12/16.
 *
 * Some people remain old fashioned and John is one of them. He doesn't like the new smart phones with full keypads and still uses the old keypads which require you
 * to tap a key multiple times to type a single letter. For example, if the keyboard has two keys, one with the letters "adef" and the other one with the letters "zyx",
 * then typing 'a' requires one keystroke, typing 'f' requires four keystrokes, typing 'y' requires two keystrokes, and so on.
 *
 * He recently moved to a new country where the language is such that his keypad is not the most efficient.
 * In every language some characters occur more often than others. He wants to create a specific keyboard for this language that uses N different letters.
 * He has a large body of text in this language, and has already analyzed it to find the frequencies of all N letters of its alphabet.
 *
 * You are given an array 'frequencies' with N elements. Each element of frequencies is the number of times one of the letters in the new language appears in the text John has.
 * Each element of frequencies will be strictly positive. (I.e., each of the N letters occurs at least once.)
 *
 * You are also given an array keySize. The number of elements of keySize is the number of keys on the keyboard.
 * Each element of keySize gives the maximal number of letters that maybe put on one of the keys.
 *
 * Find an assignment of letters to keys that minimizes the number of keystrokes needed to type the entire text.
 * Output that minimum number of keystrokes. If there is not enough room on the keys and some letters of the alphabet won't fit, Output -1 instead.
 *
 * Input Format
 * The first line will contain a number 'N' that specifies the size of 'frequencies' array
 * The second line will contain N numbers that form the frequencies array
 * The third line contains a number 'K' that specifies the size of the 'keySize' array
 * The fourth line contains K numbers that form the keySize array
 *
 * Output Format
 * Output a single integer that is answer to the problem.
 *
 * Constraints
 * frequencies will contain between 1 and 50 elements, inclusive.
 * Each element of frequencies will be between 1 and 1,000, inclusive.
 * keySizes will contain between 1 and 50 elements, inclusive.
 * Each element of keySizes will be between 1 and 50, inclusive.
 *
 * SAMPLE INPUT
 * 4
 * 7 3 4 1
 * 2
 * 2 2
 *
 * SAMPLE OUTPUT
 * 19
 *
 * Explanation
 * The foreign language has four letters. Let us call them W, X, Y and Z. John's text contains seven Ws, three Xs, four Ys, and one Z. The keyboard has two keys,
 * each of them may contain at most two letters. One optimal solution is to use the keys "WZ" and "YX". We can then type each W and each Y using a single keystroke,
 * and we need two keystrokes for each X and each Z. Therefore, the total number of keystrokes when typing the entire text will be 71 + 32 + 41 + 12 = 19.
 *
 * ALGORITHM
 *
 * 7 3 4 1
 * W X Y Z
 *
 * Step 1: Sort the given frequencies of N letters in descending order.
 *          7 4 3 1
 *          W Y X Z
 *
 * Step 2: Place the first K (number of keys) letters in first place of the key, so that highest frequency
 *         numbers will get less strokes.
 *          K1    K2
 *          W     Y
 *
 * Step 3 : Repeat step2 for the next K letters in second place of the key, so that these keys get 2 keystrokes.
 *          K1    K2
 *          WX    YZ
 *
 *          7*1 + 3*2 + 4*1 + 1*2 = 19
 */

public class OldKeypad {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        byte N = Byte.parseByte(br.readLine());
        int fre_arr[] = new int [N];
        String fre_line[] = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            fre_arr[i] = Integer.parseInt(fre_line[i]);
        }

        byte K = Byte.parseByte(br.readLine());
        byte key_arr[] = new byte[K];
        String key_line[] = br.readLine().split(" ");
        int possible_keys = 0;
        for (int j = 0; j < K; j++) {
            key_arr[j] = Byte.parseByte(key_line[j]);
            possible_keys += key_arr[j];
        }

        if (N > possible_keys) {
            System.out.println("-1");
            return;
        }

        sort(fre_arr);

        int output = 0;
        int passes = 1;
        int free_count = 0;
        while (free_count < N) {
            for (int l = 0; l < K; l++) {
                if (key_arr[l] > 0 && free_count < N) {
                    output += passes * fre_arr[free_count++];
                    key_arr[l] = (byte) (key_arr[l] - 1);
                }
            }
            passes++;
        }

        System.out.println(output);
    }

    private static void sort(int a[]) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int max = i;
            int j = i+1;
            while (j < n) {
                if (a[j] > a[max])
                    max = j;
                j++;
            }

            if (max != i) {
                int tmp = a[max];
                a[max] = a[i];
                a[i] = tmp;
            }
        }
    }
}

