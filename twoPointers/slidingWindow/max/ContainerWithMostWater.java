package slidingWindow.min.max;

// 11
public class ContainerWithMostWater 
{
    public int maxArea(int[] height) 
	{
        int max = 0, start = 0, end = height.length - 1;
        while (start < end) 
		{
			int area = Math.min(height[start], height[end]) * (end - start);
            max = Math.max(max, area);

            if (height[start] < height[end]) 
			{
				start++;
			}
            else 
			{
				end--;
			}
        }

        return max;
    }
}
