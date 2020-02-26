package array.regular;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 349
 * Given two arrays, write a function to compute their intersection.
 */
public class IntersectionOfTwoArrays {

    // O(m + n)
    class Solution {
        public int[] intersection(int[] nums1, int[] nums2) {
            HashSet<Integer> set = new HashSet<>();
            List<Integer> list = new ArrayList<>();
            for(int i =0; i< nums1.length; i++) set.add(nums1[i]);
            for(int j = 0; j < nums2.length; j++) {
                if(set.contains(nums2[j])) {
                    list.add(nums2[j]);
                    set.remove(nums2[j]);
                }
            }
            int[] arr = new int[list.size()];
            for (int i= 0; i < list.size(); i++) arr[i] = list.get(i);
            return arr;
        }
    }
}
