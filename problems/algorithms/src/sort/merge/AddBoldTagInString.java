package sort.merge;

/**
 * 616
 */
public class AddBoldTagInString {
    class Solution {
        public String addBoldTag(String s, String[] dict) {
            boolean[] bold = new boolean[s.length()];
            for (String word : dict) {
                int i = -1;
                while ((i = s.indexOf(word, i + 1)) != -1) {
                    for (int j = i; j < i + word.length(); j++) {
                        bold[j] = true;
                    }
                }
            }
            StringBuilder sb = new StringBuilder();
            boolean tag = false;
            for (int i = 0; i < s.length(); i++) {
                if (!tag && bold[i]) {
                    sb.append("<b>");
                    tag = true;
                } else if (tag && !bold[i]) {
                    sb.append("</b>");
                    tag = false;
                }
                sb.append(s.charAt(i));
            }
            if (tag) sb.append("</b>");
            return sb.toString();
        }
    }
}