package string.regular;

/*
 * 833
 * Company:Google
 */
class Solution 
{
    public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) 
	{
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < sources.length; i++)
        {
            if (S.startsWith(sources[i], indexes[i]))
            {
                map.put(indexes[i], i);
            }
        }

        int i = 0;
        String ans = "";
        while (i < S.length())
        {
            if (map.containsKey(i))
            {
                ans += targets[map.get(i)];
                i += sources[map.get(i)].length();
            }
            else
            {
                ans += S.charAt(i);
                i++;
            }
        }
        return ans;
    }
}

