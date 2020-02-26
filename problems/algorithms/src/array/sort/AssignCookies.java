package array.sort;

import java.util.Arrays;
import java.util.TreeMap;

/**
 * 455
 * Assume you are an awesome parent and want to give your children some cookies.
 * But, you should give each child at most one cookie.
 * Each child i has a greed factor gi, which is the minimum size of a cookie that the child will be content with;
 * and each cookie j has a size sj. If sj >= gi, we can assign the cookie j to the child i, and the child i will be content.
 * Your goal is to maximize the number of your content children and output the maximum number.
 */
public class AssignCookies {

    // O(nlogn)
    // O(1)
    class Solution {
        public int findContentChildren(int[] g, int[] s) {
            Arrays.sort(g);
            Arrays.sort(s);
            int c = 0;
            for (int j = 0; c < g.length && j < s.length; j++)
                if (g[c] <= s[j]) c++;
            return c;
        }
    }

    // O(n)
    // O(n)
    class Solution2 {
        public int findContentChildren(int[] g, int[] s) {
            int count = 0;
            TreeMap<Integer, Integer> tree = new TreeMap<>();
            for (int i : s) tree.put(i, tree.getOrDefault(i, 0) + 1);
            for (int i : g) {
                Integer t = tree.ceilingKey(i);
                if (t != null) {
                    Integer num = tree.get(t);
                    if (num > 0) {
                        count++;
                        if (num == 1) tree.remove(t);
                        else tree.put(t, num - 1);
                    }
                }
            }
            return count;
        }
    }
}
