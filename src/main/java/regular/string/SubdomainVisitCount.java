package regular.string;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 811
 *
 * A website domain like "discuss.leetcode.com" consists of various subdomains.
 * At the top level, we have "com", at the next level, we have "leetcode.com",
 * and at the lowest level, "discuss.leetcode.com". When we visit a domain like
 * "discuss.leetcode.com", we will also visit the parent domains "leetcode.com"
 * and "com" implicitly.
 * Now, call a "count-paired domain" to be a count (representing the number of
 * visits this domain received), followed by a space, followed by the address.
 * An example of a count-paired domain might be "9001 discuss.leetcode.com".
 * We are given a list cpdomains of count-paired domains. We would like a list
 * of count-paired domains, (in the same format as the input, and in any order),
 * that explicitly counts the number of visits to each subdomain.
 *
 * Example 2:
 * Input:["900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"]
 * Output:["901 mail.com","50 yahoo.com","900 google.mail.com","5 wiki.org","5 org","1 intel.mail.com","951 com"]
 */
public class SubdomainVisitCount {
    /**
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    class Solution {
        public List<String> subdomainVisits(String[] cpdomains) {
            Map<String, Integer> map = new HashMap<>();
            for (int i = 0 ; i < cpdomains.length ; i++) {
                String[] a = cpdomains[i].split(" ");
                int a1 = Integer.parseInt(a[0]);
                String[] b = a[1].split("\\.");

                if (b.length == 2) {
                    map.put(b[1], map.getOrDefault(b[1], 0) + a1);
                } else {
                    map.put(b[2], map.getOrDefault(b[2], 0) + a1);
                    map.put(b[1] + "." + b[2], map.getOrDefault(b[1] + "." + b[2], 0) + a1);
                }
                map.put(a[1], map.getOrDefault(a[1], 0) + a1);
            }

            List<String> l = new LinkedList<>();
            for (String s : map.keySet())
                l.add(map.get(s) + " " + s);

            return l;
        }
    }
}
