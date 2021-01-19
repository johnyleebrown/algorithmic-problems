package tree._ds.SegmentTree;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ImplicitSegmentTree2DTest {
	@Test
	void segmentTree2dIsBuiltCorrectly() {

		// generate matrix
//		Random r = new Random();
//		int n = 10;
//		int m = 5;
//		int[][] matrix = new int[n][m];
//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < m; j++) {
//				matrix[i][j] = r.nextInt(100);
//			}
//		}

		int[][] matrix = new int[][]{{1, 2, 3}, {4, 5, 6}};

		// create segment tree
		ImplicitSegmentTree2D sT2D = new ImplicitSegmentTree2D(AggregateFunction.SUM, matrix);

		// check that cur node sum = left child node sum + right child node sum
		check(sT2D.getRoot());
	}

	private void check(ImplicitSegmentTree2D.Node node) {
		if (node.left == null && node.right == null) {
			return;
		}
		checkInnerTreeSums(node, node.left, node.right, node.tree.getRoot());
		check(node.left);
		check(node.right);
	}

	private boolean checkInnerTreeSums(ImplicitSegmentTree2D.Node treeParent,
	                                   ImplicitSegmentTree2D.Node treeChildLeft,
	                                   ImplicitSegmentTree2D.Node treeChildRight,
	                                   ImplicitSegmentTree.Node node) {
		if (node == null) return true;
		if (node.left == null && node.right == null) return true;
		int lo = node.lo;
		int hi = node.hi;
		int mid = lo + (hi - lo) / 2;
		int sumParent = treeParent.tree.sum(lo, hi);
		int sumChildLeft = treeChildLeft == null ? 0 : treeChildLeft.tree.sum(lo, mid);
		int sumChildRight = treeChildRight == null ? 0 : treeChildRight.tree.sum(mid + 1, hi);
		return sumParent == sumChildLeft + sumChildRight
		       && checkInnerTreeSums(treeParent, treeChildLeft, treeChildRight, node.left)
		       && checkInnerTreeSums(treeParent, treeChildLeft, treeChildRight, node.right);
	}
}