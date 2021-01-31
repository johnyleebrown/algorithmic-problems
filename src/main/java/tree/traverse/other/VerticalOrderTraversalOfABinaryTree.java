package tree.traverse.other;

import commons.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 987
 */
class VerticalOrderTraversalOfABinaryTree {

	class Solution {
		public List<List<Integer>> verticalTraversal(TreeNode root) {
			if (root == null) {
				return new ArrayList<>();
			}

			List<Element> elements = new ArrayList<>();

			// obtain coordinates for each of the nodes
			obtainElementsCoordinates(root, 0, 0, elements);

			// sort as per problem statement
			sortElements(elements);

			// put nodes with same x coordinate in the same list
			return createVerticalLists(elements);
		}

		private List<List<Integer>> createVerticalLists(List<Element> elements) {
			List<List<Integer>> result = new ArrayList<>();

			int i = 0;
			while (i < elements.size()) {
				List<Integer> curList = new ArrayList<>();
				int currentVertical = elements.get(i).x;

				while (i < elements.size() && elements.get(i).x == currentVertical) {
					curList.add(elements.get(i).val);
					i++;
				}

				result.add(curList);
			}

			return result;
		}

		private void sortElements(List<Element> elements) {
			Collections.sort(elements, new Comparator<Element>() {
				public int compare(Element a, Element b) {
					if (a.x == b.x && a.y == b.y) {
						return a.val - b.val;
					}
					if (a.x == b.x) {
						// because we decrease y as we go deeper
						return b.y - a.y;
					}
					return a.x - b.x;
				}
			});
		}

		private void obtainElementsCoordinates(TreeNode root, int x, int y, List<Element> elements) {
			if (root == null) {
				return;
			}

			elements.add(new Element(x, y, root.val));

			obtainElementsCoordinates(root.left, x - 1, y - 1, elements);
			obtainElementsCoordinates(root.right, x + 1, y - 1, elements);
		}

		class Element {
			int x, y, val;

			public Element(int x, int y, int val) {
				this.x = x;
				this.y = y;
				this.val = val;
			}
		}
	}
}