package Medium.Array;

/**
 * 799
 * We stack glasses in a pyramid, where the first row has 1 glass, the
 * second row has 2 glasses, and so on until the 100th row. Each glass
 * holds one cup (250ml) of champagne. Then, some champagne is poured
 * in the first glass at the top.  When the top most glass is full, any
 * excess liquid poured will fall equally to the glass immediately to
 * the left and right of it. When those glasses become full, any excess
 * champagne will fall equally to the left and right of those glasses, and
 * so on.(A glass at the bottom row has it's excess champagne fall on the floor.)
 * Now after pouring some non-negative integer cups of champagne, return how
 * full the j-th glass in the i-th row is (both i and j are 0 indexed.)
 */
public class ChampagneTower {
    /**
     * HINT: fill the first row with max amount at the beginning
     * Time complexity: O(n^2) n - rows
     * Space complexity: O(n^2)
     */
    public static double champagneTower(int poured, int query_row, int query_glass){
        double[][] res = new double[100][100];
        res[0][0] = poured;

        // iterate through all rows and all glasses in each row
        for (int i = 0 ; i < 100 ; i++){
            for (int j = 0 ; j <= i ; j++) {
                if (res[i][j] > 1) {
                    double amountLeft = res[i][j] - 1;
                    if (i != 99) {
                        res[i + 1][j] += amountLeft / 2;
                        res[i + 1][j + 1] += amountLeft / 2;
                    }
                    res[i][j] = 1;
                }
            }
        }

        return res[query_row][query_glass];
    }

    public static void main(String[] args) {
        System.out.println(champagneTower(4,2,1));
    }
}
