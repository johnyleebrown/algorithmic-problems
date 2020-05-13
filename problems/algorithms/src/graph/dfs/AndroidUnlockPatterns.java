package graph.dfs;

import java.util.ArrayDeque;
import java.util.BitSet;
import java.util.Deque;

/**
 * 351
 *
 * ======
 *
 * Task.
 *
 * Given an Android 3x3 key lock screen and two integers m and n, where 1 ≤ m ≤
 * n ≤ 9, count the total number of unlock patterns of the Android lock screen,
 * which consist of minimum of m keys and maximum n keys.
 *
 * Rules for a valid pattern:
 *
 * Each pattern must connect at least m keys and at most n keys.
 * All the keys must be distinct.
 * If the line connecting two consecutive keys in the pattern passes through any
 * other keys, the other keys must have previously selected in the pattern. No
 * jumps through non selected key is allowed.
 * The order of keys used matters.
 *
 * ======
 *
 * Source: Leetcode
 */
public class AndroidUnlockPatterns {
    /**
     * BFS, slow because:
     * - no symmetry optimization
     * - cloning of bit set
     * - dynamic checks but not static
     */
    public static class Solution1 {
        public int numberOfPatterns(int m, int n) {
            Deque<Item> q = new ArrayDeque<>();
            for (int i = 1; i <= 9; i++) q.add(new Item(i));
            int count = 0;
            int ans = 0;
            while (!q.isEmpty()) {
                count++;
                if (count > n) break;
                int size = q.size();
                while (--size >= 0) {
                    Item cur = q.removeFirst();
                    if (count >= m) ans++;
                    if (count == n) continue;
                    for (int i = 1; i <= 9; i++) {
                        if (i == cur.val) continue;
                        if (cur.seen.get(i)) continue;
                        if (!isValid(cur, i)) continue;
                        BitSet newSet = (BitSet) cur.seen.clone();
                        q.addLast(new Item(i, newSet));
                    }
                }
            }
            return ans;
        }

        private boolean isValid(Item cur, int dest) {
            if (cur.val == 5) return true;
            int a = cur.val, b = dest;
            if ((a == 1 && b == 9) || (a == 9 && b == 1) || (a == 3 && b == 7) || (a == 7 && b == 3) || (a == 2 && b == 8) || (a == 8 && b == 2) || (a == 4 && b == 6) || (a == 6 && b == 4))
                return checkSeen(cur, 5);
            // if ((a==1&&b==6)||(a==6&&b==1)||(a==3&&b==4)||(a==4&&b==3)) return checkSeen(cur, 2, 5);
            // if ((a==6&&b==7)||(a==7&&b==6)||(a==4&&b==9)||(a==9&&b==4)) return checkSeen(cur, 5, 8);
            // if ((a==1&&b==8)||(a==8&&b==1)||(a==2&&b==7)||(a==7&&b==2)) return checkSeen(cur, 4, 5);
            // if ((a==2&&b==9)||(a==9&&b==2)||(a==3&&b==8)||(a==8&&b==3)) return checkSeen(cur, 5, 6);
            if ((a == 1 && b == 3) || (a == 3 && b == 1))
                return checkSeen(cur, 2);
            if ((a == 1 && b == 7) || (a == 7 && b == 1))
                return checkSeen(cur, 4);
            if ((a == 3 && b == 9) || (a == 9 && b == 3))
                return checkSeen(cur, 6);
            if ((a == 9 && b == 7) || (a == 7 && b == 9))
                return checkSeen(cur, 8);
            return true;
        }

        boolean checkSeen(Item cur, int... inp) {
            boolean ans = true;
            for (int i : inp) ans &= cur.seen.get(i);
            return ans;
        }

        class Item {
            int val;
            BitSet seen;

            Item(int v) {
                val = v;
                seen = new BitSet();
                seen.set(v);
            }

            Item(int v, BitSet s) {
                val = v;
                seen = s;
                seen.set(v);
            }
        }
    }

    /**
     * The main idea is to use backtracking, if we can reach some number - we
     * count it. To reach the number we do necessary checks.
     * DFS. 1,3,5,7 and 2,4,6,8 are symmetric so we count them once.
     */
    public static class Solution {
        public int numberOfPatterns(int m, int n) {
            int[][] c = new int[10][10];
            pre(c);
            int ans = 0;
            ans += dfs(1, 1 << 1, 1, m, n, c) * 4;
            ans += dfs(2, 1 << 2, 1, m, n, c) * 4;
            ans += dfs(5, 1 << 5, 1, m, n, c);
            return ans;
        }

        void pre(int[][] c) {
            c[1][9] = c[3][7] = c[2][8] = c[4][6] = 5;
            c[9][1] = c[7][3] = c[8][2] = c[6][4] = 5;
            c[1][3] = c[3][1] = 2;
            c[1][7] = c[7][1] = 4;
            c[9][3] = c[3][9] = 6;
            c[9][7] = c[7][9] = 8;
        }

        int dfs(int j, int s, int ind, int m, int n, int[][] c) {
            if (ind > n) return 0;
            int ans = ind >= m ? 1 : 0;
            for (int i = 1; i <= 9; i++) {
                if (i == j) continue;
                if (check(s, i)) continue;
                if (!isValid(j, s, i, c)) continue;
                ans += dfs(i, s | (1 << i), ind + 1, m, n, c);
            }
            return ans;
        }

        boolean isValid(int j, int s, int i, int[][] c) {
            return c[j][i] == 0 || check(s, c[j][i]);
        }

        boolean check(int s, int i) {
            return ((s >> i) & 1) == 1;
        }
    }
}