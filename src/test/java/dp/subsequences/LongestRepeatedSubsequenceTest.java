package dp.subsequences;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LongestRepeatedSubsequenceTest {

	@Test
	void bruteForceSolution_test1() {
		LongestRepeatedSubsequence.S s = new LongestRepeatedSubsequence.Solution1();
		assertEquals(2, s.getLongestRepeatedSubsequence("ab", "abcababdefab"));
	}

	@Test
	void bruteForceSolution_test2() {
		LongestRepeatedSubsequence.S s = new LongestRepeatedSubsequence.Solution1();
		assertEquals(12, s.getLongestRepeatedSubsequence("a", "aaaaaaaaaaaaba"));
	}

	@Test
	void bruteForceSolution_test3() {
		LongestRepeatedSubsequence.S s = new LongestRepeatedSubsequence.Solution1();
		assertEquals(1, s.getLongestRepeatedSubsequence("aba", "ababaaba"));
	}

	@Test
	void bruteForceSolution_test4() {
		LongestRepeatedSubsequence.S s = new LongestRepeatedSubsequence.Solution1();
		assertEquals(2, s.getLongestRepeatedSubsequence("aabaabaa", "aabaabaaaabaabaa"));
	}

	@Test
	void bruteForceSolution_test5() {
		LongestRepeatedSubsequence.S s = new LongestRepeatedSubsequence.Solution1();
		assertEquals(2, s.getLongestRepeatedSubsequence("aabaab", "aabaabdaabaabcaabaabaabaabq"));
	}

	@Test
	void bruteForceSolution_test6() {
		LongestRepeatedSubsequence.S s = new LongestRepeatedSubsequence.Solution1();
		assertEquals(3, s.getLongestRepeatedSubsequence(
		"fbgwgqvdsbnukcpxlpifuhbvtdxhhhqurotspohiuwhblnratk",
		"fbgwgqvdsbnukcpxlpifuhbvtdxhhhqurotspohiuwhblnratkfbgwgqvdsbnukcpxlpifuhbvtdxhhhqurotspohiuwhblnratkqfbgwgqvdsbnukcpxlpifuhbvtdxhhhqurotspohiuwhblnratkfbgwgqvdsbnukcpxlpifuhbvtdxhhhqurotspohiuwhblnratkfbgwgqvdsbnukcpxlpifuhbvtdxhhhqurotspohiuwhblnratkfbgwgqvdsbnukcpxlpifuhbvtdxhhhqur"));
	}

	/*========================================================*/

	@Test
	void optimizedSolution_test1() {
		LongestRepeatedSubsequence.S s = new LongestRepeatedSubsequence.Solution2();
		assertEquals(2, s.getLongestRepeatedSubsequence("ab", "abcababdefab"));
	}

	@Test
	void optimizedSolution_test2() {
		LongestRepeatedSubsequence.S s = new LongestRepeatedSubsequence.Solution2();
		assertEquals(12, s.getLongestRepeatedSubsequence("a", "aaaaaaaaaaaaba"));
	}

	@Test
	void optimizedSolution_test3() {
		LongestRepeatedSubsequence.S s = new LongestRepeatedSubsequence.Solution2();
		assertEquals(2, s.getLongestRepeatedSubsequence("aba", "ababaaba"));
	}

	@Test
	void optimizedSolution_test4() {
		LongestRepeatedSubsequence.S s = new LongestRepeatedSubsequence.Solution2();
		assertEquals(2, s.getLongestRepeatedSubsequence("aabaabaa", "aabaabaaaabaabaa"));
	}

	@Test
	void optimizedSolution_test5() {
		LongestRepeatedSubsequence.S s = new LongestRepeatedSubsequence.Solution2();
		assertEquals(2, s.getLongestRepeatedSubsequence("aabaab", "aabaabdaabaabcaabaabaabaabq"));
	}

	@Test
	void optimizedSolution_test6() {
		LongestRepeatedSubsequence.S s = new LongestRepeatedSubsequence.Solution2();
		assertEquals(3, s.getLongestRepeatedSubsequence(
		"fbgwgqvdsbnukcpxlpifuhbvtdxhhhqurotspohiuwhblnratk",
		"fbgwgqvdsbnukcpxlpifuhbvtdxhhhqurotspohiuwhblnratkfbgwgqvdsbnukcpxlpifuhbvtdxhhhqurotspohiuwhblnratkqfbgwgqvdsbnukcpxlpifuhbvtdxhhhqurotspohiuwhblnratkfbgwgqvdsbnukcpxlpifuhbvtdxhhhqurotspohiuwhblnratkfbgwgqvdsbnukcpxlpifuhbvtdxhhhqurotspohiuwhblnratkfbgwgqvdsbnukcpxlpifuhbvtdxhhhqur"));
	}
}