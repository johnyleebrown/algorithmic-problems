package queue.monotonicQueue.nearestValues.FindDeepestPit;

import static org.junit.jupiter.api.Assertions.*;

import generators.ArrayGenerator;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import queue.monotonicQueue.nearestValues.FindDeepestPit.FindDeepestPit.Solution;
import queue.monotonicQueue.nearestValues.FindDeepestPit.FindDeepestPit.Solution1;
import queue.monotonicQueue.nearestValues.FindDeepestPit.FindDeepestPit.Solution2;

class FindDeepestPitTest {

	List<Solution> solutionList = Arrays.asList(new Solution1(), new Solution2());

	@Test
	void easy() {
		int[][] inputs = new int[][]{
				new int[]{10, 0, 10},
				new int[]{100, 0, 10, 10, 100},
				new int[]{100, 0, 10, -50, 0},
				new int[]{30, 0, 40},
				new int[]{30, 0, 20},
				new int[]{60, 30, 0, 20},
				new int[]{60, 30, 0, 50},
				new int[]{60, 30, 0, 100}
		};

		int[] answers = new int[]{
				10,
				100,
				50,
				30,
				20,
				20,
				50,
				60
		};

		for (int i = 0; i < inputs.length; i++) {
			for (Solution s : solutionList) {
				assertEquals(true, s.solve(inputs[i]) == answers[i]);
			}
		}
	}

	@Test
	void noPit() {
		int[][] inputs = new int[][]{
				new int[]{100, 200},
				new int[]{100},
				new int[]{100, 50},
				new int[]{100, 50, 0},
				new int[]{0, 50},
				new int[]{60, 30, 0, -30}
		};

		int[] answers = new int[]{
				-1,
				-1,
				-1,
				-1,
				-1,
				-1
		};

		for (int i = 0; i < inputs.length; i++) {
			for (Solution s : solutionList) {
				assertEquals(true, s.solve(inputs[i]) == answers[i]);
			}
		}
	}

	@Test
	void harder() {
		int[][] inputs = new int[][]{
				new int[]{5, 0, 5, 100, 50, 100},
				new int[]{40, 20, 0, 30, 10, 20},
				new int[]{40, 20, 0, 30, 20, 30},
				new int[]{40, 20, 0, 30, 20, 35},
				new int[]{40, 20, 0, 30, -20, 20},
				new int[]{40, 20, 0, 30, -20, 20, 20, 10},
				new int[]{40, 20, 0, 30, -20, 30},
				new int[]{40, 20, 0, 30, -20, 35},
				new int[]{40, 20, 0, 30, 50, 45, 45, 50}
		};

		int[] answers = new int[]{
				50,
				30,
				30,
				35,
				40,
				40,
				50,
				55,
				40
		};

		for (int i = 0; i < inputs.length; i++) {
			for (Solution s : solutionList) {
				assertEquals(true, s.solve(inputs[i]) == answers[i]);
			}
		}
	}

	@Test
	void gen() {
		int[] genAr = ArrayGenerator.genIntArray(10, 100, true);
		Solution2 solution2 = new Solution2();
		System.out.println(Arrays.toString(genAr));
		int ans = solution2.solve(genAr);
		System.out.println("solve: " + ans);
		int newAnswer = new Solution1().solve(genAr);
		assertEquals(ans, newAnswer);


	}
}