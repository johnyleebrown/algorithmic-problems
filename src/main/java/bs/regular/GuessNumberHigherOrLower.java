package bs.regular;

/**
 * 374
 */
public class GuessNumberHigherOrLower {

    // Binary Search
    // O(log2n)
    public class Solution extends GuessGame {

        public int guessNumber(int n) {
            int low = 1;
            int high = n;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                int res = guess(mid);
                if (res == 0)
                    return mid;
                else if (res < 0)
                    high = mid - 1;
                else
                    low = mid + 1;
            }
            return -1;
        }
    }

    // Ternary Search
    // O(log3(n))
    public class Solution2 extends GuessGame {

        public int guessNumber(int n) {
            int low = 1;
            int high = n;
            while (low <= high) {
                int mid1 = low + (high - low) / 3;
                int mid2 = high - (high - low) / 3;
                int res1 = guess(mid1);
                int res2 = guess(mid2);
                if (res1 == 0)
                    return mid1;
                if (res2 == 0)
                    return mid2;
                else if (res1 < 0)
                    high = mid1 - 1;
                else if (res2 > 0)
                    low = mid2 + 1;
                else {
                    low = mid1 + 1;
                    high = mid2 - 1;
                }
            }
            return -1;
        }
    }

    private class GuessGame {

        int guess(int mid) {
            return 0;
        }
    }

}
