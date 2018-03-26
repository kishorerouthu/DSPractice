package com.css.ds.practice.ds.trees.problems;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

/**
 * @author Kishore Routhu on 12/11/17 10:22 PM.
 *
 * Once Monk was watching a fight between an array and a tree, of being better.
 * Tree got frustrated and converted that array into a Binary Search Tree by
 * inserting the elements as nodes in BST, processing elements in the given
 * order in the array. Now Monk wants to know the height of the created Binary Search Tree.
 *
 * Help Monk for the same.
 * Note:
 * 1) In Binary Search Tree, the left sub-tree contains only nodes with values
 * less than or equal to the parent node; the right sub-tree contains only nodes
 * with values greater than the parent node.
 *
 * 2) Binary Search Tree with one node, has height equal to 1
 *
 *
 * Input Format :
 * The first line will consist of 1 integer N, denoting the number of elements in the array.
 * In next line, there will be N space separated integers,
 * A[i] , where 1≤i≤N, denoting the elements of array.
 *
 * Output Format
 * Print the height of the created Binary Search Tree.
 *
 * Constraints: :
 * 1≤N≤103
 * 1≤A[i]≤106
 * SAMPLE INPUT
 * 2 1 3 4
 * 4
 *
 */
public class MonckWatchingFight {


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

        Node bst = buildTree(a);
        out.println(Math.max(getLeftHeightOfTree(bst), getRightHeightOfTree(bst)));

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

    private static Node buildTree(int a[]) {
        Node root = createNode(a[0]);
        for (int i = 1; i < a.length; i++)
            addNodeToTree(root, a[i]);
        return root;
    }

    private static void addNodeToTree(Node root, int data) {
        if (data <= root.data) {
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

    private static int getLeftHeightOfTree(Node root) {
        if (root.left == null)
            return 1;
        return 1 + getLeftHeightOfTree(root.left);
    }

    private static int getRightHeightOfTree(Node root) {
        if (root.right == null)
            return 1;
        return 1 + getRightHeightOfTree(root.right);
    }

    private static void inOrder(Node root) {
        if (root.left != null) {
            inOrder(root.left);
        }


        inOrder(root.right);
    }

    private static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }
    }
}
