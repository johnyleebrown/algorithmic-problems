package Easy.recursion;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

// 119
public class PascalsTriangleII
{
    class Solution
    {
        public List<Integer> getRow(int rowIndex)
        {
            if (rowIndex == 0) return new LinkedList<>(Arrays.asList(1));
            if (rowIndex == 1) return new LinkedList<>(Arrays.asList(1, 1));

            List<Integer> list = getRow(rowIndex - 1);
            int n = list.size();

            // adding generated elements on right places
            int i = 0;
            while (i == 0 || list.get(i) != 1)
            {
                list.add(i + 1, list.get(i) + list.get(i + 1));
                i += 2;
            }

            // removing elements that we don't need anymore, ex 1 2 1 -> 1 3 3 1, removed 2
            i = 2;
            while (i < n + 1 - 1)
            {
                list.remove(i);
                i += 1;
            }

            return list;
        }
    }
}
