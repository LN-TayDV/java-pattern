package com.iluwatar.algorithm.theories.on.self.learning.Fundamentals;

import com.iluwatar.algorithm.theories.on.book.robert.kevin.system.StdOut;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Bag<Item> implements Iterable<Item> {

    private Node<Item> first;    // beginning of bag
    private int n;               // number of elements in bag

    public Bag () {
        first = null;
        n = 0;
    }

    public boolean isEmpty () {
        return first == null;
    }

    public int size () {
        return n ;
    }

    public void add (Item item) {
        Node<Item> old = first;
        first = new Node<Item>();
        first.item = item;
        first.next = old;
        n++;
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
            if(!hasNext())
                throw new NoSuchElementException();

            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    @Override
    public void forEach(Consumer<? super Item> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<Item> spliterator() {
        return Iterable.super.spliterator();
    }

    public static void main(String[] args) {
        Bag<String> strings = new Bag<>();
        strings.add("a");
        strings.add("b");
        strings.add("c");

        StdOut.println("Size : " + strings.size());

        strings.forEach(e -> {
            StdOut.println("Item : " + e);
        });
    }
}
