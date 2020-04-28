package graph._ds;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.utility.ArrayUtils.parseString2dAr;

class DijkstraTest {
    @Test
    void test1() {
        Map<Integer, Map<Integer, Integer>> g = getGraph("[[0,1,100],[1,2,100],[0,2,500]]");
        assertEquals(Dijkstra.find(0, 2, g, 3), 200);
    }

    @Test
    void test2() {
        Map<Integer, Map<Integer, Integer>> g = getGraph("[[0,1,5],[1,2,5],[0,3,2],[3,1,2],[1,4,1],[4,2,1]]");
        assertEquals(Dijkstra.find(0, 2, g, 5), 6);
    }

    @Test
    void test3() {
        Map<Integer, Map<Integer, Integer>> g = getGraph("[[0,3,7],[4,5,3],[6,4,8],[2,0,10],[6,5,6],[1,2,2],[2,5,9],[2,6,8],[3,6,3],[4,0,10],[4,6,8],[5,2,6],[1,4,3],[4,1,6],[0,5,10],[3,1,5],[4,3,1],[5,4,10],[0,1,6]]");
        assertEquals(Dijkstra.find(2, 4, g, 7), 16);
    }

    @Test
    void test4() {
        Map<Integer, Map<Integer, Integer>> g = getGraph("[[4,1,1],[1,2,3],[0,3,2],[0,4,10],[3,1,1],[1,4,3]]");
        assertEquals(Dijkstra.find(2, 1, g, 5), -1);
    }

    @Test
    void test5() {
        Map<Integer, Map<Integer, Integer>> g = getGraph("[[0,1,1],[0,2,5],[1,2,1],[2,3,1]]");
        assertEquals(Dijkstra.find(0, 3, g, 4), 3);
    }

    private Map<Integer, Map<Integer, Integer>> getGraph(String s) {
        Map<Integer, Map<Integer, Integer>> g = new HashMap<>();
        int[][] edges = parseString2dAr(s);
        for (int[] e : edges) {
            g.putIfAbsent(e[0], new HashMap<>());
            g.get(e[0]).put(e[1], e[2]);
            g.putIfAbsent(e[1], new HashMap<>());
        }
        return g;
    }
}