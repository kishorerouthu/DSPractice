package com.css.ds;

import java.util.Stack;

/**
 * @author Kishore Routhu on 21/8/17 8:50 PM.
 */
public class WordMachine {

    public static void main(String[] args) {
        //String s = "13 DUP 4 POP 5 DUP + DUP + -";
        //String s = "5 6 + -";
        String s = "3 DUP 5 - -";
        System.out.println(solution(s));
    }

    private static int solution(String s) {
        String ea[] = s.split(" ");
        Stack<Integer> stack = new Stack<Integer>();
        for (String e : ea) {
            if (e.equals("DUP")) {
                stack.push(stack.peek());
            } else if (e.equals("POP")) {
                if (stack.isEmpty())
                    return -1;
                stack.pop();
            } else if (e.equals("+")) {
                if (stack.size() < 2)
                    return -1;

                int a = stack.pop();
                int b = stack.pop();
                stack.push(a + b);
            } else if (e.equals("-")) {
                if (stack.size() < 2)
                    return -1;

                int a = stack.pop();
                int b = stack.pop();
                int r = a - b;
                if (r < 0)
                    return -1;
                stack.push(r);
            } else {
                stack.push(Integer.parseInt(e));
            }
        }
        return stack.isEmpty()? -1 : stack.pop();
    }
}
