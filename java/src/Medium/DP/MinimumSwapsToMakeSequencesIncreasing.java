package Medium.DP;

import java.util.Arrays;

import Helpers.Helper;

/**
 * 801
 *
 * We have two integer sequences A and B of the same non-zero length.
 * We are allowed to swap elements A[i] and B[i].  Note that both
 * elements are in the same index position in their respective sequences.
 * At the end of some number of swaps, A and B are both strictly increasing.
 * (A sequence is strictly increasing if and only if A[0] < A[1] < A[2] < ... < A[A.length - 1].)
 * Given A and B, return the minimum number of swaps to make both sequences strictly increasing.
 * It is guaranteed that the given input always makes it possible.
 *
 * [0,6,3,12] [1,1,7,5]
 * [0,4,4,5,9] [0,1,6,8,10]
 */
public class MinimumSwapsToMakeSequencesIncreasing {
    /**
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    public static int minSwap(int[] A, int[] B) {
        if (A.length == 0) return 0;

        System.out.println(Arrays.toString(A));
        System.out.println(Arrays.toString(B));
        System.out.println();

        int[] dpNormal = new int[A.length];
        int[] dpReverse = new int[A.length];

        dpNormal[0] = 0;
        dpReverse[0] = 1;

        System.out.println(Arrays.toString(dpNormal));
        System.out.println(Arrays.toString(dpReverse));
        System.out.println();

        for (int i = 1; i < A.length; i++) {
            dpNormal[i] = Integer.MAX_VALUE;
            dpReverse[i] = Integer.MAX_VALUE;

            if (A[i] > A[i - 1] && B[i] > B[i - 1]) {
                dpNormal[i] = Math.min(dpNormal[i - 1], dpNormal[i]);
                dpReverse[i] = Math.min(dpReverse[i - 1] + 1, dpReverse[i]);
            } else {
                dpNormal[i] = Math.min(dpReverse[i - 1], dpNormal[i]);
                dpReverse[i] = Math.min(dpNormal[i - 1] + 1, dpReverse[i]);
            }

            if (A[i] > B[i - 1] && B[i] > A[i - 1]) {
                dpNormal[i] = Math.min(dpReverse[i - 1], dpNormal[i]);
                dpReverse[i] = Math.min(dpNormal[i - 1] + 1, dpReverse[i]);
            }

            System.out.println(Arrays.toString(dpNormal));
            System.out.println(Arrays.toString(dpReverse));
            System.out.println();
        }

        return Math.min(dpNormal[A.length - 1], dpReverse[A.length - 1]);
    }

    public static void main(String[] args) {
        minSwap(new int[]{0,4,4,5,9},new int[]{0,1,6,8,10});
    }
}
