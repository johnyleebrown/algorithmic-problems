package hashtable.regular;

/**
 * 299
 */
public class BullsAndCows
{
	class Solution
	{
		public String getHint(String secret, String guess)
		{
			int n = secret.length();

			int ac = 0, bc = 0;

			int[] b1 = new int[10];
			int[] b2 = new int[10];

			for (int i = 0; i < n; i++)
			{
				if (secret.charAt(i) == guess.charAt(i))
				{
					ac++;
				}
				else
				{
					b1[Character.getNumericValue(secret.charAt(i))]++;
					b2[Character.getNumericValue(guess.charAt(i))]++;
				}
			}

			for (int i = 0; i < 10; i++)
			{
				if (b2[i] <= b1[i])
				{
					bc += b2[i];
				}
				else
				{
					bc += b1[i];
				}
			}

			return String.valueOf(ac) + "A" + String.valueOf(bc) + "B";
		}
	}
}