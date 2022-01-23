package string.other;

/**
 * 38
 * The count-and-say sequence is the sequence of integers with the first five terms as following:
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 * Given an integer n, generate the nth term of the count-and-say sequence.
 */
public class CountAndSay {

    // O(n)
    class Solution {
        public String countAndSay(int n) {
            if (n == 1) return "1";
            String str = "1";
            for (int i = 0; i < n - 1; i++) str = sayNum(str);
            return str;
        }

        private String sayNum(String num) {
            char[] c = num.toCharArray();
            int i = 0;
            StringBuilder sb = new StringBuilder();
            while (i < num.length()) {
                char cur = c[i];
                int count = 0;
                while (i < num.length() && c[i] == cur) {
                    count++;
                    i++;
                }
                sb.append((char)('0' + count)).append(cur);
            }
            return sb.toString();
        }
    }
}
