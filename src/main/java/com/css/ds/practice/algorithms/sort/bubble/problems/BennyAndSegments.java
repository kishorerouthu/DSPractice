package com.css.ds.practice.algorithms.sort.bubble.problems;

import java.io.IOException;

import com.css.ds.io.IOProvider;

/**
 * Created by Kishore Routhu on 13/11/16 9:41 PM.
 */
public class BennyAndSegments {

    public static void main(String[] args) throws IOException {

        IOProvider io = IOProvider.getConsoleInstance();

        StringBuilder builder = new StringBuilder();
        int T = io.readInt();
        for (int i = 0; i < T; i++) {
            long N = io.readLong();
            long L = io.readLong();
            long first = 0;
            long last = 0;
            boolean found = false;
            for (int j = 0; j < N; j++) {
                long xl = io.readLong();
                long xr = io.readLong();

                if(first == 0 || (last != 0 && last != xl)) {
                    first = xl;
                }
                last = xr;
                if (!found && ((last - first) >= L)) {
                    found = true;
                }
            }
            if (i != 0)
                builder.append("\n");

            if (found)
                builder.append("Yes");
            else
                builder.append("No");
        }
        io.println(builder.toString());
        io.finish();
    }
}
