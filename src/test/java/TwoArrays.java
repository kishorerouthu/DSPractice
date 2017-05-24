import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

/**
 * Created by kishore on 15/5/17.
 */
public class TwoArrays {


    static int numChar;
    static int curChar;
    static byte[] buffer = new byte[1024];
    static InputStream stream;
    static PrintWriter out;

    public static void main(String[] args) throws IOException {

        stream = System.in;
        out = new PrintWriter(System.out);

        int n = readInt();
        long a[] = new long[n];
        for (int i = 0; i < n; i++)
            a[i] = readLong();

        int q = readInt();
        List<Integer> queries = new ArrayList<Integer>();
        for (int j = 0; j < q; j++) {
            int type = readInt();
            int id = readInt();
            if (type == 1)
                queries.add(id);
            else
                a[id - 1] = readInt();
        }

        long[] b = buildArrayB(a, n);
        for (int query : queries)
            out.println(b[query-1]);

        out.flush();
        out.close();
    }

    private static long[] buildArrayB(long a[], int n) {
        long b[] = new long[n];
        for (int i = 0; i < n; i++) {
            b[i] = 1;
            for (int j = 0; j < n; j++)
                if (i != j)
                    b[i] = b[i] * a[j];
        }

        return b;
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

    private static int readInt() throws IOException, InputMismatchException {
        return (int) readLong();
    }

    private static boolean isSpaceChar(int c) {
        return c == ' ' || c == '\n' || c == '\t' || c == '\r' || c == -1;
    }
}
