package tree._ds.SegmentTree.test;

import tree._ds.SegmentTree.*;

import java.util.Random;

import static util.tester.Assert.assertEquals;

public class SegmentTreeTest {
    public static void main(String[] a) {
        int n = 150;

        SegmentTreeSlow rs = new SegmentTreeSlow(n);
        test1(rs);
        System.out.println(rs.max(0, 100));

        SegmentTree st = new SegmentTree(n, AggregateFunction.MAX);
        test1(st);
        System.out.println(st.max(0, 100));

        ImplicitSegmentTree ist = new ImplicitSegmentTree(n, AggregateFunction.MAX);
        test1(ist);
        System.out.println(ist.max(0, 100));
    }

    private static void test1(SegmentTreeQuery q) {
        String x = "[97,100],[51,65],[27,46],[90,100],[20,32],[15,28],[60,73],[77,91],[67,85],[58,72],[74,93],[73,83],[71,87],[97,100],[14,31],[26,37],[66,76],[52,67],[24,43],[6,23],[94,100],[33,44],[30,46],[6,20],[71,87],[49,59],[38,55],[4,17],[46,61],[13,31],[94,100],[47,65],[9,25],[4,20],[2,17],[28,42],[26,38],[72,83],[43,61],[18,35]";
        String[] xa = x.split("[^0-9]");
        int i = 0;
        int[] ar = new int[2];
        for (String s : xa) {
            if (!s.trim().isEmpty()) {
                ar[i++] = Integer.parseInt(s);
                if (i == 2) {
                    q.increment(ar[0], ar[1], 1);
                    i = 0;
                }
            }
        }
    }

    private static void runSmallTest(int n, SegmentTreeQuery stSlow, SegmentTreeQuery stRegular) {
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            int a = r.nextInt(n);
            int b = r.nextInt(n);
            while (b < a) b = r.nextInt(n);
            int minSlow = stSlow.min(a, b);
            int minRegular = stRegular.min(a, b);
            assertEquals(minSlow, minRegular);
        }
    }

    private static void runTest(int a, int b, SegmentTreeQuery stSlow, SegmentTreeQuery stRegular) {
        System.out.println("a,b: [" + a + " " + b + "]");
        int minSlow = stSlow.min(a, b);
        System.out.println("=======");
        int minRegular = stRegular.min(a, b);
        assertEquals(minSlow, minRegular);
    }

    private static void initTest(SegmentTreeQuery q) {
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

    private void testDeltas(int n, SegmentTreeQuery stSlow, SegmentTreeQuery stRegular) {
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            int a = r.nextInt(n);
            int b = r.nextInt(n);
            while (b < a) b = r.nextInt(n);
            int minSlow = stSlow.min(a, b);
            int minRegular = stRegular.min(a, b);
            assertEquals(minSlow, minRegular);
        }
    }
}
