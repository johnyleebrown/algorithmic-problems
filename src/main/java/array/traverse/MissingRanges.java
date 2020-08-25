package array.traverse;

import java.util.LinkedList;
import java.util.List;

/**
 * 163
 */
public class MissingRanges {
    class Solution {
        public List<String> findMissingRanges(int[] a, int l, int r) {
            List<String> ans = new LinkedList<>();
            int n = a.length;
            if (r < l) return ans;

            if (n == 0) {
                if (l == r) ans.add(l + "");
                else ans.add(l + "->" + r);
                return ans;
            }

            if (a[0] == 1 + l) ans.add(String.valueOf(l));
            else if (a[0] > 1 + l) ans.add(l + "->" + (a[0] - 1));

            for (int i = 0; i < n - 1; i++) {
                if (a[i + 1] <= 1 + a[i]) continue;
                if (a[i + 1] == 2 + a[i]) ans.add(String.valueOf(a[i] + 1));
                else ans.add((a[i] + 1) + "->" + (a[i + 1] - 1));
            }

            if (r == 1 + a[n - 1]) ans.add(String.valueOf(r));
            else if (r > 1 + a[n - 1]) ans.add((a[n - 1] + 1) + "->" + r);

            return ans;
        }
    }
}
