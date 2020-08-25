package a0.array.regular;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 718
 * Given two integer arrays A and B, return the maximum length of an subarray that appears in both arrays.
 */
public class MaximumLengthOfRepeatedSubarray {

    class Solution {
        public int findLength(int[] A, int[] B) {
            int ans = 0;
            Map<Integer, ArrayList<Integer>> Bstarts = new HashMap();
            for (int j = 0; j < B.length; j++) {
                Bstarts.computeIfAbsent(B[j], x -> new ArrayList()).add(j);
            }

            for (int i = 0; i < A.length; i++) if (Bstarts.containsKey(A[i])) {
                for (int j: Bstarts.get(A[i])) {
                    int k = 0;
                    while (i+k < A.length && j+k < B.length && A[i+k] == B[j+k]) {
                        k++;
                    }
                    ans = Math.max(ans, k);
                }
            }
            return ans;
        }
    }
}
