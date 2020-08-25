package math.numberTheory.fib;

import java.util.HashSet;
import java.util.Set;

public class FibNumbers {
    /**
     * Generate first n fib numbers.
     */
    public static Set<Integer> generate(int n) {
        Set<Integer> ans = new HashSet<>();

        ans.add(0);
        if (n == 0) return ans;
        ans.add(1);
        if (n == 1) return ans;

        int one = 0;
        int two = 1;
        int t;
        for (int i = 2; i <= n; i++) {
            t = one + two;
            one = two;
            two = t;
            ans.add(two);
        }

        return ans;
    }
}
