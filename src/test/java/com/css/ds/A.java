package com.css.ds;

/**
 * @author Kishore Routhu on 9/8/17 8:20 PM.
 */
public class A {
    private void method1() {
        System.out.println("A");
    }

    public static void main(String[] args) {
        A a = new B();
        a.method1();
    }
}

class B extends A {
    public void method1() {
        System.out.println("B");
    }
}