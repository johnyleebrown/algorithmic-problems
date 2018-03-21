package Medium.Array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * 15 3Sum
 *
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0?
 * Find all unique triplets in the array which gives the sum of zero.
 *
 * S = [-1, 0, 1, 2, -1, -4],
 * A solution set is:
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 */
public class ThreeSum {
    /**
     * TLE
     * Time complexity: O(n^2)
     * Space complexity: O(n)
     */
    static class Solution1 {
        public List<List<Integer>> threeSum(int[] nums) {


            int n = nums.length;
            List<List<Integer>> ans = new LinkedList<>();

            // map < 3rd integer, list of pos >
            Map<Integer, LinkedList<Integer>> mapSumInd = new HashMap<>();
            for (int i = 0 ; i < n ; i++) {
                mapSumInd.putIfAbsent(nums[i], new LinkedList<>());
                mapSumInd.get(nums[i]).add(i);
            }

            // set of string sums
            Set<String> mapSum = new HashSet<>();
            Set<String> map3Sum = new HashSet<>();
            for (int i = 0 ; i < n - 1 ; i++) {
                for (int j = i + 1 ; j < n ; j++) {
                    List<Integer> ansInner = new LinkedList<>();
                    int s = nums[i] + nums[j];

                    if (mapSum.add(getInSortedOrder(new int[]{i,j}))) {
//                        System.out.println(s + " " + s*(-1));
                        LinkedList<Integer> list = mapSumInd.get(s*(-1));
                        if (list != null)
                            for (int key : list)
                                if (key != i && key != j) {
                                    ansInner.add(nums[i]);ansInner.add(nums[j]);ansInner.add(nums[key]);
                                    if (map3Sum.add(getInSortedOrder(new int[]{nums[i],nums[j],nums[key]}))) ans.add(ansInner);
                                    break;
                                }
                    }
                }
            }

//            map3Sum.forEach(System.out::println);


            return ans;
        }

        private String getInSortedOrder(int[] arr) {
            Arrays.sort(arr);
            StringBuilder sb = new StringBuilder();
            Arrays.stream(arr).forEach(val -> sb.append(val).append("_"));
            return sb.toString();
        }

        class myHashSet{
            myHashSet(int a, int b, int c) {

            }
        }
    }

    /**
     * Time complexity: O(n^2)
     * Space complexity: O(1)
     */
    static class Solution2 {
        public List<List<Integer>> threeSum(int[] nums) {
            Arrays.sort(nums);
            List<List<Integer>> res = new LinkedList<>();

            for (int i = 0; i < nums.length - 2; i++)
                if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {
                    int lo = i + 1, hi = nums.length - 1, sum = 0 - nums[i];

                    while (lo < hi) {
                        if (nums[lo] + nums[hi] == sum) {
                            res.add(Arrays.asList(nums[i], nums[lo], nums[hi]));

                            while (lo < hi && nums[lo] == nums[lo + 1]) lo++;
                            lo++;

                            while (lo < hi && nums[hi] == nums[hi - 1]) hi--;
                            hi--;
                        } else if (nums[lo] + nums[hi] < sum) {
                            // skip duplicates
                            while (lo < hi && nums[lo] == nums[lo + 1]) lo++;
                            lo++;
                        } else {
                            // skip duplicates
                            while (lo < hi && nums[hi] == nums[hi - 1]) hi--;
                            hi--;
                        }
                    }
                }


            return res;
        }
    }

    public static void main(String[] args) {
//        ResourceBundle resourceBundle = ResourceBundle.getBundle("inputs");
//        System.out.println(resourceBundle.getString("ThreeSum"));


        Solution1 solution1 = new Solution1();
        int[] b = new int[]{-1,0,1,2,-1,-4};
        System.out.println(solution1.threeSum(b));
    }
}
