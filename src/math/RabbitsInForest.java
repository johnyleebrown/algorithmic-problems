package math;

import java.util.HashMap;
import java.util.Map;

/**
 * 781
 * In a forest, each rabbit has some color.
 * Some subset of rabbits (possibly all of them)
 * tell you how many other rabbits have the same
 * color as them. Those answers are placed in an array.
 * Return the minimum number of rabbits that could be in the forest.
 */
public class RabbitsInForest {

    public static int solution() {
        return 0;
    }

    /**
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    public int numRabbits(int[] answers) {
        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int a : answers)
            map.put(a, map.getOrDefault(a, 0) + 1);
        for (int n : map.keySet()) {
            int m = map.get(n);
            if (n > m)
                ans += n + 1;
            else if (m % (n + 1) > 0)
                ans += (n + 1) * ((m / (n + 1)) + 1 );
            else
                ans += (n + 1) * (m / (n + 1));
        }
        return ans;
    }

    /**
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    public int numRabbits_2(int[] answers) {
        int[] count = new int[1000];
        for (int x: answers)
            count[x]++;
        int ans = 0;
        for (int k = 0; k < 1000; ++k)
            ans += Math.floorMod(-count[k], k+1) + count[k];
        return ans;
    }
}
