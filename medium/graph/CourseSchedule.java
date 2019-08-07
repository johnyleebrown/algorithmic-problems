package Medium.Graph;

import java.util.ArrayList;

/**
 * 207
 *
 * There are a total of n courses you have to take, labeled from 0 to n - 1.
 * Some courses may have prerequisites, for example to take course 0 you have
 * to first take course 1, which is expressed as a pair: [0,1] Given the total
 * number of courses and a list of prerequisite pairs, is it possible for you
 * to finish all courses?
 */
public class CourseSchedule {
    /**
     * Topological sort, if has cycle return false
     *
     * Mark in three colors:
     * 0 - unvisited (initial)
     * 1 - visiting (the vertex is on the current path)
     * 2 - visited
     *
     * Time complexity: O(E+V)
     * Space complexity: O(V)
     */
    public class Solution1 {
        boolean canFinish(int numCourses, int[][] prerequisites) {
            ArrayList<Integer>[] graph = new ArrayList[numCourses];

            for (int i = 0; i < numCourses; i++)    graph[i] = new ArrayList<>();
            for (int[] edge : prerequisites)        graph[edge[1]].add(edge[0]);

            int[] marked = new int[numCourses];

            for (int v = 0; v < graph.length; v++) {
                if (hasCycle(graph, marked, v)) return false;
            }

            return true;
        }

        boolean hasCycle(ArrayList<Integer>[] graph, int[] marked, int v) {
            if (marked[v] == 1) return true;
            marked[v] = 1;

            for (int w : graph[v]) {
                if (marked[v] != 2 && hasCycle(graph, marked, w)) return true;
            }

            marked[v] = 2;
            return false;
        }
    }

    public static void main(String[] args) {
        String s = "[[0,1],[1,3],[2,3]]";
        String s2 = "[[1,0],[0,1]]";
        String s3 = "[[1,0]]";
    }
}
