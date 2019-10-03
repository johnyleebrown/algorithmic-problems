// 939
class Solution 
{
    public int minAreaRect(int[][] points) 
	{
		// map of x's and set of y's
        Map<Integer, Set<Integer>> map = new HashMap<>();
		for (int[] p : points) 
		{
            if (!map.containsKey(p[0])) 
			{
                map.put(p[0], new HashSet<>());
            }

            map.get(p[0]).add(p[1]);
        }

        int min = Integer.MAX_VALUE;
		for (int[] p1 : points) 
		{
            for (int[] p2 : points) 
			{
				// looking only for points on a diagonal
                if (p1[0] == p2[0] || p1[1] == p2[1]) 
				{
                    continue;
                }

				// if point 1 has y of point 2 and point 2 has y of point 2
                if (map.get(p1[0]).contains(p2[1]) && map.get(p2[0]).contains(p1[1])) 
				{
                    min = Math.min(min, Math.abs(p1[0] - p2[0]) * Math.abs(p1[1] - p2[1]));
                }
            }
        }

        return min == Integer.MAX_VALUE ? 0 : min;
    }
}
