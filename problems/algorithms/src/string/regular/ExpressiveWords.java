package string.regular;

/*
 * 809
 * Google
 */
class Solution
{
    private final static int VALID_MIN_LEN = 3;
    private static String source;
    public int expressiveWords(String S, String[] words)
    {
        source = S;
        int c = 0;
        for (String s: words)
        {
            if (isValidString(s))
            {
                c++;
            }
        }
        return c;
    }
    private boolean isValidString(String target)
    {
        int n = source.length(), m = target.length();
        if (m == 0 && n != 0)
        {
            return false;
        }
        if (m > n)
        {
            return false;
        }
        int i = 0, j = 0;
        while (i < n)
        {
            char curChar = source.charAt(i);
            int sourceCount = 0, targetCount = 0;
            while (equalsCurChar(i, curChar) || equalsCurChar(j, target, curChar))
            {
                if (equalsCurChar(i, curChar))
                {
                    i++;
                    sourceCount++;
                }
                if (equalsCurChar(j, target, curChar))
                {
                    j++;
                    targetCount++;
                }
            }
            if (!isValidRangeOfChars(sourceCount, targetCount))
            {
                return false;
            }
        }
        return true;
    }
    private boolean equalsCurChar(int i, char cur)
    {
        return i < source.length() && source.charAt(i) == cur;
    }
    private boolean equalsCurChar(int i, String source, char cur)
    {
        return i < source.length() && source.charAt(i) == cur;
    }
    private boolean isValidRangeOfChars(int countSourceChar, int countTargetChar)
    {
        if (countSourceChar == countTargetChar)
        {
            return true;
        }
        return countSourceChar >= VALID_MIN_LEN && countSourceChar >= countTargetChar;
    }
}

