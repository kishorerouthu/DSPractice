package com.css.ds.practice.ds.arrays;

import java.io.IOException;

import com.css.ds.io.IOProvider;

/**
 * Created by Kishore Routhu on 17/6/17 11:38 AM.
 */
public class BinaryQueries {

    public static void main(String[] args) throws IOException {

        IOProvider io = IOProvider.getConsoleInstance();
        int n = io.readInt();
        int q = io.readInt();
        byte bits[] = new byte[n];
        for (int i = 0; i < n; i++)
            bits[i] = io.readByte();

        for (int i = 0; i < q; i++) {
            int q_type = io.readInt();
            if (q_type == 1) {
                int sbit = io.readInt()-1;
                bits[sbit] = (byte)((bits[sbit] == 0)? 1 : 0);
            } else {
                io.readInt();
                io.println(bits[io.readInt()-1] == 0? "EVEN" : "ODD");
            }
        }
        io.finish();
    }
}
