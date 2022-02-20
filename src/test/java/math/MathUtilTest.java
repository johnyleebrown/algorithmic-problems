package math;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

class MathUtilTest {

	@Test
	public void testCommonDivisorsCount_shouldReturnCorrectCount() {
		assertEquals(Arrays.asList(1, 3), MathUtil.Numbers.getCommonDivisors(12, 15));
		assertEquals(Arrays.asList(1), MathUtil.Numbers.getCommonDivisors(71, 13));
		assertEquals(Arrays.asList(1, 4, 2), MathUtil.Numbers.getCommonDivisors(12, 16));
		assertEquals(Arrays.asList(1, 3), MathUtil.Numbers.getCommonDivisors(81, 33));
	}


	@Test
	public void testGcd_shouldReturnCorrectResult() {
		//		assertEquals(3, MathUtil.Numbers.gcd(12, 15));
		assertEquals(3, MathUtil.Numbers.gcd(33, 81));
	}
}