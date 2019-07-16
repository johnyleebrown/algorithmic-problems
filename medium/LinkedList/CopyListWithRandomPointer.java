package Medium.LinkedList;

import java.util.HashMap;
import java.util.Map;

/**
 * 138
 * A linked list is given such that each node contains
 * an additional random pointer which could point to
 * any node in the list or null. Return a deep copy of the list.
 */
public class CopyListWithRandomPointer {
    /**
     * The main problem that arises is that we don't have the random nodes in the first iteration
     * So we put all the original list nodes into hashmap with new nodes
     *
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    public static RandomListNode solution(RandomListNode head) {
        if (head == null) return null;
        Map<RandomListNode, RandomListNode> map = new HashMap<>();

        // loop 1. copy all the nodes
        RandomListNode node = head;
        while (node != null) {
            map.put(node, new RandomListNode(node.label));
            node = node.next;
        }

        // loop 2. assign next and random pointers
        node = head;
        while (node != null) {
            map.get(node).next = map.get(node.next);
            map.get(node).random = map.get(node.random);
            node = node.next;
        }

        return map.get(head);
    }

    static class RandomListNode {
        int label;
        RandomListNode next, random;

        RandomListNode(int x) {
            this.label = x;
        }
    }

    static void printLL(RandomListNode head) {
        while (head != null){
            System.out.println(head.label);
            if (head.random != null) {
                System.out.println("random: " + head.random.label);
            } else {
                System.out.println("random: null");
            }
            head = head.next;
        }
    }

    public static void main(String[] args) {
        RandomListNode randomListNode1 = new RandomListNode(1);
        RandomListNode randomListNode2 = new RandomListNode(2);
        RandomListNode randomListNode3 = new RandomListNode(3);
        RandomListNode randomListNode4 = new RandomListNode(4);
        randomListNode1.next = randomListNode2;
        randomListNode2.next = randomListNode3;
        randomListNode3.next = randomListNode4;
        randomListNode1.random = randomListNode3;
        randomListNode2.random = randomListNode3;

        printLL(solution(randomListNode1));
    }
}
