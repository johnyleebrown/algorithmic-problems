package Helpers;

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

}
