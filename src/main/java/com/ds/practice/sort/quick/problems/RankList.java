package com.ds.practice.sort.quick.problems;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by kishore on 17/2/17.
 * Mid sem marks of a particular subject is announced , since you are curious in knowing your position in class so you decided to make a rank list .
 * You are given the name , scholoar number and marks of every student in your class. You have to come up with accurate rank list i.e student having    * maximum marks at the top and if two students are having same marks then the student having lexicographically smaller name comes first , if both name * and marks of the student collide then student having smaller scholar number comes first.
 *
 * Input:
 * First line of input contains N - Total number of students in class
 * Next N line contains name of student , scholar number and marks scored in exam .
 *
 * Output:
 * Print the ranklist of students as explained above.
 *
 * Constraints
 * 1 <= N <= 1000
 * 1 <= length of name <= 10
 * 1 <= scholar number <= 1000
 * 0 <= marks <= 30
 *
 * SAMPLE INPUT
 * 5
 * arun 8 28
 * harshit 10 30
 * surya 7 26
 * satyam 27 6
 * arun 1 28
 *
 * SAMPLE OUTPUT
 * harshit 10 30
 * arun 1 28
 * arun 8 28
 * surya 7 26
 * satyam 27 6
 *
 */
public class RankList {

   static PrintWriter out = new PrintWriter(System.out);
   static InputReader in = new InputReader(System.in);

    public static void main(String[] args) {

        int n = in.readInt();
        Set<Student> students = new TreeSet<Student>();
        for (int i = 0; i < n; i++) {
            String name = in.readString();
            int scholar = in.readInt();
            byte marks = (byte)in.readInt();
            Student student = new Student(name, scholar, marks);
            students.add(student);
        }

        for (Student student : students) {
            out.println(student);
        }

        out.flush();
        out.close();
    }

    private static class Student implements Comparable<Student> {

        private String name;
        private int scholar;
        private byte marks;

        public Student(String name, int scholar, byte marks) {
            this.name = name;
            this.scholar = scholar;
            this.marks = marks;
        }

        public String getName() {
            return name;
        }

        public int getScholar() {
            return scholar;
        }

        public byte getMarks() {
            return marks;
        }

        public int compareTo(Student o) {
            if (o.getMarks() != marks)
                return o.getMarks() - marks;

            if (name.compareTo(o.getName()) != 0)
                return name.compareTo(o.getName());

            return scholar - o.getScholar();
        }

        @Override
        public String toString() {
            return name + " " + scholar + " " + marks;
        }
    }

    static class InputReader {

        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;
        private InputReader.SpaceCharFilter filter;

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
