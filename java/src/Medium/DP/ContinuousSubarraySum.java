package Medium.DP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 */
public class ContinuousSubarraySum {
    /**
     * Time complexity: O()
     * Space complexity: O()
     */
    public static boolean solution(int[] nums, int k) {
        if (nums.length < 2 || k == 0) return false;
        int sum = nums[0];
        for (int i = 1; i < nums.length; i++) sum += nums[i];
        int amount = (sum / k) * k;
        if (amount == sum) return true;
        int[] div = new int[amount / k];
        for (int i = 0; i < div.length; i ++) div[i] = k * (i + 1);


        System.out.println(Arrays.toString(div));

        for (int i = 0; i < div.length; i++) {
            List<Integer> list = new ArrayList<>();
            Queue<Integer> q1 = new LinkedList<>();
            Queue<Integer> q2 = new LinkedList<>();
            for (int j = 0; j < nums.length; j++) {
                System.out.println();
                System.out.print(" |" + nums[j] + "| ");
                while (!q1.isEmpty()) {
                    int item = q1.poll();
                    System.out.print(" " + item + "  ");
                    if (nums[j] == item)
                        return true;
                    if (nums[j] < item)
                        q2.add(item - nums[j]);
                }
                if (nums[j] < div[i])
                    q2.add(div[i] - nums[j]);
                System.out.print(Arrays.toString(q2.toArray()));
                q1 = q2;
                q2.clear();
            }

//            for (int j = 0; j < nums.length; j++) {
//                System.out.println();
//                System.out.print(" |" + nums[j] + "| ");
//                for (int ind = 0 ; ind < list.size() ; ind++) {
//                    int item = list.get(ind);
//                    System.out.print(" " + item + "  ");
//                    if (nums[j] == item) return true;
//                    list.remove(ind);
//                    if (nums[j] < item) list.add(item - nums[j]);
//                }
//                if (nums[j] < div[i]) list.add(div[i] - nums[j]);
//
//                System.out.print(Arrays.toString(list.toArray()));
//            }
        }
        System.out.println();
        return false;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{1,2,3}, 5));

        //   6 12 18 24
        //1
        //6
        //8
        //13


    }
}
