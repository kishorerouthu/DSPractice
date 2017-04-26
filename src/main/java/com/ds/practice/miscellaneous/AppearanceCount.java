package com.ds.practice.miscellaneous;

import java.util.Arrays;

/**
 * Created by kishore on 14/4/17.
 *
 * From past many years, militant infiltration is increasing with every passing day, especially in J &amp; K region. During a battle between          * militants and a group of Indian soldiers named Team Alpha, a soldier mistakenly crosses the Pakistan border, while following one of the militants. * After some time, he realises that he has entered to other country territory but it was too late till that time. As he cannot go back, he tries to * find a new way to return to India. After hiding almost for a day in Pakistan, he is, finally, able to contact his team
 * via a satellite phone.
 * Team Alpha uses a very unique and tricky pattern for finding the place during search operations. Soldier knows that pattern and uses encoding      * trick for telling the team about his location, so that his location cannot be traced by the enemies. Soldier sends his location altitude and      * longitude in small chunks and each chunk is difficult to crack. When his team gets the first chunk,
 * they start solving it and crack the first digit of his location.
 * He sends his team 2 strings with glyphs, first is a special word W formed with g glyphs and another sequence S of glyphs.
 * By finding the count of number of possible appearances of the word W in sequence S, they will be able to get the first digit of his location.     * Similarly, he keeps sending this encoding chunk, multiple times, so that his full location can be tracked by his team.
 *
 * You have been given the glyphs for W and the sequence S of glyphs in the message. You need to help them count the number of possible
 * appearances of W in S.
 * That is, every sequence of consecutive g glyphs in S that is a permutation of the glyphs in W.
 *
 * Input Format
 * Input 1: It will be the integer, which tells the number of glyphs in W1
 * Input 2: It will be the integer which tells the number of glyphs in the sequence S,g < |S|<= 3000000
 * Input 3: It will be a string, which contains g consecutive character that represent the glyphs in W. Valid characters are a - z and A - Z ; 	    * uppercase and lowercase characters are considered different.
 * Input 4: It will be a string which contains |S| consecutive characters that represent the glyphs in the message.
 * Valid characters are a - z and A - Z ; uppercase and lowercase characters are considered different.
 *
 * Constraints
 * 1<= g <= 3000
 * S,g <= |S| <= 3000000
 *
 * Output Format
 * It will be the integer which tells the count of possible appearances of W in S.
 *
 * Sample Test Cases
 * Test case 1
 *
 * Sample Input
 * 4
 * 11
 * cAda
 * AbrAcadAbRa
 * Sample Output
 * 2
 *
 * Explanation
 * Here, 2 possible sequence that can be found in string S are Acad , cadA.
 *
 * Test case 2
 * Sample Input
 * 6
 * 102
 * MexIco
 * MexIcobMexIcobFILbMexIcobMexIcobjMexIcobvMexIcobMexIcobMexIcobgMexIcobdMexIcobZVMexIcobMexIcobaMexIcob
 *
 * Sample Output
 * 13
 *
 */
public class AppearanceCount {

    public static void main(String[] args) {
        System.out.println(matchCount(5, 11, "cAdaA", "AbrAcadAbRa"));
    }

    public static void main1(String[] args) {
        System.out.println("cAda".indexOf('A'));
    }

    private static int matchCount(int wc, int sc, String w, String s) {
        int matchCount = 0;
        for (int i=0; i<sc; i++) {
            if (w.indexOf(s.charAt(i)) != -1) {
                int[] index = new int[58];
                Arrays.fill(index, -1);
                if (isSequenceFound(w, s, i + 1, wc, sc, index))
                    matchCount++;
            }
        }
        return matchCount;
    }

    private static boolean isSequenceFound(String w, String s, int i, int wc, int sc, int index[]) {
        while (wc > 1 && i < sc) {
            char ch = s.charAt(i);
            int chIndex = w.indexOf(ch);
            if (chIndex == -1) {
                break;
            } else if(index[122-ch] != -1) {
                chIndex = w.indexOf(ch, index[122-ch]);
                if (chIndex == -1) break;
                else index[122-ch] = chIndex;
            } else {
                index[122-ch] =  chIndex;
            }


            wc--;
            i++;
        }
        return wc == 1;
    }

}
