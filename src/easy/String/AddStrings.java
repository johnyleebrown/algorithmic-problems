package Easy.String;

/**
 * 415
 * Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.
 */
public class AddStrings {

    // O(max(m,n))
    public class Solution {
        public String addStrings(String num1, String num2) {
            StringBuilder sb = new StringBuilder();
            int carry = 0;
            for (int i = num1.length() - 1, j = num2.length() - 1; i >= 0 || j >= 0 || carry == 1; i--, j--) {
                int x = i < 0 ? 0 : num1.charAt(i) - '0';
                int y = j < 0 ? 0 : num2.charAt(j) - '0';
                sb.append((x + y + carry) % 10);
                carry = (x + y + carry) / 10;
            }
            return sb.reverse().toString();
        }
    }
}
