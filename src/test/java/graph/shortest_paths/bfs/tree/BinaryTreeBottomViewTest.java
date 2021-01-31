package graph.shortest_paths.bfs.tree;

import org.junit.jupiter.api.Test;
import commons.TreeNode;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class BinaryTreeBottomViewTest {
    @Test
    void test1() {
        BinaryTreeBottomView.Solution s = new BinaryTreeBottomView.Solution();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(6);
        assertTrue(areSameLists(Arrays.asList(4, 2, 7, 3, 6), s.solve(root)));
    }

    @Test
    void test2() {
        BinaryTreeBottomView.Solution s = new BinaryTreeBottomView.Solution();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);
        assertTrue(areSameLists(Arrays.asList(4, 2, 5, 3, 6), s.solve(root)));
    }

    @Test
    void testWhenThereIsOnlyLeftSubtree_shouldReturnTrue() {
        BinaryTreeBottomView.Solution s = new BinaryTreeBottomView.Solution();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        assertTrue(areSameLists(Arrays.asList(4, 2, 1), s.solve(root)));
    }

    @Test
    void testWhenRightSubtreeOvershadowsLeft_shouldReturnTrue() {
        BinaryTreeBottomView.Solution s = new BinaryTreeBottomView.Solution();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(6);
        root.right.left.left = new TreeNode(5);
        assertTrue(areSameLists(Arrays.asList(5, 7, 3, 6), s.solve(root)));
    }

    private boolean areSameLists(List<Integer> l1, List<Integer> l2) {
        if (l1.size() != l2.size()) return false;
        Iterator<Integer> it1 = l1.iterator(), it2 = l2.iterator();
        while (it1.hasNext()) {
            if (!it1.next().equals(it2.next())) {
                return false;
            }
        }
        return true;
    }
}