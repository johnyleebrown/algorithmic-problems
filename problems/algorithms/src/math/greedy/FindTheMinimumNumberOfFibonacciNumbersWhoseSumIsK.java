package math.greedy;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

/**
 * 1414
 *
 * ======
 *
 * Task.
 *
 * Given the number k, return the minimum number of Fibonacci numbers whose sum
 * is equal to k, whether a Fibonacci number could be used multiple times.
 *
 * ======
 *
 * Source: Leetcode
 */
public class FindTheMinimumNumberOfFibonacciNumbersWhoseSumIsK {
    /**
     * Proof by minimal counter example.
     * We assume that there is an optimal solution. We can try to replace some
     * step of the optimal solution to the step from our greedy solution
     * without
     * loosing it's optimality.
     * Let's say greedy solution: 17=13+3+1
     * And we say here is an optimal one: 17=8+8+1
     * We replace first step with the step from our solution: 17=13+2+2,
     * along with the step we had to adjust the other numbers.
     * In optimal solution the count of numbers hasn't changed, the sum is the
     * same. The solution is still optimal but is one step closer to greedy
     * solution. Optimal solution got better so it wan't optimal.
     */
    public static class Solution {
        public int findMinFibonacciNumbers(int k) {
            if (k == 0 || k == 1) return 1;
            Deque<Integer> nums = getFib(k);
            Iterator<Integer> it = nums.descendingIterator();
            int ans = 0;
            while (it.hasNext()) {
                int x = it.next();
                if (k >= x) {
                    k -= x;
                    ans++;
                }
                if (k == 0) break;
            }
            return ans;
        }

        private Deque<Integer> getFib(int n) {
            Deque<Integer> q = new ArrayDeque<>();
            q.addLast(0);
            int one = 0;
            int two = 1;
            int sum;
            while (two <= n) {
                q.addLast(two);
                sum = one + two;
                one = two;
                two = sum;
            }
            return q;
        }
    }
}