package string.trie;

import java.util.LinkedList;
import java.util.List;

/**
 * 1408
 *
 * ======
 *
 * Task.
 *
 * Given an array of string words. Return all strings in words which is
 * substring of another word in any order.
 *
 * String words[i] is substring of words[j], if can be obtained removing some
 * characters to left and/or right side of words[j].
 *
 * ======
 *
 * Source: Leetcode
 */
public class StringMatchingInAnArray {
	/**
	 * New methods put suffixes - adding all variants of the word except of the
	 * whole word.
	 */
	public static class Solution {
		public List<String> stringMatching(String[] a) {
			RWayTrie t = new RWayTrie();
			for (String s : a) {
				t.putSuffixes(s);
			}
			List<String> ans = new LinkedList<>();
			for (String s : a) {
				if (t.prefixExists(s)) {
					ans.add(s);
				}
			}
			return ans;
		}

		public static class RWayTrie {
			private static final int R = 256;
			private Node root;
			private int wordCount;

			public RWayTrie() {
			}

			public boolean prefixExists(String prefix) {
				if (root == null) {
					return false;
				}

				Node currentNode = root;
				int n = prefix.length();

				for (int i = 0; i < n; i++) {
					char c = prefix.charAt(i);
					if (!currentNode.contains(c)) {
						return false;
					}
					currentNode = currentNode.get(c);
				}

				return true;
			}

			public void putSuffixes(String str) {
				int n = str.length();
				for (int i = 0; i < n; i++) {
					for (int j = i; j < n; j++) {
						if (!(i == 0 && j == n - 1)) {
							put(str, i, j);
						}
					}
				}
			}

			public void put(String key, int lo, int hi) {
				putIteratively(key, lo, hi, null);
			}

			private void putIteratively(String key, int lo, int hi, Object val) {
				if (root == null) {
					root = new Node();
				}

				Node currentNode = root;

				for (int i = lo; i <= hi; i++) {
					char currentChar = key.charAt(i);
					if (!currentNode.contains(currentChar)) {
						currentNode.put(currentChar);
					}
					currentNode = currentNode.get(currentChar);
				}

				if (!currentNode.isEnd) {
					currentNode.setEnd();
					wordCount++;
				}

				currentNode.setVal(val);
			}

			private class Node {
				private Object val;
				private boolean isEnd;
				private Node[] next = new Node[R];

				public boolean isWordEnd() {
					return this.isEnd;
				}

				public void setEnd() {
					this.isEnd = true;
				}

				public Node get(char c) {
					return next[c];
				}

				public boolean contains(char c) {
					return get(c) != null;
				}

				private void put(char currentChar) {
					next[currentChar] = new Node();
				}

				private void setVal(Object val) {
					this.val = val;
				}

				public Node[] getNext() {
					return next;
				}
			}
		}
	}
}