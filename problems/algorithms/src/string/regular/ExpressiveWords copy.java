package string.regular;

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
     * LC 16ms
     * Time complexity: O(n*m)
     * Space complexity: O(n)
     */
    static class Solution {
        public static int expressiveWords(String S, String[] words) {
            Queue<int[]> q = new LinkedList<>();
            for (int i = 0 ; i < S.length() ; i++) {
                int[] a = new int[]{S.charAt(i), 1};
                while (i < S.length() - 1 && a[0] == S.charAt(i + 1)) { i++; a[1]++;}
                q.add(a);
            }

            int count = 0;
            for (String w : words)
                if (helper(w, q))
                    count++;

            return count;
        }

        private static boolean helper(String w, Queue<int[]> q) {
            int j = 0;

            for (int[] a : q) {
                if (j >= w.length()) return false;
                if (a[1] <= 2) { // check if the sub strings are equals
                    if (w.charAt(j) != a[0]) return false;
                    if (a[1] == 2) {
                        if (w.charAt(j + 1) != a[0]) return false;
                        j++;
                    }
                    j++;
                } else { // move to the next letter in w
                    int k = j;
                    while (j < w.length() && w.charAt(j) == a[0]) j++;
                    if (j - k > a[1]) return false;
                }
            }

            return j >= w.length();
        }
    }

    /**
     * LC 8ms
     * Time complexity: O(n*m)
     * Space complexity: O(n)
     */
    static class Solution2 {
        public static int expressiveWords(String S, String[] words) {
            int ans = 0;

            for (String word : words)
                if (isok(S, word))
                    ans++;

            return ans;
        }

        static boolean isok(String a, String b) {
            int i = 0;
            int j = 0;

            while (i < a.length() && j < b.length()) {
                int ii = i + 1;
                int jj = j + 1;

                while (ii < a.length() && a.charAt(ii) == a.charAt(i)) ii++;
                while (jj < b.length() && b.charAt(jj) == b.charAt(j)) jj++;

                int cnt1 = ii - i;
                int cnt2 = jj - j;
                if (cnt1 < 3 && cnt1 != cnt2) return false;
                if (cnt1 >= 3 && cnt1 < cnt2) return false;

                i = ii;
                j = jj;
            }

            return i >= a.length() && j >= b.length();
        }
    }

    public static void main(String[] args) {
        Solution.expressiveWords("heeellooo", new String[]{"hello", "hi", "helo"});
        Solution.expressiveWords("hellooo", new String[]{"hello", "hi", "helo"});
        Solution.expressiveWords("heeell", new String[]{"hello", "hi", "helo"});
        Solution.expressiveWords("heeellooo", new String[]{"heeeello", "hi", "helo"});
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
