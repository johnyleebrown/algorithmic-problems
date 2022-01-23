package array.other;

/*
 * 1165
 */
public class SingleRowKeyboard {
    class Solution {
        public int calculateTime(String keyboard, String word) {
            int[] index = new int[26];
            for (int i = 0; i < keyboard.length(); ++i) {
                index[keyboard.charAt(i) - 'a'] = i;
            }

            int sum = 0;
            int last = 0;
            for (char c : word.toCharArray()) {
                sum += Math.abs(index[c - 'a'] - last);
                last = index[c - 'a'];
            }

            return sum;
        }
    }
}
