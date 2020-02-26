package array.regular;

import java.util.Map;
import java.util.TreeMap;

/**
 * Given an array of n elements, where each element
 * is at most k away from its target position, devise
 * an algorithm that sorts in O(n log k) time.
 * For example, let us consider k is 2, an element at
 * index 7 in the sorted array, can be at indexes
 * 5, 6, 7, 8, 9 in the given array.
 */
public class SortAKSortedArray {
    /**
     * Time complexity: O()
     * Space complexity: O()
     */
    public static int[] solution(int[] a, int k) {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for (int i = 0; i < k; i++)
            treeMap.put(a[i], i);
        for (int i = 0; i < a.length - k - 1; i++) {
            Map.Entry<Integer, Integer> entry = treeMap.pollFirstEntry();
            int val = entry.getValue();
            int idx = entry.getKey();
            a[i] = val;
            treeMap.put(a[i + k + 1], i + k);
        }
        return a;
    }
}
