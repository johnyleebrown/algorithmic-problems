// 658
class Solution 
{
    public List<Integer> findClosestElements(int[] arr, int k, int x) 
	{
		int lo = 0;
        int hi = arr.length - k - 1;

        while (lo <= hi)
        {
            int mid = lo + (hi - lo) / 2;

            if (x - arr[mid] > arr[mid + k] - x)
            {
                lo = mid + 1;
            }
            else
            {
                hi = mid - 1;
            }
        }

        List<Integer> res = new ArrayList(k);
        for (int i = 0; i < k; i++) res.add(arr[lo + i]);
        return res;
    }
}

