package tree._ds.SegmentTree;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ImplicitSegmentTreeTest {

    SegmentTreeSlow rs;
    SegmentTree st;
    ImplicitSegmentTree ist;

    @Test
    void test_randomSmall_Increment_Min() {
        int n = 30;
        initTrees(n, AggregateFunction.MIN);

        initTest1(rs);
        initTest1(st);
        initTest1(ist);

        Random r = new Random();
        int step = n / 2;
        while (--step >= 0) {
            for (int i = 0; i < n; i++) {
                int a = r.nextInt(n);
                int b = r.nextInt(n);
                while (b < a) b = r.nextInt(n);
                int rs_min = rs.min(a, b), st_min = st.min(a, b), ist_min = ist.min(a, b);
                assertEquals(rs_min, st_min, ist_min);
            }
        }
    }

    void initTrees(int n, AggregateFunction f) {
        rs = new SegmentTreeSlow(n);
        st = new SegmentTree(n, f);
        ist = new ImplicitSegmentTree(n, f);
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

    @Test
    void test_randomSmall_Increment_Max() {
        int n = 30;
        initTrees(n, AggregateFunction.MAX);

        initTest1(rs);
        initTest1(st);
        initTest1(ist);

        Random r = new Random();
        int step = n / 2;
        while (--step >= 0) {
            for (int i = 0; i < n; i++) {
                int a = r.nextInt(n);
                int b = r.nextInt(n);
                while (b < a) b = r.nextInt(n);

                int rs_max = rs.max(a, b), st_max = st.max(a, b), ist_max = ist.max(a, b);
                assertEquals(rs_max, st_max, ist_max);
//                if (rs_max != 0) System.out.println(rs_max + "," + st_max + "," + ist_max);
            }
        }
    }

    @Test
    void test_randomLarge_Increment_Max() {
        int n = 150;
        initTrees(n, AggregateFunction.MAX);

        initTest2(rs);
        initTest2(st);
        initTest2(ist);

        Random r = new Random();
        int step = n / 2;
        while (--step >= 0) {
            for (int i = 0; i < n; i++) {
                int a = r.nextInt(n);
                int b = r.nextInt(n);
                while (b < a) b = r.nextInt(n);

                int rs_max = rs.max(a, b), st_max = st.max(a, b), ist_max = ist.max(a, b);
                assertEquals(rs_max, st_max, ist_max);
            }
        }
    }

    private static void initTest2(SegmentTreeQuery q) {
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
}