package sort.heap;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SortArrayOfSortedArraysTest {
	@Test
	void test1() {
		SortArrayOfSortedArrays.Solution1 s = new SortArrayOfSortedArrays.Solution1();
		int[][] answer = s.sort(new int[][]{{5, 12, 17, 21, 23}, {1, 2, 4, 6, 8}, {12, 14, 18, 19
		, 27}, {3, 7, 9, 15, 25}});
		int[][] expected = new int[][]{{1, 2, 3, 4, 5}, {6, 7, 8, 9, 12}, {12, 14, 15, 17, 18},
		{19, 21, 23, 25, 27}};
		for (int i = 0; i < answer.length; i++) {
			for (int j = 0; j < answer[0].length; j++) {
				assertEquals(expected[i][j], answer[i][j]);
			}
		}
	}
}