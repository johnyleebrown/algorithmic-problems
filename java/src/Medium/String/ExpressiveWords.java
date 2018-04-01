package Medium.String;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 809
 *
 * Sometimes people repeat letters to represent extra feeling, such as
 * "hello" -> "heeellooo", "hi" -> "hiiii".  Here, we have groups, of
 * adjacent letters that are all the same character, and adjacent
 * characters to the group are different.  A group is extended if that
 * group is length 3 or more, so "e" and "o" would be extended in the
 * first example, and "i" would be extended in the second example.
 * As another example, the groups of "abbcccaaaa" would be "a", "bb",
 * "ccc", and "aaaa"; and "ccc" and "aaaa" are the extended groups of
 * that string. For some given string S, a query word is stretchy if
 * it can be made to be equal to S by extending some groups.  Formally,
 * we are allowed to repeatedly choose a group (as defined above) of
 * characters c, and add some number of the same character c to it so
 * that the length of the group is 3 or more.  Note that we cannot
 * extend a group of size one like "h" to a group of size two like
 * "hh" - all extensions must leave the group extended - ie., at
 * least 3 characters long. Given a list of query words, return the
 * number of words that are stretchy.
 *
 * Example:
 * Input: S = "heeellooo" words = ["hello", "hi", "helo"]
 * Output: 1
 *
 * Explanation:
 * We can extend "e" and "o" in the word "hello" to get "heeellooo".
 * We can't extend "helo" to get "heeellooo" because the group "ll" is not extended.
 *
 * Notes:
 * 0 <= len(S) <= 100.
 * 0 <= len(words) <= 100.
 * 0 <= len(words[i]) <= 100.
 * S and all words in words consist only of lowercase letters
 */
public class ExpressiveWords {
    /**
     * Time complexity: O()
     * Space complexity: O()
     */
    static class Solution {
        public static int expressiveWords(String S, String[] words) {
            Queue<int[]> q = new LinkedList<>();
            for (int i = 0 ; i < S.length() ; i++) {
                int[] a = new int[]{S.charAt(i), 1};
                while (i < S.length() - 1 && a[0] == S.charAt(i + 1)) { i++; a[1]++;}
                q.add(a);
            }

            for (int[] a : q) {
                System.out.println((char)a[0] + " " + a[1]);
            }

            int count = 0;
            for (int i = 0 ; i < words.length ; i++) {
                String w = words[i];
                int j = 0;
                boolean flag = true;
                for (int[] a : q) {
                    if (j >= w.length()) {flag = false; break;}
                    if (a[1] == 1) {
                        // check if the sub strings are equals
                        if (j < w.length() && w.charAt(j) != a[0]) {flag = false; break;}
                        j++;
                    } else if (a[1] == 2) {
                        // check if the sub strings are equals
                        if (j < w.length() && (w.charAt(j) != a[0] || w.charAt(j + 1) != a[0])) {flag = false; break;}
                        j++;
                        j++;
                    } else {
                        // move to the next letter in w
                        int k = j;
                        while (j < w.length() && w.charAt(j) == a[0]) j++;
                        if (j - k > a[1]) {flag = false; break;}
                    }
                }
                if (flag && j >= w.length()) count++;
            }

            System.out.println(count);
            return count;
        }
    }

    static class Solution2 {
        public static int expressiveWords(String S, String[] words) {
            Queue<int[]> q = new LinkedList<>();
            for (int i = 0 ; i < S.length() ; i++) {
                int[] a = new int[]{S.charAt(i), 1};
                while (i < S.length() - 1 && S.charAt(i) == S.charAt(i + 1)) { i++; a[1]++;}
                q.add(a);
            }

            for (int[] a : q) {
                System.out.println((char)a[0] + " " + a[1]);
            }

            int count = 0;


            System.out.println(count);
            return count;
        }
    }

    public static void main(String[] args) {
        Solution.expressiveWords("abcd", new String[]{"abc"});
    }
    /*
"heeellooo"
["hello", "hi", "helo"]
"hellooo"
["hello", "hi", "helo"]
"heeell"
["hello", "hi", "helo"]
"heeellooo"
["heeeello", "hi", "helo"]
"abcd"
["abc"]
     */
}
