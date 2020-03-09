package regular.array;

import util.test.Tester;

import java.util.PriorityQueue;

/**
 * 1366
 *
 * ======
 *
 * Task.
 *
 * In a special ranking system, each voter gives a rank from highest to lowest
 * to all teams participated in the competition.
 *
 * The ordering of teams is decided by who received the most position-one votes.
 * If two or more teams tie in the first position, we consider the second
 * position to resolve the conflict, if they tie again, we continue this process
 * until the ties are resolved. If two or more teams are still tied after
 * considering all positions, we rank them alphabetically based on their team
 * letter.
 *
 * Given an array of strings votes which is the votes of all voters in the
 * ranking systems. Sort all teams according to the ranking system described
 * above.
 *
 * Return a string of all teams sorted by the ranking system.
 *
 * ======
 *
 * Source: Leetcode
 */
public class RankTeamsByVotes
{
	/**
	 * SF.
	 */
	private static class Solution
	{
		public String rankTeams(String[] v)
		{
			boolean[] seen = new boolean[26];
			int[][] c = new int[26][v[0].length() + 1];

			for (String s : v)
			{
				for (int i = 0; i < s.length(); i++)
				{
					int ch = s.charAt(i) - 'A';
					c[ch][i]++;
					seen[ch] = true;
				}
			}

			PriorityQueue<CharAndPositionsCounts> q = new PriorityQueue<>((a, b) ->
			{
				int[] aCounts = a.counts;
				int[] bCounts = b.counts;
				int i = 0;
				while (i < aCounts.length && aCounts[i] == bCounts[i]) i++;
				return i == aCounts.length ? a.ch - b.ch : bCounts[i] - aCounts[i];
			});

			for (int i = 0; i < 26; i++)
				if (seen[i])
					q.add(new CharAndPositionsCounts((char) (i + 'A'), c[i]));

			StringBuilder sb = new StringBuilder();
			while (!q.isEmpty())
				sb.append(q.poll().ch);

			return sb.toString();
		}

		private class CharAndPositionsCounts
		{
			char ch;
			int[] counts;

			public CharAndPositionsCounts(char ch, int[] counts)
			{
				this.ch = ch;
				this.counts = counts;
			}
		}
	}

	public static void main(String[] args)
	{
		new Tester(new Solution())
				.add(new String[]{"ABC", "ACB", "ABC", "ACB", "ACB"}).expect("ACB")
				.add(new String[]{"WXYZ", "XYZW"}).expect("XWYZ")
				.add(new String[]{"ZMNAGUEDSJYLBOPHRQICWFXTVK"}).expect("ZMNAGUEDSJYLBOPHRQICWFXTVK")
				.add(new String[]{"BCA", "CAB", "CBA", "ABC", "ACB", "BAC"}).expect("ABC")
				.add(new String[]{"M", "M", "M", "M"}).expect("M")
				.add(new String[]{"FVSHJIEMNGYPTQOURLWCZKAX", "AITFQORCEHPVJMXGKSLNZWUY", "OTERVXFZUMHNIYSCQAWGPKJL", "VMSERIJYLZNWCPQTOKFUHAXG", "VNHOZWKQCEFYPSGLAMXJIUTR", "ANPHQIJMXCWOSKTYGULFVERZ", "RFYUXJEWCKQOMGATHZVILNSP", "SCPYUMQJTVEXKRNLIOWGHAFZ", "VIKTSJCEYQGLOMPZWAHFXURN", "SVJICLXKHQZTFWNPYRGMEUAO", "JRCTHYKIGSXPOZLUQAVNEWFM", "NGMSWJITREHFZVQCUKXYAPOL", "WUXJOQKGNSYLHEZAFIPMRCVT", "PKYQIOLXFCRGHZNAMJVUTWES", "FERSGNMJVZXWAYLIKCPUQHTO", "HPLRIUQMTSGYJVAXWNOCZEKF", "JUVWPTEGCOFYSKXNRMHQALIZ", "MWPIAZCNSLEYRTHFKQXUOVGJ", "EZXLUNFVCMORSIWKTYHJAQPG", "HRQNLTKJFIEGMCSXAZPYOVUW", "LOHXVYGWRIJMCPSQENUAKTZF", "XKUTWPRGHOAQFLVYMJSNEIZC", "WTCRQMVKPHOSLGAXZUEFYNJI"}).expect("VWFHSJARNPEMOXLTUKICZGYQ")
				.run();
	}
}
