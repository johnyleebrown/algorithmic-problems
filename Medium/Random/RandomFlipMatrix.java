package Medium.Random;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 881
 *
 * You are given the number of rows n_rows and number of columns n_cols of a 2D binary
 * matrix where all values are initially 0. Write a function flip which chooses a 0 value
 * uniformly at random, changes it to 1, and then returns the position [row.id, col.id]
 * of that value. Also, write a function reset which sets all values back to 0. Try to
 * minimize the number of calls to system's Math.random() and optimize the time and space
 * complexity.
 */
public class RandomFlipMatrix {
    /**
     * modern method of the Fisher–Yates shuffle
     * idea is to gen random from 0 to n: m, then swap m and n, and then decrease n, repeat until n == 0
     *
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    class Solution {
        Map<Integer, Integer> map;
        int rows, cols, total;
        Random rand;

        public Solution(int n_rows, int n_cols) {
            map = new HashMap<>();
            rand = new Random();
            rows = n_rows;
            cols = n_cols;
            total = rows * cols;
        }

        public int[] flip() {
            int r = rand.nextInt(total--); // supposedly generated index
            int x = map.getOrDefault(r, r); // check if we have already put something at this index
            map.put(r, map.getOrDefault(total, total)); // swap - put total at index that we generated
            return new int[]{x / cols, x % cols};
        }

        public void reset() {
            map.clear();
            total = rows * cols;
        }
    }
}
