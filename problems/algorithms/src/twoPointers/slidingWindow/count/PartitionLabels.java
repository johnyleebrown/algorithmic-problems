package twoPointers.slidingWindow.count;

import java.util.ArrayList;
import java.util.List;

/**
 * 763
 *
 * ======
 *
 * Task.
 *
 * A string S of lowercase letters is given. We want to partition this string
 * into as many parts as possible so that each letter appears in at most one
 * part, and return a list of integers representing the size of these parts.
 *
 * ======
 *
 * Source: Leetcode
 */
public class PartitionLabels {

	/**
	 * Think about the problem of maximum number of intersecting intervals.
	 * We keep count of intersecting intervals and increment when the interval starts, decrement
	 * when it ends. All we need to do is find starts and ends of each char.
	 */
	public static class Solution {
		public List<Integer> partitionLabels(String s) {
			List<Integer> ans = new ArrayList<>();
			int n = s.length();

			// start and ends of chars
			int[][] intervals = new int[26][2];
			for (int i = 0; i < n; i++) {
				int curChar = s.charAt(i) - 'a';
				if (intervals[curChar][0] == 0) {
					intervals[curChar][0] = i;
				}
				intervals[curChar][1] = i;
			}

			// because we might have overwritten 0 index char start position
			intervals[s.charAt(0) - 'a'][0] = 0;

			int cur = 0;
			int prev = -1;
			for (int i = 0; i < n; i++) {
				int curChar = s.charAt(i) - 'a';

				// if we are at start
				if (intervals[curChar][0] == i) {
					cur++;
				}

				// if we are at end
				if (intervals[curChar][1] == i) {
					cur--;
				}

				// if we don't have overlapping intervals at the moment
				if (cur == 0) {
					ans.add(i - prev);
					prev = i;
				}
			}
			return ans;
		}
	}

	/**
	 * Get ends of all chars, then record a substring when the end index is
	 * equal to cur index.
	 */
	public static class Solution1 {
		public List<Integer> partitionLabels(String s) {
			List<Integer> ans = new ArrayList<>();

			// record the last index of the each char
			int[] lastIndexes = new int[26];
			for (int i = 0; i < s.length(); i++) {
				lastIndexes[s.charAt(i) - 'a'] = i;
			}

			// record the end index of the current sub string
			int last = 0;
			int start = 0;
			for (int i = 0; i < s.length(); i++) {
				last = Math.max(last, lastIndexes[s.charAt(i) - 'a']);

				// reached the end of substring
				if (last == i) {
					ans.add(last - start + 1);
					start = last + 1;
				}
			}

			return ans;
		}
	}
}