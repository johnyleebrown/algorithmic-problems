package sort.heap;

/**
 * 973
 */
public class KClosestPointsToOrigin {

  /**
   * Heap O(n*logk)
   */
  public class Solution2 {

    public int[][] kClosest(int[][] points, int K) {
      int n = points.length;
      int[][] x = new int[K][2];

      for (int i = 0; i < K; i++) {
        x[i] = points[i];
        swim(x, i);
      }

      for (int i = K; i < points.length; i++) {
        if (greater(x[0], points[i])) {
          x[0] = points[i];
          sink(x, 0, K);
        }
      }

      return x;
    }

    // min heap - using greater instead of less
    private void sink(int[][] a, int k, int n) {
      while (2 * k < n) {
        int j = 2 * k;

        if (j + 1 < n && greater(a[j + 1], a[j])) {
          j++;
        }

        if (!greater(a[j], a[k])) {
          break;
        }

        exch(a, k, j);

        k = j;
      }
    }

    // moving element up, towards the start of the array
    private void swim(int[][] a, int k) {
      while (k > 0 && greater(a[k], a[k / 2])) {
        exch(a, k, k / 2);
        k /= 2;
      }
    }

    private boolean greater(int[] a, int[] b) {
      return a[0] * a[0] + a[1] * a[1] > b[0] * b[0] + b[1] * b[1];
    }

    private void exch(int[][] a, int i, int j) {
      int[] temp = a[i];
      a[i] = a[j];
      a[j] = temp;
    }
  }
}