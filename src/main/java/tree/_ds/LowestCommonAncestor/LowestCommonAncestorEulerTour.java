package tree._ds.LowestCommonAncestor;

import tree._ds.SegmentTree.AggregateFunction;
import tree._ds.SegmentTree.SegmentTree;
import tree._ds.SegmentTree.SegmentTreeQuery;
import _commons.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LowestCommonAncestorEulerTour
 *
 * It's just poor naming bc we are not going to use an Euler tour(a tour of
 * graph when u visit each node once), this is why the better naming is 'Tree
 * Traversal'.
 *
 * The idea is to get data from tree traversal such as depth of node and the
 * traversal itself.
 * Then use Sparse Table or Segment Tree to do Range Minimum Queries.
 *
 * @formatter:off
 *      0
 *  /      \
 * 1        2
 * |      /  \
 * 3     4    5
 * |
 * 6
 * @formatter:on
 * tour:  [0,1,3,1,0,2,4,6,4,2,5,2,0]
 * depth: [0,1,2,1,0,1,2,3,2,1,2,1,0]
 *
 * we will need to keep last[] array to keep the last occurrence of the value,
 * because we have duplicates
 *
 * Note:
 * - node keys should be unique, if they aren't we can give them unique ids
 *
 * TODO
 * right now the problem is that we return depth because in my impl of segment tree min function returns value and not a node so we can't return an index
 * fix that first then get back to this.
 */
public class LowestCommonAncestorEulerTour {

    private List<Integer> tour; // values of each node
    private List<Integer> depth; // depths of each node - indexed to tour list
    private Map<Integer, Integer> last; // last index of value in tour

    private TreeNode root;
    private SegmentTreeQuery st;

    public LowestCommonAncestorEulerTour(TreeNode root, int n) {

        this.root = root;

        // traverse tree to get necessary data
        precompute(root, n);

        // create segment tree
        st = new SegmentTree(tour.size() - 1, AggregateFunction.MIN);
        for (int i = 0; i < tour.size(); i++) {
            st.increment(i, i, depth.get(i));
        }
    }

    public int lca(int a, int b) {
        // query segment tree
        return st.min(last.get(a), last.get(b));
    }

    private void precompute(TreeNode root, int n) {
        tour = new ArrayList<>();
        depth = new ArrayList<>();
        last = new HashMap<>();

        dfs(root, 0);
    }

    void dfs(TreeNode root, int depth) {
        recordState(root.val, depth);

        // regular dfs, on the way out record again
        if (root.left != null) {
            dfs(root.left, depth + 1);
            recordState(root.val, depth);
        }
        if (root.right != null) {
            dfs(root.right, depth + 1);
            recordState(root.val, depth);
        }
    }

    private void recordState(int curVal, int curDepth) {
        tour.add(curVal);
        depth.add(curDepth);
        last.put(curVal, tour.size() - 1);
    }
}
