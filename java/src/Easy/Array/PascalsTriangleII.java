package Easy.Array;

import java.util.ArrayList;
import java.util.List;

/**
 * 119
 * Given an index k, return the kth row of the Pascal's triangle.
 */
public class PascalsTriangleII {
    class Solution{
        public List<Integer> getRow(int rowIndex) {
            List<Integer> list = new ArrayList<>();
            while (list.size() != rowIndex + 1){
                list.add(0, 1);
                for (int i = 1 ; i < list.size() - 1; i++) list.set(i, list.get(i) + list.get(i + 1));
            }
            return list;
        }
    }
}
