package Medium.Array;

import java.util.TreeMap;

/**
 * 870
 *
 * Given two arrays A and B of equal size, the advantage of A with respect to B is the number of indices i for which A[i] > B[i].
 * Return any permutation of A that maximizes its advantage with respect to B.
 */
public class AdvantageShuffle {
    /**
     * Time complexity: O(nlogn)
     * Space complexity: O(n)
     */
    class Solution1 {
        public int[] advantageCount(int[] A, int[] B) {
            TreeMap<Integer, Integer> map = new TreeMap<>();
            for (int i : A) map.put(i, map.getOrDefault(i, 0) + 1);

            for (int i = 0; i < A.length; i++) {
                Integer c = map.higherKey(B[i]);
                if (c == null) c = map.firstKey();
                A[i] = c;
                int val = map.get(c);
                if (val - 1 != 0) map.put(c, val - 1);
                else map.remove(c);
            }

            return A;
        }
    }
}
