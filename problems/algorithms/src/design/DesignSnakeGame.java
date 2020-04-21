package design;

import java.util.LinkedList;
import java.util.List;

/**
 * 353
 *
 * ======
 *
 * Task.
 *
 * Design a Snake game that is played on a device with screen size = width x
 * height. Play the game online if you are not familiar with the game.
 *
 * The snake is initially positioned at the top left corner (0,0) with length =
 * 1 unit.
 *
 * You are given a list of food's positions in row-column order. When a snake
 * eats the food, its length and the game's score both increase by 1.
 *
 * Each food appears one by one on the screen. For example, the second food will
 * not appear until the first food was eaten by the snake.
 *
 * When a food does appear on the screen, it is guaranteed that it will not
 * appear on a block occupied by the snake.
 *
 * ======
 *
 * Source: Leetcode
 */
public class DesignSnakeGame {
    public static class Solution {
        public static class SnakeGame {
            int n, m;
            int[][] food;
            int foodInd;
            List<Cell> s; // snake
            boolean[][] seen;

            public SnakeGame(int m, int n, int[][] food) {
                this.n = n;
                this.m = m;
                this.food = food;
                foodInd = 0;
                s = new LinkedList<>();
                s.add(new Cell(0, 0));
                seen = new boolean[this.n][this.m];
                seen[0][0] = true;
            }

            public int move(String d) {
                Cell x = getNewCell(d, s.get(0));
                if (isNotOnGrid(x) || isPartOfSnake(x)) return -1;
                if (isFoodFound(x)) {
                    seen[x.getI()][x.getJ()] = true;
                } else {
                    Cell last = s.get(s.size() - 1);
                    if (!(last.getI() == x.getI() && last.getJ() == x.getJ())) {
                        seen[x.getI()][x.getJ()] = true;
                        seen[last.getI()][last.getJ()] = false;
                    }
                    s.remove(s.size() - 1);
                }
                s.add(0, x);
                return foodInd;
            }

            private boolean isPartOfSnake(Cell x) {
                return seen[x.getI()][x.getJ()] && !(x.getI() == s.get(s.size() - 1).getI() && x.getJ() == s.get(s.size() - 1).j);
            }

            private boolean isNotOnGrid(Cell x) {
                return x.getI() >= n || x.getJ() >= m || x.getI() < 0 || x.getJ() < 0;
            }

            private Cell getNewCell(String d, Cell cur) {
                Cell x = new Cell(cur);
                if ("L".equals(d)) x.setJ(x.getJ() - 1);
                else if ("R".equals(d)) x.setJ(x.getJ() + 1);
                else if ("U".equals(d)) x.setI(x.getI() - 1);
                else x.setI(x.getI() + 1);
                return x;
            }

            private boolean isFoodFound(Cell newCell) {
                if (foodInd == food.length) return false;
                if (newCell.i == food[foodInd][0] && newCell.j == food[foodInd][1]) {
                    foodInd++;
                    return true;
                }
                return false;
            }

            private class Cell {
                int i, j;

                private Cell(int i, int j) {
                    this.i = i;
                    this.j = j;
                }

                private Cell(Cell c) {
                    this.i = c.i;
                    this.j = c.j;
                }

                private void setI(int i) {
                    this.i = i;
                }

                private void setJ(int j) {
                    this.j = j;
                }

                private int getI() {
                    return i;
                }

                private int getJ() {
                    return j;
                }
            }
        }
    }
}