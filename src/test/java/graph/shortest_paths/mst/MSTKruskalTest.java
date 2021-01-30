package graph.shortest_paths.mst;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MSTKruskalTest {
//	@Test
//	void name() {
//		List<List<Integer>> l = new ArrayList<>();
//		try {
//			BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream("test.txt")));
//			String cur = r.readLine();
//			while (cur != null) {
//				List<Integer> ll = Arrays.stream(cur.split(","))
//				                   .map(Integer::parseInt)
//				                   .collect(java.util.stream.Collectors.toList());
//				l.add(ll);
//				cur = r.readLine();
//			}
//		} catch (IOException e) {
//		}
//		MSTKruskal mst = new MSTKruskal(l);
//		assertEquals(181, mst.getMSTWeight());
//		for (int[] e : mst.getMSTEdges()) {
//			System.out.println(e[0] + "," + e[1]);
//		}
//	}
}