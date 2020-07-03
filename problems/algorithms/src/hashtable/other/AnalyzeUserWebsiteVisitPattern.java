package hashtable.other;

import java.util.*;

/**
 * 1152
 *
 * ======
 *
 * Task.
 *
 * We are given some website visits: the user with name username[i] visited the
 * website website[i] at time timestamp[i].
 *
 * A 3-sequence is a list of websites of length 3 sorted in ascending order by
 * the time of their visits.  (The websites in a 3-sequence are not necessarily
 * distinct.)
 *
 * Find the 3-sequence visited by the largest number of users. If there is more
 * than one solution, return the lexicographically smallest such 3-sequence.
 *
 * ======
 *
 * Source: Leetcode
 */
public class AnalyzeUserWebsiteVisitPattern {
    /**
     * Straight forward. Not optimized.
     *
     * 1. For each user sort sites by time
     * 2. With backtracking create patterns of 3 sites
     * 3. Add them to treeset with counts
     * 4. Get the first pattern out of treeset with max count
     * 5. Split it up and add to list
     */
    public static class Solution {

        public List<String> mostVisitedPattern(String[] users, int[] times, String[] sites) {

            // user - <time, site>
            Map<String, TreeMap<Integer, String>> userToTimeToSite = new HashMap<>();
            for (int i = 0; i < users.length; i++) {
                userToTimeToSite.putIfAbsent(users[i], new TreeMap<>());
                userToTimeToSite.get(users[i]).put(times[i], sites[i]);
            }

            // pattern is a 3 sites pattern
            int max = 0;
            TreeMap<String, Integer> patternToCount = new TreeMap<>();
            for (String user : userToTimeToSite.keySet()) {
                int size = userToTimeToSite.get(user).size();
                if (size >= 3) {
                    String[] ar = userToTimeToSite.get(user).values().toArray(new String[size]);
                    max = gen(ar, 0, patternToCount, "", 0, new HashSet<>(), max);
                }
            }

            List<String> ans = new ArrayList<>();
            for (String st : patternToCount.keySet()) {
                if (patternToCount.get(st) == max) {
                    Collections.addAll(ans, st.split(","));
                    return ans;
                }
            }

            return ans;
        }

        int gen(String[] ar, int count, TreeMap<String, Integer> patternToCount, String cur, int start, Set<String> seen, int max) {
            if (count == 3) {
                if (seen.contains(cur)) return max;
                seen.add(cur);
                patternToCount.put(cur, patternToCount.getOrDefault(cur, 0) + 1);
                return patternToCount.get(cur);
            }

            for (int i = start; i < ar.length; i++) {
                int ret = gen(ar, count + 1, patternToCount, cur.isEmpty() ? ar[i] : cur + "," + ar[i], i + 1, seen, max);
                max = Math.max(max, ret);
            }

            return max;
        }
    }

    /**
     * Optimized.
     * StringBuilder + stop after (ar.length - 3).
     */
    public static class Solution2 {

        public List<String> mostVisitedPattern(String[] users, int[] times, String[] sites) {

            // user - <time, site>
            Map<String, TreeMap<Integer, String>> userToTimeToSite = new HashMap<>();
            for (int i = 0; i < users.length; i++) {
                userToTimeToSite.putIfAbsent(users[i], new TreeMap<>());
                userToTimeToSite.get(users[i]).put(times[i], sites[i]);
            }

            // pattern is a 3 sites pattern
            int max = 0;
            TreeMap<String, Integer> patternToCount = new TreeMap<>();
            for (String user : userToTimeToSite.keySet()) {
                int size = userToTimeToSite.get(user).size();
                if (size >= 3) {
                    String[] ar = userToTimeToSite.get(user).values().toArray(new String[size]);
                    String[] pat = new String[3];
                    int ret = gen(ar, 0, patternToCount, pat, 0, new HashSet<>(), 0);
                    max = Math.max(max, ret);
                }
            }

            List<String> ans = new ArrayList<>();
            for (String st : patternToCount.keySet()) {
                if (patternToCount.get(st) == max) {
                    Collections.addAll(ans, st.split(","));
                    return ans;
                }
            }

            return ans;
        }

        int gen(String[] ar, int count, TreeMap<String, Integer> patternToCount, String[] cur, int start, Set<String> seen, int b) {
            if (count == 3) {
                String xx = new StringBuilder().append(cur[0]).append(",").append(cur[1]).append(",").append(cur[2]).toString();
                if (seen.contains(xx)) return 0;
                seen.add(xx);
                patternToCount.put(xx, patternToCount.getOrDefault(xx, 0) + 1);
                return patternToCount.get(xx);
            }

            int max = 0;

            for (int i = start; i < ar.length; i++) {
                if (count == 0 && b > ar.length - 3) return max;
                cur[count] = ar[i];
                if (count == 0) b++;
                int ret = gen(ar, count + 1, patternToCount, cur, i + 1, seen, b);
                max = Math.max(max, ret);
            }

            return max;
        }
    }
}