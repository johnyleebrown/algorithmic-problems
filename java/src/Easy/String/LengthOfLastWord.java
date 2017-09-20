package Easy.String;

/**
 * 58
 * Given a string s consists of upper/lower-case alphabets and empty space characters ' ',
 * return the length of last word in the string.
 * If the last word does not exist, return 0.
 */
public class LengthOfLastWord {

    // O(n), O(1)
    public int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0) return 0;
        int j = s.length() - 1;
        for (; j >= 0; j--) if (s.charAt(j) != ' ') break;
        int end = j;
        for (; j >= 0; j--) if (s.charAt(j) == ' ') break;
        return end - j;
    }

    public int lengthOfLastWord2(String s) {
        return s.trim().length() - s.trim().lastIndexOf(" ") - 1;
    }

    public int lengthOfLastWord3(String s) {
        return s.trim().length() == 0 ? 0 : s.split(" ")[s.split(" ").length - 1].length();
    }
}
