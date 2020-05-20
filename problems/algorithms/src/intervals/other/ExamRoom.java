package intervals.other;

import java.util.TreeSet;

/**
 * 855
 */
public class ExamRoom {
    /**
     * Using treeset to get the difference between each student and find the
     * biggest.
     */
    class Solution1 {
        private int N;
        private TreeSet<Integer> studentPositions;

        public Solution1(int N) {
            this.N = N;
            studentPositions = new TreeSet();
        }

        public int seat() {
            int newPosition = 0;

            if (studentPositions.size() > 0) {
                int maximumDistance = studentPositions.first();
                Integer prevPosition = null;

                for (Integer position : studentPositions) {
                    if (prevPosition != null) {
                        // check for max
                        int diff = (position - prevPosition) / 2;
                        if (diff > maximumDistance) {
                            maximumDistance = diff;
                            newPosition = prevPosition + diff;
                        }
                    }

                    prevPosition = position;
                }

                // if the right most seat will have a bigger distance to anybody
                if (N - 1 - studentPositions.last() > maximumDistance) {
                    newPosition = N - 1;
                }
            }

            studentPositions.add(newPosition);

            return newPosition;
        }

        public void leave(int p) {
            studentPositions.remove(p);
        }
    }
}