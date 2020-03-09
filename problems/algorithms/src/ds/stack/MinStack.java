package ds.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 155
 */
public class MinStack
{
	private Deque<Integer> s;
	private Deque<Integer> minstack;

	public MinStack() 
	{
		s = new ArrayDeque<>();
		minstack = new ArrayDeque<>();
	}

	public void push(int x) 
	{
		s.push(x);
		
		if (minstack.isEmpty() || x <= minstack.peek())
		{
			minstack.push(x);
		}
	}

	public void pop() 
	{
		int x = s.pop();
		
		if (!minstack.isEmpty() && x == minstack.peek())
		{
			minstack.pop();
		}
	}

	public int top() 
	{
		return s.peek();
	}

	public int getMin() 
	{
		return minstack.peek();
	}
}