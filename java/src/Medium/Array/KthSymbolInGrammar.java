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
     * Time complexity: O()
     * Space complexity: O()
     */
    public static int solution(int N, int K) {
        byte[] ar = new byte[(int) Math.pow(2, N - 1)];
        if (K == 1) return 0;
        if (K == 2) return 1;
        ar[1] = 1;
        return (int)helper(ar, 2, K);
    }

    // always be filling the second half
    private static byte helper(byte[] ar, int pos, int k) {
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

    public int kthGrammar(int N, int K) {
        return Integer.bitCount(K-1) & 1;
    }

    public static void main(String[] args) {
//        System.out.println(solution(4, 5));
//        System.out.println(solution(1, 1));
//        System.out.println(solution(2, 1));
//        System.out.println(solution(2, 2));
        System.out.println(solution(30, 434991989));
    }
}
