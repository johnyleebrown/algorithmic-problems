package math;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 728
 * A self-dividing number is a number that is divisible by every digit it contains.
 * Given a lower and upper number bound, output a list of every possible self dividing number, including the bounds if possible.
 */
public class SelfDividingNumbers {

    class Solution {
        public List<Integer> selfDividingNumbers(int left, int right) {
            List<Integer> ans = new ArrayList();
            for (int n = left; n <= right; ++n) if (selfDividing(n)) ans.add(n);
            return ans;
        }
        public boolean selfDividing(int n) {
            for (char c: String.valueOf(n).toCharArray()) {
                if (c == '0' || (n % (c - '0') > 0))
                    return false;
            }
            return true;
        }
    }

    class Solution2 {
        public List<Integer> selfDividingNumbers(int left, int right) {
            List<Integer> list = new LinkedList<>();
            if (right < left) return list;
            for (int i = left ; i <= right ; i++){
                int x = i;
                int c = 0;
                while (x != 0){
                    int d = x % 10;
                    if (d == 0 || i % d != 0) break;
                    x /= 10;
                    c++;
                }
                if (String.valueOf(i).length() == c) list.add(i);
            }
            return list;
        }
    }
}
