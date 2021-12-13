package math.operations;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import math.operations.PowXN.Solution;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PowXNTest {

	List<Solution> solutions = Arrays.asList(new PowXN.Solution1(), new PowXN.Solution2(),
			new PowXN.Solution3());

	@BeforeEach
	void prep() {

	}

	@Test
	void test1() {
		for (Solution s : solutions) {
			assertEquals(Math.pow(2, 10), s.myPow(2, 10));
			assertEquals(Math.pow(2, 10), s.myPow(2, 10));
		}
	}
}

