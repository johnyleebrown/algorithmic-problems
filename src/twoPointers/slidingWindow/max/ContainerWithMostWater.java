package twoPointers.slidingWindow.max;

/**
 * 11
 *
 * ======
 *
 * Task.
 *
 * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines
 * are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis
 * forms a container, such that the container contains the most water.
 */
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
