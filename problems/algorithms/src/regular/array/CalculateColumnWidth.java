package regular.array;

import java.util.LinkedList;
import java.util.List;

/**
 * CalculateColumnWidth
 *
 * ======
 *
 * Task.
 *
 * Given the maxWidth (characters that could fit in one line) and an array of
 * strings, fit the strings in a table and return the width of each column.
 *
 * ======
 *
 * Company: Google
 * Source: Leetcode
 */
public class CalculateColumnWidth {
    public static void main(String[] args) {
        System.out.println(Solution.calculateColumnWidth(
                new String[]{"IsAudioBuffer", "GetTimestamp", "SetTimestamp", "GetSampleRate", "GetSampleSize",
                        "GetNumberOfChannels", "GetNumberOfSamples", "GetDataBuffer", "GetChannel"}, 70));

        System.out.println(Solution.calculateColumnWidth(
                new String[]{"abcde", "abcdvv"}, 10));

        System.out.println(Solution.calculateColumnWidth(
                new String[]{"abcde", "abcdv"}, 10));

        System.out.println(Solution.calculateColumnWidth(
                new String[]{"abcd", "abcdv"}, 10));

        System.out.println(Solution.calculateColumnWidth(
                new String[]{"abcde", "abcd"}, 10));

        System.out.println(Solution.calculateColumnWidth(
                new String[]{"abcde", "abcde"}, 5));

        System.out.println(Solution.calculateColumnWidth(
                new String[]{"abcde", "abcd"}, 5));

        System.out.println(Solution.calculateColumnWidth(
                new String[]{"abcd", "abcde"}, 5));

        System.out.println(Solution.calculateColumnWidth(
                new String[]{"a", "abcde"}, 5));
    }

    public static class Solution {
        public static List<Integer> calculateColumnWidth(String[] w, int maxWidth) {
            for (int i = 0; i < maxWidth; i++) {
                System.out.print("#");
            }
            System.out.println();
            List<Integer> res = new LinkedList<>();
            if (w.length == 0 || maxWidth < 1) return res;
            int j = 0; // last col ind at cur row
            for (int i = 0; i < w.length; i++) {
                if (w[i].length() > maxWidth) return new LinkedList<>();
                if (j + w[i].length() <= maxWidth) {
                    j += w[i].length();
                    System.out.print(w[i]);
                } else {
                    res.add(j);
                    System.out.println();
                    System.out.print(w[i]);
                    j = w[i].length();
                }
                if (i == w.length - 1) {
                    res.add(j);
                }
            }
            System.out.println();
            return res;
        }
    }
}