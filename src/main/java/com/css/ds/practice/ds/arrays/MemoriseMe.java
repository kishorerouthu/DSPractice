package com.css.ds.practice.ds.arrays;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.css.ds.io.IOProvider;

/**
 * Created by Kishore Routhu on 17/6/17 12:45 PM.
 *
 * Arijit is a brilliant boy. He likes memory games.
 * He likes to participate alone but this time he has to have a partner.
 * So he chooses you.
 *
 * In this Game , your team will be shown N numbers for few minutes .
 * You will have to memorize these numbers.
 *
 * Now, the questioner will ask you Q queries, in each query He will give you a number ,
 * and you have to tell him the total number of occurrences of that number in the array
 * of numbers shown to your team . If the number is not present ,
 * then you will have to say “NOT PRESENT” (without quotes).
 *
 * INPUT And OUTPUT
 *
 * The first line of input will contain N, an integer, which is the total number of numbers shown to your team.
 *
 * The second line of input contains N space separated integers .
 *
 * The third line of input contains an integer Q , denoting the total number of integers.
 *
 * The Next Q lines will contain an integer denoting an integer,
 * Bi , for which you have to print the number of occurrences of that number (Bi) in those N numbers on a new line.
 *
 * If the number Bi isn’t present then Print “NOT PRESENT” (without quotes) on a new line.
 *
 * CONSTRAINTS
 * 1≤N≤10^5
 * 0≤Bi≤1000
 * 1≤Q≤10^5
 *
 * SAMPLE INPUT
 * 6
 * 1 1 1 2 2 0
 * 6
 * 1
 * 2
 * 1
 * 0
 * 3
 * 4
 *
 * SAMPLE OUTPUT
 *
 * 3
 * 2
 * 3
 * 1
 * NOT PRESENT
 * NOT PRESENT
 */

public class MemoriseMe {

    public static void main(String[] args) throws IOException {

        IOProvider io = IOProvider.getConsoleInstance();
        int n = io.readInt();
        Map<Integer, Integer> lookup = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            int v = io.readInt();
            Integer count = lookup.get(v);
            count = count == null ? 0 : count;
            count++;
            lookup.put(v, count);
        }

        int q = io.readInt();
        while (q > 0) {
            int qi = io.readInt();
            Integer v = lookup.get(qi);
            io.println(v == null? "NOT PRESENT" : v);
            q--;
        }
        io.finish();
    }
}
