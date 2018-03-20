package Medium.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import Helpers.Helper;

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
     * Time complexity: O(E+V)
     * Space complexity: O(V)
     */
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        ArrayList<Integer>[] graph = new ArrayList[numCourses];
        for (int i = 0; i < graph.length; i++)
            graph[i] = new ArrayList<>();
        for (int[] edge : prerequisites)
            graph[edge[0]].add(edge[1]);

        int[] marked = new int[numCourses];
        for (int i = 0; i < graph.length; i++)
            if (hasCycle(graph, marked, i))
                return false;

        return true;
    }

    // 0 is unvisited
    // 1 is visiting
    // 2 is visited
    private static boolean hasCycle(ArrayList<Integer>[] graph, int[] marked, int v) {
        if (marked[v] == 1) return true;
        if (marked[v] == 2) return false;
        marked[v] = 1;

        for (int w : graph[v])
            if (hasCycle(graph, marked, w))
                return true;

        marked[v] = 2;
        return false;
    }

    public static void main(String[] args) {
        Map<String, Integer> map2Sum = new HashMap<>();
        if(map2Sum.put(",1",1) != null) System.out.println();
        System.out.println(-1 + "_" + 0 + "" + (3+5));
        String s = "[[0,1],[1,3],[2,3]]";
        System.out.println(canFinish(4, Helper.replaceBracets(s)));

        String s2 = "[[1,0],[0,1]]";
        System.out.println(canFinish(2, Helper.replaceBracets(s2)));

        String s3 = "[[1,0]]";
        System.out.println(canFinish(2, Helper.replaceBracets(s3)));
    }
}
