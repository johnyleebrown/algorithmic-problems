package Easy.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 118
public class PascalsTriangle
{
    class Solution {

        public List<List<Integer>> generate(int numRows) {
            List<List<Integer>> massiveList = new ArrayList<>();
            if (numRows == 0) return massiveList;
            helper(massiveList, numRows);
            return massiveList;
        }

        private List<Integer> helper(List<List<Integer>> massiveList, int row)
        {
            if (row == 1) {
                massiveList.add(Arrays.asList(1));
                return new ArrayList<>(Arrays.asList(1));
            }

            List<Integer> newList = new ArrayList<>();
            newList.add(1);

            List<Integer> prevList = helper(massiveList, row - 1);
            for (int i = 0; i < prevList.size() - 1; i++) newList.add(prevList.get(i) + prevList.get(i + 1));

            newList.add(1);

            massiveList.add(newList);

            return newList;
        }
    }
}
