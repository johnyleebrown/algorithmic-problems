package linkedList.other;

import commons.Node;

/**
 * 138
 */
public class CopyListWithRandomPointer {
    /**
     * 3 steps
     * - link oldnodes to new nodes and new nodes to old ones
     * old->new->old->new ..
     * - fill random pointers for new nodes
     * - put everything back in place
     */
    public static class Solution {
        public Node copyRandomList(Node head) {
            if (head == null) return null;
            Node cur = head;
            while (cur != null) {
                Node next = cur.next;
                Node newNode = new Node(cur.val);
                cur.next = newNode;
                newNode.next = next;
                cur = next;
            }
            cur = head;
            while (cur != null) {
                if (cur.random != null)
                    cur.next.random = cur.random.next;
                cur = cur.next.next;
            }
            cur = head;
            Node newHead = cur.next;
            while (cur != null) {
                Node next = cur.next.next;
                Node newNode = cur.next;
                if (newNode.next != null)
                    newNode.next = newNode.next.next;
                cur.next = next;
                cur = next;
            }
            return newHead;
        }
    }
}