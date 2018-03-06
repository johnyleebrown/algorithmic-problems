package Medium.LinkedList;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 138
 * A linked list is given such that each node contains
 * an additional random pointer which could point to
 * any node in the list or null. Return a deep copy of the list.
 */
public class CopyListWithRandomPointer {
    /**
     * Time complexity: O()
     * Space complexity: O()
     */
    public static RandomListNode solution(RandomListNode head) {
        if (head == null) return null;
        HashMap<RandomListNode, RandomListNode> nodeMap = new HashMap<>();
        HashMap<RandomListNode, RandomListNode> randomNodeMap = new HashMap<>();

        RandomListNode random = new RandomListNode(head.label);
        RandomListNode pointer = random;
        while (head.next != null) {
            nodeMap.put(head, pointer);

            if (randomNodeMap.containsKey(head.next)) {
                RandomListNode node = randomNodeMap.get(head.next);
                node = pointer.next;
            }
            if (nodeMap.containsKey(head.random)) {
                RandomListNode node = nodeMap.get(head.random);
                pointer.random = node;
            }
            if (head.random != null)
                randomNodeMap.put(head.random, pointer.random);

            pointer.next = new RandomListNode(head.next.label);
            pointer = pointer.next;
            head = head.next;
        }
        return random;
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
        randomListNode1.next = randomListNode2;
        randomListNode2.next = randomListNode3;
        randomListNode3.random = randomListNode1;

        printLL(solution(randomListNode1));
    }
}
