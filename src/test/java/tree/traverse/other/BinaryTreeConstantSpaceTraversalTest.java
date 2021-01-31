package tree.traverse.other;

import org.junit.jupiter.api.Test;
import commons.TreeNode;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BinaryTreeConstantSpaceTraversalTest {

	private void tester(TreeNode root, int expectedCount) {
		BinaryTreeConstantSpaceTraversal.Solution s = new BinaryTreeConstantSpaceTraversal.Solution();
		s.traverse(root);

		// check count
		assertEquals(expectedCount, s.countOfValues);

		// check values and counts
		assertEquals(TreeNode.collectValues(root), s.debugMap);
	}

	@Test
	void balancedTreeTest() {
		tester(createBalancedTree(), 7);
	}

	private TreeNode createBalancedTree() {
		TreeNode root = new TreeNode(1);
		// left
		root.left=new TreeNode(2);
		root.left.parent=root;
		// right
		root.right=new TreeNode(3);
		root.right.parent=root;
		// children of left
		root.left.left=new TreeNode(4);
		root.left.left.parent=root.left;
		root.left.right=new TreeNode(5);
		root.left.right.parent=root.left;
		// children of right
		root.right.left=new TreeNode(6);
		root.right.left.parent=root.right;
		root.right.right=new TreeNode(7);
		root.right.right.parent=root.right;
		return root;
	}

	@Test
	void unbalancedTreeTest1() {
		tester(createUnbalancedTree1(), 4);
	}

	private TreeNode createUnbalancedTree1() {
		TreeNode root = new TreeNode(1);
		// left
		root.left=new TreeNode(2);
		root.left.parent=root;
		// right
		root.right=new TreeNode(3);
		root.right.parent=root;
		// children of right
		root.right.right=new TreeNode(7);
		root.right.right.parent=root.right;
		return root;
	}

	@Test
	void unbalancedTreeTest2() {
		tester(createUnbalancedTree2(), 4);
	}

	private TreeNode createUnbalancedTree2() {
		TreeNode root = new TreeNode(1);
		// left
		root.left=new TreeNode(2);
		root.left.parent=root;
		// right
		root.right=new TreeNode(3);
		root.right.parent=root;
		// children of right
		root.right.left=new TreeNode(6);
		root.right.left.parent=root.right;
		return root;
	}

	@Test
	void unbalancedTreeTest3() {
		tester(createUnbalancedTree3(), 3);
	}

	private TreeNode createUnbalancedTree3() {
		TreeNode root = new TreeNode(1);
		// right
		root.right=new TreeNode(3);
		root.right.parent=root;
		// children of right
		root.right.right=new TreeNode(7);
		root.right.right.parent=root.right;
		return root;
	}

	@Test
	void unbalancedTreeTest4() {
		tester(createUnbalancedTree4(), 3);
	}

	private TreeNode createUnbalancedTree4() {
		TreeNode root = new TreeNode(1);
		// left
		root.left=new TreeNode(2);
		root.left.parent=root;
		// children of left
		root.left.left=new TreeNode(4);
		root.left.left.parent=root.left;
		return root;
	}

	@Test
	void unbalancedTreeTest5() {
		tester(createUnbalancedTree5(), 3);
	}

	private TreeNode createUnbalancedTree5() {
		TreeNode root = new TreeNode(1);
		// left
		root.left=new TreeNode(2);
		root.left.parent=root;
		// children of left
		root.left.right=new TreeNode(5);
		root.left.right.parent=root.left;
		return root;
	}

	@Test
	void unbalancedTreeTest6() {
		tester(createUnbalancedTree6(), 5);
	}

	private TreeNode createUnbalancedTree6() {
		TreeNode root = new TreeNode(1);
		// left
		root.left=new TreeNode(2);
		root.left.parent=root;
		// children of left
		root.left.left=new TreeNode(4);
		root.left.left.parent=root.left;
		root.left.right=new TreeNode(5);
		root.left.right.parent=root.left;
		// children of left right
		root.left.right.left=new TreeNode(6);
		root.left.right.left.parent=root.left.right;
		return root;
	}

	@Test
	void justRootTest() {
		tester(createJustRootTree(), 1);
	}

	private TreeNode createJustRootTree() {
		return new TreeNode(1);
	}
}