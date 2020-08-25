package a0.string.regular;

import java.util.LinkedList;
import java.util.List;

/**
 * 784
 * Given a string S, we can transform every letter individually to
 * be lowercase or uppercase to create another string.
 * Return a list of all possible strings we could create.
 */
public class LetterCasePermutation {
    /**
     * Time complexity: O(n^2)
     * Space complexity: O(1)
     */
    public static List<String> letterCasePermutation(String S) {
        LinkedList<String> q = new LinkedList<>();
        q.add(S);
        int i = 0;

        while (i < S.length()) {
            while (i < S.length() && !Character.isLetter(S.charAt(i))) i++;
            int size = q.size();
            while (size > 0 && i < S.length()) {
                String x = q.removeFirst();
                String a = x.substring(0, i);
                String b = x.substring(i + 1);
                char c = x.charAt(i);
                q.add(a + Character.toLowerCase(c) + b);
                q.add(a + Character.toUpperCase(c) + b);
                size--;
            }
            i++;
        }

        for (String s : q) {
            System.out.println(s);
        }

        return q;
    }

    public static void main(String[] args) {
//        letterCasePermutation("");
//        letterCasePermutation("a");
//        letterCasePermutation("2");
//        letterCasePermutation("22");
        letterCasePermutation("a2bc3");
    }
}
