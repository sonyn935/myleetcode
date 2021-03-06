package com.hit.basmath.learn.queue_stack;

import com.hit.common.ListNode;

import java.util.Stack;

/**
 * 155. Min Stack
 * <p>
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 * <p>
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * getMin() -- Retrieve the minimum element in the stack.
 * <p>
 * Example:
 * <p>
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> Returns -3.
 * minStack.pop();
 * minStack.top();      --> Returns 0.
 * minStack.getMin();   --> Returns -2.
 */
public class _155 {
    class MinStack {
        int min = Integer.MAX_VALUE;
        Stack<Integer> stack = new Stack<Integer>();

        public void push(int x) {
            // only push the old minimum value when the current
            // minimum value changes after pushing the new value x
            if (x <= min) {
                stack.push(min);
                min = x;
            }
            stack.push(x);
        }

        public void pop() {
            // if pop operation could result in the changing of the current minimum value,
            // pop twice and change the current minimum value to the last minimum value.
            if (stack.pop() == min) min = stack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return min;
        }
    }

    class MinStack2 {
        private int minValue;
        private ListNode head = null;

        /**
         * initialize your data structure here.
         */
        public MinStack2() {
            head = new ListNode(-1);
        }

        public void push(int x) {
            ListNode value = new ListNode(x);
            ListNode temp = head.next;

            if (head.next == null) {
                minValue = x;
            } else {
                if (x < minValue) {
                    minValue = x;
                }
            }

            head.next = value;
            value.next = temp;
        }

        public void pop() {
            if (head.next != null) {
                int currValue = head.next.val;
                head.next = head.next.next;
                if (currValue == minValue) {
                    minValue = this.throughMin();
                }
            }
        }

        public int top() {
            if (head.next != null) {
                return head.next.val;
            }
            return -1;
        }

        public int getMin() {
            if (head.next != null) {
                return minValue;
            }
            return -1;
        }

        private int throughMin() {
            int min = -1;
            ListNode curr = head.next;
            if (curr != null) {
                min = curr.val;

                while (curr != null) {
                    if (curr.val < min) {
                        min = curr.val;
                    }
                    curr = curr.next;
                }
            }
            return min;
        }
    }
}
