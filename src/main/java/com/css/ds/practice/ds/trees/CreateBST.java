package com.css.ds.practice.ds.trees;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

/**
 * @author Kishore Routhu on 12/11/17 8:45 PM.
 *
 * Create a Binary Search Tree from list A containing N elements.
 * Insert elements in the same order as given. Print the pre-order traversal of the subtree with root node data equal to
 * Q (inclusive of Q), separating each element by a space.
 *
 * Input:
 * First line contains a single integer N
 * N – number of elements.
 * Second line contains N
 * N space-separated integers.
 * Third line contains a single integer Q
 * Q – the element whose subtree is to be printed in pre-order form.
 *
 * Output:
 * Print K space-separated integers – where
 * K is the number of elements in the subtree of Q
 * Q (inclusive of Q)
 *
 * Constraints:
 * 1≤N≤103
 * −109≤A[i]≤109
 *
 * SAMPLE INPUT
 * 4
 * 2 1 3 4
 * 3
 *
 * SAMPLE OUTPUT
 * 3
 * 4
 */
public class CreateBST {

    static int numChar;
    static int curChar;
    static byte[] buffer = new byte[1024];
    static InputStream stream;
    static PrintWriter out;

    public static void main(String args[] ) throws Exception {

        stream = System.in;
        out = new PrintWriter(System.out);

        int n = readInt();
        int a[] = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = readInt();

        int q = readInt();
        Node bst = buildTree(a);
        Node qNode = searchTree(bst, q);
        printPreOrder(qNode);

        out.close();
    }

    private static int read() throws IOException {
        if (numChar <= curChar) {
            curChar = 0;
            numChar = stream.read(buffer);
            if (numChar <= 0) {
                return -1;
            }
        }
        return buffer[curChar++];
    }

    private static int readInt() throws IOException, InputMismatchException {
        return (int) readLong();
    }
    private static long readLong() throws IOException, InputMismatchException {
        int c = read();
        if (c == -1)
            throw new IOException();
        while (isSpaceChar(c)) {
            c = read();
        }
        boolean negative = false;
        if (c == '-') {
            negative = true;
            c = read();
        }
        long res = 0;
        while (!isSpaceChar(c)) {
            if (c < '0' || c > '9')
                throw new InputMismatchException();
            res *= 10;
            res += (c - '0');
            c = read();
        }
        if (negative)
            return -res;
        return res;
    }
    private static boolean isSpaceChar(int c) {
        return c == ' ' || c == '\n' || c == '\t' || c == '\r' || c == -1;
    }

    private static Node searchTree(Node root, int data) {
        if (root.data == data)
            return root;

        return data < root.data ? searchTree(root.left, data) : searchTree(root.right, data);
    }

    private static Node buildTree(int a[]) {
        Node root = new Node(a[0]);
        for (int i = 1; i < a.length; i++)
            addNodeToTree(root, a[i]);
        return root;
    }

    private static void addNodeToTree(Node root, int data) {
        if (data < root.data) {
            if (root.left == null)
                root.left = createNode(data);
            else
                addNodeToTree(root.left, data);
        } else {
            if (root.right == null)
                root.right = createNode(data);
            else
                addNodeToTree(root.right, data);
        }
    }

    private static Node createNode(int data) {
        return new Node(data);
    }

    private static void printPreOrder(Node root) {
        out.println(root.data);

        if (root.left != null) {
            printPreOrder(root.left);
        }

        if (root.right != null) {
            printPreOrder(root.right);
        }
    }

    private static class Node {
        public int data;
        public Node left;
        public Node right;

        public Node(int data) {
            this.data = data;
        }
    }
}
