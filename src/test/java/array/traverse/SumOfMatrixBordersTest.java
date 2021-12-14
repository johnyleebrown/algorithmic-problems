package array.traverse;

import array.traverse.SumOfMatrixBorders.S;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SumOfMatrixBordersTest {

	@Test
	void testSolution1Solve() {
		assertEquals(0, (new SumOfMatrixBorders.Solution1()).solve(new int[][]{}).length);
	}

	@Test
	void testSolution1Solve2() {
		int[] actualSolveResult = (new SumOfMatrixBorders.Solution1())
				.solve(new int[][]{new int[]{0, 0, 0, 0}, new int[]{0, 0, 0, 0}});
		assertEquals(1, actualSolveResult.length);
		assertEquals(0, actualSolveResult[0]);
	}

	/*
			int[][] a = new int[][]{
					{9, 7, 4, 5},
					{1, 6, 2, -6},
					{12, 20, 2, 0},
					{-5, -6, 7, -2}};
	 */
	@Test
	void test1() {
		List<S> sols = Arrays.asList(new SumOfMatrixBorders.Solution1(),
				new SumOfMatrixBorders.Solution2());
		int numCases = 10;
		while (--numCases >= 0) {
			int[][] a = genMatrix();
			System.out.println("case #" + (100 - numCases) + ", length: " + a.length);
			if (a.length < 5) {
				for (int i = 0; i < a.length; i++) {
					System.out.println(Arrays.toString(a[i]));
				}
			}
			int[] ans1 = sols.get(0).solve(a);
			int[] ans2 = sols.get(1).solve(a);
			assertArrayEquals(ans1, ans2);
			System.out.println(Arrays.toString(ans1));
			System.out.println("=============");
		}
	}

	private int[][] genMatrix() {
		Random r = new Random();
		int n = r.nextInt(100);
		if (n % 2 != 0) n++;
		int[][] a = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				a[i][j] = r.nextInt(50);
			}
		}
		return a;
	}
}