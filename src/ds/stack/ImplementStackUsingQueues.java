package ds.stack;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 225
 * You must use only standard operations of a queue -- which means only push to back, peek/pop from front, size, and is empty operations are valid.
 */
public class ImplementStackUsingQueues {



    // O(n), O(1)
    class MyStack {
        Queue<Integer> stack;

        /** Initialize your data structure here. */
        public MyStack() {
            stack = new LinkedList<>();
        }

        /** Push element x onto stack. */
        public void push(int x) {
            stack.add(x);
            for(int i=0;i<stack.size()-1;i++){
                stack.add(stack.poll());
            }
        }

        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            return stack.poll();
        }

        /** Get the top element. */
        public int top() {
            return stack.peek();
        }

        /** Returns whether the stack is empty. */
        public boolean empty() {
            return stack.isEmpty();
        }
    }
}
