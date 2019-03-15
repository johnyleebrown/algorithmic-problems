package Hard.Array;

import java.util.LinkedList;

/**
 * 891
 *
 * Given an array of integers A, consider all non-empty subsequences of A.
 * For any sequence S, let the width of S be the difference between the maximum and minimum element of S.
 * Return the sum of the widths of all subsequences of A.
 * As the answer may be very large, return the answer modulo 10^9 + 7.
 */
public class SumOfSubsequenceWidths {
    /**
     * Time complexity: O()
     * Space complexity: O()
     */
    public int sumSubseqWidths(int[] A) {
        LinkedList<Integer> ll = new LinkedList<>();
        LinkedList<Integer> llsmall = new LinkedList<>();
        int k = A.length - 1;
        for (int i = 0; i < k; i++) {
            while (!ll.isEmpty() && A[ll.getFirst()] < A[i])
                ll.removeFirst();
            while (!llsmall.isEmpty() && A[llsmall.getFirst()] > A[i])
                llsmall.removeFirst();
            llsmall.addFirst(i);
            ll.addFirst(i);
        }
        for (int i = k; i < A.length; i++) {
//            System.out.println("min : " + A[llsmall.getLast()] + " max : " + A[ll.getLast()]);
            if (ll.getLast() < i - k + 1)
                ll.removeLast();
            if (llsmall.getLast() < i - k + 1)
                llsmall.removeLast();
            while (!ll.isEmpty() && A[ll.getFirst()] < A[i])
                ll.removeFirst();
            while (!llsmall.isEmpty() && A[llsmall.getLast()] > A[i])
                llsmall.removeFirst();
            llsmall.addFirst(i);
            ll.addFirst(i);
        }
        return ll.getLast() + llsmall.getFirst();
    }
}
