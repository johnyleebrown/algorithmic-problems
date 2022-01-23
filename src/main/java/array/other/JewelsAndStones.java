package array.other;

import java.util.HashSet;
import java.util.Set;

/**
 * 771
 *
 * ======
 *
 * Task.
 *
 * You're given strings J representing the types of stones that are jewels, and
 * S representing the stones you have.  Each character in S is a type of stone
 * you have.  You want to know how many of the stones you have are also jewels.
 *
 * The letters in J are guaranteed distinct, and all characters in J and S are
 * letters. Letters are case sensitive, so "a" is considered a different type of
 * stone from "A".
 *
 * ======
 *
 * Source: Leetcode
 */
public class JewelsAndStones {
    public static class Solution {
        public int numJewelsInStones(String j, String s) {
            Set<Character> jj = new HashSet<>();
            for (char c : j.toCharArray()) jj.add(c);
            int ans = 0;
            for (char c : s.toCharArray()) if (jj.contains(c)) ans++;
            return ans;
        }
    }
}