package com.css.ds.practice.algorithms.sort.quick.problems;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by kishore on 27/2/17.
 */
public class FredoNotifSystem {

    public static void main(String[] args) {

        InputReader in = new InputReader(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int n = in.readInt();
        int priorities[] = new int[n];
        for (int i = 0; i < n; i++) {
            priorities[i] = in.readInt();
        }

        int intervals = in.readInt();
        for (int i = 0; i < intervals; i++) {
            int q = in.readInt();
            int u = in.readInt();


            List<Notification> notifications = new ArrayList<Notification>();
            for (int k = 0; k < q; k++) {
                int app_Index = in.readInt();
                int priority = priorities[app_Index-1];
                Notification notification = new Notification(app_Index, priority);
                if (!notifications.contains(notification))
                    notifications.add(notification);
            }

            for (int j = 0; j < u; j++) {
                int p_index = in.readInt()-1;
                int p_value = in.readInt();
                priorities[p_index] = p_value;
            }

            updatePriorities(notifications, priorities);

            List<Notification> notifications1 = new ArrayList<Notification>();
            notifications1.addAll(notifications);
            Collections.sort(notifications1, new Comparator<Notification>() {
                public int compare(Notification o1, Notification o2) {
                    return o2.getPriority() - o1.getPriority();
                }
            });

            for (Notification notification : notifications1) {
                out.printf("%d ",notification.getApp_index());
            }
            out.println();
        }

        out.flush();
        out.close();

    }

    private static void updatePriorities(List<Notification> notifications, int[] priorities) {
        for (Notification notification : notifications) {
            int app_index = notification.getApp_index();
            notification.setPriority(priorities[app_index-1]);
        }
    }

    static class Notification {

        int app_index;
        int priority;

        public Notification(int app_index, int priority) {
            this.app_index = app_index;
            this.priority = priority;
        }

        public int getApp_index() {
            return app_index;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Notification that = (Notification) o;

            return app_index == that.app_index;
        }

        @Override
        public int hashCode() {
            return app_index;
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
        public final long readLong() {
            int c = 0;
            c = read();

            while (isSpaceChar(c)) {
                c = read();
            }
            boolean negative = false;
            if (c == '-') {
                negative = true;
                c = read();
            }
            long res = 0;
            do {
                if (c < '0' || c > '9');
                res *= 10;
                res += (c - '0');
                c = read();
            } while (!isSpaceChar(c));
            return negative ? (-res) : (res);
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
