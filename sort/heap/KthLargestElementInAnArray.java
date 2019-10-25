/*
 * 215
 * Google
 *
 * Comparing to the smallest of the biggest k elements.
 */
class Solution
{
	public int findKthLargest(int[] nums, int k)
	{
		int[] temp = new int[k + 1];
		for (int i = 0; i < k; i++)
		{
			temp[i + 1] = nums[i];
			swim(temp, i + 1);
		}

		for (int i = k; i < nums.length; i++)
		{
			if (temp[1] < nums[i])
			{
				temp[1] = nums[i];
				sink(temp, k + 1);
			}
		}

		return temp[1];
	}

	private void swim(int[] a, int i)
	{
		while (i > 1 && a[i] < a[i/2])
		{
			exch(a, i, i/2);
			i = i/2;
		}
	}

	private void sink(int[] a, int k)
	{
		int i = 1;
		while (i * 2 < k)
		{
			int j = i * 2;

			if (j + 1 < k && a[j + 1] < a[j])
			{
				j++;
			}

			if (a[j] > a[i])
			{
				break;
			}

			exch(a, i, j);
			i = j;
		}
	}

	private void exch(int[] a, int i, int j)
	{
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
}

