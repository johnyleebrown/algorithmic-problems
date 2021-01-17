package tree._ds.SegmentTree;

import org.junit.jupiter.api.Test;
import util.utils.StringUtils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ImplicitSegmentTreeTest {

    SegmentTreeSlow segmentTreeSlow;
    SegmentTree segmentTree;
    ImplicitSegmentTree implicitSegmentTree;

    void initTrees(int n, AggregateFunction f) {
        segmentTreeSlow = new SegmentTreeSlow(n);
        segmentTree = new SegmentTree(n, f);
        implicitSegmentTree = new ImplicitSegmentTree(n, f);
    }

    /**************************************************************************/

    @Test
    void test_randomSmall_Increment_Min() {
        int maxNumber = 30;
        int countTests = 1_000;

        initTrees(maxNumber, AggregateFunction.MIN);
        initTest1(segmentTreeSlow);
        initTest1(segmentTree);
        initTest1(implicitSegmentTree);
        List<SegmentTreeQuery> l = new LinkedList<>(Arrays.asList(segmentTreeSlow, segmentTree, implicitSegmentTree));

        Random r = new Random();
        int[] result = new int[3];
        for (int i = 0; i < countTests; i++) {
            int[] interval = getInterval(r, maxNumber);
            for (int j = 0; j < l.size(); j++) {
                result[j] = l.get(j).min(interval[0], interval[1]);
            }
            assertEquals(result[0], result[1]);
            assertEquals(result[0], result[2]);
        }
    }

    void initTest1(SegmentTreeQuery q) {
        q.increment(0, 0, 15);
        q.increment(1, 1, 3);
        q.increment(2, 2, 4);
        q.increment(3, 3, 2);
        q.increment(4, 4, 1);
        q.increment(5, 5, 6);
        q.increment(6, 6, -1);

        q.increment(0, 4, 3);
        q.increment(1, 3, -4);
        q.increment(5, 6, 10);
        q.increment(0, 6, 0);
    }

    /**************************************************************************/

    @Test
    void test_randomSmall_Increment_Max() {
        int n = 30;
        initTrees(n, AggregateFunction.MAX);

        initTest1(segmentTreeSlow);
        initTest1(segmentTree);
        initTest1(implicitSegmentTree);

        Random r = new Random();
        int step = n / 2;
        while (--step >= 0) {
            for (int i = 0; i < n; i++) {
                int a = r.nextInt(n);
                int b = r.nextInt(n);
                while (b < a) b = r.nextInt(n);

                int rs_max = segmentTreeSlow.max(a, b), st_max = segmentTree.max(a, b), ist_max = implicitSegmentTree.max(a, b);
                assertEquals(rs_max, st_max);
                assertEquals(rs_max, ist_max);
            }
        }
    }

    /**************************************************************************/

    /**
     * todo
     * make nicer like others
     */
    @Test
    void test_randomLarge_Increment_Max() {
        int n = 150;
        initTrees(n, AggregateFunction.MAX);

        initTest2(segmentTreeSlow);
        initTest2(segmentTree);
        initTest2(implicitSegmentTree);

        Random r = new Random();
        int step = n / 2;
        while (--step >= 0) {
            for (int i = 0; i < n; i++) {
                int a = r.nextInt(n);
                int b = r.nextInt(n);
                while (b < a) b = r.nextInt(n);

                int rs_max = segmentTreeSlow.max(a, b), st_max = segmentTree.max(a, b), ist_max = implicitSegmentTree.max(a, b);
                assertEquals(rs_max, st_max);
                assertEquals(rs_max, ist_max);
            }
        }
    }

    private static void initTest2(SegmentTreeQuery q) {
        String inputString = "[97,100],[51,65],[27,46],[90,100],[20,32],[15,28],[60,73],[77,91],[67,85],[58,72],[74,93],[73,83],[71,87],[97,100],[14,31],[26,37],[66,76],[52,67],[24,43],[6,23],[94,100],[33,44],[30,46],[6,20],[71,87],[49,59],[38,55],[4,17],[46,61],[13,31],[94,100],[47,65],[9,25],[4,20],[2,17],[28,42],[26,38],[72,83],[43,61],[18,35]";
        int[][] input = StringUtils.stringToAr(inputString);
        for (int[] i : input) {
            q.increment(i[0], i[1], 1);
        }
    }

    /**************************************************************************/

    @Test
    void test_Increment_Sum() {
        int countIntervals = 1_000;
        int maxIncrement = 100;
        int maxNumber = 1_000;
        int countTests = 1_000;
        initTrees(maxNumber, AggregateFunction.SUM);
        List<SegmentTreeQuery> l = new LinkedList<>(Arrays.asList(segmentTreeSlow, segmentTree, implicitSegmentTree));
        fillData(countIntervals, maxIncrement, maxNumber, l);
        test_Increment_Sum(countTests, maxNumber, l);
    }

    private void test_Increment_Sum(int countTests, int maxNumber, List<SegmentTreeQuery> trees) {
        int[][] results = new int[countTests][3]; //3 types of rmq
        Random r = new Random();
        for (int i = 0; i < countTests; i++) {
            int[] interval = getInterval(r, maxNumber);
            for (int j = 0; j < trees.size(); j++) {
                results[i][j] = trees.get(j).sum(interval[0], interval[1]);
            }
            assertEquals(results[i][1], results[i][0]);
            assertEquals(results[i][2], results[i][0]);
        }
    }

    private void fillData(int countIntervals, int maxIncrement, int maxNumber, List<SegmentTreeQuery> l) {
        int[][] randomIncrements = genRandomIntervalIncrements(countIntervals, maxIncrement, maxNumber);
        for (SegmentTreeQuery q : l) {
            for (int[] i : randomIncrements) {
                // i = lo,hi,val
                for (int j = i[0]; j <= i[1]; j++) {
                    q.increment(j, j, i[2]);
                }
            }
        }
    }

    private int[][] genRandomIntervalIncrements(int countIntervals, int maxIncrement, int maxNumber) {
        int[][] res = new int[countIntervals][3]; //lo,hi,val
        Random r = new Random();
        for (int i = 0; i < countIntervals; i++) {
            int[] interval = getInterval(r, maxNumber);
            res[i] = new int[]{interval[0], interval[1], r.nextInt(maxIncrement)};
        }
        return res;
    }

    /**************************************************************************/

    private int[] getInterval(Random r, int n) {
        int lo = r.nextInt(n);
        int hi = r.nextInt(n);
        while (hi < lo) hi = r.nextInt(n);
        return new int[]{lo, hi};
    }
}