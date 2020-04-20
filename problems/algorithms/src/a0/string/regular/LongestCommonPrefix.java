package a0.string.regular;

import java.util.Arrays;

/**
 * 14
 * Company: Yelp
 *
 * Write a function to find the longest common prefix string amongst an array of strings.
 */
public class LongestCommonPrefix {

    // O(n), where n is the sum of all characters in all strings
    // O(1)
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        String min = strs[0];

        int i = 1;
        while (i < strs.length){
            while (strs[i].indexOf(min) != 0) min = min.substring(0, min.length() - 1);
            i++;
        }

        return min;
    }

    // O(nlogn), O(1)
    class Solution2 {
        public String longestCommonPrefix(String[] strs) {
            if (strs.length == 0) return "";
            Arrays.sort(strs);
            if (strs[0].trim().equals("")) return "";

            StringBuilder sb = new StringBuilder();
            int i = 0;
            while (i < strs[0].length()
                    && strs[0].charAt(i) == strs[strs.length - 1].charAt(i)) {
                sb.append(strs[0].charAt(i++));
            }

            return sb.toString();
        }
    }
}
