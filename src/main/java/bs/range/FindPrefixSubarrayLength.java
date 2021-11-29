package bs.range;

/**
 * @company google
 */
public class FindPrefixSubarrayLength {

  public static class Solution1 {

    public int findPrefixLength(String[] ar, String prefix) {
      int left = findLeftBoundary(ar, prefix);
      int right = findRightBoundary(ar, prefix);
      if (left == -1 || left == ar.length) {
        return 0;
      }
      return right - left + 1;
    }

    private int findLeftBoundary(String[] ar, String prefix) {
      int n = ar.length, lo = -1, hi = n;
      while (hi - lo > 1) {
        int mid = lo + (hi - lo) / 2;
        if (greaterOrEquals(ar[mid], prefix)) {
          hi = mid;
        } else {
          lo = mid;
        }
      }
      return hi;
    }

    private int findRightBoundary(String[] ar, String prefix) {
      int n = ar.length, lo = -1, hi = n;
      while (hi - lo > 1) {
        int mid = lo + (hi - lo) / 2;
        if (lessOrEquals(ar[mid], prefix)) {
          lo = mid;
        } else {
          hi = mid;
        }
      }
      return lo;
    }

    private boolean lessOrEquals(String a, String b) {
      return a.startsWith(b) || a.compareTo(b) < 0;
    }

    private boolean greaterOrEquals(String a, String b) {
      return a.startsWith(b) || a.compareTo(b) > 0;
    }

  }
}
