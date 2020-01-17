package string;

/*
 * 205
 * Google
 *
 * Keep a map of characters at same position, for each char we keep the other.
 */
class Solution
{
    public boolean isIsomorphic(String s, String t)
    {
        char[] sourceMap = new char[256], targetMap = new char[256];

        for (int i = 0; i < s.length(); i++)
        {
            char sourceChar = s.charAt(i), targetChar = t.charAt(i);

            if (sourceMap[sourceChar] == 0 && targetMap[targetChar] == 0)
            {
                sourceMap[sourceChar] = targetChar;
                targetMap[targetChar] = sourceChar;
            }
            else if (sourceMap[sourceChar] != targetChar || targetMap[targetChar] != sourceChar)
            {
                return false;
            }
        }
        return true;
    }
}

