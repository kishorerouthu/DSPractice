package com.practice.algorithms.sort.merge.problems;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *Today is the 25th anniversary of Berland International School in Berland. On this auspicious Occasion, our friend Monk has been given the *responsibility of preparing the Inventory for his school.
 *There are exactly N teachers and M students in the school. Each of these teachers teaches arbitary number of students. However, each student is *taught by exactly one teacher.
 *Monk has been given the task of finding out for each teacher the Students he/she teaches. For each student Monk has been given the Student's Name *and Age. However , Monk is too busy , So he has assigned this task to us.
 *We need to print the name of the Teacher and the Name and age of the students that he/she teaches.
 *However, there is a catch here. We need to print the list of students of each Teacher in Lexicographical order of their names . That is list of the *teacher with lexicographically smaller name will appear before other teachers with lexicographically greater names.
 *In Addition , The students appearing in a particular teachers list should appear in Increasing order of their Age.
 *
 *Input Format :
 *
 *The first line contains two integers N and M denoting the number of Teachers and number of Students respectively. Each of the next
 *N lines contain a single string denoting a Teachers name.It is guaranteed that each teachers name shall be unique. The next
 *M lines contain 2 Strings and an Integer, denoting the Teachers name, a Student's name being taught by that Teacher and that Student's Age. It is *guaranteed that each Student's name shall be unique and shall appear only once in the Input.
 *
 *Output Format:
 *
 *Print
 *N+M lines . Print the teachers name first and then the name and age of each student taught by this teacher. The list of each teacher should appear *in order of their lexicographical rank in comparision to all other teachers. For example the list of the teacher with lexicographically smallest *name should appear first, then the list of the teacher with the 2nd smallest lexicographical name and so on.
 * The students in a particular teachers list should appear in the output in Increasing order of their Age.
 *
 *Constraints:
 *1≤N≤100
 *1≤M≤10^5
 *1≤Age of Each Student≤10^6
 *1≤Length of each Student and Teachers Name≤35
 *
 *The Name of Each Teacher and Student will consist of Lowercase English Alphabets only.
 *
 *It is guaranteed that no two students with the same age shall appear in the same Teacher's List.
 *
 * Hint : You Need to Use Comparable Interface Here.
 *
 * SAMPLE INPUT
 * 3 4
 * vasya
 * petya
 * kolya
 * vasya errichto 21
 * kolya petr 22
 * petya egor 19
 * vasya tourist 19
 *
 * SAMPLE OUTPUT
 * kolya
 * petr 22
 * petya
 * egor 19
 * vasya
 * tourist 19
 * errichto 21
 *
 * Created by kishore on 27/1/17.
 */
public class MonksSchool {

    public static void main(String[] args) throws IOException {

        PrintWriter out = new PrintWriter(System.out);
        InputReader in = new InputReader(System.in);

        int N = in.readInt(); //no of teachers
        int M = in.readInt(); //no of students

        Map<String, Teacher> teacherMap = new HashMap();
        for (int i=0; i<N; i++) {
            String name = in.readString();
            teacherMap.put(name, new Teacher(name)); //name of teacher
        }

        for (int j=0; j<M; j++) {
            String teacherName = in.readString();
            String studentName = in.readString();
            int age = in.readInt();

            Teacher teacher = teacherMap.get(teacherName);
            teacher.getStudents().add(new Student(age, studentName));
        }

        SortedSet<Teacher> teachers = new TreeSet<Teacher>(teacherMap.values());
        for (Teacher teacher : teachers) {
            out.println(teacher.getName());
            for (Student student : teacher.getStudents()) {
                out.println(student.getName() + " " + student.getAge());
            }
        }
        out.flush();
        out.close();

    }


    private static class Teacher implements Comparable<Teacher> {

        private String name;
        private SortedSet<Student> students;

        Teacher(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public SortedSet<Student> getStudents() {
            if (students == null)
                students = new TreeSet<Student>();
            return students;
        }

        public int compareTo(Teacher o) {
            return name.compareTo(o.getName());
        }
    }

    private static class Student implements Comparable<Student> {

        private int age;
        private String name;

        Student(int age, String name) {
            this.age = age;
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public String getName() {
            return name;
        }

        public int compareTo(Student o) {
            return this.age - o.getAge();
        }
    }


    static final class InputReader {

        private final InputStream stream;
        private final byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        private int read() throws IOException {
            if (curChar >= numChars) {
                curChar = 0;
                numChars = stream.read(buf);
                if (numChars <= 0) {
                    return -1;
                }
            }
            return buf[curChar++];
        }

        public final int readInt() {
            return (int) readLong();
        }

        public final long readLong() {
            int c = 0;
            try {
                c = read();
            } catch (IOException ex) {
            }
            while (isSpaceChar(c)) {
                try {
                    c = read();
                } catch (IOException ex) {
                }
            }
            boolean negative = false;
            if (c == '-') {
                negative = true;
                try {
                    c = read();
                } catch (IOException ex) {
                }
            }
            long res = 0;
            do {
                if (c < '0' || c > '9');
                res *= 10;
                res += (c - '0');
                try {
                    c = read();
                } catch (IOException ex) {
                }
            } while (!isSpaceChar(c));
            return negative ? (-res) : (res);
        }

        public final String readString() {
            int c = 0;
            try {
                c = read();
            } catch (IOException ex) {
            }
            while (isSpaceChar(c)) {
                try {
                    c = read();
                } catch (IOException ex) {
                }
            }
            StringBuilder res = new StringBuilder();
            do {
                res.append((char) c);
                try {
                    c = read();
                } catch (IOException ex) {
                }
            } while (!isSpaceChar(c));
            return res.toString();
        }

        private boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }
    }
}