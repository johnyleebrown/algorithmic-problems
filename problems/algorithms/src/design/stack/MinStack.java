package design.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

/**
 * 155
 */
public class MinStack {
	/**
	 * SF.
	 */
	public static class Solution1 {
		private Deque<Integer> s;
		private Deque<Integer> minstack;

		public Solution1() {
			s = new ArrayDeque<>();
			minstack = new ArrayDeque<>();
		}

		public void push(int x) {
			s.push(x);

			if (minstack.isEmpty() || x <= minstack.peek()) {
				minstack.push(x);
			}
		}

		public void pop() {
			int x = s.pop();

			if (!minstack.isEmpty() && x == minstack.peek()) {
				minstack.pop();
			}
		}

		public int top() {
			if (s.isEmpty()) {
				throw new RuntimeException("Error. Stack is empty.");
			}
			return s.peek();
		}

		public int getMin() {
			if (minstack.isEmpty()) {
				throw new RuntimeException("Error. Stack is empty.");
			}
			return minstack.peek();
		}
	}

	/**
	 * Using list and pq. Adding same nodes as used for list, to the pq.
	 */
	public class Solution2 {
		// stack
		Node root;
		Node last;

		// min stack
		PriorityQueue<Node> pq;

		public Solution2() {
			pq = new PriorityQueue<>();
		}

		public void push(int val) {
			if (root == null) {
				root = new Node(val);
				pq.add(root);
				last = root;
			}
			else {
				Node x = new Node(val);
				last.next = x;
				x.prev = last;
				last = x;
				pq.add(x);
			}
		}

		public void pop() {
			if (last == null) {
				throw new RuntimeException("Error. Stack is empty.");
			}
			pq.remove(last);
			if (last == root) {
				last = null;
				root = null;
			}
			else {
				last = last.prev;
				last.next = null;
			}
		}

		public int top() {
			if (last == null) {
				throw new RuntimeException("Error. Stack is empty.");
			}
			return (int) last.val;
		}

		public int getMin() {
			if (root == null) {
				throw new RuntimeException("Error. Stack is empty.");
			}
			return (int) pq.peek().val;
		}

		private class Node implements Comparable {
			long val;
			Node prev, next;

			public Node(int val) {
				this.val = val;
			}

			public int compareTo(Object o) {
				return Long.compare(this.val, ((Node) o).val);
			}
		}
	}
}