package com.example.finalxcmeettracker;

/**
 * Last in, first out data structure that supports O(1) insertion, removal, and size operations
 * @param <T> The type of data stored in the Stack
 */
public class Stack<T> {
    private Node<T> head;
    private int size;

    /**
     * Creates a new blank Stack
     */
    public Stack() {
        this.size = 0;
        this.head = null;
    }

    /**
     * Removes and returns the object on the top of the stack
     * @return T the object at the top of the stack
     */
    public T pop() {
        T rtn = head.val;
        head = head.next;
        size--;
        return rtn;
    }

    /**
     * Adds a new nonnull value to the Stack
     * @param value T the value to be added to the Stack
     * @return true if the item was successfully added to the Stack, false otherwise
     */
    public boolean push(T value) {
        if (value == null) {
            return false;
        }
        this.head = new Node<T>(value, head);
        this.size++;
        return true;
    }

    /**
     * Determines if there are no items currently in the Stack
     * @return true if the Stack is empty, i.e. 0 items
     */
    public boolean isEmpty() {
        return size == 0;
    }

    private class Node<T> {
        T val;
        Node<T> next;
        public Node(T value, Node<T> next) {
            this.val = value;
            this.next = next;
        }
    }
}
