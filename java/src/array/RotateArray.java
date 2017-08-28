package Array;

/**
 * Rotate an Array of n elements to the right by k steps.
 * For example, with n = 7 and k = 3,
 * the Array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].
 *
 * Note: Try to come up as many solutions as you can,
 * there are at least 3 different ways to solve this problem.
 */

public class RotateArray {

    // Time complexity : O(n); Space complexity : O(n).
    public void rotate(int[] nums, int k) {
        int[] a = new int[nums.length];
        for (int i = 0; i < nums.length; i++) a[(i + k) % nums.length] = nums[i];
        for (int i = 0; i < nums.length; i++) nums[i] = a[i];
    }


    // Space complexity : O(n)
    public void rotate2(int[] nums, int k) {
        k = k%nums.length;
        int n = nums.length;
        int[] res = new int[nums.length];
        System.arraycopy(nums,0,res,k,n-k);
        System.arraycopy(nums,n-k,res,0,k);
        System.arraycopy(res,0,nums,0,n);
    }

    // Time complexity : O(n); Space complexity : O(1)
    public void rotate3(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
