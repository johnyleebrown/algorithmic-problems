import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 2849. Determine if a Cell Is Reachable at a Given Time
 *
 * You are given four integers sx, sy, fx, fy, and a non-negative integer t.
 *
 * In an infinite 2D grid, you start at the cell (sx, sy). Each second, you must move to any of its adjacent cells.
 *
 * Return true if you can reach cell (fx, fy) after exactly t seconds, or false otherwise.
 *
 * A cell's adjacent cells are the 8 cells around it that share at least one corner with it. You can visit the same cell several times.
 *
 * tags: bfs, geometry
 */
public class DetermineIfACellIsReachableAtAGivenTime {
    static int max = 1_000_000_000;

    /**
     * Number of seconds is number of steps is the shortest distance.
     * In this case if we reach the dest in the shortest dist it means we can circle around for the rest of time because "You can visit the same cell several times.".
     * No obstacles on the map. What's the SP in rectangle with 8 direction movement? Imagine a square, here it is a length of a diagonal.
     * If me move destination in any direction, the SP will be diagonal to one of the axis of dest + straight line length.
     */
    private static class Solution {
        public boolean isReachableAtTime(int sx, int sy, int fx, int fy, int t) {
            boolean samePlace = (sx == fx && sy == fy);
            if (samePlace && t == 1) {
                return false;
            }
            if (samePlace) {
                return true;
            }
            if (t == 0) {
                return false;
            }
            return Math.max(Math.abs(sx - fx), Math.abs(sy - fy)) <= t;
        }
    }

    /**
     * BFS, can go for 2 ways, can use diff hash fn,  but slow with big distances.
     */
    private static class Solution2 {
        public boolean isReachableAtTime(int sx, int sy, int fx, int fy, int t) {
            boolean samePlace = (sx == fx && sy == fy);
            // in 1 second we make one step out of the cell, coming back takes one more second
            if (samePlace && t == 1) {
                return false;
            }
            // in all other cases we can circle around
            if (samePlace) {
                return true;
            }
            // if we are not in the same place, but we have 0 seconds, we can't reach the dest
            if (t == 0) {
                return false;
            }
            int[][] df = new int[][]{{0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};
            List<int[]> q = new ArrayList<>();
            q.add(new int[]{sx, sy});
            Set<String> s = new HashSet<>();
            int dist = 0;
            while (!q.isEmpty()) {
                int size = q.size();
                while (--size >= 0) {
                    int[] cur = q.remove(0);
                    if (cur[0] == fx && cur[1] == fy) {
                        System.out.println(dist);
                        return dist <= t;
                    }
                    for (int[] d : df) {
                        int newX = d[0] + cur[0];
                        int newY = d[1] + cur[1];
                        if (!isInBounds(newX, newY)) {
                            continue;
                        }
                        if (!isInTheTargetDirection(cur, newX, newY, fx, fy)) {
                            continue;
                        }
                        String hash = getHash(newX, newY);
                        if (s.contains(hash)) {
                            continue;
                        }
                        s.add(hash);
                        q.add(new int[]{newX, newY});
                    }
                }
                dist++;
                if (dist > t) {
                    return false;
                }
            }
            return false;
        }

        private String getHash(int newX, int newY) {
            return newX + "," + newY;
        }

        private boolean isInBounds(int newX, int newY) {
            return newX <= max && newY <= max && newX >= 1 && newY >= 1;
        }

        boolean isInTheTargetDirection(int[] cur, int newX, int newY, int fx, int fy) {
            int curDiffX = Math.abs(cur[0] - fx);
            int newDiffX = Math.abs(newX - fx);
            int curDiffY = Math.abs(cur[1] - fy);
            int newDiffY = Math.abs(newY - fy);
            return curDiffX - newDiffX >= 0 && curDiffY - newDiffY >= 0;
        }
    }

    public static void main(String[] args) {
        long st = System.currentTimeMillis();
        boolean res = new Solution().isReachableAtTime(1, 1, 4000, 4000, 4000);
        long en = System.currentTimeMillis();
        System.out.println(en - st);
        System.out.println(res);
    }
}
