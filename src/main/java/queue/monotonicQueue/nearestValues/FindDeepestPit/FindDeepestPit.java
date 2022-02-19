package queue.monotonicQueue.nearestValues.FindDeepestPit;

import static java.util.stream.Collectors.toList;

import draw.StdDraw;
import generators.ArrayGenerator;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * @company TikTok
 * @desc Find Deepest Pit
 * You have an array of heights of the mountain, find the maximum depth in that mountain.
 */
public class FindDeepestPit {

	public static void main(String[] args) {

		StdDraw.setCanvasSize(1000, 1000);
		StdDraw.setPenColor(StdDraw.BLACK);

		int[] genAr = ArrayGenerator.genIntArray(10, 100, true);
		double offset = 0.02;
		System.out.println(Arrays.toString(genAr));

		Solution2 solution2 = new Solution2();
		int resultDepth = solution2.solve(genAr);
		double resD = (double) resultDepth / 100;
		System.out.println("resultDepth: " + resultDepth);
		int resultIndex = solution2.getIndMax();

		for (int i = 0; i < genAr.length; i++) {
			int val = genAr[i];

			double h = (double) val / 100;
			System.out.println("h: " + h);
			StdDraw.filledRectangle(offset, h / 2, 0.02, h / 2);
			double fh = Double.parseDouble(new DecimalFormat("0.00").format(h));

			if (i == resultIndex) {
				double x = h + resD / 2;
				double fx = Double.parseDouble(new DecimalFormat("0.00").format(h + resD));
				double fresd = Double.parseDouble(new DecimalFormat("0.00").format(resD));
				StdDraw.setPenColor(StdDraw.RED);
				StdDraw.filledRectangle(offset, x, 0.02, resD / 2);
				StdDraw.text(offset, fx + 0.01, (fresd) + "");
				StdDraw.setPenColor(StdDraw.BLACK);
				StdDraw.text(offset, h + 0.01, (fh) + "");
			} else {
				StdDraw.text(offset, h + 0.01, h + "");
			}

			offset += 0.04;
		}
	}

	public interface Solution {

		int solve(int[] heights);
	}

	/**
	 * Monotonic queue with types
	 * 3 types: left, bottom, right - in that order
	 * bigger left would remove everything to the left
	 * deeper bottom would remove everything until left
	 * bigger right would remove everything until bottom
	 */
	public static class Solution1 implements Solution {

		public int solve(int[] heights) {
			MQ q = new MQ(heights);
			for (int i = 1; i < heights.length; i++) {
				q.add(i, heights[i]);
			}
			return q.deepest == Integer.MIN_VALUE ? -1 : q.deepest;
		}

		class MQ {

			Height left, floor, right;
			int deepest;

			MQ(int[] heights) {
				deepest = Integer.MIN_VALUE;
				left = new Height(0, 0, heights[0]);
			}

			void add(int ind, int val) {
				if (val > left.value) {
					updateMax(val);
					left = new Height(0, ind, val);
					floor = null;
					right = null;
				} else if (floor == null || val < floor.value) {
					floor = new Height(1, ind, val);
				} else {
					right = new Height(2, ind, val);
					updateMax(right.value);
				}
			}

			void updateMax(int val) {
				if (floor != null) {
					int diff = Math.min(left.value, val) - floor.value;
					deepest = Math.max(diff, deepest);
				}
			}
		}

		class Height {

			int type; // 0,1,2
			int ind;
			int value;

			public Height(int type, int ind, int value) {
				this.type = type;
				this.ind = ind;
				this.value = value;
			}
		}
	}

	/**
	 * Simulating increasing the water level in each hole until the left or right pointer
	 * hits end or beginning of array.
	 */
	public static class Solution2 implements Solution {

		private int indMax;

		public int getIndMax() {
			return indMax;
		}

		public int solve(int[] h) {
			System.out.println("-------");
			System.out.println(Arrays.toString(h));

			int n = h.length;
			List<Pair> list = IntStream
					.range(0, n)
					.mapToObj(ind -> new Pair(ind, h[ind]))
					.sorted((a, b) -> a.val - b.val)
					.collect(toList());
			int maxHeight = -1;
			Set<Integer> seenInd = new HashSet<>();
			for (Pair p : list) {

				if (seenInd.contains(p.ind)) {
					continue;
				}

				Set<Integer> seenIndLocal = new HashSet<>();
				System.out.println("Checking index: " + p.ind);
				String[] seen2 = new String[n];
				Arrays.fill(seen2, "_");
				seen2[p.ind] = "|X|";
				seenIndLocal.add(p.ind);
				int curH = p.val, newH = curH;
				int curInd = p.ind;
				int maxLocalHeight = -1, maxLocalInd = -1;
				boolean reachedEdge = false;

				while (!reachedEdge) {
					// go left
					// 4,2,[0]
					int l = curInd;
					while (l >= 0 && h[l] <= newH) {
						if (seenInd.contains(l)) {
							l--;
							continue;
						}
						seenIndLocal.add(l);
						if (l != curInd) seen2[l] = "X";
						if (newH - h[l] != 0) {
							if (newH - h[l] > maxLocalHeight) {
								maxLocalHeight = newH - h[l];
								maxLocalInd = l;
							}
						}
						l--;
					}
					if (l <= -1) reachedEdge = true;
					// go right
					// [0],2
					int r = curInd;
					while (r < n && h[r] <= newH) {
						if (seenInd.contains(r)) {
							r++;
							continue;
						}
						seenIndLocal.add(r);
						if (r != curInd) seen2[r] = "X";
						if (newH - h[r] != 0) {
							if (newH - h[r] > maxLocalHeight) {
								maxLocalHeight = newH - h[r];
								maxLocalInd = r;
							}
						}
						r++;
					}
					if (r >= n) reachedEdge = true;

					newH++;
				}

				seenInd.addAll(seenIndLocal);
				System.out.println(Arrays.toString(seen2));
				System.out.println("maxLocalHeight: " + maxLocalHeight);
				if (maxLocalHeight > maxHeight) {
					maxHeight = maxLocalHeight;
					indMax = maxLocalInd;
				}
			}

			System.out.println("maxHeight: " + maxHeight);

			return maxHeight;
		}

		class Pair {

			int ind, val;

			public Pair(int ind, int val) {
				this.ind = ind;
				this.val = val;
			}
		}
	}
}


