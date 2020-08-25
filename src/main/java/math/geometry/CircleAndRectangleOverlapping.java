package math.geometry;

/**
 * 1401
 *
 * ======
 *
 * Task.
 *
 * Given a circle represented as (radius, x_center, y_center) and an
 * axis-aligned rectangle represented as (x1, y1, x2, y2), where (x1, y1) are
 * the coordinates of the bottom-left corner, and (x2, y2) are the coordinates
 * of the top-right corner of the rectangle.
 *
 * Return True if the circle and rectangle are overlapped otherwise return
 * False.
 *
 * In other words, check if there are any point (xi, yi) such that belongs to
 * the circle and the rectangle at the same time.
 *
 * ======
 *
 * Source: Leetcode
 */
public class CircleAndRectangleOverlapping {
    /**
     * SF.
     */
    public static class Solution {
        public boolean checkOverlap(int radius, int x_center, int y_center, int x1, int y1, int x2, int y2) {
            return intersects(new Circle(x_center, y_center, radius), new Rectangle(x1, y1, x2, y2));
        }

        boolean intersects(Circle circle, Rectangle rect) {
            double xAxisCentersDiff = Math.abs(circle.x - rect.x);
            double yAxisCentersDiff = Math.abs(circle.y - rect.y);

            // outside
            if (xAxisCentersDiff > (rect.width / 2 + circle.radius) || yAxisCentersDiff > (rect.height / 2 + circle.radius)) {
                return false;
            }

            // intersect
            if (xAxisCentersDiff <= (rect.width / 2) || yAxisCentersDiff <= (rect.height / 2)) {
                return true;
            }

            // circle may intersect the corner of the rectangle
            double cornerDistance = Math.pow((xAxisCentersDiff - rect.width / 2), 2) + Math.pow((yAxisCentersDiff - rect.height / 2), 2);
            return cornerDistance <= Math.pow(circle.radius, 2);
        }

        static class Circle {
            double x, y; // center
            double radius;

            public Circle(int x, int y, int radius) {
                this.x = x;
                this.y = y;
                this.radius = radius;
            }
        }

        static class Rectangle {
            double x, y; // center
            double width, height;

            public Rectangle(int x1, int y1, int x2, int y2) {
                this.width = Math.abs(x1 - x2);
                this.height = Math.abs(y1 - y2);
                this.x = Math.min(x1, x2) + width / 2;
                this.y = Math.min(y1, y2) + height / 2;
            }
        }
    }
}