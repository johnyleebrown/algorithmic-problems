package Medium.DP;

/**
 * 0-1 Knapsack problem
 *
 * Given weights and values of n items,
 * put these items in a knapsack of capacity W
 * to get the maximum total value in the knapsack.
 * You cannot break an item, either pick the complete item,
 * or don’t pick it (0-1 property).
 */
public class Knapsack01 {
    class Solution {
        /**
         * @param values array of values of items in the knapsack
         * @param weights array of weights of items in the knapsack, indexes correspond to indexes in {@code val}
         * @param capacity knapsack capacity
         * @return maximum value of items
         */
        public int Knapsack01(int[] values, int[] weights, int capacity) {
            /*
              Algorithm:
              Building a 2d array ixj
              It will hold all of the best solutions to the sub problems
              by finding the maximum profit of each item at each weight
              a[i][j] = Math.max(a[i-1][j], val[i] + a[i-1][j-w[i]]
              The value in the bottom right of the array is the maximum profit
             */
            int i, w;
            int k[][] = new int[values.length + 1][capacity + 1];
            for (i = 0; i <= values.length; i++) {
                for (w = 0; w <= capacity; w++) {
                    if (i == 0 || w == 0) k[i][w] = 0;
                    else if (weights[i - 1] <= w)
                        k[i][w] = Math.max(values[i - 1] + k[i - 1][w - weights[i - 1]], k[i - 1][w]);
                    else k[i][w] = k[i - 1][w];
                }
            }
            return k[values.length][capacity];
        }
    }
}
