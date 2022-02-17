package graph.topological_sort;

import static org.junit.jupiter.api.Assertions.assertThrows;

import graph.topological_sort.PrintHierarchy.Solution;
import org.junit.jupiter.api.Test;

class PrintHierarchyTest {

	@Test
	public void test() {
		new Solution().solve(
				new String[][]{{"dog", "poodle"}, {"mammal", "dog"}, {"mammal", "cat"},
						{"dog", "bulldog"}, {"dog", "terrier"}});
	}

	@Test
	public void test_cycle() {
		Solution s = new Solution();
		String[][] ar = new String[][]{{"dog", "poodle"}, {"poodle", "dog"}};
		assertThrows(RuntimeException.class, () -> s.solve(ar));
	}

	@Test
	public void test_no_cycle() {
		Solution s = new Solution();
		String[][] ar = new String[][]{{"dog", "poodle"}, {"poodle", "fox"},
				{"dog", "fox"}};
		s.solve(ar);
	}
}