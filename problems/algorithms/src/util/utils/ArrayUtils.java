package util.utils;

import java.util.LinkedList;
import java.util.List;

public class ArrayUtils {
    public static int[][] parseString2dAr(String source) {
        String[] ar = source.substring(1, source.length() - 1).split("]");
        int[][] ans = new int[ar.length][];
        int i = 0;
        for (String s : ar) {
            String[] nums = s.split("[^0-9]");
            List<Integer> loc = new LinkedList<>();
            for (String n : nums) {
                if (!n.trim().isEmpty()) {
                    loc.add(Integer.parseInt(n));
                }
            }
            int[] a = new int[loc.size()];
            int k = 0;
            for (int j : loc) {
                a[k++] = j;
            }
            ans[i++] = a;
        }
        return ans;
    }

    public static void exch(int[] a, int j, int i) {
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
}
