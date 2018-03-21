package Helpers;

import java.util.Arrays;

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

    // [[3,4,6],[3,6],[3,6],[0,1,2,5],[0,7,8],[3],[0,1,2,7],[4,6],[4],[]]
    public static int[][] replaceBracets(String input) {
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

    public static void trackTime(){
        long startTime = System.nanoTime();
        // action
        long endTime = System.nanoTime();
        System.out.println("That took " + (endTime - startTime)/1000000 + " milliseconds");
    }
}
