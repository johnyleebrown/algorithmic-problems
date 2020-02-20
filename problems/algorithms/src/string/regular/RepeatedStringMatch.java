package string.regular;

/**
 * 686
 * Given two strings A and B, find the minimum number of times A has to be repeated such that B is a substring of it. If no such solution, return -1.
 */
public class RepeatedStringMatch {

    // O(n*(m+n))
    // O(m+n)
    class Solution {
        public int repeatedStringMatch(String A, String B) {
            int q = 1;
            StringBuilder S = new StringBuilder(A);
            for (; S.length() < B.length(); q++) S.append(A);
            if (S.indexOf(B) >= 0) return q;
            if (S.append(A).indexOf(B) >= 0) return q + 1;
            return -1;
        }
    }
}
