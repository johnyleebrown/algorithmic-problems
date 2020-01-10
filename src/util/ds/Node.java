package util;

import java.util.List;

public class Node
{
	public int val;
	public List<Node> children;
	public Node() {}
	
	public Node(final int _val, final List<Node> _children)
	{
		val = _val;
		children = _children;
	}
}
