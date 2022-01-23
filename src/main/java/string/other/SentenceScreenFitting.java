package string.other;

/*
 * 418
 */
public class SentenceScreenFitting {
    /**
     * TODO
     */
    public static class Solution {
        public int wordsTyping(String[] sentence, int rows, int cols) {
            int n = rows;
            int m = cols;
            int maxlen = n * m;
            int c = 0;
            int i = 0;
            int filledrowlen = 0;

            while (i < maxlen) {
                for (String s : sentence) {
                    int len = s.length();

                    if (len > m) {
                        return 0;
                    }

                    if (filledrowlen + len > m) {
                        i += (m - filledrowlen);
                        i += len + 1;
                        filledrowlen = len + 1;
                    } else if (filledrowlen + len == m) {
                        i += len;
                        filledrowlen = 0;
                    } else {
                        i += len + 1;
                        filledrowlen += len + 1;
                    }

                    if (i > maxlen) {
                        return c;
                    }
                }

                c++;
            }

            return c;
        }
    }
}