package intervals.other;

import static org.junit.jupiter.api.Assertions.*;

import intervals.other.RedTapeBlueTape.Solution1;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class RedTapeBlueTapeTest {

  @Test
  void shouldBeOk() {
    RedTapeBlueTape.Solution1 solution1 = new Solution1();
    int[] red = new int[]{0, 10};
    List<int[]> blue = Arrays.asList(new int[]{0, 10});
    assertEquals(false, solution1.solve(red, blue));
  }

  @Test
  void shouldReturnTrue_whenLeftEdgeInclusiveRightEdgeExclusive() {
    RedTapeBlueTape.Solution1 solution1 = new Solution1();
    int[] red = new int[]{0, 10};
    List<int[]> blue = Arrays.asList(new int[]{0, 9});
    assertEquals(true, solution1.solve(red, blue));
  }

  @Test
  void shouldReturnTrue_whenLeftEdgeExclusiveRightEdgeInclusive() {
    RedTapeBlueTape.Solution1 solution1 = new Solution1();
    int[] red = new int[]{0, 10};
    List<int[]> blue = Arrays.asList(new int[]{1, 10});
    assertEquals(true, solution1.solve(red, blue));
  }

  @Test
  void shouldReturnTrue_whenLeftEdgeExclusiveRightEdgeExclusive() {
    RedTapeBlueTape.Solution1 solution1 = new Solution1();
    int[] red = new int[]{0, 10};
    List<int[]> blue = Arrays.asList(new int[]{1, 9});
    assertEquals(true, solution1.solve(red, blue));
  }

  @Test
  void shouldReturnFalse_whenNoBlueTape() {
    RedTapeBlueTape.Solution1 solution1 = new Solution1();
    int[] red = new int[]{0, 10};
    List<int[]> blue = Arrays.asList();
    assertEquals(false, solution1.solve(red, blue));
  }

  @Test
  void shouldReturnTrue_whenTwoNonCoveringBlueTapes() {
    RedTapeBlueTape.Solution1 solution1 = new Solution1();
    int[] red = new int[]{0, 10};
    List<int[]> blue = Arrays.asList(new int[]{1, 3}, new int[]{7, 8});
    assertEquals(true, solution1.solve(red, blue));
  }

  @Test
  void shouldReturnFalse_whenTwoCoveringBlueTapes() {
    RedTapeBlueTape.Solution1 solution1 = new Solution1();
    int[] red = new int[]{0, 10};
    List<int[]> blue = new ArrayList<>(
        Arrays.asList(new int[]{0, 3}, new int[]{1, 4}, new int[]{4, 10},
            new int[]{4, 10}));
    assertEquals(false, solution1.solve(red, blue));
  }
}