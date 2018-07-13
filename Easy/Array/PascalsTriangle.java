package Easy.Array;

import java.util.ArrayList;
import java.util.List;

/**
 * 118
 * Given numRows, generate the first numRows of Pascal's triangle.
 * [
 *     [1],
 *    [1,1],
 *   [1,2,1],
 *  [1,3,3,1],
 * [1,4,6,4,1]
 * ]
 */
public class PascalsTriangle {
    public class Solution {
        public List<List<Integer>> generate(int numRows) {
            List<List<Integer>> list = new ArrayList<>();
            ArrayList<Integer> row = new ArrayList<>();
            for (int i = 0; i < numRows; i++) {
                row.add(0, 1);
                for (int j = 1; j < row.size() - 1; j++)
                    row.set(j, row.get(j) + row.get(j + 1));
                list.add(row);
            }
            return list;
        }
    }
}
