package tree._ds.LowestCommonAncestor;

import org.junit.jupiter.api.Test;
import util.ds.TreeNode;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LowestCommonAncestorEulerTourTest {
    /**
     * @formatter:off
     *      0
     *  /      \
     * 1        2
     * |      /  \
     * 3     4    5
     * |
     * 6
     * @formatter:on
     */
    @Test
    void whenInputIsRegularTree_shouldReturnCorrectAnswer() {

        // create tree
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);
        root.left.left = new TreeNode(3);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(4);
        root.right.left.left = new TreeNode(6);
        root.right.right = new TreeNode(5);

        int n = 7; // num of nodes
        LowestCommonAncestorEulerTour lcaService = new LowestCommonAncestorEulerTour(root, n);

        assertEquals(2, lcaService.lca(4, 5));
    }
}