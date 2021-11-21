package regular.array;

/**
 * 2079. Watering Plants
 *
 * https://leetcode.com/problems/watering-plants/
 */
public class WateringPlants {

  /**
   * Simulation
   */
  public static class Solution {

    public int wateringPlants(int[] plants, int capacity) {
      int left = capacity;
      int n = plants.length;
      int i = -1;
      int steps = 0;
      while (i < n) {
        if (i != -1) {
          left -= plants[i];
          steps++;
        }
        if (i + 1 < n && left < plants[i + 1]) {
          left = capacity;
          steps += i + i + 2;
        }
        i++;
      }
      return steps;
    }
  }
}