package hashtable.other;

import java.util.TreeMap;

/**
 * 846
 *
 * Alice has a hand of cards, given as an array of integers.
 * Now she wants to rearrange the cards into groups so that each group is size W,
 * and consists of W consecutive cards.
 * Return true if and only if she can.
 */
public class HandOfStraights {
    /**
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    public boolean solution(int[] hand, int W) {
        if (hand.length < W || hand.length % W != 0) return false;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i : hand) map.put(i, map.getOrDefault(i, 0) + 1);

        int countGroups = 0;
        int pointerGlobal = map.firstKey();

        while (!map.isEmpty()) {
            int pointerLocal = pointerGlobal;
            int countCards = 0;

            while (countCards != W && !map.isEmpty()) {
                Integer val = map.get(pointerLocal);
                if (val == null) return false;
                if (val == 1) map.remove(pointerLocal);
                else map.replace(pointerLocal, val - 1);
                pointerLocal = pointerLocal + 1;
                countCards++;
            }

            if (countCards == W) countGroups++;
            if (!map.isEmpty()) pointerGlobal = map.ceilingKey(pointerGlobal);
        }

        return hand.length / W == countGroups;
    }
}
