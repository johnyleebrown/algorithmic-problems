package design.map;

import java.util.*;

/**
 * 380
 *
 * ======
 *
 * Task.
 *
 * Design a data structure that supports all following operations in average
 * O(1) time.
 *
 * insert(val): Inserts an item val to the set if not already present.
 * remove(val): Removes an item val from the set if present. getRandom: Returns
 * a random element from current set of elements. Each element must have the
 * same probability of being returned.
 *
 * ======
 *
 * Source: Leetcode
 */
public class InsertDeleteGetRandomO1 {
    /**
     * Use map to keep value and index in list. Remove is O(1). Use list to rand
     * val in O(1). Removing value from list can tale up to O(n) if it is not
     * the last one. We remove tha last one and set it to index of val in list
     * we want to remove.
     */
    class Solution {
        class RandomizedSet {
            Map<Integer, Integer> m = new HashMap<>();//val and ind in l
            List<Integer> l = new ArrayList<>();
            Random r = new Random();

            public RandomizedSet() {
            }

            public boolean insert(int val) {
                if (m.containsKey(val)) return false;
                l.add(val);
                m.put(val, l.size() - 1);
                return true;
            }

            public boolean remove(int val) {
                if (!m.containsKey(val)) return false;
                int ind = m.remove(val);
                int last = l.get(l.size() - 1);
                l.remove(l.size() - 1);
                if (ind != l.size()) {
                    m.put(last, ind);
                    l.set(ind, last);
                }
                return true;
            }

            public int getRandom() {
                return l.get(r.nextInt(l.size()));
            }
        }
    }
}