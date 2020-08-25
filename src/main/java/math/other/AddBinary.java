package math.other;

/**
 * 67
 *
 * Given two binary strings, return their sum (also a binary string).
 * For example, a = "11" b = "1" Return "100".
 */
public class AddBinary {
    /**
     * 0 0 1  1
     * 0 1 0  1
     * 0 1 1 10
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    public static String solution(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() -1;
        int carry = 0;

        while (i >= 0 || j >= 0) {
            int sum = carry;
            if (j >= 0) sum += b.charAt(j--) - '0';
            if (i >= 0) sum += a.charAt(i--) - '0';
            sb.append(sum % 2);
            carry = sum / 2;
        }

        if (carry != 0) sb.append(carry);
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(solution("11", "1"));
    }
}
