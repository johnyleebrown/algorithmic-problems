package contests.contest96;

import java.util.HashMap;

/**
 * 885
 *
 * The i-th person has weight people[i], and each boat can carry a maximum weight of limit.
 * Each boat carries at most 2 people at the same time, provided the sum of the weight of those people is at most limit.
 * Return the minimum number of boats to carry every given person.  (It is guaranteed each person can be carried by a boat.)
 */
public class BoatsToSavePeople {
    /**
     * Time complexity: O()
     * Space complexity: O()
     */
    public int numRescueBoats(int[] people, int limit) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int i = 0; i < people.length; i++) {
            if (people[i] == limit) count++;
            else map.put(i, map.getOrDefault(i, 0) + 1);
        }
        for (int i : map.keySet()) {
            int c = map.getOrDefault(i, 0);
            if (c > 0) {
                int c2 = map.getOrDefault(limit - i, 0);
                if (c2 > 0) {
                    map.put(limit - i, c2 - 1);
                }
                map.put(i, c - 1);
                count++;
            }
        }
        return count;
    }
}
