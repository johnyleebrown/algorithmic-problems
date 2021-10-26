package dp.subset.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudentGoodwillSelectionTest {

  StudentGoodwillSelection.Solution1 solution1;

  @BeforeEach
  void start() {
    solution1 = new StudentGoodwillSelection.Solution1();
  }

  @Test
  void test1() {
    int result = solution1.solve(4, new int[] {1, 2, 3, 6}, new int[] {2, 6, 7, 5}, 5);
    assertEquals(3, result);
  }

  @Test
  void test2() {
    int result = solution1.solve(5, new int[] {1, 2, 3, 6, 1}, new int[] {2, 6, 7, 5, 8}, 5);
    assertEquals(7, result);
  }

  @Test
  void test3() {
    int result = solution1.solve(5, new int[] {1, 2, 3, 3, 3}, new int[] {2, 6, 7, 5, 6}, 6);
    assertEquals(1, result);
  }

  @Test
  void test4() {
    int result = solution1.solve(2, new int[] {3, 3}, new int[] {2, 3}, 2);
    assertEquals(3, result);
  }

  @Test
  void test5() {
    int result = solution1.solve(2, new int[] {3, 3}, new int[] {2, 3}, 7);
    assertEquals(0, result);
  }

  @Test
  void test6() {
    int result = solution1.solve(1, new int[] {3}, new int[] {1}, 2);
    assertEquals(1, result);
  }

  @Test
  void test7() {
    int result = solution1.solve(1, new int[] {1}, new int[] {1}, 2);
    assertEquals(0, result);
  }

  @Test
  void testSolution1Solve() {
    assertEquals(
        0,
        (new StudentGoodwillSelection.Solution1())
            .solve(1, new int[] {1, 1, 1, 1}, new int[] {1, 1, 1, 1}, 1));
    assertEquals(
        1,
        (new StudentGoodwillSelection.Solution1())
            .solve(1, new int[] {4, 1, 1, 1}, new int[] {1, 1, 1, 1}, 1));
    assertThrows(
        ArrayIndexOutOfBoundsException.class,
        () ->
            (new StudentGoodwillSelection.Solution1())
                .solve(1, new int[] {}, new int[] {1, 1, 1, 1}, 1));
    assertThrows(
        ArrayIndexOutOfBoundsException.class,
        () ->
            (new StudentGoodwillSelection.Solution1())
                .solve(1, new int[] {0, 0, 0, 0, 0, 0, 0, 0}, new int[] {1, 1, 1, 1}, 1));
    assertEquals(
        3,
        (new StudentGoodwillSelection.Solution1())
            .solve(1, new int[] {1, 1, 1, 1}, new int[] {0, 1, 1, 1}, 1));
    assertThrows(
        ArrayIndexOutOfBoundsException.class,
        () ->
            (new StudentGoodwillSelection.Solution1())
                .solve(1, new int[] {0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 1, 1, 1}, 1));
  }
}
