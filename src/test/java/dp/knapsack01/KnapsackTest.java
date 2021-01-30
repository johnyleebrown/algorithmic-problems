package dp.knapsack01;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class KnapsackTest {

	public static void waitUntilDone(List<Future<?>> futures) {
		boolean allDone = false;
		while (!allDone) {
			System.out.println("waiting..");

			// A) Await all runnables to be done (blocking)
			for (Future<?> future : futures) {
				try {
					// get will block until the future is done
					future.get();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			// B) Check if all runnables are done (non-blocking)
			for (Future<?> future : futures) {
				if (!future.isDone()) {
					allDone = false;
					break;
				} else allDone = true;
			}
		}
	}

	@Test
	void compareAnswers() {

		List<int[]> profitsList = Arrays.asList(
		new int[]{1, 6, 10, 16},
		new int[]{1, 6, 10, 16},
		new int[]{3, 7, 12, 18, 9, 4, 81, 47});
		List<int[]> weightsList = Arrays.asList(
		new int[]{1, 2, 3, 5},
		new int[]{1, 2, 3, 5},
		new int[]{13, 14, 81, 9, 3, 5, 41, 12});
		List<Integer> capacityList = Arrays.asList(7, 6, 31);

		List<Knapsack.Solution> solutions = Arrays.asList(new Knapsack.Solution1(),
		new Knapsack.Solution2(), new Knapsack.Solution3(), new Knapsack.Solution4(),
		new Knapsack.Solution5());

		List<Integer> ans = new ArrayList<>();
		for (int i = 0; i < profitsList.size(); i++) {
			for (Knapsack.Solution s : solutions) {
				ans.add(s.solveKnapsack(profitsList.get(i),
				weightsList.get(i), capacityList.get(i)));
//				assertTrue(Comparator.compare(ans));
			}
			ans.clear();
		}
	}

	@Test
	void compareTimes() {

		List<int[]> profitsList = Arrays.asList(
		new int[]{1, 6, 10, 16},
		new int[]{1, 6, 10, 16},
		new int[]{3, 7, 12, 18, 9, 4, 81, 47});
		List<int[]> weightsList = Arrays.asList(
		new int[]{1, 2, 3, 5},
		new int[]{1, 2, 3, 5},
		new int[]{13, 14, 81, 9, 3, 5, 41, 12});
		List<Integer> capacityList = Arrays.asList(7, 6, 31);

		List<Future<?>> futures = new ArrayList<Future<?>>();
		ExecutorService executor = Executors.newSingleThreadExecutor();

		List<Knapsack.Solution> solutions = Arrays.asList(
		new Knapsack.Solution1(),
		new Knapsack.Solution2(),
		new Knapsack.Solution3(),
		new Knapsack.Solution4(),
		new Knapsack.Solution5());

		List<String> ansStrings = new ArrayList<>();
		List<Long> shortestAnsTimes = new ArrayList<>();
		List<String> shortestAnsStrings = new ArrayList<>();
		for (int i = 0; i < profitsList.size(); i++) {
			int finalI = i;
			for (Knapsack.Solution s : solutions) {
				Future<?> f = executor.submit(() -> {
					long startTime = System.nanoTime();

					s.solveKnapsack(profitsList.get(finalI), weightsList.get(finalI), capacityList.get(finalI));

					long t = System.nanoTime() - startTime;

					ansStrings.add("Test " + (finalI + 1) + ". Time for " + s.getClass().getSimpleName() + " is " + t + " ns");
					if (shortestAnsTimes.size() == finalI + 1) {
						if (t < shortestAnsTimes.get(finalI)) {
							shortestAnsTimes.set(finalI, t);
							shortestAnsStrings.set(finalI,
							"Fastest solution is " + s.getClass().getSimpleName() + " and time " + t);
						}
					} else {
						shortestAnsTimes.add(t);
						shortestAnsStrings.add("Fastest solution is " + s.getClass().getSimpleName() + " and time " + t);
					}
				});
				futures.add(f);
			}
		}

		waitUntilDone(futures);
		executor.shutdown();

		for (String s : ansStrings) {
			System.out.println(s);
		}
		for (int i = 0; i < shortestAnsTimes.size(); i++) {
			System.out.println(shortestAnsStrings.get(i));
		}
	}
}