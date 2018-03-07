package Medium.String;

/**
 * 791
 * S and T are strings composed of lowercase letters. In
 * S, no letter occurs more than once.
 * S was sorted in some custom order previously. We want
 * to permute the characters of T so that they match the
 * order that S was sorted. More specifically, if x occurs
 * before y in S, then x should occur before y in the
 * returned string. Return any permutation of T (as a
 * string) that satisfies this property.
 */
public class CustomSortString {
    /**
     * Time complexity: O(S.length+T.length)
     * Space complexity: O(T.length)
     */
    public static String solution(String S, String T) {
        int[] count = new int[26];
        for (char c : T.toCharArray()) count[c - 'a']++;
        StringBuilder ans = new StringBuilder();
        for (char c : S.toCharArray()) {
            for (int i = 0; i < count[c - 'a']; ++i)
                ans.append(c);
            count[c - 'a'] = 0;
        }
        for (char c = 'a'; c <= 'z'; ++c)
            for (int i = 0; i < count[c - 'a']; ++i)
                ans.append(c);
        return ans.toString();
    }

    /**
     * Time complexity: O(S.length+T.length)
     * Space complexity: O(T.length)
     */
    public static String solution2(String S, String T){
        StringBuilder sb = new StringBuilder();
        StringBuilder[] sbArray = new StringBuilder[27];
        for (char c : T.toCharArray()) {
            int ind = S.indexOf(c);
            if (ind > -1) {
                if (sbArray[ind] == null) sbArray[ind] = new StringBuilder();
                sbArray[ind].append(c);
            } else {
                if (sbArray[26] == null) sbArray[26] = new StringBuilder();
                sbArray[26].append(c);
            }
        }
        for (StringBuilder s : sbArray) if (s != null) sb.append(s);
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(solution2("abc", "xbxcaxx"));
    }
}
