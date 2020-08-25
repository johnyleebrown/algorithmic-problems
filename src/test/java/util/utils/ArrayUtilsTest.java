package util.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayUtilsTest {
	@Test
	void circularArrayJumpTest_NegativeJumps() {
		assertEquals(3, ArrayUtils.getNewIndex(2, -13, 7));
		assertEquals(0, ArrayUtils.getNewIndex(2, -9, 7));
		assertEquals(1, ArrayUtils.getNewIndex(2, -1, 7));
		assertEquals(0, ArrayUtils.getNewIndex(2, -2, 7));
		assertEquals(2, ArrayUtils.getNewIndex(2, 0, 7));
	}

	@Test
	void circularArrayJumpTest_PositiveJumps() {
		assertEquals(1, ArrayUtils.getNewIndex(2, 13, 7));
		assertEquals(0, ArrayUtils.getNewIndex(2, 5, 7));
		assertEquals(4, ArrayUtils.getNewIndex(2, 2, 7));
		assertEquals(2, ArrayUtils.getNewIndex(2, 0, 7));
		assertEquals(4, ArrayUtils.getNewIndex(0, 9, 5));
		assertEquals(4, ArrayUtils.getNewIndex(4, 100, 5));
	}
}