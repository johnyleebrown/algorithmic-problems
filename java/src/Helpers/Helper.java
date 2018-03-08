package Helpers;

import java.util.Arrays;

/**
 * Helper methods
 */
public class Helper {

    public static void print2Darray(int[][] k, int y, int x) {
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
        input = input.replace("[]","[ ]")
                .replace("[[", "")
                .replaceAll("]]", "");

        String[] strings = input.split("],\\[");
        int[][] out = new int[strings.length][];
        for (int i = 0 ; i < strings.length ; i++) {
            out[i] = new int[strings[i].length()];
            if (strings[i].equals(" ")) out[i] = new int[]{};
            else out[i] = Arrays.stream(strings[i].split(",")).mapToInt(Integer::valueOf).toArray();
            System.out.println(Arrays.toString(out[i]));
        }

        return out;
    }
}
