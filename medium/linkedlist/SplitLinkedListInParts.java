package Medium.LinkedList;

import Helpers.ListNode;

/**
 * 725
 *
 * Given a (singly) linked list with head node root, write a function to split the linked list into k consecutive linked list "parts".
 * The length of each part should be as equal as possible: no two parts should have a size differing by more than 1. This may lead to some parts being null.
 * The parts should be in order of occurrence in the input list, and parts occurring earlier should always have a size greater than or equal parts occurring later.
 * Return a List of ListNode's representing the linked list parts that are formed.
 * Examples 1->2->3->4, k = 5 // 5 equal parts [ [1], [2], [3], [4], null ]
 */
public class SplitLinkedListInParts {

    // O(n + k), O(max(n, k))
    class Solution {
        public ListNode[] splitListToParts(ListNode root, int k) {
            ListNode cur = root;
            int N = 0;
            while (cur != null) {
                cur = cur.next;
                N++;
            }
            int width = N / k, rem = N % k;
            ListNode[] ans = new ListNode[k];
            cur = root;
            for (int i = 0; i < k; ++i) {
                ListNode head = new ListNode(0), write = head;
                for (int j = 0; j < width + (i < rem ? 1 : 0); ++j) {
                    write = write.next = new ListNode(cur.val);
                    if (cur != null) cur = cur.next;
                }
                ans[i] = head.next;
            }
            return ans;
        }
    }

    // O(n + k), O(k)
    class Solution2 {
        public ListNode[] splitListToParts(ListNode root, int k) {
            ListNode cur = root;
            int N = 0;
            while (cur != null) {
                cur = cur.next;
                N++;
            }

            int width = N / k, rem = N % k;

            ListNode[] ans = new ListNode[k];
            cur = root;
            for (int i = 0; i < k; ++i) {
                ListNode head = cur;
                for (int j = 0; j < width + (i < rem ? 1 : 0) - 1; ++j) {
                    if (cur != null) cur = cur.next;
                }
                if (cur != null) {
                    ListNode prev = cur;
                    cur = cur.next;
                    prev.next = null;
                }
                ans[i] = head;
            }
            return ans;
        }
    }
}
