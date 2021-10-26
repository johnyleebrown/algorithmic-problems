package dp.subset.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import dp.subset.order.StudentGoodwillSelection.Solution;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudentGoodwillSelectionTest {

  StudentGoodwillSelection.Solution1 solution1;
  StudentGoodwillSelection.Solution2 solution2;
  StudentGoodwillSelection.Solution3 solution3;
  List<Solution> solutionList;

  @BeforeEach
  void start() {
    solution1 = new StudentGoodwillSelection.Solution1();
    solution2 = new StudentGoodwillSelection.Solution2();
    solution3 = new StudentGoodwillSelection.Solution3();
    solutionList = Arrays.asList(solution1, solution2, solution3);
  }

  @Test
  void test1() {
    for (Solution s : solutionList) {
      int result = s.solve(4, new int[] {1, 2, 3, 6}, new int[] {2, 6, 7, 5}, 5);
      assertEquals(3, result);
    }
  }

  @Test
  void test2() {
    for (Solution s : solutionList) {
      int result = s.solve(5, new int[] {1, 2, 3, 6, 1}, new int[] {2, 6, 7, 5, 8}, 5);
      assertEquals(7, result);
    }
  }

  @Test
  void test3() {
    for (Solution s : solutionList) {
      int result = s.solve(5, new int[] {1, 2, 3, 3, 3}, new int[] {2, 6, 7, 5, 6}, 6);
      assertEquals(1, result);
    }
  }

  @Test
  void test4() {
    for (Solution s : solutionList) {
      int result = s.solve(2, new int[] {3, 3}, new int[] {2, 3}, 2);
      assertEquals(3, result);
    }
  }

  @Test
  void test5() {
    for (Solution s : solutionList) {
      int result = s.solve(2, new int[] {3, 3}, new int[] {2, 3}, 7);
      assertEquals(0, result);
    }
  }

  @Test
  void test6() {
    for (Solution s : solutionList) {
      int result = s.solve(1, new int[] {3}, new int[] {1}, 2);
      assertEquals(1, result);
    }
  }

  @Test
  void test7() {
    for (Solution s : solutionList) {
      int result = s.solve(1, new int[] {1}, new int[] {1}, 2);
      assertEquals(0, result);
    }
  }

  @Test
  void testSolution1Dfs() {
    StudentGoodwillSelection.Solution1 solution1 = new StudentGoodwillSelection.Solution1();
    HashSet<String> stringSet = new HashSet<String>();
    solution1.dfs(
        1,
        1,
        new int[] {1, 1, 1, 1},
        new int[] {1, 1, 1, 1},
        1,
        stringSet,
        new StringBuilder("foo"));
    assertEquals(1, stringSet.size());
  }

  @Test
  void testSolution1Dfs2() {
    // TODO: This test is incomplete.
    //   Reason: R004 No meaningful assertions found.
    //   Diffblue Cover was unable to create an assertion.
    //   Make sure that fields modified by dfs(int, int, int[], int[], int, Set, StringBuilder)
    //   have package-private, protected, or public getters.
    //   See https://diff.blue/R004 to resolve this issue.

    StudentGoodwillSelection.Solution1 solution1 = new StudentGoodwillSelection.Solution1();
    HashSet<String> set = new HashSet<String>();
    solution1.dfs(
        0, 1, new int[] {1, 1, 1, 1}, new int[] {1, 1, 1, 1}, 1, set, new StringBuilder("foo"));
  }

  @Test
  void testSolution1Dfs3() {
    StudentGoodwillSelection.Solution1 solution1 = new StudentGoodwillSelection.Solution1();
    HashSet<String> set = new HashSet<String>();
    assertThrows(
        ArrayIndexOutOfBoundsException.class,
        () ->
            solution1.dfs(
                1,
                4,
                new int[] {1, 1, 1, 1},
                new int[] {1, 1, 1, 1},
                1,
                set,
                new StringBuilder("foo")));
  }

  @Test
  void testSolution1Dfs4() {
    StudentGoodwillSelection.Solution1 solution1 = new StudentGoodwillSelection.Solution1();
    HashSet<String> set = new HashSet<String>();
    assertThrows(
        ArrayIndexOutOfBoundsException.class,
        () ->
            solution1.dfs(
                1,
                1,
                new int[] {0, 0, 0, 0, 0, 0, 0, 0},
                new int[] {1, 1, 1, 1},
                1,
                set,
                new StringBuilder("foo")));
  }

  @Test
  void testSolution1Dfs5() {
    StudentGoodwillSelection.Solution1 solution1 = new StudentGoodwillSelection.Solution1();
    HashSet<String> stringSet = new HashSet<String>();
    solution1.dfs(
        1,
        1,
        new int[] {1, 1, 1, 1},
        new int[] {1, 0, 1, 1},
        1,
        stringSet,
        new StringBuilder("foo"));
    assertEquals(3, stringSet.size());
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

  @Test
  void testSolution2Dfs() {
    StudentGoodwillSelection.Solution2 solution2 = new StudentGoodwillSelection.Solution2();
    HashSet<String> stringSet = new HashSet<String>();
    assertEquals(
        1,
        solution2.dfs(
            1,
            1,
            new int[] {1, 1, 1, 1},
            new int[] {1, 1, 1, 1},
            1,
            stringSet,
            new StringBuilder("foo")));
    assertEquals(1, stringSet.size());
  }

  @Test
  void testSolution2Dfs2() {
    StudentGoodwillSelection.Solution2 solution2 = new StudentGoodwillSelection.Solution2();
    HashSet<String> set = new HashSet<String>();
    assertEquals(
        0,
        solution2.dfs(
            0,
            1,
            new int[] {1, 1, 1, 1},
            new int[] {1, 1, 1, 1},
            1,
            set,
            new StringBuilder("foo")));
  }

  @Test
  void testSolution2Dfs3() {
    StudentGoodwillSelection.Solution2 solution2 = new StudentGoodwillSelection.Solution2();
    HashSet<String> set = new HashSet<String>();
    assertThrows(
        ArrayIndexOutOfBoundsException.class,
        () ->
            solution2.dfs(
                1,
                4,
                new int[] {1, 1, 1, 1},
                new int[] {1, 1, 1, 1},
                1,
                set,
                new StringBuilder("foo")));
  }

  @Test
  void testSolution2Dfs4() {
    StudentGoodwillSelection.Solution2 solution2 = new StudentGoodwillSelection.Solution2();
    HashSet<String> set = new HashSet<String>();
    assertThrows(
        ArrayIndexOutOfBoundsException.class,
        () ->
            solution2.dfs(
                1,
                1,
                new int[] {0, 0, 0, 0, 0, 0, 0, 0},
                new int[] {1, 1, 1, 1},
                1,
                set,
                new StringBuilder("foo")));
  }

  @Test
  void testSolution2Dfs5() {
    StudentGoodwillSelection.Solution2 solution2 = new StudentGoodwillSelection.Solution2();
    HashSet<String> stringSet = new HashSet<String>();
    assertEquals(
        3,
        solution2.dfs(
            1,
            1,
            new int[] {1, 1, 1, 1},
            new int[] {1, 0, 1, 1},
            1,
            stringSet,
            new StringBuilder("foo")));
    assertEquals(3, stringSet.size());
  }

  @Test
  void testSolution2Solve() {
    assertEquals(
        0,
        (new StudentGoodwillSelection.Solution2())
            .solve(1, new int[] {1, 1, 1, 1}, new int[] {1, 1, 1, 1}, 1));
    assertEquals(
        1,
        (new StudentGoodwillSelection.Solution2())
            .solve(1, new int[] {4, 1, 1, 1}, new int[] {1, 1, 1, 1}, 1));
    assertThrows(
        ArrayIndexOutOfBoundsException.class,
        () ->
            (new StudentGoodwillSelection.Solution2())
                .solve(1, new int[] {}, new int[] {1, 1, 1, 1}, 1));
    assertThrows(
        ArrayIndexOutOfBoundsException.class,
        () ->
            (new StudentGoodwillSelection.Solution2())
                .solve(1, new int[] {0, 0, 0, 0, 0, 0, 0, 0}, new int[] {1, 1, 1, 1}, 1));
    assertEquals(
        3,
        (new StudentGoodwillSelection.Solution2())
            .solve(1, new int[] {1, 1, 1, 1}, new int[] {0, 1, 1, 1}, 1));
    assertThrows(
        ArrayIndexOutOfBoundsException.class,
        () ->
            (new StudentGoodwillSelection.Solution2())
                .solve(1, new int[] {0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 1, 1, 1}, 1));
  }
}
