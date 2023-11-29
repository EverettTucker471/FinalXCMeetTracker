package com.example.finalxcmeettracker;

import java.util.Arrays;
import java.util.Comparator;

/**
 * A data structure that stores semi-ordered data such that the minimum value is always at the top.
 * Supports O(logn) push and pop operations.
 * @param <T> The type of data you want to store in the heap, must extend comparable.
 * @author Everett Tucker
 * @version 11.1.2023
 */
public class Heap<T extends Comparable<T>> {
    private T[] arr;
    private int size;
    private final Comparator<T> comparator;
    private final int capacity;

    /**
     * Creates a new min heap with capacity n, uses T's compareTo function for sorting
     * @param n The starting capacity of the heap, this can expand. Must be positive.
     * @timeComplexity O(1)
     * @spaceComplexity O(n)
     */
    public Heap(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("How are you going to make a heap with negative elements? I actually want to know what kind of neurons are firing inside your brain to cause you to have such an illogical thought.");
        }
        arr = (T[]) new Comparable[n];
        size = 0;
        comparator = new NaturalOrdering<>();
        capacity = n;
    }

    /**
     * Creates a new min heap with starting capacity n and sorts according to the comparator comp
     * @param n The starting capacity of the heap, this can expand. Must be positive
     * @param comp The comparator used to order items in the heap.
     * @timeComplexity O(1)
     * @spaceComplexity O(n)
     */
    public Heap(int n, Comparator<T> comp) {
        if (n <= 0) {
            throw new IllegalArgumentException("How are you going to make a heap with negative elements? I actually want to know what kind of neurons are firing inside your brain to cause you to have such an illogical thought.");
        }
        arr = (T[]) new Comparable[n];
        size = 0;
        capacity = n;
        comparator = comp;
    }

    /**
     * Determines if the heap is empty
     * @return true if the heap is empty, i.e. has no elements
     * @timeComplexity O(1)
     * @spaceComplexity O(1)
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Adds an element to the end of the heap and heapifys up.
     * Compensates for heap size and expands the heap if necessary.
     * Heap expansion is never necessary in Huffman Encoding because the heap has a maximum size.
     * @param value The value that you want to insert into the heap
     * @timeComplexity O(logn) amortized
     * @spaceComplexity O(logn)
     */
    public void push(T value) {
        if (size >= capacity) {
            T[] temp = (T[]) new Comparable[capacity * 2];
            System.arraycopy(this.arr, 0, temp, 0, capacity);
            this.arr = temp;
        }
        arr[size] = value;
        heapifyUp(size);
        size++;
    }

    /**
     * Removes an item from the top of the heap and heapifys down
     * @return The element that was at the top of the heap
     * @timeComplexity O(logn)
     * @spaceComplexity O(logn)
     */
    public T pop() {
        if (size == 0) {
            throw new ArrayIndexOutOfBoundsException("Heap ain't got no stuff in it.");
        }
        T rtn = arr[0];
        size--;
        arr[0] = arr[size];
        arr[size] = null;
        heapifyDown(0);
        return rtn;
    }

    /**
     * Ensures that the heap is properly ordered at index, cascades up.
     * @param index The index that you want to sort the heap from.
     */
    private void heapifyUp(int index) {
        if (index != 0) {
            int parent = (index - 1) / 2;
            if (comparator.compare(arr[parent], arr[index]) < 0) {
                swap(parent, index);
                heapifyUp(parent);
            }
        }
    }

    /**
     * Ensures that the heap is properly ordered at index, cascades down.
     * @param index The index that you want to sort the heap from.
     */
    private void heapifyDown(int index) {
        int left = index * 2 + 1;
        int right = index * 2 + 2;

        if (left == size - 1 && comparator.compare(arr[left], arr[index]) > 0) {
            swap(left, index);
        } else if (right < size) {
            if (comparator.compare(arr[left], arr[index]) > 0) {
                if (comparator.compare(arr[left], arr[right]) >= 0) {
                    swap(left, index);
                    heapifyDown(left);
                } else {
                    swap(right, index);
                    heapifyDown(right);
                }
            } else if (comparator.compare(arr[right], arr[index]) > 0) {
                swap(right, index);
                heapifyDown(right);
            }
        }
    }

    /**
     * Returns the element at the top of the heap without modifying the heap
     * @return The element at the top of the heap
     * @timeComplexity O(1)
     * @spaceComplexity O(1)
     */
    public T peek() {
        return arr[0];
    }

    /**
     * Helper method to swap the elements at array positions i and j
     * @param i The element here goes to arr[j]
     * @param j The element here goes to arr[i]
     * @timeComplexity O(1)
     * @spaceComplexity O(1)
     */
    private void swap(int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Returns a string representation of the heap as an array
     * @return The heap as an array represented as a String
     * @timeComplexity O(n)
     * @spaceComplexity O(1)
     */
    public String toString() {
        return Arrays.toString(arr);
    }

    /**
     * This is the default comparator for the heap, working off the object's compareTo function.
     * @param <T> The type parameter stored in the heap.
     */
    private class NaturalOrdering<T extends Comparable<T>> implements Comparator<T> {
        @Override
        public int compare(T left, T right) {
            return left.compareTo(right);
        }
    }
}
