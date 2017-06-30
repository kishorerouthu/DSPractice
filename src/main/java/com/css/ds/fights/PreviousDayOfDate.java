package com.css.ds.fights;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Kishore Routhu on 29/5/17 2:20 PM.
 */
public class PreviousDayOfDate {


    static int numChar;
    static int curChar;
    static byte[] buffer = new byte[1024];
    static InputStream stream;
    static PrintWriter out;

    static Map<String, Integer> monthDaysMap = new HashMap<String, Integer>();

   static String months[] = new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
   static List mns = Arrays.asList(months);

    public static void main(String[] args) throws IOException {

        stream = System.in;
        out = new PrintWriter(System.out);

        int t = readInt();
        while (t > 0) {
            String s[] = readLine0().split(" ");
            int d = Integer.parseInt(s[0]);
            String m = s[1];
            int y = Integer.parseInt(s[2]);

            Date date = new Date();
            date.setYear(y%1900);
            date.setMonth(mns.indexOf(m)+1);
            date.setDate(d);

            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DATE, -30);
            Date previousDate = cal.getTime();
            DateFormat format = new SimpleDateFormat("dd MMM yyyy");
            String previousDay = format.format(previousDate);

            out.println(previousDay);

            t--;
        }

        out.flush();
        out.close();
    }

    private static String readLine0() throws IOException {
        StringBuilder buf = new StringBuilder ();
        int c = read ();
        while (c != '\n' && c != -1){
            if (c != '\r'){
                buf.appendCodePoint (c);
            }
            c = read ();
        }
        return buf.toString ();
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
}
