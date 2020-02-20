package string.regular;

/**
 * 28
 * Implement strStr().
 * Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 */
public class ImplementStrStr {

    // brute force
    public  int strStr( String haystack,  String needle) {
        if (needle.isEmpty()) return 0;
        if (haystack.isEmpty() || haystack.length() < needle.length()) return -1;
        int N = haystack.length();
        int M = needle.length();
        for(int i = 0; i <= N-M; i++){
            int j;
            for(j = 0; j < M; j++) if(haystack.charAt(i+j) != needle.charAt(j)) break;
            if (j == M) return i;
        }
        return -1;
    }

    // w substring
    public int strStr2(String haystack, String needle) {
        int l1 = haystack.length();
        int l2 = needle.length();
        if (l1 < l2) return -1;
        for (int i = 0; i <= haystack.length() - l2; i++) if (haystack.substring(i, i+l2).equals(needle)) return i;
        return -1;
    }
}
