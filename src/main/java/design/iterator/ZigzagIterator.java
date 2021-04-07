package design.iterator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * 281. Zigzag Iterator
 *
 * <p>======
 *
 * <p>Given two vectors of integers v1 and v2, implement an iterator to return their elements
 * alternately.
 *
 * <p>Implement the ZigzagIterator class:
 *
 * <p>ZigzagIterator(List<int> v1, List<int> v2) initializes the object with the two vectors v1 and
 * v2. boolean hasNext() returns true if the iterator still has elements, and false otherwise. int
 * next() returns the current element of the iterator and moves the iterator to the next element.
 *
 * <p>Example 1:
 *
 * <p>Input: v1 = [1,2], v2 = [3,4,5,6] Output: [1,3,2,4,5,6] Explanation: By calling next
 * repeatedly until hasNext returns false, the order of elements returned by next should be:
 * [1,3,2,4,5,6]. Example 2:
 *
 * <p>Input: v1 = [1], v2 = [] Output: [1] Example 3:
 *
 * <p>Input: v1 = [], v2 = [1] Output: [1]
 *
 * <p>Constraints:
 *
 * <p>0 <= v1.length, v2.length <= 1000 1 <= v1.length + v2.length <= 2000 -231 <= v1[i], v2[i] <=
 * 231 - 1
 *
 * <p>Follow up: What if you are given k vectors? How well can your code be extended to such cases?
 *
 * <p>Clarification for the follow-up question:
 *
 * <p>The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases. If "Zigzag" does
 * not look right to you, replace "Zigzag" with "Cyclic".
 *
 * <p>Example:
 *
 * <p>Input: v1 = [1,2,3], v2 = [4,5,6,7], v3 = [8,9] Output: [1,4,8,2,5,9,3,6,7]
 *
 * <p>======
 *
 * <p>https://leetcode.com/problems/zigzag-iterator/
 */
public class ZigzagIterator {
  /**
   * sf
   */
  public static class Solution {
    public static class ZigzagIteratorSolution {
      Iterator<Integer> it1, it2;
      int ind;
      List<Iterator<Integer>> its;
      int n;

      public ZigzagIteratorSolution(List<Integer> v1, List<Integer> v2) {
        ind = 0;
        its = new ArrayList<>();
        if (v1 != null) {
          it1 = v1.iterator();
          n += v1.size();
        }
        its.add(it1);
        if (v2 != null) {
          it2 = v2.iterator();
          n += v2.size();
        }
        its.add(it2);
      }

      public int next() {
        Iterator<Integer> cur = Collections.emptyIterator();
        int moves = its.size() + 1;
        while (!cur.hasNext() && --moves >= 0) {
          cur = its.get(ind);
          ind = (ind + 1) % its.size();
        }
        n--;
        return cur.next();
      }

      public boolean hasNext() {
        return n != 0;
      }
    }
  }
}
