package com.iluwatar.algorithm.theories.on.learning.Fundamentals;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class Stack <Item> implements Iterable<Item>{

    private Node<Item> first;
    private int n;

    public Stack () {
        first = null;
        n = 0;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size () {
        return n;
    }

    public void push (Item item) {
        Node <Item> old = first;
        first = new Node<>();
        first.item = item;
        first.next = old;
        n++;
    }

    public Item pop () {
        if(isEmpty())  throw new NoSuchElementException("Stack underflow");

        Item item = first.item;
        first = first.next;
        n--;
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new LinkedIterator(first);
    }

    private class LinkedIterator implements Iterator<Item>  {

        private Node<Item> current ;

        public LinkedIterator(Node<Item> first) {
            current = first;
        }

        @Override
        public boolean hasNext() {
            return Objects.nonNull(current);
        }

        @Override
        public Item next() {
            if(!hasNext()) throw new NoSuchElementException();

            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
