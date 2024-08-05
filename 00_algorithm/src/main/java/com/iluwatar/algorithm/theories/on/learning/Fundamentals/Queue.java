package com.iluwatar.algorithm.theories.on.learning.Fundamentals;

import com.iluwatar.algorithm.theories.on.book.robert.kevin.system.StdOut;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<Item> implements Iterable<Item> {

    private Node<Item> first;
    private Node<Item> last;
    private int n;

    /**
     * create an empty queue
     */
    public Queue () {
        first = null;
        last = null;
        n = 0;
    }


    @Override
    public Iterator<Item> iterator() {
        return new LinkedIterator(first);
    }

    private class LinkedIterator implements Iterator<Item> {

        private Node<Item> current;

        public LinkedIterator(Node<Item> first) {
            current = first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if(!hasNext()) throw  new NoSuchElementException();

            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    //void enqueue(Item item) add an item
    public void enqueue(Item item) {
        Node<Item> old = last;

        last = new Node<>();
        last.item = item;
        last.next = null;

        if(isEmpty()) {
            first = last;
        }else {
            old.next = last;
        }

        n++;
    }

    //Item dequeue() remove the least recently added item
    public Item dequeue() {
        if(isEmpty()) throw new NoSuchElementException("Queue Underflow");

        Item item = first.item;
        first = first.next;
        n--;

        if(isEmpty()) last = null;

        return item;
    }

    //boolean isEmpty() is the queue empty?
    public boolean isEmpty() {
        return first == null;
    }

    //int size() number of items in the queue
    public int size() {
        return n;
    }

    public static void main(String[] args) {
        Queue<String> strings = new Queue<>();
        strings.enqueue("a");
        strings.enqueue("c");
        strings.enqueue("b");

        StdOut.println("Size : " + strings.size());

        strings.forEach(e -> {
            StdOut.println("Item : " + e);
        });

        StdOut.println("dequeue 1  : " + strings.dequeue());
        strings.forEach(e -> {
            StdOut.println("Item : " + e);
        });

        StdOut.println("dequeue 2  : " + strings.dequeue());
        strings.forEach(e -> {
            StdOut.println("Item : " + e);
        });

        StdOut.println("dequeue 3  : " + strings.dequeue());
        strings.forEach(e -> {
            StdOut.println("Item : " + e);
        });

    }
}
