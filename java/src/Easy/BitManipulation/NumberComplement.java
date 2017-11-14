package Easy.BitManipulation;

/**
 * 476
 */
public class NumberComplement {
    class Solution {
        public int findComplement(int num) {
            return ~num & (Integer.highestOneBit(num) - 1);
        }
    }
}
