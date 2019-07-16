package Hard.Math;

/**
 * 793
 *
 * Let f(x) be the number of zeroes at the end of x!. (Recall that x! = 1 * 2 * 3 * ... * x, and by convention, 0! = 1.)
 * For example, f(3) = 0 because 3! = 6 has no zeroes at the end, while f(11) = 2 because 11! = 39916800 has 2 zeroes at the end. Given K, find how many non-negative integers x have the property that f(x) = K.
 *
 * Example 1:
 * Input: K = 0
 * Output: 5
 * Explanation: 0!, 1!, 2!, 3!, and 4! end with K = 0 zeroes.
 * Example 2:
 * Input: K = 5
 * Output: 0
 * Explanation: There is no x such that x! ends in K = 5 zeroes.
 *
 * K will be an integer in the range [0, 10^9].
 */
public class PreimageSizeOfFactorialZeroesFunction {
    /**
     * Time complexity: O(log n)
     * Space complexity: O(1)
     */
    // (<= K zeroes) - (<= K - 1 zeroes)
    public static int solution(int K) {
        return findRange(K) - findRange(K - 1);
    }

    // binary search
    static int findRange(int k) {
        long low = 0, high = Long.MAX_VALUE;

        while (high > low) {
            long mid = low + (high - low) / 2;

            if (getZeroes(mid) > k) high = mid - 1;
            else                    low = mid + 1;
        }

        return (int) low - 1;
    }

    // every 5^n 5,25,125.. extra zero
    static long getZeroes(long N) {
        long count = 0;

        for (long i = 5; N / i >= 1; i = i * 5) count += N / i;

        return count;
    }
}
