package unionFind.weighted;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class EvaluateDivisionTest {
    @Test
    void test1() {
        EvaluateDivision.Solution s = new EvaluateDivision.Solution();
        List<List<String>> e = new ArrayList<>();
        e.add(new LinkedList<>(Arrays.asList("a", "b")));
        e.add(new LinkedList<>(Arrays.asList("b", "c")));
        double[] vals = new double[]{2.0, 3.0};
        List<List<String>> queries = new ArrayList<>();
        queries.add(new LinkedList<>(Arrays.asList("a", "c")));
        queries.add(new LinkedList<>(Arrays.asList("b", "a")));
        assertArrayEquals(new double[]{6.0, 0.5}, s.calcEquation(e, vals, queries));
    }
}