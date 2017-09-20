package Easy.String;

/**
 * 14
 * Write a function to find the longest common prefix string amongst an array of strings.
 */
public class LongestCommonPrefix {

    // O(n), where n is the sum of all characters in all strings; O(1)
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
}
