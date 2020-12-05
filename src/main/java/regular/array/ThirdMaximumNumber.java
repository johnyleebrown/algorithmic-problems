package regular.array;

import java.util.PriorityQueue;

/**
 * 414
 * Given a non-empty array of integers, return the third maximum number in this array.
 * If it does not exist, return the maximum number.
 * The time complexity must be in O(n).
 */
public class ThirdMaximumNumber {

    // O(n) ; O(1)
    public int thirdMax(int[] nums) {
        Integer three = null;
        Integer two = null;
        Integer one = null;
        for (Integer i : nums){
            if (i.equals(three) || i.equals(two) || i.equals(one)) continue;
            if (one == null || i > one){
                three = two;
                two = one;
                one = i;
            }
            else if (two == null || i > two) {
                three = two;
                two = i;
            }
            else if (three == null || three < i) {
                three = i;
            }
        }
        return three == null ? one : three;
    }

    // Priority Queue O(n) ; O(1)
    public int thirdMax2(int[] nums) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0 ; i < nums.length ; i++) {
            if(!pq.contains(nums[i])){
                if (pq.size() < 3) {
                    pq.add(nums[i]);
                    continue;
                } else if (nums[i] > pq.peek()){
                    pq.poll();
                    pq.add(nums[i]);
                }
            }
        }
        if (pq.size() < 3) while(pq.size() != 1) pq.poll();
        return pq.peek();
    }
}
