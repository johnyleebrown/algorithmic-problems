package Medium.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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
     * Time complexity: O()
     * Space complexity: O()
     */
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        boolean[] marked = new boolean[numCourses];
        ArrayList[] graph = new ArrayList[numCourses];
        Arrays.fill(graph, new ArrayList());
        for (int[] p : prerequisites) graph[p[1]].add(p[0]);

        for (int v = 0; v < graph.length; v++)
            if (!dfs(graph, marked, v))
                return false;

        return true;
    }

    private static boolean dfs(ArrayList[] g, boolean[] marked, int v) {
        if (marked[v]) return false;
        marked[v] = true;

        for (int w = 0; w < g[v].size(); w++)
            if (!dfs(g, marked, w))
                return false;

        marked[v] = false;
        return true;
    }

    public static void main(String[] args) {
        String s = "[[0,1],[1,3],[2,3]]";
        System.out.println(canFinish(4, Helper.replaceBracets(s)));

        String s2 = "[[1,0],[0,1]]";
        System.out.println(canFinish(2, Helper.replaceBracets(s2)));

        String s3 = "[[1,0]]";
        System.out.println(canFinish(2, Helper.replaceBracets(s3)));
    }
}
