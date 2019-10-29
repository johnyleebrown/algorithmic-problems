/*
 * 382
 */
class Solution
{
	private ListNode head;

	public Solution(ListNode head)
	{
		this.head = head;	
	}

	public int getRandom()
	{
		int result = 0;
		int countNodes = 0;

		while (head != null)
		{
			Random r = new Random();
			countNodes++;
			if (r.nextInt(countNodes) == 0)
			{
				result = head.val;
			}
		}
		return result;
	}
}

