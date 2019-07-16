package Medium.Array;

/**
 * 779
 *
 * K-th Symbol in Grammar
 * On the first row, we write a 0. Now in every subsequent row, we look at the previous row and replace each occurrence of 0 with 01, and each occurrence of 1 with 10.
 * Given row N and index K, return the K-th indexed symbol in row N. (The values of K are 1-indexed.) (1 indexed).
 *
 * Examples:
 * Input: N = 1, K = 1
 * Output: 0
 *
 * N will be an integer in the range [1, 30].
 * K will be an integer in the range [1, 2^(N-1)].
 */
public class KthSymbolInGrammar {
    /**
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    class solution1 {
        public int solution(int N, int K) {
            byte[] ar = new byte[(int) Math.pow(2, N - 1)];
            if (K == 1) return 0;
            if (K == 2) return 1;
            ar[1] = 1;
            return (int) helper(ar, 2, K);
        }

        // always be filling the second half
        private byte helper(byte[] ar, int pos, int k) {
            int len = pos / 2;

            for (int i = 0; i < len; i++) {
                if (pos == k - 1) return ar[len + i];
                ar[pos++] = ar[len + i];
            }

            for (int i = 0; i < len; i++) {
                if (pos == k - 1) return ar[i];
                ar[pos++] = ar[i];
            }

            return helper(ar, pos, k);
        }
    }

    /**
     * Decimal:   0 1 2  3  4   5   6   7   8    9    10   11   ------ K
     * Binary:    0 1 10 11 100 101 110 111 1000 1001 1010 1011
     * 1's count: E O O  E  O   E   E   O   O    E    E    O
     * Sequence:  0 1 1  0  1   0   0   1   1    0    0    1
     *
     * So count the number of 1 bits in K, and check if it is ODD or EVEN
     */
    public int kthGrammar(int N, int K) {
        return Integer.bitCount(K - 1) & 1;
    }

    public static void main(String[] args) {
//        System.out.println(solution(4, 5));
//        System.out.println(solution(1, 1));
//        System.out.println(solution(2, 1));
//        System.out.println(solution(2, 2));
//        System.out.println(solution(30, 434991989));
    }
}
