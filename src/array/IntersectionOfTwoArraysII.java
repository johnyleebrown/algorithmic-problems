package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * 350
 * Given two arrays, write a function to compute their intersection.
 Each element in the result should appear as many times as it shows in both arrays.
 The result can be in any order.
 */
public class IntersectionOfTwoArraysII {

    // O(m + n)
    // O(min(m, n))
    class Solution {
        public int[] intersect(int[] nums1, int[] nums2) {
            ArrayList<Integer> l = new ArrayList<>();
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums1.length; i++) map.put(nums1[i], map.getOrDefault(nums1[i], 0) + 1);
            for (int i = 0; i < nums2.length; i++) {
                int x = map.getOrDefault(nums2[i], -1);
                if (x > 0) {
                    l.add(nums2[i]);
                    map.put(nums2[i], x - 1);
                }
            }
            int[] a = new int[l.size()];
            for (int i = 0; i < a.length; i++) a[i] = l.get(i);
            return a;
        }
    }

    // O(n*log n + m*log m)
    class Solution2 {
        public int[] intersect(int[] nums1, int[] nums2) {
            if (nums1.length == 0 || nums2.length == 0) return new int[0];
            Arrays.sort(nums1);
            Arrays.sort(nums2);
            ArrayList<Integer> tempList = new ArrayList<>();
            int i1 = 0, i2 = 0;
            while (i1 < nums1.length && i2 < nums2.length) {
                if (nums1[i1] == nums2[i2]) {
                    tempList.add(nums1[i1]);
                    i1++;
                    i2++;
                } else if (nums1[i1] < nums2[i2]) i1++;
                else i2++;
            }
            int[] result = new int[tempList.size()];
            for (int i = 0; i < result.length; i++) result[i] = tempList.get(i);
            return result;
        }
    }
}
