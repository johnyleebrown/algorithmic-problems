package Array;

/**
 * 665
 * Given an array with n integers, your task is to check
 * if it could become non-decreasing by modifying at most 1 element.
 * We define an array is non-decreasing if array[i] <= array[i + 1] holds for every i (1 <= i < n).
 */
public class NonDecreasingArray {

    // changing array
    public boolean checkPossibility(int[] nums) {
        int c = 0;
        for(int i = 1; i < nums.length && c <= 1 ; i++){
            if(nums[i-1] > nums[i]){
                c++;
                if (i < 2 || nums[i-2] <= nums[i]) nums[i-1] = nums[i];
                else nums[i] = nums[i-1];
            }
        }
        return c <= 1;
    }

    // without
    public boolean checkPossibility2(int[] a) {
        int c = 0;
        for (int i = 1, prev = a[0]; i < a.length; i++) {
            if (a[i] < prev) {
                if (c++ > 0) return false;
                if (i >= 2 && a[i - 2] > a[i]) continue;
            }
            prev = a[i];
        }
        return true;
    }
}
