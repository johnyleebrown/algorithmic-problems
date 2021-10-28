package dp.subset.order;

import static org.junit.jupiter.api.Assertions.assertEquals;

import dp.subset.order.StudentGoodwillSelection.Solution;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudentGoodwillSelectionTest {

  StudentGoodwillSelection.Solution1 solution1;
  StudentGoodwillSelection.Solution2 solution2;
  StudentGoodwillSelection.Solution3 solution3;
  StudentGoodwillSelection.Solution4 solution4;
  List<Solution> solutionList;
  int[] bigAr1 =
      new int[] {
        91, 146, 860, 421, 974, 339, 860, 686, 40, 637, 189, 589, 320, 424, 827, 954, 437, 500, 895,
        57, 336, 755, 255, 813, 862, 727, 589, 231, 665, 267, 466, 572, 658, 686, 623, 460, 517,
        233, 736, 159, 328, 122, 126, 602, 457, 33, 591, 951, 996
      };
  int[] bigAr2 =
      new int[] {
        450, 800, 83, 711, 488, 891, 705, 848, 90, 13, 813, 244, 109, 879, 682, 255, 567, 334, 306,
        525, 718, 412, 847, 279, 617, 973, 305, 20, 697, 788, 546, 759, 515, 3, 954, 486, 605, 698,
        895, 19, 506, 206, 396, 979, 636, 187, 573, 435, 525
      };

  @BeforeEach
  void start() {
    solution1 = new StudentGoodwillSelection.Solution1();
    solution2 = new StudentGoodwillSelection.Solution2();
    solution3 = new StudentGoodwillSelection.Solution3();
    solution4 = new StudentGoodwillSelection.Solution4();
    solutionList =
        Arrays.asList(
            solution1, solution2, solution3
            //        , solution4
            );
  }

  @Test
  void loadTest() {
    List<Double> times = new ArrayList<>();
    for (StudentGoodwillSelection.Solution s : solutionList) {
      double start = (double) System.nanoTime();
      int result = s.solve(49, bigAr1, bigAr2, 7000);
      double elapsedTime = ((double) System.nanoTime()) - start;

      System.out.printf("solution [%s]\n", s.getClass().getSimpleName());
      if (!times.isEmpty()) {
        for (int i = 0; i < times.size(); i++) {
          double compareWithTime = times.get(i);
          double x = ((compareWithTime / elapsedTime) * 100);
          DecimalFormat formatter = new DecimalFormat("#0");
          System.out.println(String.format("Faster than [%s] by [%s%%]", i, formatter.format(x)));
        }
      } else {
        System.out.println("--nothing to compare with--");
      }
      times.add(elapsedTime);
      assertEquals(19, result);
    }
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
}
