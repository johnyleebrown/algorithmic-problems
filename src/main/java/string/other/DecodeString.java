package string.other;

/*
 * 394
 * Company: Google.
 */
public class DecodeString
{
	// Straight forward recursive solution.
	class Solution
	{
		private int i = 0;

		public String decodeString(String s)
		{
			String decodedString = "";

			while (i < s.length())
			{
				char charAtIndex = s.charAt(i);

				if (Character.isDigit(charAtIndex))
				{
					int k = parseNumberFromString(s);
					String t = decodeString(s);

					while (--k >= 0)
					{
						decodedString += t;
					}
				}
				else if (charAtIndex == ']')
				{
					i++;
					break;
				}
				else
				{
					i++;
					decodedString += charAtIndex;
				}
			}

			return decodedString;
		}

		private int parseNumberFromString(String s)
		{
			String num = "";
			while (i < s.length() && Character.isDigit(s.charAt(i)))
			{
				num += s.charAt(i);
				i++;
			}
			i++; // get to valid character

			return Integer.parseInt(num);
		}
	}

}
