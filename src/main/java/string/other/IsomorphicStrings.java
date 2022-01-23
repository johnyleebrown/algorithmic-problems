package string.other;

/**
 * 205
 */
public class IsomorphicStrings
{
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
}