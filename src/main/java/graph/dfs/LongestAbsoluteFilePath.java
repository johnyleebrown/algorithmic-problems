package graph.dfs;

/**
 * 388
 *
 * ======
 *
 * Here, we have dir as the only directory in the root. dir contains two subdirectories, subdir1 and
 * subdir2. subdir1 contains a file file1.ext and subdirectory subsubdir1. subdir2 contains a
 * subdirectory subsubdir2, which contains a file file2.ext.
 *
 * In text form, it looks like this (with ⟶ representing the tab character):
 *
 * dir
 * ⟶ subdir1
 * ⟶ ⟶ file1.ext
 * ⟶ ⟶ subsubdir1
 * ⟶ subdir2
 * ⟶ ⟶ subsubdir2
 * ⟶ ⟶ ⟶ file2.ext
 * If we were to write this representation in code, it will look like this:
 * "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext". Note
 * that the '\n' and '\t' are the new-line and tab characters.
 *
 * Every file and directory has a unique absolute path in the file system, which is the order of
 * directories that must be opened to reach the file/directory itself, all concatenated by '/'s.
 * Using the above example, the absolute path to file2.ext is "dir/subdir2/subsubdir2/file2.ext".
 * Each directory name consists of letters, digits, and/or spaces. Each file name is of the form
 * name.extension, where name and extension consist of letters, digits, and/or spaces.
 *
 * Given a string input representing the file system in the explained format, return the length of
 * the longest absolute path to a file in the abstracted file system. If there is no file in the
 * system, return 0.
 *
 * ======
 *
 * https://leetcode.com/problems/longest-absolute-file-path/
 */
public class LongestAbsoluteFilePath {
	/**
	 * Recursive
	 */
	public static class Solution {

		public int lengthLongestPath(String input) {
			int res = dfs(0, input.split("\n"), new Ind());
			return res == Integer.MIN_VALUE ? 0 : res;
		}

		private int dfs(int curLvl, String[] ar, Ind i) {

			int ans = Integer.MIN_VALUE;

			while (i.v < ar.length && getCurLevel(ar[i.v]) == curLvl) {
				String str = ar[i.v];
				i.v++;
				if (isFileExists(str)) {
					ans = Math.max(ans, str.length() - curLvl);
				} else {
					int localAns = dfs(curLvl + 1, ar, i);
					if (localAns != Integer.MIN_VALUE)
						ans = Math.max(ans, -curLvl + str.length() + 1 + localAns);
				}
			}

			return ans;
		}

		private int getCurLevel(String s) {
			int j = 0;
			while (s.charAt(j) == '\t') {
				j++;
			}
			return j;
		}

		private boolean isFileExists(String inp) {
			return inp.contains(".");
		}

		private class Ind {
			int v;
		}
	}
}