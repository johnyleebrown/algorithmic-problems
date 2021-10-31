package greedy;

/** 670. Maximum Swap https://leetcode.com/problems/maximum-swap/ */
public class MaximumSwap {
  /** num should start with max numbers [9]?[8]?[7]?.. */
  public static class Solution {
    public int maximumSwap(int num) {
      String numString = String.valueOf(num);
      int len = numString.length();

      int[] numArray = new int[len];
      for (int i = 0; i < len; i++) {
        numArray[i] = numString.charAt(i) - '0';
      }

      int validationPointer = 0;
      for (int val = 9; val >= 0; val--) {
        int lookerPointer = len - 1;

        while (validationPointer < lookerPointer) {
          while (validationPointer < len && numArray[validationPointer] == val) {
            validationPointer++;
          }
          while (lookerPointer >= 0 && numArray[lookerPointer] != val) {
            lookerPointer--;
          }
          if (validationPointer >= len || lookerPointer < 0 || validationPointer >= lookerPointer) {
            break;
          }
          if (numArray[validationPointer] < numArray[lookerPointer]) {
            swapNums(numArray, validationPointer, lookerPointer);
            return getNumFromArrayOfDigits(numArray);
          }
        }
      }

      return num;
    }

    public int getNumFromArrayOfDigits(int[] numArray) {
      int powerOfTen = 1;
      int ans = 0;
      for (int i = numArray.length - 1; i >= 0; i--) {
        ans += powerOfTen * numArray[i];
        powerOfTen *= 10;
      }
      return ans;
    }

    public void swapNums(int[] numArray, int i, int j) {
      int temp = numArray[i];
      numArray[i] = numArray[j];
      numArray[j] = temp;
    }
  }
}
