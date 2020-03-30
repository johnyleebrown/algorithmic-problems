package design.stack;

import java.util.HashMap;

/**
 * 716
 *
 * ======
 *
 * Task.
 *
 * Design a max stack that supports push, pop, top, peekMax and popMax.
 *
 * push(x) -- Push element x onto stack. pop() -- Remove the element on top of
 * the stack and return it. top() -- Get the element on the top. peekMax() --
 * Retrieve the maximum element in the stack. popMax() -- Retrieve the maximum
 * element in the stack, and remove it. If you find more than one maximum
 * elements, only remove the top-most one.
 *
 * ======
 *
 * Source: Leetcode
 */
public class MaxStack {
	/**
	 * $INSERT_EXPLANATION.
	 */
	public static class Solution {
		// use doubly linked list, map, custom pq
		// pq for max, ll for stack cuz we need to remove efficiently
		// map of some kind

		Map<> m = new HashMap<>();
		Node back;

		public Solution() {

		}

		public void push(int x) {

		}

		public int pop() {

		}

		public int top() {

		}

		public int peekMax() {

		}

		public int popMax() {

		}

		private class Node {
			Node prev, cur;
			int val;
		}

		private class PQ {
			private static final int INIT_CAP = 11;
			private int[] queue;
			private int n; // cur n

			/**
			 * starts from 1st index.
			 */
			public PQ() {
				queue = new int[INIT_CAP];
			}

			int poll() {

			}

			void push() {

			}

			/**
			 * Moving down.
			 *
			 * i - index of el.
			 */
			void sink(int i) {
				while (2*i <= n) {

				}
			}

			/**
			 * Moving up.
			 */
			void swim(int i) {

			}

			void exch(int i, int j) {
				int t = queue[i];
				queue[i] = queue[j];
				queue[j] = t;
			}
		}
	}
}