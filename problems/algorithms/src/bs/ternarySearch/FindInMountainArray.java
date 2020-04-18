package bs.ternarySearch;

/**
 * 1095
 *
 * ======
 *
 * Task.
 *
 * (This problem is an interactive problem.)
 *
 * You may recall that an array A is a mountain array if and only if:
 *
 * A.length >= 3
 * There exists some i with 0 < i < A.length - 1 such that:
 * A[0] < A[1] < ... A[i-1] < A[i]
 * A[i] > A[i+1] > ... > A[A.length - 1]
 *
 * Given a mountain array mountainArr, return the minimum index such that mountainArr.get(index) == target.
 * If such an index doesn't exist, return -1.
 *
 * You can't access the mountain array directly.  You may only access the array using a MountainArray interface:
 *
 * MountainArray.get(k) returns the element of the array at index k (0-indexed).
 * MountainArray.length() returns the length of the array.
 *
 * Submissions making more than 100 calls to MountainArray.get will be judged Wrong Answer.
 * Also, any solutions that attempt to circumvent the judge will result in disqualification.
 *
 * ======
 *
 * Source: Leetcode
 */
public class FindInMountainArray {
    /**
     * Ternary search + 2 binary ones.
     */
    public static class Solution {
        public int findInMountainArray(int x, MountainArray m) {
            int peak = ternarySearch(m);
            if (m.get(peak) == x) {
                return peak;
            }
            int left = binarySearchIncreasingArr(0, peak - 1, x, m);
            if (left != -1) {
                return left;
            }
            int right = binarySearchDecreasingArr(peak + 1, m.length() - 1, x, m);
            if (right != -1) {
                return right;
            }
            return -1;
        }

        private int ternarySearch(MountainArray m) {
            int lo = -1;
            int hi = m.length();
            while (hi - lo > 2) {
                int m1 = lo + (hi - lo) / 3;
                int m2 = lo + 2 * (hi - lo) / 3;
                if (m.get(m1) > m.get(m2)) {
                    hi = m2;
                } else {
                    lo = m1;
                }
            }
            return (lo + hi) / 2;
        }

        private int binarySearchIncreasingArr(int lo, int hi, int x, MountainArray m) {
            while (hi - lo >= 0) {
                int mid = lo + (hi - lo) / 2;
                if (m.get(mid) == x) {
                    return mid;
                }
                if (m.get(mid) < x) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
            return -1;
        }

        private int binarySearchDecreasingArr(int lo, int hi, int x, MountainArray m) {
            while (hi - lo >= 0) {
                int mid = lo + (hi - lo) / 2;
                if (m.get(mid) == x) {
                    return mid;
                }
                if (m.get(mid) > x) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
            return -1;
        }

        private class MountainArray {
            private int get(int mid) {
                return 0;
            }

            private int length() {
                return 0;
            }
        }
    }
}