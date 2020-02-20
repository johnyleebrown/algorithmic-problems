package string.regular;

/**
 * 557
 * Given a string, you need to reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.
 */
public class ReverseWordsInAStringIII {
    // O(n) ; O(n)
    class Solution {
        public String reverseWords(String s) {
            String[] strs = s.split(" ");
            StringBuilder sb = new StringBuilder();
            for (String str : strs) {
                sb.append(" ").append(new StringBuilder(str).reverse());
            }
            return sb.substring(1).toString();
        }
    }

    class Solution2 {
        public String reverseWords(String s) {
            char[] c = s.toCharArray();
            for (int i = 0; i < c.length; i++) {
                if (c[i] != ' ') {
                    int j = i;
                    while (j + 1 < c.length && c[j + 1] != ' ') j++;
                    reverse(c, i, j);
                    i = j;
                }
            }
            return new String(c);
        }

        private void reverse(char[] c, int i, int j) {
            for (; i < j; i++, j--) {
                char tmp = c[i];
                c[i] = c[j];
                c[j] = tmp;
            }
        }
    }
}
