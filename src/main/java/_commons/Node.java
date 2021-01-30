package _commons;

import java.util.List;

public class Node {
    public int val;
    public List<Node> children;
    public List<Node> neighbors;
    public Node left, right, next;
    public Node random;

    public Node(int val, List<Node> children) {
        this.val = val;
        this.children = children;
    }

    public Node(int val) {
        this.val = val;
    }
}
