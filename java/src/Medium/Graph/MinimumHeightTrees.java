package Medium.Graph;

import static Helpers.Helper.replaceBracets;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * 310
 * For a undirected graph with tree characteristics, we can choose any node as the root.
 * The result graph is then a rooted tree. Among all possible rooted trees, those with
 * minimum height are called minimum height trees (MHTs). Given such a graph, write a
 * function to find all the MHTs and return a list of their root labels.
 * tree = graph - cycles
 */
public class MinimumHeightTrees {
    /**
     * Time complexity: O()
     * Space complexity: O()
     */
    public static List<Integer> solution1(int n, int[][] edges) {
        List<Integer> l = new LinkedList<>();
        if (edges.length == 0){
            if (n == 1) {
                l.add(0);
            } else if (n == 2){
                l.add(0);
                l.add(1);
            }
            return l;
        }
        if (n == 0) return null;
        if (n == 1) {
            l.add(0);
            return l;
        }
        if (n == 2) {
            l.add(edges[0][0]);
            l.add(edges[0][1]);
            return l;
        }

        int[] edgesCount = new int[n];
        int[] positions = new int[n];

        for (int i = 0; i < edges.length; i++) {
            positions[edges[i][0]] = positions[edges[i][1]] = i;
            edgesCount[edges[i][0]]++;
            edgesCount[edges[i][1]]++;
        }

        for (int i = 0; i < edgesCount.length; i++) {
            if (edgesCount[i] == 1 && positions[getSibling(positions[i], i, edges)] != -1) {
                int sib = getSibling(positions[i], i, edges);
                l.add(sib);
                positions[sib] = -1;
            }
        }

        return l;
    }

    private static int getSibling(int i, int a, int[][] edges) {
        if (edges[i][0] == a)   return edges[i][1];
        else                    return edges[i][0];
    }

    public static void main(String[] args) {
        String s = "[[0,1],[0,2],[0,3],[3,4],[4,5]]";
        System.out.println(Arrays.toString(solution1(6, replaceBracets(s)).toArray()));
    }
}
