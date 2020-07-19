package yandex.contest.MarsianPromocodes;

import yandex.contest.FileReader;
import yandex.contest.Reader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MarsianPromocodes
 */
public class MarsianPromocodes {
    public static class Solution {
        public void solve(int promoLen, String promo, int keyLen, String key, int L) {
            if (promoLen > keyLen) System.out.println("NO");
            else if (promoLen % L != 0) System.out.println("NO");
            else {
                Map<String, Integer> m = new HashMap<>(); // substring to last "first index" in key
                for (int i = 0; i < promoLen; i += L) {
                    m.put(promo.substring(i, i + L), -1);
                }
                for (String part : m.keySet()) {
                    int pos = key.indexOf(part);
                    if (pos > m.get(part)) {
                        m.put(part, pos);
                    } else {
                        System.out.println("NO");
                        return;
                    }
                }
                System.out.println("YES");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        MarsianPromocodes.Solution s = new MarsianPromocodes.Solution();
        Reader r = new FileReader(new FileInputStream("problems/interviews/src/yandex/contest/MarsianPromocodes/test.txt"));
        List<String> inputs = r.readLines();

        Reader rExpectations = new FileReader(new FileInputStream("problems/interviews/src/yandex/contest/MarsianPromocodes/res.txt"));
        List<String> expectations = rExpectations.readLines();

        for (int i = 0; i < inputs.size(); i += 5) {
            s.solve(Integer.parseInt(inputs.get(i)), inputs.get(i + 1),
                    Integer.parseInt(inputs.get(i + 2)), inputs.get(i + 3),
                    Integer.parseInt(inputs.get(i + 4)));
            System.out.println("==");
            System.out.println(expectations.get(i / 5));
            System.out.println("*****");
        }
    }
}
