package Easy.String;

import java.util.HashSet;

/**
 * 409
 Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can be built with those letters.
 This is case sensitive, for example "Aa" is not considered a palindrome here.
 */
public class LongestPalindrome {
    private class Solution{
        public int longestPalindrome(String s) {
            if(s==null || s.length()==0) return 0;
            HashSet<Character> hs = new HashSet<Character>();
            int count = 0;
            for(int i=0; i<s.length(); i++){
                if(hs.contains(s.charAt(i))){
                    hs.remove(s.charAt(i));
                    count++;
                }else{
                    hs.add(s.charAt(i));
                }
            }
            if(!hs.isEmpty()) return count*2+1;
            return count*2;
        }
    }
}
