package sort.heap;

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
	 *
	 */
	private static class Solution
	{
		public String rankTeams(String[] a) {
			int[] c = new int[26];
			for (String s : a)
			{
				for (int i = 0; i < s.length(); i++)
				{
					int x = s.charAt(i) - 'A';
					c[x] += 26 - i;
				}
			}
			int max = 26_026;
			PriorityQueue<Character>[] cc = new PriorityQueue[max + 1];
			for (int i = 0; i < 26; i++)
			{
				if (cc[c[i]] == null)
					cc[c[i]] = new PriorityQueue<>();
				cc[c[i]].add((char)(i + 'A'));
			}
			StringBuilder sb = new StringBuilder();
			for (int i = max; i >= 1; i--)
				while (cc[i]!=null&&!cc[i].isEmpty())
					sb.append(cc[i].poll());
			return sb.toString();
		}
	}

	public static void main(String[] args)
	{
		new Tester(new Solution())
				.add(new String[]{"ABC","ACB","ABC","ACB","ACB"}).expect("ACB")
				.add(new String[]{"WXYZ","XYZW"}).expect("XWYZ")
				.add(new String[]{"ZMNAGUEDSJYLBOPHRQICWFXTVK"}).expect("ZMNAGUEDSJYLBOPHRQICWFXTVK")
				.add(new String[]{"BCA","CAB","CBA","ABC","ACB","BAC"}).expect("ABC")
				.add(new String[]{"M","M","M","M"}).expect("M")
				.add(new String[]{"FVSHJIEMNGYPTQOURLWCZKAX","AITFQORCEHPVJMXGKSLNZWUY","OTERVXFZUMHNIYSCQAWGPKJL","VMSERIJYLZNWCPQTOKFUHAXG","VNHOZWKQCEFYPSGLAMXJIUTR","ANPHQIJMXCWOSKTYGULFVERZ","RFYUXJEWCKQOMGATHZVILNSP","SCPYUMQJTVEXKRNLIOWGHAFZ","VIKTSJCEYQGLOMPZWAHFXURN","SVJICLXKHQZTFWNPYRGMEUAO","JRCTHYKIGSXPOZLUQAVNEWFM","NGMSWJITREHFZVQCUKXYAPOL","WUXJOQKGNSYLHEZAFIPMRCVT","PKYQIOLXFCRGHZNAMJVUTWES","FERSGNMJVZXWAYLIKCPUQHTO","HPLRIUQMTSGYJVAXWNOCZEKF","JUVWPTEGCOFYSKXNRMHQALIZ","MWPIAZCNSLEYRTHFKQXUOVGJ","EZXLUNFVCMORSIWKTYHJAQPG","HRQNLTKJFIEGMCSXAZPYOVUW","LOHXVYGWRIJMCPSQENUAKTZF","XKUTWPRGHOAQFLVYMJSNEIZC","WTCRQMVKPHOSLGAXZUEFYNJI"}).expect("VWFHSJARNPEMOXLTUKICZGYQ")
				.run();
	}
}
