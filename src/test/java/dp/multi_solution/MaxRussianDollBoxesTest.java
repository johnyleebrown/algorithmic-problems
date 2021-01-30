package dp.multi_solution;

import org.junit.jupiter.api.Test;
import util.utils.Generator;
import util.utils.Timer;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MaxRussianDollBoxesTest {
	@Test
	void smallTest1() {
		MaxRussianDollBoxes.Solution s = new MaxRussianDollBoxes.Solution2();
		int[][] input = new int[][]{{3, 9, 9}, {1, 4, 10}, {5, 10, 11}, {3, 9, 3}, {1, 5, 3}, {7, 12, 1}};
		assertEquals(3, s.solve(input));
	}

	@Test
	void smallTest2() {
		MaxRussianDollBoxes.Solution s = new MaxRussianDollBoxes.Solution2();
		int[][] input = new int[][]{{0, 0, 0}, {1, 1, 1}, {2, 2, 2}, {3, 3, 3}};
		assertEquals(4, s.solve(input));
	}

	@Test
	void smallTest3() {
		MaxRussianDollBoxes.Solution s = new MaxRussianDollBoxes.Solution2();
		int[][] input = new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
		assertEquals(1, s.solve(input));
	}

	@Test
	void stressTest() {
		int[][] in = Generator.Two.intAr(10_000, 3, 1_000);

		List<MaxRussianDollBoxes.Solution> solutions = new LinkedList<>();
		solutions.add(new MaxRussianDollBoxes.Solution1());
		solutions.add(new MaxRussianDollBoxes.Solution2());
		solutions.add(new MaxRussianDollBoxes.SolutionAgainst());

		List<Integer> answers = new LinkedList<>();
		for (MaxRussianDollBoxes.Solution solution : solutions) {
			System.out.println("[start] - test " + answers.size());
			Timer t = new Timer().start();
			int ans = solution.solve(in);
			t.end().printResult();
			System.out.println("Ans: " + ans);
			System.out.println("[done] - test " + answers.size());
			answers.add(ans);
		}
		for (int i = 0; i < answers.size() - 1; i++) {
			assertEquals(answers.get(i), answers.get(i + 1));
		}
	}
}