package dp.knapsack01;

import dp.subset.SubsetSum;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SubsetSumTest {

  @Test
  void test1() {
    int[] ar = new int[]{1, 2, 3, 7};
    int sum = 6;
    boolean expectedAnswer = true;

    for (SubsetSum.Solution s : getSolutions()) {
      assertEquals(expectedAnswer, s.canPartition(ar, sum));
    }
  }

  @Test
  void test2() {
    int[] ar = new int[]{4, 1, 10, 12, 5, 2};
    int sum = 9;
    boolean expectedAnswer = true;

    for (SubsetSum.Solution s : getSolutions()) {
      assertEquals(expectedAnswer, s.canPartition(ar, sum));
    }
  }

  @Test
  void test3() {
    int[] ar = new int[]{1, 8, 2, 5};
    int sum = 4;
    boolean expectedAnswer = false;

    for (SubsetSum.Solution s : getSolutions()) {
      assertEquals(expectedAnswer, s.canPartition(ar, sum));
    }
  }

  @Test
  void test4() {
    int[] ar = new int[]{6, 2, 5};
    int sum = 7;
    boolean expectedAnswer = true;

    for (SubsetSum.Solution s : getSolutions()) {
      assertEquals(expectedAnswer, s.canPartition(ar, sum));
    }
  }

  @Test
  void test5() {
    int[] ar = new int[]{3, 34, 4, 12, 5, 2};
    int sum = 9;
    boolean expectedAnswer = true;

    for (SubsetSum.Solution s : getSolutions()) {
      assertEquals(expectedAnswer, s.canPartition(ar, sum));
    }
  }

  @Test
  void test6() {
    int[] ar = new int[]{1, 2, 7, 1, 5};
    int sum = 10;
    boolean expectedAnswer = true;

    for (SubsetSum.Solution s : getSolutions()) {
      assertEquals(expectedAnswer, s.canPartition(ar, sum));
    }
  }

  @Test
  void test7() {
    int[] ar = new int[]{1, 3, 4, 8};
    int sum = 6;
    boolean expectedAnswer = false;

    for (SubsetSum.Solution s : getSolutions()) {
      assertEquals(expectedAnswer, s.canPartition(ar, sum));
    }
  }

  @Test
  void test8() {
    int[] ar = new int[]{9, 14, 71, 32, 11, 3, 2, 7, 13, 14, 5};
    int sum = 31;
    boolean expectedAnswer = true;

    for (SubsetSum.Solution s : getSolutions()) {
      assertEquals(expectedAnswer, s.canPartition(ar, sum));
    }
  }

  @Test
  void test9() {
    int[] ar = new int[]{100, 10, 15, 9, 19, 20};
    int sum = 20;
    boolean expectedAnswer = true;

    for (SubsetSum.Solution s : getSolutions()) {
      assertEquals(expectedAnswer, s.canPartition(ar, sum));
    }
  }

  @Test
  void test10() {
    int[] ar = new int[]{0, 1, 0, 0, 1, 0, 0, 0};
    int sum = 2;
    boolean expectedAnswer = true;

    for (SubsetSum.Solution s : getSolutions()) {
      assertEquals(expectedAnswer, s.canPartition(ar, sum));
    }
  }

  private List<SubsetSum.Solution> getSolutions() {
    return Arrays.asList(new SubsetSum.Solution1(),
        new SubsetSum.Solution2(), new SubsetSum.Solution3());
  }
}