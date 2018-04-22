package Helpers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * Helper methods
 */
public class Helper {

    public static void print2DArray(int[][] k) {
        int y = k.length - 1;
        int x = k[0].length - 1;

        int spaceN = 1;

        for (int i = 0; i <= y; i++) {
            for (int j = 0; j <= x; j++) {
                spaceN = Math.max(spaceN, String.valueOf(k[i][j]).length());
            }
        }

        for (int i = 0; i <= y; i++) {
            for (int j = 0; j <= x; j++) {
                int len = String.valueOf(k[i][j]).length();
                String space = new String(new char[spaceN + 1 - len]).replace("\0", " ");
                System.out.print(k[i][j] + space);
            }
            System.out.println();
        }
    }

    public static int[][] replaceBracets(String input) {
        // [[3,4,6],[3,6],[3,6],[0,1,2,5],[0,7,8],[3],[0,1,2,7],[4,6],[4],[]]
        input = input.replaceAll(" ", "")
                .replace("[]","[ ]")
                .replace("[[", "")
                .replaceAll("]]", "");

//        System.out.println("======================");
        String[] strings = input.split("],\\[");
        int[][] out = new int[strings.length][];
        for (int i = 0 ; i < strings.length ; i++) {
            out[i] = new int[strings[i].length()];
            if (strings[i].equals(" ")) out[i] = new int[]{};
            else out[i] = Arrays.stream(strings[i].split(",")).mapToInt(Integer::valueOf).toArray();
//            System.out.println(Arrays.toString(out[i]));
        }
//        System.out.println("======================");
        return out;
    }

    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val);
            if (head.next != null) System.out.print(" -> ");
            head = head.next;
        }
    }

    public static TreeNode genTree(int n) { // balanced
        Queue<TreeNode> q = new LinkedList<>();
        TreeNode root = new TreeNode(1);
        q.add(root);
        int c = 2;

        while (c != n + 1) {
            TreeNode x = q.poll();
            x.left = new TreeNode(c++);
            if (c == n + 1) break;
            q.add(x.left);
            x.right = new TreeNode(c++);
            q.add(x.right);
        }

        return root;
    }

    public static void printTree(TreeNode root) {
        if (root == null) return;
        int height = getTreeHeight(root);

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        int lastLevelNumberOfNodes = (int) Math.pow(2, height);
        int totalNodes = (int) Math.pow(2, height + 1) - 1;
        lastLevelNumberOfNodes*=2;

        printEmpty(lastLevelNumberOfNodes - 2);
        System.out.print(root.val);

        int offset = -2;

        while (!q.isEmpty()) {
            int size = q.size();
            int nextLevelLength = size * 2;

            System.out.println();
            printEmpty(lastLevelNumberOfNodes - (nextLevelLength / 2) + offset);
            offset = Math.abs(offset) * (-2);

            while (size != 0) {
                TreeNode x = q.poll();

                if (x.left != null) {
                    System.out.print(x.left.val + " ");
                    q.add(x.left);
                } else
                    System.out.print("o ");

                if (x.right != null) {
                    System.out.print(x.right.val + " ");
                    q.add(x.right);
                } else
                    System.out.print("o ");

                size--;
            }
        }
    }

    private static int getTreeHeight(TreeNode root) {
        if (root == null) return -1;
        return getTreeHeight(root.left) + 1;
    }

    private static void printEmpty(int num) {
        int i = 0;
        while (i++ != Math.abs(num)) System.out.print(" ");
    }

    public static int log2n(int bits) {
        if (bits == 0) return 0;
        return 31 - Integer.numberOfLeadingZeros(bits);
    }

    public static int[] genArray(int min, int max, int size) {
        int[] arr = new int[size];
        for (int i = 0; i < arr.length; i++) {
            final Random random = new Random();
            int r = min - 1;
            while (r < min) r = random.nextInt(max + 1);
            arr[i] = r;
        }
        return arr;
    }

    public static void exch(int[] a, int i, int j) {
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static void trackTime(){
        long startTime = System.nanoTime();
        // action
        long endTime = System.nanoTime();
        System.out.println("That took " + (endTime - startTime)/1000000 + " milliseconds");
    }
}
