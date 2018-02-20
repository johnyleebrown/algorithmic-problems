package Easy.String;

import java.util.ArrayList;
import java.util.List;

/**
 * 784
 * Given a string S, we can transform every letter individually to
 * be lowercase or uppercase to create another string.
 * Return a list of all possible strings we could create.
 */
public class LetterCasePermutation {
    /**
     * Time complexity: O(N*2^N)
     * Space complexity: O(N*2^N)
     */
    public static List<String> letterCasePermutation(String S) {
        List<StringBuilder> ans = new ArrayList<>();
        ans.add(new StringBuilder());

        for (char c: S.toCharArray()) {
            int n = ans.size();
            if (Character.isLetter(c)) {
                for (int i = 0; i < n; ++i) {
                    ans.add(new StringBuilder(ans.get(i)));
                    ans.get(i).append(Character.toLowerCase(c));
                    ans.get(n+i).append(Character.toUpperCase(c));
                }
            } else {
                for (int i = 0; i < n; ++i)
                    ans.get(i).append(c);
            }
        }

        List<String> res = new ArrayList<>();
        for (StringBuilder sb: ans)
            res.add(sb.toString());
        return res;
    }

    public static void main(String[] args) {
        letterCasePermutation("a2b");
    }


}
