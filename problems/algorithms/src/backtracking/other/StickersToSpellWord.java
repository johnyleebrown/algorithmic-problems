package backtracking.other;

import java.util.ArrayList;
import java.util.List;

/**
 * 691
 */
public class StickersToSpellWord
{
	class Solution
	{
		private int ans = Integer.MAX_VALUE;

		private static final int BASE = 0, CUR = 1;

		private int[][][] dict;
		private boolean[] targetSeen;
		private int[] localCounter;

		private void fillDict()
		{
			// todo
		}

		public int minStickers(String[] stickers, String target)
		{
			dict = new int[stickers.length][123][2];
			targetSeen = new boolean[target.length()];
			localCounter = new int[stickers.length];

			generate(target.length());

			return ans == Integer.MAX_VALUE ? -1 : ans;
		}

		private void generate(int leftOver)
		{
			if (leftOver == 0)
			{
				// todo
				// looop over localCounter
				int curAns = 0;
				ans = Math.min(ans, curAns);
			}
			else
			{
				List<Integer> stickers = new ArrayList<>();
				for (int word = 0; word < stickers.size(); word++)
				{
					for (int letter = 0; letter < targetSeen.length; letter++)
					{
						if (targetSeen[letter]) continue;
						targetSeen[letter] = true;

						if (dict[word][letter][BASE] != 0)
						{
							localCounter[word] = Math.max(localCounter[word],
									(++dict[word][letter][CUR] / dict[word][letter][BASE]));
							generate(leftOver - 1);
						}

						targetSeen[letter] = false;
					}
				}
			}
		}
	}
}
/*
"thehat"
^
110101

["with", "example", "science"]

"with"
w 1
i 1
t 1 / 2 curans = 2
h 1 / 2 curans = 2

"example"
e 2 / 1 curans = 2
x 1
a 1
m 1
p 1
l 1

"science"
s 1
c 2
i 1
e 2
n 1
*/