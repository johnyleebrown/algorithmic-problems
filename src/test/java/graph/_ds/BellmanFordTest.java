package graph._ds;

import org.junit.jupiter.api.Test;
import util.utils.reader.InputReader;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.utils.NumberUtils.formatDouble;
import static util.utils.Other.getReader;

class BellmanFordTest {
//    @Test
//    void test1() throws IOException {
//        InputReader r = new InputReader(getReader(this.getClass(), "tinyEWDn.txt"));
//
//        Map<Integer, Map<Integer, Double>> g = new HashMap<>();
//        int n = r.nextInt();
//        int edges = r.nextInt();
//        while (--edges >= 0) {
//            int v = r.nextInt(), w = r.nextInt();
//            double cost = r.nextDouble();
//            g.putIfAbsent(v, new HashMap<>());
//            g.get(v).put(w, cost);
//            g.putIfAbsent(w, new HashMap<>());
//        }
//
//        int s = 0;
//        BellmanFord bellmanFord = new BellmanFord(n, s, g);
//        double[] ans = new double[]{0.00, 0.93, 0.26, 0.99, 0.26, 0.61, 1.51, 0.60};
//        System.out.println(Arrays.toString(bellmanFord.getDistTo()));
//        for (int i = 0; i < n; i++) {
//            System.out.println(formatDouble(bellmanFord.getDistTo()[i], 2));
//            assertEquals(formatDouble(bellmanFord.getDistTo()[i], 2), String.valueOf(formatDouble(ans[i], 2)));
//        }
//    }
}