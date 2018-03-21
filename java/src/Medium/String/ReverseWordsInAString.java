package Medium.String;

import java.util.Arrays;
import java.util.Collections;

/**
 * 151
 *
 * Given an input string, reverse the string word by word.
 * For example, given s = "the sky is blue", return "blue is sky the".
 */
public class ReverseWordsInAString {
    /**
     * Reverse just words
     */
    public static String solution(String s) {
        StringBuilder reversed = new StringBuilder();

        s = s.replaceAll("\\s+", " ").trim();
        char[] ca = s.toCharArray();

        String[] a = s.split("[^a-zA-Z]+");
        if (a.length == 0) return s;

        int i = 0;
        int j = a.length - 1;
        int flag = 1;

        while (i < s.length()) {
            while (i < s.length() && Character.isLetter(ca[i])) {
                flag = 0;
                i++;
            }
            if (j >= 0 && flag == 0) {
                flag = 1;
                reversed.append(a[j]);
                j--;
            }
            while (i < s.length() && !Character.isLetter(ca[i])) {
                reversed.append(ca[i]);
                i++;
            }
        }

        return reversed.toString();
    }

    public static String solution2(String s) {
        StringBuilder sb = new StringBuilder();
        // s = s.replaceAll("\\s+", " ").trim();
        String[] str = s.split(" ");

        for (int i = str.length - 1; i >= 0; i--) {
            if (!str[i].equals("")) {
                if (sb.length() > 0) sb.append(" ");
                sb.append(str[i]);
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {

        String s1 = " AA abc &&&&      abc 8'8 B.v ";
        String s2 = " << >> ";
        String s3 = "+---...2x+--x--+x-+-x2...---+";

        System.out.println(solution2(s2));

    }
}
