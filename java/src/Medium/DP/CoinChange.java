package Medium.DP;

import java.util.Arrays;
import java.util.Map;

/**
 * Created by Greg on 1/31/18.
 */
public class CoinChange {
    /**
     * Bottom-up solution
     * Time complexity: O(amount*coins.length)
     * Space complexity: O(amount)
     * Recurrence: total[i] = min(total[i], 1 + total[i - coins[j]])
     *
     * @param amount total to return with change
     * @param coins  coin denominations
     * @return last item in the array
     */
    public int SolutionBU(int amount, int[] coins) {
        int[] total = new int[amount + 1];
        Arrays.fill(total, Integer.MAX_VALUE);
        total[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    // if it is possible to give
                    // change with coin of denomination j
                    total[i] = Math.min(total[i], 1 + total[i - coin]);
                }
            }
        }
        return total[amount] > amount ? -1 : total[amount];
    }

    /**
     * Top-down solution
     * Time complexity: O(amount*coins.length)
     * Space complexity: O(amount)
     */
    public int SolutionTD(int amount, int[] coins, Map<Integer, Integer> map) {
        if (amount == 0) return 0;
        if (map.containsKey(amount)) return map.get(amount);
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            if (coin > amount) continue;
            int val = SolutionTD(amount - coin, coins, map);
            min = Math.min(min, val);
        }
        min = min == Integer.MAX_VALUE ? min : min + 1;
        map.put(amount, min);
        return min;
    }
}
