package Easy.String;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 599
 * Company: Yelp
 *
 * Suppose Andy and Doris want to choose a restaurant for dinner, and they both have a list of favorite restaurants represented by strings.
 * You need to help them find out their common interest with the least list index sum. If there is a choice tie between answers, output all of them with no order requirement. You could assume there always exists an answer.
 */
public class MinimumIndexSumOfTwoLists {
    /**
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    public static String[] findRestaurant(String[] list1, String[] list2) {
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < list1.length; i++) map.put(list1[i], i);
        int min = 1998;
        HashMap<Integer, ArrayList<String>> map2 = new HashMap<>();
        for (int i = 0; i < list2.length; i++) {
            if (map.containsKey(list2[i])) {
                int x = i + map.get(list2[i]);
                if (min > x) min = x;
                map2.putIfAbsent(x, new ArrayList<>());
                map2.get(x).add(list2[i]);
            }
        }
        return map2.get(min).toArray(new String[0]);
    }
}
