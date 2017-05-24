package com.practice.miscellaneous;

import java.util.ArrayList;
import java.util.List;

/**
 * This program demonstrate the appending of string such that all character at
 * give index will be in sequence.
 * Example :
 *      S1 = "ONE"
 *      S2 = "TWO"
 *      S3 = "THREE"
 *   OUTPUT : OTTNWHEOREE
 *
 * Created by Kishore Routhu on 17/9/16 10:52 AM.
 */
public class StringAppender {

    private static String appendStringsOnIndexes() {
        final String s1 = "ONE";
        final String s2 = "TWO";
        final String s3 = "THREE";

        int len1 = 0;
        int len2 = 0;
        int len3 = 0;

        List<Character> list = new ArrayList();

        while ( len1 != s1.length() || len2 != s2.length() || len3 != s3.length() ) {

            if (len1 != s1.length()) {
                list.add(s1.charAt(len1++));
            }

            if (len2 != s2.length()) {
                list.add(s2.charAt(len2++));
            }

            if (len3 != s3.length()) {
                list.add(s3.charAt(len3++));
            }
        }

        StringBuffer sb = new StringBuffer();
        for (Character c : list) {
            sb.append(c);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(appendStringsOnIndexes());
    }
}
