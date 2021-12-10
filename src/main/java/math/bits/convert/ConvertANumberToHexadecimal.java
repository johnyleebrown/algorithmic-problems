package math.bits.convert;

/**
 * 405
 */
public class ConvertANumberToHexadecimal {

  /**
   * For example, num = 4012
   * Step 1) What is 4012 in binary number?
   * 00000000000000000000111110101100
   *
   * Step 2) How to convert a binary to hex presentation? We separate the binary in 8
   * groups, each group has 4 bits.
   * 0000 0000 0000 0000 0000 1111 1010 1100
   *
   * Step 3) We create a while loop to read 4 bits each time. Since it's a 32-bit Integer,
   * we can only execute this while loop 8 times. We also want to exit the while loop if
   * the num is 0.
   * while (loopCount < 8 && num != 0)
   *
   * Step 4) How to read 4 bits each time?
   * We can use & operation. 15 in binary is 1111. If we do num & 15, it will give you the
   * last four bits.
   * num & 15 will give you the number in range [0...15], we can use this to map to the
   * hexChar array.
   * 0 -> 0 ....9 -> 9....10 -> a....15 -> f
   * After each loop, we need to remove the last 4 bits. num = num >> 4, and also
   * increment the loopCount by 1.
   *
   * This apporach can also handle the negative case.
   * For example -1 in binary is 1111 1111 1111 1111 1111 1111 1111 1111. We just apply
   * the same logic above.
   */
  public static class Solution {

    public String toHex(int num) {
      if (num == 0) return "0";
      StringBuilder ans = new StringBuilder();

      // binary = 32 digits, 8 by 4
      int count = 8;

      while (num != 0 && --count >= 0) {
        // 15=1111, res from 0 to 15
        // take last 4
        int last4 = num & 15;
        // 0,..9,a,..f
        char x = (char) (last4 < 10 ? '0' + last4 : 'a' + last4 % 10);
        ans.insert(0, x);
        // remove last 4
        num >>>= 4;
      }

      return ans.toString();
    }
  }
}
