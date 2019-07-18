package Easy.BitManipulation;

/**
 * 476
 * Given a positive integer, output its complement number.
 * The complement strategy is to flip the bits of its binary representation.
 */
public class NumberComplement {
    class Solution {
        public int findComplement(int num) {
            return ~num & (Integer.highestOneBit(num) - 1);
        }
    }
}
