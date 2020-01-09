// 252
public class MeetingRooms
{
	public boolean canAttendMeetings(int[][] intervals) 
	{
		if (intervals.length == 0 || intervals[0].length == 0) return true;

		try
		{
			Arrays.sort(intervals, new Comparator<int[]>() {
				
				// early exit if we find anything during sort
				@Override
				public int compare(final int[] a, final int[] b)
				{
					if (a[0] < b[1] && a[1] > b[0]) 
					{
						throw new IllegalArgumentException();
					}

					return a[0] - b[0];
				}
			});
		}
		catch (IllegalArgumentException e)
		{
			return false;
		}

		// check condition of intesection
		for (int i = 1; i < intervals.length; i++)
		{
			if (intervals[i - 1][0] < intervals[i][1]
					&& intervals[i - 1][1] > intervals[i][0]) 
			{
				return false;
			}
		}

		return true;
	}

}

