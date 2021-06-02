package com.css.ds.practice.string;

import java.util.LinkedList;
import java.util.Queue;

public class JavaVariable {

    public static void main(String[] args) {
        String s = "thisIsVariable";
        String variable = convertToVariable(s);
        System.out.println("Variable :: " + variable);
        s = convertToString(variable);
        System.out.println("String :: " + s);
    }

    /**
     * This will convert variable to string format
     * I/P : this_is_variable
     * O/P : thisIsVariable
     */
    private static String convertToString(String s) {
        Queue<Character> q = new LinkedList<>();
        char c[] = s.toCharArray();
        boolean isSpecial = false;
        for(char ch : c) {
            if (ch == '_')
                    isSpecial = true;
            else if(isSpecial) {
                q.add(Character.toUpperCase(ch)); isSpecial = false;
            } else {
                q.add(ch);
            }
        }
        StringBuilder sb = new StringBuilder();
        q.stream().forEach(sb::append);
        return sb.toString();
    }

    /**
     * This will convert string to variable format
     * I/P : thisIsVariable
     * O/P : this_is_variable
     */
    private static String convertToVariable(String s) {
        Queue<Character> q = new LinkedList<>();
        char c[] = s.toCharArray();
        for (char ch : c) {
            if(Character.isUpperCase(ch)) {
                q.add('_'); q.add(Character.toLowerCase(ch));
            } else
                q.add(ch);
        }
        StringBuilder sb = new StringBuilder();
        q.stream().forEach(sb::append);
        return sb.toString();
    }
}
