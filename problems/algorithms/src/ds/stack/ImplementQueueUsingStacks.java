package ds.stack;

import java.util.Stack;

/**
 * 232
 */
public class ImplementQueueUsingStacks
{
	class MyQueue
	{
		Stack<Integer> stack;

		/**
		 * Initialize your data structure here.
		 */
		public MyQueue()
		{
			stack = new Stack<Integer>();
		}

		/**
		 * Push element x to the back of queue.
		 */
		public void push(int x)
		{
			Stack<Integer> t = new Stack<Integer>();
			while (!stack.isEmpty())
			{
				t.push(stack.pop());
			}
			stack.push(x);
			while (!t.isEmpty())
			{
				stack.push(t.pop());
			}
		}

		/**
		 * Removes the element from in front of queue and returns that element.
		 */
		public int pop()
		{
			return stack.pop();
		}

		/**
		 * Get the front element.
		 */
		public int peek()
		{
			return stack.peek();
		}

		/**
		 * Returns whether the queue is empty.
		 */
		public boolean empty()
		{
			return stack.isEmpty();
		}
	}

	class MyQueue2
	{

		Stack<Integer> input = new Stack();
		Stack<Integer> output = new Stack();

		public void push(int x)
		{
			input.push(x);
		}

		public void pop()
		{
			peek();
			output.pop();
		}

		public int peek()
		{
			if (output.empty())
				while (!input.empty())
					output.push(input.pop());
			return output.peek();
		}

		public boolean empty()
		{
			return input.empty() && output.empty();
		}
	}
}
