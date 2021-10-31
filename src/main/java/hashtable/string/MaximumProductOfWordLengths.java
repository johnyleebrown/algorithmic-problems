package hashtable.string;

/**
 * 318. Maximum Product of Word Lengths
 * https://leetcode.com/problems/maximum-product-of-word-lengths/
 */
public class MaximumProductOfWordLengths {

  /**
   * Encode each word as a set of unique letters (1-26). Knowing masks for each word we can
   * calculate intersection of two in O(1).
   */
  public static class Solution {
    public int maxProduct(String[] words) {
      int ans = 0;
      int n = words.length;

      // calculate all masks
      int[] masks = new int[n];
      for (int i = 0; i < n; i++) {
        masks[i] = getMask2(words[i]);
      }

      // count the product if words don't have same letters
      for (int i = 0; i < n; i++) {
        int mask1 = masks[i];
        for (int j = i + 1; j < n; j++) {
          int mask2 = masks[j];
          if (haveSameLetters(mask1, mask2)) {
            continue;
          }
          ans = Math.max(ans, words[i].length() * words[j].length());
        }
      }

      return ans;
    }

    private boolean haveSameLetters(int mask1, int mask2) {
      return (mask1 & mask2) != 0;
    }

    private int getMask2(String s) {
      boolean[] alphabet = new boolean[26];
      for (Character c : s.toCharArray()) {
        alphabet[c - 'a'] = true;
      }
      int mask = 0;
      for (int i = 0; i < 26; i++) {
        if (alphabet[i]) {
          mask |= (1 << i);
        }
      }
      return mask;
    }

    // faster
    private int getMask(String s) {
      int mask = 0;
      for (int i = 0; i < s.length(); i++) {
        mask |= (1 << (s.charAt(i) - 'a'));
      }
      return mask;
    }
  }
}
