package hashtable;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 1429
 *
 * ======
 *
 * Task.
 *
 * You have a queue of integers, you need to retrieve the first unique integer
 * in the queue.
 *
 * Implement the FirstUnique class:
 *
 * FirstUnique(int[] nums) Initializes the object with the numbers in the
 * queue.
 * int showFirstUnique() returns the value of the first unique integer of the
 * queue, and returns -1 if there is no such integer.
 * void add(int value) insert value to the queue.
 *
 * ======
 *
 * Source: Leetcode
 */
public class FirstUniqueNumber {
    /**
     * Map with index and value and reversed map with value and last added
     * index.
     */
    public class Solution {
        class FirstUnique {
            Map<Integer, Integer> notUnique;
            TreeMap<Integer, Integer> orderedQueue;
            int curInd;

            public FirstUnique(int[] nums) {
                notUnique = new HashMap<>();
                orderedQueue = new TreeMap<>();
                curInd = nums.length;
                for (int i = 0; i < nums.length; i++) {
                    helper(nums[i], i);
                }
            }

            private void helper(int val, int i) {
                if (notUnique.containsKey(val)) {
                    orderedQueue.remove(notUnique.get(val));
                } else {
                    notUnique.put(val, i);
                    orderedQueue.put(i, val);
                }
            }

            public int showFirstUnique() {
                if (orderedQueue.isEmpty()) return -1;
                return orderedQueue.firstEntry().getValue();
            }

            public void add(int val) {
                helper(val, curInd++);
            }
        }
    }
}