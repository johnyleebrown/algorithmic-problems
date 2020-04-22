package tree._ds.SegmentTree.test;

import tree._ds.SegmentTree.ImplicitSegmentTree;
import tree._ds.SegmentTree.SegmentTreeQuery;
import tree._ds.SegmentTree.SegmentTreeSlow;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static util.tester.Assert.assertEquals;

public class ImplicitSegmentTreeTest {
    public static void main(String[] a) {
        int n = 50;

        SegmentTreeSlow rs = new SegmentTreeSlow(n);
        initTest(rs);
        //rs.print();

        ImplicitSegmentTree st = new ImplicitSegmentTree(n);
        st.increment(10, 20, 10);
        st.print();
        //st.print();
//        initTest(st);

        //runTest(3, 49, rs, st);
        //runTest(16, 41, rs, st);

//        runSmallTest(n, rs, st);
    }

    private static void runSmallTest(int n, SegmentTreeQuery stSlow, SegmentTreeQuery stRegular) {
        Random r = new Random();
        String testType = "Random choices test.";
        String step = "step";
        int st = n / 2;
        System.out.println(testType);
        while (--st >= 0) {
            System.out.println(step + " " + (n / 2 - st));
            List<String> ret = new LinkedList<>();
            boolean allOk = true;
            for (int i = 0; i < n; i++) {
                int a = r.nextInt(n);
                int b = r.nextInt(n);
                while (b < a) b = r.nextInt(n);
                ret.add("> [" + a + " " + b + "]");

                int minSlow = stSlow.min(a, b);
                int minRegular = stRegular.min(a, b);
                boolean ans = assertEquals(minSlow, minRegular, false);
                if (ans) {
                    ret.add("[++++] " + minSlow + " == " + minRegular);
                } else {
                    ret.add("[FAIL] " + minSlow + " != " + minRegular);
                }
                if (!ans) {
                    allOk = false;
                }
            }
            if (allOk) {
                System.out.println("[ == ACCEPTED == ]");
            } else {
                for (String s : ret) {
                    System.out.println(s);
                }
            }
        }
    }

    private static void runTest(int a, int b, SegmentTreeQuery stSlow, SegmentTreeQuery stRegular) {
        //		System.out.println("a,b: [" + a + " " + b + "]");
        int minSlow = stSlow.min(a, b);
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
}
