package backtracking.array;

import java.util.*;
import static test.Out.sout;

/*
 * TODO
 * Company:Google
 *
 * https://leetcode.com/discuss/interview-question/363871/Google-or-Memorize-Phone-Number
 */
public class MemorizePhoneNumber
{
	private List<List<Integer>> ans;
	private int quality = Integer.MIN_VALUE;
	private boolean[] seen;
	private int eg = 0, gg = 0;

	public List<List<Integer>> memorize(int[] number)
	{
		if (number.length == 0) return null;
		ans = new ArrayList<>();
		seen = new boolean[number.length];

		generate(new ArrayList<>(), number, 0, new ArrayList<>());

		return ans;
	}

	private void generate(List<Integer> cur, int[] a, 
			int localc, List<List<Integer>> curans)
	{
		if (localc > 1)
		{
			g(cur, localc, curans);
		}

		if (hasMaxQuality()) 
		{
			ans = new ArrayList(curans);
		}
		else
		{
			for (int i = 0; i < a.length; i++)
			{
				if (seen[i]) continue;

				seen[i] = true;
				cur.add(a[i]);
				generate(cur, a, localc + 1 == 4 ? 1 : localc + 1, curans);
				generate(cur, a, localc + 1 == 3 ? 1 : localc + 1, curans);
				cur.remove(i);
				seen[i] = false;
			}
		}
	}

	private boolean hasMaxQuality()
	{
		int x = 2 * eg + gg;
		if (quality < x)
		{
			quality = x;
			return true;
		}

		return false;
	}

	private void g(List<Integer> cur, int localc, List<List<Integer>> curans)
	{
		if (localc == 2)
		{
			if (cur.get(0) == cur.get(1)) 
			{
				eg++;
			}
		}
		else
		{
			if (cur.get(0) == cur.get(1) || cur.get(2) == cur.get(1)) 
			{
				if (cur.get(0) == cur.get(2))
				{
					eg++;
				}
				else
				{
					gg++;
				}
			}
			else if (cur.get(0) == cur.get(2))
			{
				gg++;
			}
		}

		curans.add(new ArrayList(cur));
	}

	public static void main(String[] args)
	{
		sout("hi");
		int[] a = new int[]{3,3,3,0,0,2,1};
		MemorizePhoneNumber m = new MemorizePhoneNumber();
		List<List<Integer>> ans = m.memorize(a);
		for (List<Integer> l: ans)
		{
			sout(l);
		}
	}
}

