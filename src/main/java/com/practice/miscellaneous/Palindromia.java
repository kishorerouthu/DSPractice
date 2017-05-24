package com.practice.miscellaneous;

/**
 * Created by Kishore Routhu on 15/10/16 1:11 PM.
 */
public class Palindromia {

    public static void main(String[] args) {

    }

    private static boolean palnidrom(String s) {

        char[] chars = s.toCharArray();
        int i = 0;
        int j = s.length() - 1;
        int rev_count = 0;
        while(i != j) {
            if (chars[i] == chars[j]) {
                i++; j--;
            } else {
                i++;
            }
        }

        return rev_count <= 2;
    }
}
