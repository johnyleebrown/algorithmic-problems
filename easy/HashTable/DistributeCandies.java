package Easy.HashTable;

import java.util.HashSet;

/**
 * 575
 * Given an integer array with even length, where different numbers in
 * this array represent different kinds of candies. Each number means one candy of the corresponding kind.
 * You need to distribute these candies equally in number to brother and sister.
 * Return the maximum number of kinds of candies the sister could gain.
 *
 * The length of the given array is in range [2, 10,000], and will be even.
 * The number in given array is in range [-100,000, 100,000].
 */
public class DistributeCandies {

    public class Solution {
        public int distributeCandies(int[] candies) {
            HashSet<Integer> set = new HashSet<>();
            for (int candy : candies) set.add(candy);
            return Math.min(set.size(), candies.length / 2);
        }
    }

    class Solution2 {
        public int distributeCandies(int[] candies) {
            boolean[] map = new boolean[200001];
            int res = 0;
            for (int i = 0; i < candies.length; i++) {
                if (!map[candies[i] + 100000]) {
                    map[candies[i] + 100000] = true;
                    if (++res == candies.length / 2) return res;
                }
            }
            return res;
        }
    }
}
