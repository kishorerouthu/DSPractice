package com.practice.miscellaneous;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kishore on 14/4/17.
 */
public class JumpWall {

    public static void main(String[] args) {
        int x = 5;
        int y = 1;
        int h[] = new int[]{9, 10};
        System.out.println(countJumps(x, y, h));
    }

    private static int countJumps(int x, int y, int[] h) {
        int count = 0;
        Map<String, Integer> lookup = new HashMap<String, Integer>();
        for (int wh : h)
            count += jumpWall(x, y, wh, lookup);
        return count;
    }

    private static int jumpWall(int x, int y, int h, Map<String, Integer> lookup) {
        String key =  x + "$" + y + "$" + h;

        if (!lookup.containsKey(key)) {
            if (x >= h)
                lookup.put(key,1);
            else
                lookup.put(key, 1 + jumpWall(x, y, h - (x - y), lookup));
        }

        return lookup.get(key);
    }
}
