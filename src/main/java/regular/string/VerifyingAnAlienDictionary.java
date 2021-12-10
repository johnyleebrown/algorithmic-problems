package regular.string;

/**
 * 953
 */
public class VerifyingAnAlienDictionary {

  /**
   * Assign integer order index to each char, so we could check order in place.
   */
  public static class Solution {

    public boolean isAlienSorted(String[] words, String order) {
      int[] idxs = new int[26];
      for (int i = 0; i < order.length(); i++) {
        idxs[order.charAt(i) - 'a'] = i;
      }

      for (int i = 1; i < words.length; i++) {
        boolean allSame = true;
        for (int j = 0;
             j < Math.min(words[i - 1].length(), words[i].length());
             j++) {
          char a = words[i - 1].charAt(j), b = words[i].charAt(j);
          if (idxs[a - 'a'] != idxs[b - 'a']) {
            allSame = false;
          }
          if (idxs[a - 'a'] < idxs[b - 'a']) {
            break;
          }
          if (idxs[a - 'a'] > idxs[b - 'a']) {
            return false;
          }
        }

        if (allSame && words[i - 1].length() > words[i].length()) {
          return false;
        }
      }

      return true;
    }
  }

  /**
   * Prettier
   */
  public static class Solution2 {

    public boolean isAlienSorted(String[] words, String order) {
      int[] idxs = new int[26];
      for (int i = 0; i < order.length(); i++) {
        idxs[order.charAt(i) - 'a'] = i;
      }
      for (int i = 1; i < words.length; i++) {
        if (!check(words, order, i, idxs)) return false;
      }
      return true;
    }

    boolean check(String[] words, String order, int i, int[] idxs) {
      for (int j = 0;
           j < Math.min(words[i - 1].length(), words[i].length());
           j++) {
        char a = words[i - 1].charAt(j), b = words[i].charAt(j);
        if (idxs[a - 'a'] != idxs[b - 'a']) {
          return idxs[a - 'a'] < idxs[b - 'a'];
        }
      }
      return words[i - 1].length() <= words[i].length();
    }
  }
}
